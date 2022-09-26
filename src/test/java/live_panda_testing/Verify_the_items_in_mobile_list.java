package live_panda_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Verify_the_items_in_mobile_list {

    @Test
    public void VerifyItemInTheMobileListPageCanBeShortedByNameTest(){
        WebDriver driver = new ChromeDriver();

        //1.Goto http://live.techpanda.org/

        //2.Verify the title of the page

        //3.Click on Mobile menu

        //4.Verify the tittle of the page

        //5.In the list of all mobile, select SORT BY dropdown as name

        //6.Verify all product are sorted by name



        driver.quit();
    }
}
