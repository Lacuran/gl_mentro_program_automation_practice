package base.test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.HighlightHelper;

@Slf4j
public class BaseTestSetup {

    final String URL = "http://live.techpanda.org/";
    public WebDriver driver;
    public WebDriver decorated;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        decorated = new HighlightHelper().decorate(driver);

        log.info("WebDriver Initialization");
        decorated.manage().window().maximize();

        log.info("1. Go to http://live.techpanda.org/");
        decorated.get(URL);
    }

    @AfterMethod
    public void cleanUp() {
        log.info("Cleanup Driver");
        decorated.quit();
    }

}