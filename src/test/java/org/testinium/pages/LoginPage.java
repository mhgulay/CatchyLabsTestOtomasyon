package org.testinium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testinium.utils.Driver;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//input[@placeholder=\"Username\"]")
    public WebElement username;

    @FindBy(xpath = "//input[@placeholder=\"Password\"]")
    public WebElement password;

    @FindBy(xpath = "//*[text()=\"Login\"]")
    public WebElement loginButton;



}
