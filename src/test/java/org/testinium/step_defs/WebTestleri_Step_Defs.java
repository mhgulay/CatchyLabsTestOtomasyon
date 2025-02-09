package org.testinium.step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testinium.pages.AccountPage;
import org.testinium.pages.LoginPage;
import org.testinium.utils.BrowserUtils;
import org.testinium.utils.ConfigurationReader;
import org.testinium.utils.Driver;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.testinium.utils.BrowserUtils.waitTwoSeconds;
import static org.testinium.utils.Driver.closeDriver;
import static org.testinium.utils.Driver.getDriver;

public class WebTestleri_Step_Defs {

    LoginPage loginPage = new LoginPage();
    AccountPage accountPage = new AccountPage();

    final static Logger logger = Logger.getLogger(WebTestleri_Step_Defs.class);
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @Given("Giriş sayfasına gidiyorum")
    public void giriş_sayfasına_gidiyorum() {
        logger.info("Test started");
        getDriver().get(ConfigurationReader.getBaseUrl());
//        waitTwoSeconds();
    }

    @Then("Giriş sayfasını görebilmeliyim")
    public void giriş_sayfasını_görebilmeliyim() {
        String expectedTitle = "login";
        waitTwoSeconds();
        String actualTitle = getDriver().getTitle();
        logger.info("User is on the " + actualTitle);
//        waitTwoSeconds();
        assertEquals(expectedTitle, actualTitle);
    }

    @When("Geçerli kullanıcı adı {string} giriyorum")
    public void geçerli_kullanıcı_adı_giriyorum(String username) {
        loginPage.username.sendKeys(username);
    }

    @When("Geçerli şifre {string} giriyorum")
    public void geçerli_şifre_giriyorum(String pasword) {
        loginPage.password.sendKeys(pasword);
    }

    @When("Giriş butonuna tıklıyorum")
    public void giriş_butonuna_tıklıyorum() {
        loginPage.loginButton.click();
    }

    @Then("Başarıyla giriş yapabilmeliyim")
    public void başarıyla_giriş_yapabilmeliyim() {
        String expectedTitle = "apps";
        waitTwoSeconds();
        String actualTitle = getDriver().getTitle();
        logger.info("User is on the " + actualTitle);
        waitTwoSeconds();
        assertEquals(expectedTitle, actualTitle);
    }


    @And("Geçersiz şifre {string} giriyorum")
    public void geçersizŞifreGiriyorum(String arg0) {
    }

    @Then("Giriş yapamamalıyım")
    public void girişYapamamalıyım() {
    }

    @When("Geçersiz kullanıcı adı {string} giriyorum")
    public void geçersizKullanıcıAdıGiriyorum(String arg0) {
    }

    @Given("Geçerli kimlik bilgilerimle giriş yaptım")
    public void geçerliKimlikBilgilerimleGirişYaptım() {
    }

    @When("{string} butonuna tıklıyorum ve {string} bölümüne gidiyorum")
    public void butonunaTıklıyorumVeBölümüneGidiyorum(String arg0, String arg1) {
    }

    @Then("Para Transferi sayfasına erişebilmeli ve gönderenin hesabında yeterli bakiye bulunmalı")
    public void paraTransferiSayfasınaErişebilmeliVeGöndereninHesabındaYeterliBakiyeBulunmalı() {
    }

    @When("{string} butonuna tıklıyorum")
    public void butonunaTıklıyorum(String arg0) {
    }

    @Then("Para Gönderme sayfasını görebilmeliyim")
    public void paraGöndermeSayfasınıGörebilmeliyim() {
    }

    @When("Gönderen hesabını {string} seçiyorum")
    public void gönderenHesabınıSeçiyorum(String arg0) {
    }

    @And("Alıcı hesabını {string} seçiyorum")
    public void alıcıHesabınıSeçiyorum(String arg0) {
    }

    @And("Transfer tutarını {string} giriyorum")
    public void transferTutarınıGiriyorum(String arg0) {
    }

    @Then("Transfer başarıyla tamamlanmalıdır")
    public void transferBaşarıylaTamamlanmalıdır() {
    }

    @And("{string} sayfasındaki İşlemler bölümünde {string} tutarını görebilmeliyim")
    public void sayfasındakiİşlemlerBölümündeTutarınıGörebilmeliyim(String arg0, String arg1) {
    }

    @And("Hesap bakiyesinin {string} azaldığını doğrulamalıyım")
    public void hesapBakiyesininAzaldığınıDoğrulamalıyım(String arg0) {
    }

    @Then("Miktarın geçersiz olduğunu belirten bir hata mesajı görmeliyim")
    public void miktarınGeçersizOlduğunuBelirtenBirHataMesajıGörmeliyim() {
    }

    @And("{string} sayfasındaki İşlemler bölümünde {string} tutarını görememeliyim")
    public void sayfasındakiİşlemlerBölümündeTutarınıGörememeliyim(String arg0, String arg1) {
    }

    @And("Hesap bakiyesinin değişmediğini doğrulamalıyım")
    public void hesapBakiyesininDeğişmediğiniDoğrulamalıyım() {
    }

    @Then("Para Transferi sayfasına erişebilmeliyim")
    public void paraTransferiSayfasınaErişebilmeliyim() {
    }

    @And("Hesap adını {string} giriyorum")
    public void hesapAdınıGiriyorum(String arg0) {
    }

    @Then("{string} sayfasında güncellenmiş adı görebilmeliyim")
    public void sayfasındaGüncellenmişAdıGörebilmeliyim(String arg0) {
    }

    @Then("Bir hata mesajı görmeliyim")
    public void birHataMesajıGörmeliyim() {
    }

    @And("Kart numarasını {string} giriyorum")
    public void kartNumarasınıGiriyorum(String arg0) {
    }

    @And("Kart sahibini {string} giriyorum")
    public void kartSahibiniGiriyorum(String arg0) {
    }

    @And("Son kullanma tarihini {string} giriyorum")
    public void sonKullanmaTarihiniGiriyorum(String arg0) {
    }

    @And("CVV {string} giriyorum")
    public void cvvGiriyorum(String arg0) {
    }

    @And("Tutar {string} giriyorum")
    public void tutarGiriyorum(String arg0) {
    }

    @Then("İşlem başarıyla tamamlanmalıdır")
    public void işlemBaşarıylaTamamlanmalıdır() {
    }

    @And("Hesap bakiyesinin {string} arttığını doğrulamalıyım")
    public void hesapBakiyesininArttığınıDoğrulamalıyım(String arg0) {
    }

    @Then("İşlem tamamlanmamalıdır")
    public void işlemTamamlanmamalıdır() {
    }

    @Then("Anasayfa görüntülenmelidir")
    public void anasayfaGörüntülenmelidir() {
        String expectedTitle = "apps";
        waitTwoSeconds();
        String actualTitle = getDriver().getTitle();
        logger.info("User is on the " + actualTitle);
        waitTwoSeconds();
        assertEquals(expectedTitle, actualTitle);
    }

    @Then("Bakiye kontrolü yapılır")
    public void bakiyeKontrolüYapılır() {

        WebElement element = accountPage.accountBalance;
        String accountBalanceText = element.getText().replaceAll(",", "");

        BigDecimal accountBalance = new BigDecimal(accountBalanceText);

        System.out.println("accountBalance = " + accountBalance);

        scenarioContext.setContext("accountBalance", accountBalance);

//        if (accountBalance <= 0){
//            System.out.println("accountBalance = " + accountBalance);
//            closeDriver();
//        }
    }


    @And("Amount alanına {string} değeri girilir")
    public void amountAlanınaDeğeriGirilir(String amount) {
        accountPage.input.sendKeys(amount);
    }

    @Then("{string} mesajı görüntülenir")
    public void mesajıGörüntülenir(String messageText) {
        WebElement element = Driver.getDriver().findElement(By.xpath("//*[text()='" + messageText + "']"));
        assertTrue(element.isDisplayed());
    }

    @And("Hesap Amount değerindeki azalmanın {string} olduğu kontrol edilir")
    public void hesapAmountDeğerindekiAzalmanınOlduğuKontrolEdilir(String value) {
        BigDecimal accountBalance = (BigDecimal) scenarioContext.getContext("firstAccountBalance");
        BigDecimal newAccountBalance = (BigDecimal) scenarioContext.getContext("newAccountBalance");

        BigDecimal actualAmount = accountBalance.subtract(newAccountBalance);

        BigDecimal expectedAmount = new BigDecimal(value);

        Assert.assertEquals(expectedAmount, actualAmount);
    }

    @Then("Transaction detayda {string} {string} kontrol edilir")
    public void transactionDetaydaKontrolEdilir(String detailName, String detail) {

        WebElement element = Driver.getDriver().findElement(By.xpath("((//*[text()='" + detailName + "']//..//div)[1])/following-sibling::div[1]"));

        String value = element.getText();
        System.out.println("value = " + value);

        if (detail.equals("işlem tarihi")) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String currentDate = LocalDate.now().format(formatter);

            String actualDate = value.split(" ")[0];

            assertEquals(currentDate, actualDate);
        } else {
            assertEquals(detail, value);
        }
    }

    @Then("Hesap bakiye değeri alınır")
    public void hesapBakiyeDeğeriAlınır() {

        WebElement element = accountPage.accountBalance;
        String accountBalanceText = element.getText().replaceAll(",", "");

        BigDecimal accountBalance = new BigDecimal(accountBalanceText);

        System.out.println("firstAccountBalance = " + accountBalance);

        scenarioContext.setContext("firstAccountBalance", accountBalance);
    }

    @And("Yeni bakiye değeri alınır")
    public void yeniBakiyeDeğeriAlınır() {
        WebElement element = accountPage.accountBalance;
        String accountBalanceText = element.getText().replaceAll(",", "");

        BigDecimal accountBalance = new BigDecimal(accountBalanceText);

        System.out.println("newAccountBalance = " + accountBalance);

        scenarioContext.setContext("newAccountBalance", accountBalance);
    }

    @And("Reviever account {string} seçilir")
    public void revieverAccountAccountNameSeçilir(String accountName) {
        WebElement selectElement = getDriver().findElement(By.id("your-select-id"));

        Select select = new Select(selectElement);
    }


    @Then("Transaction detayda {string} <accountName> kontrol edilir")
    public void transactionDetaydaAccountNameKontrolEdilir(String arg0) {

    }

    @And("{string} alıcı hesabı olarak seçilir")
    public void accountnameAlıcıHesabıOlarakSeçilir(String accountName) {
        // accountName parametresini kullanarak, doğru hesabı seçme işlemini gerçekleştir
        System.out.println("Alıcı hesabı seçiliyor: " + accountName);
        // Burada, UI ile etkileşimde bulunarak hesabı seçebilirsiniz.
        WebElement receiverAccount = Driver.getDriver().findElement(By.xpath("//option[text()='" + accountName + "']"));
        receiverAccount.click();

    }

    @And("Browser alert {string} butonuna tıklanır")
    public void browserAlertButonunaTıklanır(String arg0) {
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    @And("{string} tıklanır")
    public void tıklanır(String name) {
        WebElement receiverAccount = Driver.getDriver().findElement(By.xpath("//*[text()='" + name + "']/following::select[1]"));
        receiverAccount.click();
    }


    @And("{string} input alanına tıklanır ve dolu ise temizlenir ve {string} değeri girilir")
    public void inputAlanınaTıklanırVeDoluIseTemizlenirVeDeğeriGirilir(String inputName, String value) {
        WebElement inputElement = Driver.getDriver().findElement(By.xpath("(//*[text()='" + inputName + "'])//..//input"));
        System.out.println("inputElement.getCssValue() = " + inputElement.getCssValue("value"));
        if (!inputElement.getCssValue("value").isEmpty()) {
            inputElement.clear();
        }
        inputElement.sendKeys(value);
    }

    @And("{string} alanında {string} değeri olduğu kontrol edilir")
    public void alanındaDeğeriOlduğuKontrolEdilir(String labelText, String value) {
        WebElement element = Driver.getDriver().findElement(By.xpath("(//*[text()='" + labelText + "'])/following-sibling::div"));
        String actualValue = element.getText();
        assertEquals(value, actualValue);
    }


    @And("{string} input alanı kotrol edilir ve dolu ise temizlenir")
    public void inputAlanıKotrolEdilirVeDoluIseTemizlenir(String inputName) {
        WebElement inputElement = Driver.getDriver().findElement(By.xpath("(//*[text()='" + inputName + "'])//..//input"));
        // Input değerini al
        String inputValue = inputElement.getAttribute("value");
        System.out.println("Input Değeri: " + inputValue);

        // Input alanı dolu ise temizle
        if (inputValue != null && !inputValue.isEmpty()) {
            inputElement.clear();
            System.out.println("Input alanı temizlendi.");
        } else {
            System.out.println("Input alanı zaten boş.");
        }
    }

    @And("{string} input alanı dolu ise temizlenir ve {string} değeri girilir")
    public void inputAlanıDoluIseTemizlenirVeDeğeriGirilir(String inputName, String value) {
        WebElement inputElement = Driver.getDriver().findElement(By.xpath("(//*[text()='" + inputName + "'])//..//input"));
        String inputValue = inputElement.getAttribute("value");

        if (inputValue != null && !inputValue.isEmpty()) {
            inputElement.clear();
        }
        inputElement.sendKeys(value);


    }

    @And("Hesap Amount değerindeki artışın {string} olduğu kontrol edilir")
    public void hesapAmountDeğerindekiArtışınOlduğuKontrolEdilir(String value) {
        BigDecimal accountBalance = (BigDecimal) scenarioContext.getContext("firstAccountBalance");
        BigDecimal newAccountBalance = (BigDecimal) scenarioContext.getContext("newAccountBalance");

        BigDecimal actualAmount = newAccountBalance.subtract(accountBalance);

        BigDecimal expectedAmount = new BigDecimal(value);

        Assert.assertEquals(expectedAmount, actualAmount);
    }

    @And("{string} input alanına {string} değeri girilir")
    public void inputAlanınaDeğeriGirilir(String inputName, String value) {

        WebElement inputElement = Driver.getDriver().findElement(By.xpath("(//*[text()='" + inputName + "'])//..//input"));
        BrowserUtils.waitForClickability(inputElement, Duration.ofSeconds(10));
        inputElement.sendKeys(value);
    }
}

