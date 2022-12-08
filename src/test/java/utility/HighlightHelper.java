package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighlightHelper {

    public void highlightElement(WebDriver driver, By by) {
        //TODO implement this to all test cases
        WebElement element = driver.findElement(by);
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='4px groove red'", element);
            Thread.sleep(500);
            js.executeScript("arguments[0].style.border=''", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
