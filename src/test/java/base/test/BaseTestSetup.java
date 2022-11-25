package base.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseTestSetup {

    final String URL = "http://live.techpanda.org/";
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverManager.chromedriver().create();

        log.info("WebDriver Initialization");
        driver.manage().window().maximize();

        log.info("1. Go to http://live.techpanda.org/");
        driver.get(URL);
    }

    @AfterMethod
    public void cleanUp() {
        log.info("Cleanup Driver");
        driver.quit();
    }
}
