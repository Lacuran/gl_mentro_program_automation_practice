package base.test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseTestSetup{

    final String URL = "http://live.techpanda.org/";
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();

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

    public void highlightElement(WebDriver driver, WebElement element) {
        //TODO implement this to all test cases
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='4px groove red'", element);
            Thread.sleep(1000);
            js.executeScript("arguments[0].style.border=''", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}