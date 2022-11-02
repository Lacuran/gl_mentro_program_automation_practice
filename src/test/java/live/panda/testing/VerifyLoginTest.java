package live.panda.testing;

import base.test.BaseTestSetup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

public class VerifyLoginTest extends BaseTestSetup {

    By accountCssSelector = By.cssSelector("[data-target-element='#header-account']");
    By logInCssSelector = RelativeLocator.with(By.cssSelector("[title='Log In']")).below(accountCssSelector);
    By logoutCssSelector = RelativeLocator.with(By.cssSelector("[title='Log Out']")).below(accountCssSelector);
    By[] loginID = {By.id("email"), By.id("pass")};
    String[] loginData = {"lenrok.test1@gmail.com", "test123"};
    By loginButton = By.id("send2");
    By welcomeMsg = By.cssSelector(".hello");
    String expectedWelcomeMsg = "Hello, Kornel Maybe Test!";
    String expectedPageTitle = "Home page";

    @Test(groups = "login", priority = 1)
    public void verifyIfUserIsLoggedSuccessfully(){
        LOGGER.info("2. Click on the Log In");
        driver.findElement(accountCssSelector).click();
        driver.findElement(logInCssSelector).click();

        LOGGER.info("3. Fill login user information");
        IntStream.range(0, loginID.length)
                .forEach(i -> driver.findElement(loginID[i]).sendKeys(loginData[i]));

        LOGGER.info("4. Click login button");
        driver.findElement(loginButton).click();

        LOGGER.info("5. Verify if user is logged successfully");
        assertEquals(driver.findElement(welcomeMsg).getText(), expectedWelcomeMsg,"Check welcome MSG");

    }

    @Test(groups = "login", priority = 2)
    public void verifyUserLogout(){
        LOGGER.info("2. Click on the Log In");
        driver.findElement(accountCssSelector).click();
        driver.findElement(logInCssSelector).click();

        LOGGER.info("3. Fill login user information");
        IntStream.range(0, loginID.length)
                .forEach(i -> driver.findElement(loginID[i]).sendKeys(loginData[i]));

        LOGGER.info("4. Click login button");
        driver.findElement(loginButton).click();

        LOGGER.info("5. Verify if user is logged successfully");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(welcomeMsg).getText(), expectedWelcomeMsg,"Check welcome MSG");

        LOGGER.info("6. Click on the Logout");
        driver.findElement(accountCssSelector).click();
        driver.findElement(logoutCssSelector).click();

        LOGGER.info("7. Verify if user is log out successfully");
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
        String[] editInfoData = {"Thomas", "Brzeczy", "Szczykiewicz", "test123"};
        By saveButtonCssSelector = By.cssSelector("[title='Save']");
        By successMsgCssSelector = By.cssSelector(".success-msg");
        String expectedSuccessMsg = "The account information has been saved.";


        LOGGER.info("2. Click on the Log In");
        driver.findElement(accountCssSelector).click();
        driver.findElement(logInCssSelector).click();

        LOGGER.info("3. Fill login user information");
        IntStream.range(0, loginID.length)
                .forEach(i -> driver.findElement(loginID[i]).sendKeys(loginData[i]));

        LOGGER.info("4. Click login button");
        driver.findElement(loginButton).click();

        LOGGER.info("5. Click on the Account Information");
        driver.findElement(accInfoXPath).click();

        LOGGER.info("6. Edit Account information and click on the Save button");
        IntStream.range(0, 3).forEach(i -> driver.findElement(editInfoID[i]).clear());
        IntStream.range(0, 3).forEach(i -> driver.findElement(editInfoID[i]).sendKeys(editInfoData[i]));
        driver.findElement(editInfoID[3]).sendKeys(editInfoData[3]);
        driver.findElement(saveButtonCssSelector).click();

        LOGGER.info("7. Verify edit of user information");
        assertEquals(driver.findElement(successMsgCssSelector).getText(), expectedSuccessMsg, "Check success MSG");
        assertEquals(driver.findElement(welcomeMsg).getText()
                , String.format("Hello, %s %s %s!", editInfoData[0], editInfoData[1], editInfoData[2])
                , "Check welcome MSG");

    }

    @AfterGroups("login")
    public void restoreUserInformation(){
        //This aftergroups test restore account information
        By accInfoXPath = By.xpath("//a[contains(text(), 'Account Information')]");
        By[] editInfoID = {By.id("firstname"), By.id("middlename"), By.id("lastname"), By.id("current_password")};
        String[] editInfoData = {"Kornel", "Maybe", "Test", "test123"};
        By saveButtonCssSelector = By.cssSelector("[title='Save']");
        String URL = "http://live.techpanda.org/";

        LOGGER.info("Restoring account information");
        LOGGER.info("WebDriver Initialization");
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();

        LOGGER.info("1. Go to http://live.techpanda.org/");
        driver.get(URL);

        LOGGER.info("2. Click on the Log In");
        driver.findElement(accountCssSelector).click();
        driver.findElement(logInCssSelector).click();

        LOGGER.info("3. Fill login user information");
        IntStream.range(0, loginID.length)
                .forEach(i -> driver.findElement(loginID[i]).sendKeys(loginData[i]));

        LOGGER.info("4. Click login button");
        driver.findElement(loginButton).click();

        LOGGER.info("5. Click on the Account Information");
        driver.findElement(accInfoXPath).click();

        LOGGER.info("6. Restoring Account information");
        IntStream.range(0, 3).forEach(i -> driver.findElement(editInfoID[i]).clear());
        IntStream.range(0, 3).forEach(i -> driver.findElement(editInfoID[i]).sendKeys(editInfoData[i]));
        driver.findElement(editInfoID[3]).sendKeys(editInfoData[3]);
        driver.findElement(saveButtonCssSelector).click();

        LOGGER.info("7. Checking welcome msg");
        assertEquals(driver.findElement(welcomeMsg).getText(), expectedWelcomeMsg,"Check welcome MSG");

        LOGGER.info("Cleanup Driver");
        driver.quit();

    }
}
