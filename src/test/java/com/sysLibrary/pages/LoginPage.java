package com.sysLibrary.pages;

import com.sysLibrary.utilities.BrowserUtils;
import com.sysLibrary.utilities.ConfigurationReader;
import com.sysLibrary.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){ PageFactory.initElements(Driver.get(),this);}

    @FindBy(id="inputEmail")
    public WebElement userEmail;

    @FindBy(id="inputPassword")
    public WebElement password;

    @FindBy(xpath = "//button[text()='Sign in']")
    public WebElement signInBtn;

    public void logInAsLibrarian() {
        BrowserUtils.waitForPageToLoad(5);
        userEmail.sendKeys(ConfigurationReader.get("librarian_email"));
        password.sendKeys(ConfigurationReader.get("librarian_password"));
        signInBtn.click();
    }

}
