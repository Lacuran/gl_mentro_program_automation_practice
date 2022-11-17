package live.panda.testing;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.User;

import java.time.Duration;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

@Slf4j
public class VerifyLoginTest extends BaseTestSetup {

    By accountCssSelector = By.cssSelector("[data-target-element='#header-account']");
    By logInCssSelector = RelativeLocator.with(By.cssSelector("[title='Log In']")).below(accountCssSelector);
    By logoutCssSelector = RelativeLocator.with(By.cssSelector("[title='Log Out']")).below(accountCssSelector);
    By[] loginID = {By.id("email"), By.id("pass")};
    User user1 = User.createUserFrom("Userdata1.json");
    User user2 = User.createUserFrom("Userdata2.json");
    By loginButton = By.id("send2");
    By welcomeMsg = By.cssSelector(".hello");
    String expectedWelcomeMsg = "Hello, Kornel Maybe Test!";
    String expectedPageTitle = "Home page";

    @Test(groups = "login", priority = 1, description = "self-assignment")
    public void verifyIfUserIsLoggedSuccessfully(){
        loginSteps();

        log.info("5. Verify if user is logged successfully");
        assertEquals(driver.findElement(welcomeMsg).getText(), expectedWelcomeMsg,"Check welcome MSG");

    }

    @Test(groups = "login", priority = 2)
    public void verifyUserLogout(){
        loginSteps();

        log.info("5. Verify if user is logged successfully");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(welcomeMsg).getText(), expectedWelcomeMsg,"Check welcome MSG");

        log.info("6. Click on the Logout");
        driver.findElement(accountCssSelector).click();
        driver.findElement(logoutCssSelector).click();

        log.info("7. Verify if user is log out successfully");
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        explicitWait.until(ExpectedConditions.titleIs(expectedPageTitle));
        softAssert.assertEquals(driver.getTitle(), expectedPageTitle, "Check page title");
        softAssert.assertAll();
    }

    @Test(groups = "login", priority = 3)
    public void verifyEditOfUserInformation(){
        //variables
        By accInfoXPath = By.xpath("//a[contains(text(), 'Account Information')]");
        By[] editInfoID = {By.id("firstname"), By.id("middlename"), By.id("lastname"), By.id("current_password")};
        String[] editInfoData = {user2.getFirstName(), user2.getMiddleName(), user2.getLastName(), user2.getPassword()};
        By saveButtonCssSelector = By.cssSelector("[title='Save']");
        By successMsgCssSelector = By.cssSelector(".success-msg");
        String expectedSuccessMsg = "The account information has been saved.";

        loginSteps();

        log.info("5. Click on the Account Information");
        driver.findElement(accInfoXPath).click();

        log.info("6. Edit Account information and click on the Save button");
        editUserInformation(editInfoID, editInfoData, saveButtonCssSelector);

        log.info("7. Verify edit of user information");
        assertEquals(driver.findElement(successMsgCssSelector).getText(), expectedSuccessMsg, "Check success MSG");
        assertEquals(driver.findElement(welcomeMsg).getText()
                , String.format("Hello, %s %s %s!", editInfoData[0], editInfoData[1], editInfoData[2])
                , "Check welcome MSG");

    }

    @BeforeGroups("login")
    public void setUpUserInformation(){
        //This BeforeGroup test restore account information
        user1.setEmail("lenrok.test1@gmail.com");
        By accInfoXPath = By.xpath("//a[contains(text(), 'Account Information')]");
        By[] editInfoID = {By.id("firstname"), By.id("middlename"), By.id("lastname"), By.id("current_password")};
        String[] editInfoData = {user1.getFirstName(), user1.getMiddleName(), user1.getLastName(), user1.getPassword()};
        By saveButtonCssSelector = By.cssSelector("[title='Save']");

        log.info("Preparing account information");

        setUp();

        loginSteps();

        log.info("5. Click on the Account Information");
        driver.findElement(accInfoXPath).click();

        log.info("6. Restoring Account information");
        editUserInformation(editInfoID, editInfoData, saveButtonCssSelector);

        log.info("7. Checking welcome msg");
        assertEquals(driver.findElement(welcomeMsg).getText(), expectedWelcomeMsg,"Check welcome MSG");

        cleanUp();

    }

    private void editUserInformation(By[] editInfoID, String[] editInfoData, By saveButtonCssSelector) {
        IntStream.range(0, 3).forEach(i -> driver.findElement(editInfoID[i]).clear());
        IntStream.range(0, 3).forEach(i -> driver.findElement(editInfoID[i]).sendKeys(editInfoData[i]));
        driver.findElement(editInfoID[3]).sendKeys(editInfoData[3]);
        driver.findElement(saveButtonCssSelector).click();
    }

    private void loginSteps() {
        String[] loginData = {user1.getEmail(), user1.getPassword()};
        user1.setEmail("lenrok.test1@gmail.com");

        log.info("2. Click on the Log In");
        driver.findElement(accountCssSelector).click();
        driver.findElement(logInCssSelector).click();

        log.info("3. Fill login user information");
        IntStream.range(0, loginID.length)
                .forEach(i -> driver.findElement(loginID[i]).sendKeys(loginData[i]));

        log.info("4. Click login button");
        driver.findElement(loginButton).click();
    }
}
