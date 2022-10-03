package live.panda.testing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import static org.testng.Assert.assertEquals;

public class VerifyCostOfProductTest {
    final static Logger LOGGER = Logger.getLogger(VerifyCostOfProductTest.class);

    @Test
    public void VerifyCostOfProductInListPageAndDetailsPageTest(){

        //variables
        final String URL = "http://live.techpanda.org/";
        By mobileXpath = By.xpath("//*[text()='Mobile']");
        By listPageXperiaPrice = By.xpath("//*[@id='product-price-1']");
        By sonyXperiaXpath = By.xpath("//*[@title='Sony Xperia']");
        By sonyXperiaDetailInfoXpath = By.xpath("//*[@class='price-info']");

        LOGGER.info("Initialization chromedriver");
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();

        LOGGER.info("1. Go to http://live.techpanda.org/");
        driver.get(URL);

        LOGGER.info("2. Click on the 'MOBILE' menu");
        driver.findElement(mobileXpath).click();

        LOGGER.info("3. In the list of all mobile, read the cost of Sony XPeria mobile. Note this value.");
        String xperiaCost = driver.findElement(listPageXperiaPrice).getText();


        LOGGER.info("4. Click on the Sony XPeria mobile");
        driver.findElement(sonyXperiaXpath).click();

        LOGGER.info("5. Read the cost of Sony XPeria mobile in detail page");
        String xperiaCostInDetailPage = driver.findElement(sonyXperiaDetailInfoXpath).getText();

        LOGGER.info("6. Compare value in Step 3 and Step 5");
        assertEquals(xperiaCost, xperiaCostInDetailPage);

        LOGGER.info("Clean up driver");
        driver.quit();
    }

}
