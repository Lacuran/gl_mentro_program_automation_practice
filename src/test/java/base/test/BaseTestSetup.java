package base.test;

import io.qameta.allure.Step;
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
    public WebDriver driver1;
    public WebDriverListener listener;
    public WebDriver driver;

    @BeforeMethod
    @Step("WebDriver Initialization")
    public void setUp() {
        driver1 = new ChromeDriver();
        listener = new MyDriverListener(driver1);
        driver = new EventFiringDecorator<>(listener).decorate(driver1);

        log.info("WebDriver Initialization");
        driver.manage().window().maximize();

        log.info("1. Go to http://live.techpanda.org/");
        driver.get(URL);
    }

    @AfterMethod
    @Step("Cleanup Driver")
    public void cleanUp() {
        log.info("Cleanup Driver");
        driver.quit();
    }

}