package base.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;

public class BaseTestSetup {

    public final static Logger LOGGER = Logger.getLogger(BaseTestSetup.class);
    final String URL = "http://live.techpanda.org/";
    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverManager.chromedriver().create();

        LOGGER.info("WebDriver Initialization");
        driver.manage().window().maximize();

        LOGGER.info("1. Go to http://live.techpanda.org/");
        driver.get(URL);
    }

    @AfterMethod
    public void cleanUp() {

        LOGGER.info("Cleanup Driver");
        driver.quit();
    }
}
