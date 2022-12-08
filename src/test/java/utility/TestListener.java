package utility;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class TestListener implements ITestListener {
WebDriver driver;
    private void takeScreenshot(String testMethodName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        String TimeStamp = d.toString()
                .replace(":", "_")
                .replace(" ", "_");

        try {
            FileUtils.copyFile(srcFile, new File("C:\\Screenshots_from_test\\"
                    + testMethodName + "_" + TimeStamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test Failed need evidence");
        this.driver = ((BaseTestSetup)result.getInstance()).driver;
        try {
            takeScreenshot(result.getName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
