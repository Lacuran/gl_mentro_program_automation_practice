package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;

import static org.testng.Assert.assertEquals;

@Slf4j
public class PageObjectVerifyTheItemsInMobileListTest extends BaseTestSetup {

    @Test(description = "1")
    public void verifyItemInTheMobileListPageCanBeShortedByNameTest() {
        //variables
        PandaHomePage homePage = new PandaHomePage(driver);
        String expectedTitle = "Home page";
        String mobileExpectedTitle = "Mobile";

        log.info("2.Verify the title of the page");
        assertEquals(homePage.getHomePageTitle(), expectedTitle);

        log.info("3.Click on Mobile menu");
        homePage.clickMobileLink();

        log.info("4.Verify the tittle of the page");
        assertEquals(homePage.getMobilePage().getMobilePageTitle(), mobileExpectedTitle);

        log.info("5.In the list of all mobile, select SORT BY dropdown as name");
        homePage.getMobilePage().clickSelectByNameDropdown();

        log.info("6.Verify all product are sorted by name");
        assertEquals(homePage.getMobilePage().getMobileList()
                , homePage.getMobilePage().getMobileList().stream().sorted().toList());

    }
}
