package com.sysLibrary.pages;

import com.sysLibrary.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UsersPage {

    public UsersPage() { PageFactory.initElements(Driver.get(), this); }

    @FindBy(xpath = "//*[@id='users']//a[@data-toggle='modal']")
    public WebElement addUser;

    @FindBy(xpath = "//*[@name='full_name']")
    public WebElement fullName;

    @FindBy(xpath = "//*[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//*[@name='email']")
    public WebElement email;

    @FindBy(xpath = "//*[@name='user_group_id']")
    public WebElement userGroup;

    @FindBy(xpath = "//*[@name='status']")
    public WebElement status;

    @FindBy(xpath = "//*[@name='start_date']")
    public WebElement startDate;

    @FindBy(xpath = "//*[@name='end_date']")
    public WebElement endDate;

    @FindBy(xpath = "//*[@name='address']")
    public WebElement adress;

    @FindBy(xpath = "//button[@type='close']")
    public WebElement close;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveChanges;

    @FindBy(id="toast-container")
    public WebElement hasBeenCreatedMessage;

    @FindBy(id="add_user_modal")
    public WebElement addUserModal;
}
