package org.testinium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;

    private Driver() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigurationReader.getBrowser();
            boolean headless = ConfigurationReader.getHeadlessValue().equalsIgnoreCase("true");
            String mobile = ConfigurationReader.getMobileValue();

            System.out.println("Browser: " + browser);
            System.out.println("Headless: " + headless);
            System.out.println("Mobile: " + mobile);

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    if (headless) {
                        chromeOptions.addArguments("--headless");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }

            if (mobile.equalsIgnoreCase("iphone16")) {
                driver.manage().window().setSize(new Dimension(390, 844));
            } else if (mobile.equalsIgnoreCase("samsungS24")) {
                driver.manage().window().setSize(new Dimension(412, 915));
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}