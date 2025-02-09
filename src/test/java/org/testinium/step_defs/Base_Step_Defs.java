package org.testinium.step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testinium.utils.BrowserUtils;
import org.testinium.utils.ConfigurationReader;
import org.testinium.utils.Driver;

public class Base_Step_Defs {

    @Given("{string} input alanına {string} değeri yazılır")
    public void input_alanına_değeri_yazılır(String inputText, String inputValue) {
        WebElement inputElement = Driver.getDriver().findElement(By.xpath("//input[@placeholder='" + inputText + "']"));
        inputElement.sendKeys(inputValue);
    }
    @Given("{string} butonuna tıklanır")
    public void butonuna_tıklanır(String buttonName) {
        WebElement button = Driver.getDriver().findElement(By.xpath("//*[text()='" + buttonName + "']"));
        button.click();
    }

    @When("{string} input alanına configten okunan {string} değeri yazılır")
    public void inputAlanınaConfigtenOkunanDeğeriYazılır(String inputText, String inputValue) {
        WebElement inputElement = Driver.getDriver().findElement(By.xpath("//input[@placeholder='" + inputText + "']"));

        String value;
        switch (inputValue) {
            case "valid_username":
                value = ConfigurationReader.getValidUsername();
                break;
            case "valid_password":
                value = ConfigurationReader.getValidPassword();
                break;
            case "invalid_username":
                value = ConfigurationReader.getInvalidCredentials().getJSONObject(0).getString("username");
                break;
            case "invalid_password":
                value = ConfigurationReader.getInvalidCredentials().getJSONObject(0).getString("password");
                break;
            default:
                value = ConfigurationReader.getProperty(inputValue);
        }

        inputElement.sendKeys(value);
    }

    @Then("{string} ekranda görülmelidir")
    public void ekrandaGörülmelidir(String message) {
        WebElement messageText = Driver.getDriver().findElement(By.xpath("//*[text()='" + message + "']"));
        Assert.assertTrue(messageText.isDisplayed());
    }

    @And("iki saniye beklenir")
    public void iki_saniye_beklenir() {
        BrowserUtils.waitTwoSeconds();

    }

    @And("{int} saniye beklenir")
    public void saniyeBeklenir(int seconds) {
        BrowserUtils.waitSeconds(seconds);
    }
}
