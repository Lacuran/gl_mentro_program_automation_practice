package live.panda.testing;

import base.test.BaseTestSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class VerifyWishlistTest extends BaseTestSetup {

    @Test
    public void VerifyAccountCreationInECommerceAndShareWishlistTest(){
        //variables
        String addToEmail = String.valueOf(ThreadLocalRandom.current().nextInt(999999999));
        By accountXPath = By.cssSelector("[data-target-element='#header-account']");
        By myAccountLink = RelativeLocator.with(By.cssSelector("[title='My Account']")).below(accountXPath);
        By createAccButton = By.cssSelector("[title='Create an Account']");
        By submitAccButton = RelativeLocator.with(By.cssSelector("[title='Register']")).below(By.cssSelector(".button"));
        By[] registrationForm = {By.id("firstname"), By.id("middlename")
                , By.id("lastname"), By.id("email_address")
                , By.id("password"), By.id("confirmation")};
        String[] registrationData = {"Kornel", "Maybe"
                , "Test", "lenrok.test" + addToEmail +"@gmail.com"
                , "test123", "test123"};
        By registrationDoneText = By.cssSelector(".success-msg");
        By tvXpath = By.xpath("//*[text()='TV']");
        By addToWishListButton = RelativeLocator.with(By.cssSelector(".link-wishlist"))
                .below(By.cssSelector("[title='LG LCD']"));
        By shareWishListButton = By.cssSelector(".btn-share");
        By submitShareButton = By.cssSelector("[title='Share Wishlist']");
        By successMsgLocator = By.cssSelector(".success-msg");
        String expectedRegistrationMsg = "Thank you for registering with Main Website Store.";
        String expectedMsg = "Your Wishlist has been shared.";


        LOGGER.info("2. Click on my account link");
        driver.findElement(accountXPath).click();
        driver.findElement(myAccountLink).click();

        LOGGER.info("3. Click Create Account link and fill New User information except Email ID");
        driver.findElement(createAccButton).click();
        IntStream.range(0, registrationForm.length)
                .forEach(i -> driver.findElement(registrationForm[i]).sendKeys(registrationData[i]));

        LOGGER.info("4. Click Register");
        driver.findElement(submitAccButton).click();

        LOGGER.info("5. Verify Registration is done");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(registrationDoneText).getText(), expectedRegistrationMsg
                , "Check welcome msg");

        LOGGER.info("6. Go to TV menu");
        driver.findElement(tvXpath).click();

        LOGGER.info("7. Add product in your wish list");
        driver.findElement(addToWishListButton).click();

        LOGGER.info("8. Click SHARE WISHLIST");
        driver.findElement(shareWishListButton).click();

        LOGGER.info("9. In next page enter Email and a message and click SHARE WISHLIST");
        driver.findElement(registrationForm[3]).sendKeys(registrationData[3]);
        driver.findElement(submitShareButton).click();

        LOGGER.info("10. Check wishlist is shared");
        softAssert.assertEquals(driver.findElement(successMsgLocator).getText(), expectedMsg
                , "Check share Msg");
        softAssert.assertAll();
    }
}
