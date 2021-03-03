package com.sysLibrary.step_definitions;

import com.github.javafaker.Faker;
import com.sysLibrary.pages.DashboardPage;
import com.sysLibrary.pages.LoginPage;
import com.sysLibrary.pages.UsersPage;
import com.sysLibrary.utilities.BrowserUtils;
import com.sysLibrary.utilities.ConfigurationReader;
import com.sysLibrary.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class AddUserStepDef {

   @Given("Librarian is on the add user page")
    public void librarian_is_on_the_add_user_page() {
        Driver.get().get(ConfigurationReader.get("url"));
        new LoginPage().logInAsLibrarian();
        BrowserUtils.waitForPageToLoad(5);
        new DashboardPage().navigatePages("Users");
        new UsersPage().addUser.click();
    }
    @When("Librarian enters valid inputs to the options fields")
    public void librarian_enters_valid_inputs_to_the_options_fields() {
        BrowserUtils.waitForPageToLoad(5);
        Faker faker = new Faker();
        UsersPage usersPage = new UsersPage();
        usersPage.fullName.sendKeys(faker.name().firstName());
        usersPage.email.sendKeys(faker.internet().emailAddress());
        usersPage.password.sendKeys(faker.internet().password());
        new Select(usersPage.userGroup).selectByIndex(1);
        new Select(usersPage.status).selectByVisibleText("ACTIVE");
        usersPage.startDate.sendKeys("2021-06-20");
        usersPage.endDate.sendKeys("2021-08-20");
        usersPage.adress.sendKeys(faker.address().fullAddress());
    }
    @Then("Librarian should be able to click save changes button")
    public void librarian_should_be_able_to_click_save_changes_button() {

        new UsersPage().saveChanges.click();
    }

    @Then("Librarian should be able to see {string} message")
    public void librarian_should_be_able_to_see_message(String message) {
        UsersPage usersPage = new UsersPage();
        BrowserUtils.waitForPageToLoad(20);
        BrowserUtils.waitForVisibility(usersPage.hasBeenCreatedMessage,20);
        Assert.assertTrue(usersPage.hasBeenCreatedMessage.isDisplayed());
        String actualMessage = Driver.get().findElement(By.id("toast-container")).getText();
        Assert.assertEquals(message,actualMessage);
    }


    @Then("librarian should see following add user options")
    public void librarian_should_see_following_add_user_options(List<String> options) {
        BrowserUtils.waitForPageToLoad(5);
        WebDriverWait wait = new WebDriverWait(Driver.get(),5);
        List<WebElement> actualOptions = Driver.get().findElements(By.xpath("//*[@id='add_user_form']//label[@class='control-label']"));
        wait.until(ExpectedConditions.visibilityOfAllElements(actualOptions));
        //System.out.println(Arrays.toString(BrowserUtils.getElementsText(actualOptions).toArray()));
        Assert.assertEquals(options,BrowserUtils.getElementsText(actualOptions));
    }

    @When("Librarian click user type dropdown")
    public void librarian_click_user_type_dropdown() {
        BrowserUtils.waitForPageToLoad(5);
        new UsersPage().userGroup.click();

    }
    @Then("Librarian should see following user group options")
    public void librarian_should_see_following_user_group_options(List<String> userGroupOptions) {
        BrowserUtils.waitForPageToLoad(5);
        Select select = new Select(new UsersPage().userGroup);
        List<String> actualUserGroupOptions = BrowserUtils.getElementsText(select.getOptions());
        Assert.assertEquals(userGroupOptions, actualUserGroupOptions);
    }

    @When("Librarian click on status dropdown")
    public void librarian_click_on_status_dropdown() throws InterruptedException {
        BrowserUtils.waitForPageToLoad(10);
        //new UsersPage().status.click();

    }
    @Then("Librarian should see following status options")
    public void librarian_should_see_following_status_options(List<String> statusOptions) {
        BrowserUtils.waitForPageToLoad(10);
        UsersPage usersPage = new UsersPage();
        Select select = new Select(usersPage.status);
        System.out.println(select.getFirstSelectedOption().getText());
        //select.selectByIndex(1);
        BrowserUtils.waitForVisibility(usersPage.status,10);
        List<WebElement> dropdownOptions = new Select(new UsersPage().status).getOptions();
        List<String> actualStatusOptions = BrowserUtils.getElementsText(dropdownOptions);
        System.out.println(Arrays.toString(actualStatusOptions.toArray()));
        Assert.assertEquals(statusOptions, actualStatusOptions);
    }

    @When("Librarian enter start date {int}-{int}-{int} and end date {int}-{int}-{int}")
    public void librarian_enter_start_date_and_end_date(Integer year1, Integer month1, Integer day1, Integer year2, Integer month2, Integer day2){
        UsersPage usersPage = new UsersPage();
        usersPage.startDate.clear();
        usersPage.startDate.sendKeys(year1 + "-" + month1 + "-" + day1);
        usersPage.endDate.clear();
        usersPage.endDate.sendKeys(year2 + "-" + month2 + "-" + day2);
    }
    @When("Librarian enter start date {string} and end date {string}")
    public void librarian_enter_start_date_and_end_date(String startDate, String endDate) {
        UsersPage usersPage = new UsersPage();
        usersPage.startDate.clear();
        usersPage.startDate.sendKeys(startDate);
        usersPage.endDate.clear();
        usersPage.endDate.sendKeys(endDate);
    }
    @And("Librarian enter valid options to other fields")
    public void librarian_enter_valid_options_to_other_fields() {

        Faker faker = new Faker();
        UsersPage usersPage = new UsersPage();
        usersPage.fullName.sendKeys(faker.name().firstName());
        usersPage.email.sendKeys(faker.internet().emailAddress());
        usersPage.password.sendKeys(faker.internet().password());
        new Select(usersPage.userGroup).selectByVisibleText("Librarian");
        new Select(usersPage.status).selectByVisibleText("ACTIVE");
    }

    @Then("Librarian should not be able to save new user")
    public void librarian_should_not_be_able_to_save_new_user() throws InterruptedException {

        UsersPage usersPage = new UsersPage();
        usersPage.saveChanges.click();
        BrowserUtils.waitFor(3);
        /*Actions actions = new Actions(Driver.get());
        actions.moveToElement(usersPage.hasBeenCreatedMessage).perform();
        Assert.assertFalse(usersPage.hasBeenCreatedMessage.isDisplayed());*/
        Assert.assertTrue(new UsersPage().addUserModal.isDisplayed());
    }

    @When("Librarian make {string} empty")
    public void librarian_make_empty(String option) throws InterruptedException {

        //Driver.get().findElement(By.xpath("//*[@name='" + option + "']")).sendKeys("" Keys.ENTER);
        new UsersPage().saveChanges.click();
    }

   @Then("Librarian should see {string} menu {string} error message")
   public void librarian_should_see_menu_error_message(String option, String fieldErrorMessage) {

        BrowserUtils.waitForVisibility(Driver.get().findElement(By.xpath("//*[@id='"+option+"-error']")),5);
        String actualErrorMessage = Driver.get().findElement(By.xpath("//*[@id='"+option+"-error']")).getText();
        Assert.assertEquals(fieldErrorMessage,actualErrorMessage);
   }


    @When("Librarian enter invalid email")
    public void librarian_enter_invalid_email(){
        Faker faker = new Faker();
        String invalidEmail = faker.name().firstName();
        UsersPage usersPage= new UsersPage();
        BrowserUtils.waitForPageToLoad(5);
        usersPage.email.sendKeys(invalidEmail);
        usersPage.saveChanges.click();
    }


    @Then("Librarian should be able to see {string} error message")
    public void librarian_should_be_able_to_see_error_message(String validEmailMessage) {

        BrowserUtils.waitForVisibility(Driver.get().findElement(By.xpath("//*[@id='email-error']")),4);

        String actualErrorMessage = Driver.get().findElement(By.id("email-error")).getText();
        Assert.assertEquals(validEmailMessage,actualErrorMessage);
    }

    @When("Librarian put valid inputs to other fields except {string}")
    public void librarian_put_valid_inputs_to_other_fields_except(String fieldName) {
        BrowserUtils.waitForPageToLoad(5);
        Faker faker = new Faker();
        UsersPage usersPage = new UsersPage();
        new Select(usersPage.userGroup).selectByVisibleText("Librarian");
        new Select(usersPage.status).selectByVisibleText("ACTIVE");
        usersPage.adress.sendKeys(faker.address().fullAddress());
        switch (fieldName){
            case "full_name":
                usersPage.email.sendKeys(faker.internet().emailAddress());
                usersPage.password.sendKeys(faker.internet().password());
                break;
            case "email":
                usersPage.fullName.sendKeys(faker.name().fullName());
                usersPage.password.sendKeys(faker.internet().password());
                break;
            case "password":
                usersPage.fullName.sendKeys(faker.name().fullName());
                usersPage.email.sendKeys(faker.internet().emailAddress());
                break;
        }
        new UsersPage().saveChanges.click();
    }

}
