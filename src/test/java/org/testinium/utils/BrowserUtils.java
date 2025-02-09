package org.testinium.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtils {
    public static void waitForClickability(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitTwoSeconds() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
