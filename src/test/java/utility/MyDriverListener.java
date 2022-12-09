package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;

public class MyDriverListener implements WebDriverListener {

    WebDriver driver;

    public MyDriverListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
        highlightElement(driver, element);
    }

    @Override
    public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
        highlightElement(driver, element);
    }

    private void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='4px groove red'", element);
//            Thread.sleep(500);
//            js.executeScript("arguments[0].style.border=''", element);
    }

}
