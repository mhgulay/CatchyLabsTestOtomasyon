package org.testinium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testinium.utils.Driver;

public class AccountPage {

    public AccountPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "(//*[text()=\"Amount\"]//..//div)[3]")
    public WebElement accountBalance;

    @FindBy(xpath = "(//*[text()=\"Sender:\"]//..//div)[3]")
    public WebElement transactionsDetail;

    @FindBy(css = ".css-11aywtz")
    public WebElement input;







}
