package org.testinium.step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testinium.utils.ConfigurationReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApiTestleri_Step_Defs {
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private String currentOperation;
    private int requestCount;
    private Response response;
    private final List<Response> responses = new ArrayList<>();
    private final List<Long> responseTimes = new ArrayList<>();
    private final Random random = new Random();
    private final String username = ConfigurationReader.getValidUsername();
    private final String password = ConfigurationReader.getValidPassword();
    public float number1, number2;
    public String stringNumber1, stringNumber2;

    @When("kullanıcı giriş isteğini gönderir")
    public void kullanıcı_giriş_isteğini_gönderir() {
        String baseUri = "https://catchylabs-api.testinium.com/api/v1/login";
        response = RestAssured.given()
                .header("Content-Type", "application/json;charset=utf-8")
                .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
                .when()
                .post(baseUri);
    }

    @Then("yanıt durum kodu {int} olmalıdır")
    public void yanıt_durum_kodu_200_olmalıdır(int status) {
        int statusCode = response.getStatusCode();
        assertEquals(status, statusCode);
    }

    @Then("yanıt bir access_token içermelidir")
    public void yanıt_bir_access_token_içermelidir() {
        String access_token = response.path("access_token");
        assertNotNull(access_token);
        scenarioContext.setContext("access_token", access_token);
    }

    @Then("yanıt bir refresh_token içermelidir")
    public void yanıt_bir_refresh_token_içermelidir() {
        String refreshToken = response.path("refresh_token");
        assertNotNull(refreshToken);
    }

    @Given("{string} değeri {float} ve {string} değeri {float}")
    public void değeriVeDeğeri(String arg0, float num_1, String arg2, float num_2) {
        this.number1 = num_1;
        this.number2 = num_2;
    }

    @When("{string} API'sine istek gönder")
    public void apıSineIstekGönder(String endpoint) {
//        String baseUri = "https://catchylabs-api.testinium.com/api/v1/calculators/multiplies";
        String baseUri = ConfigurationReader.getBaseUri() + ConfigurationReader.getPaths().getJSONObject(0).getString(endpoint);

        // Create request body with the numbers
        String requestBody = String.format("{\"number1\": %.0f, \"number2\": %.0f}", number1, number2);
        String access_token = (String) scenarioContext.getContext("access_token");
        response = RestAssured.given()
                .header("Authorization", "Bearer " + access_token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(baseUri);
    }

    @Then("Yanıt {string}: {float} olmalıdır")
    public void yanıtOlmalıdır(String field, float expectedResult) {
        float actualResult = response.path(field);
        assertEquals(expectedResult, actualResult, 0.0);
    }

    @And("hata mesajı {string} olmalıdır")
    public void hataMesajıOlmalıdır(String message) {
        String actualMessage = response.path("message");
        assertEquals(message, actualMessage);
    }


    @Given("{int} adet {string} işlemi yapılacak")
    public void adetOperationIşlemiYapılacak(int count, String operation) {
        this.requestCount = count;
        this.currentOperation = operation;
        System.out.println(count + " adet " + operation + " işlemi başlatılıyor...");
    }

    @When("Her işlem için random sayılar ile API'ye istek gönderilir")
    public void herIşlemIçinRandomSayılarIleAPIYeIstekGönderilir() {
        String access_token = (String) scenarioContext.getContext("access_token");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < requestCount; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                double number1 = generateRandomNumber();
                double number2 = generateRandomNumber();

                // Bölme işlemi için sıfır kontrolü
                if (currentOperation.equals("Calculator-Divide") && Math.abs(number2) < 0.000001) {
                    number2 = 1.0;
                }

                long startTime = System.currentTimeMillis();

                Response response = RestAssured.given()
                        .header("Authorization", "Bearer " + access_token)
                        .header("Content-Type", "application/json")
                        .body(String.format("{\"number1\": %.2f, \"number2\": %.2f}", number1, number2))
                        .when()
                        .post(ConfigurationReader.getBaseUri() + ConfigurationReader.getPaths().getJSONObject(0).getString(currentOperation));

                long endTime = System.currentTimeMillis();

                synchronized (this) {
                    responses.add(response);
                    responseTimes.add(endTime - startTime);
                }
            }, executor);

            futures.add(future);
        }

        // Tüm isteklerin tamamlanmasını bekle
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executor.shutdown();
    }

    @Then("Tüm yanıtlar {int} olmalıdır")
    public void tümYanıtlarOlmalıdır(int expectedStatus) {
        List<Response> failedResponses = responses.stream()
                .filter(response -> response.getStatusCode() != expectedStatus)
                .collect(Collectors.toList());

        if (!failedResponses.isEmpty()) {
            throw new AssertionError(String.format(
                    "%d adet başarısız istek bulundu. İlk hatalı yanıt kodu: %d",
                    failedResponses.size(),
                    failedResponses.get(0).getStatusCode()
            ));
        }
    }

    @And("Ortalama yanıt süresi {int}ms altında olmalıdır")
    public void ortalamaYanıtSüresiMsAltındaOlmalıdır(int maxAverageTime) {
        double averageTime = responseTimes.stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0.0);

        if (averageTime >= maxAverageTime) {
            throw new AssertionError(String.format(
                    "Ortalama yanıt süresi çok yüksek. Beklenen: < %dms, Gerçekleşen: %.2fms",
                    maxAverageTime,
                    averageTime
            ));
        }

        // İstatistikleri yazdır
        System.out.println("Performans Test Sonuçları:");
        System.out.println("Toplam İstek: " + requestCount);
        System.out.println("Ortalama Yanıt Süresi: " + String.format("%.2f ms", averageTime));
        System.out.println("Minimum Yanıt Süresi: " + responseTimes.stream().min(Long::compare).orElse(0L) + " ms");
        System.out.println("Maximum Yanıt Süresi: " + responseTimes.stream().max(Long::compare).orElse(0L) + " ms");
    }

    private double generateRandomNumber() {
        return random.nextDouble() * 2000 - 1000;
    }


    @Given("{string} değeri {string} ve {string} değeri {int} sayı olarak gönderilir")
    public void değeriVeDeğeriSayıOlarakGönderilir(String arg0, String num_1, String arg2, int num_2) {
        this.stringNumber1 = num_1;
        this.number2 = num_2;
    }

    @Given("{string} değeri {string} boş ve {string} değeri {int} sayı gönderilir")
    public void değeriBoşVeDeğeriSayıGönderilir(String arg0, String num_1, String arg2, int num_2) {
        this.stringNumber1 = num_1;
        this.number2 = num_2;
    }
}