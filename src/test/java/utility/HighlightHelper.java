package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.decorators.Decorated;
import org.openqa.selenium.support.decorators.WebDriverDecorator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HighlightHelper extends WebDriverDecorator<WebDriver> {

    public HighlightHelper() {
        super();
    }

    @Override
    public void beforeCall(Decorated<?> target, Method method, Object[] args) {
        super.beforeCall(target, method, args);
    }

    @Override
    public Object call(Decorated<?> target, Method method, Object[] args) throws Throwable {
        return super.call(target, method, args);
    }

    @Override
    public void afterCall(Decorated<?> target, Method method, Object[] args, Object res) {
        super.afterCall(target, method, args, res);
    }

    @Override
    public Object onError(Decorated<?> target, Method method, Object[] args, InvocationTargetException e) throws Throwable {
        return super.onError(target, method, args, e);
    }

    public void highlightElement(WebDriver driver, By by) {
        //TODO ...
        WebElement element = driver.findElement(by);
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
