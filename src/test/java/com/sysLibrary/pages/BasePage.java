package com.sysLibrary.pages;

import com.sysLibrary.utilities.BrowserUtils;
import com.sysLibrary.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    @FindBy(xpath = "//*[@id='menu_item']//*[contains(text(),'Dashboard')]")
    public WebElement dashBoard;

    @FindBy(xpath = "//*[@id='menu_item']//*[contains(text(),'Users')]")
    public WebElement users;

    @FindBy(xpath = "//*[@id='menu_item']//*[contains(text(),'Books')]")
    public WebElement books;

    @FindBy(id = "navbarDropdown")
    public WebElement navbarDropdown;

    public BasePage(){
        PageFactory.initElements(Driver.get(),this);
    }



    public void navigatePages(String pageName){
        BrowserUtils.waitFor(3);
        Driver.get().findElement(By.xpath("//*[@id='menu_item']//*[contains(text(),'"+pageName+"')]")).click();
    }
}
