package base.test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.MyDriverListener;

@Slf4j
public class BaseTestSetup {

    final String URL = "http://live.techpanda.org/";
    public WebDriver driver;
    public WebDriverListener listener;
    public WebDriver decorated;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        listener = new MyDriverListener(driver);
        decorated = new EventFiringDecorator<>(listener).decorate(driver);

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