package refactored.test;

import base.test.BaseTestSetup;
import org.testng.annotations.Test;
import panda.pages.MobilePage;
import panda.pages.PandaHomePage;

import static org.testng.Assert.assertEquals;

public class PageObjectVerifyTheItemsInMobileListTest extends BaseTestSetup {

    @Test(description = "1")
    public void verifyItemInTheMobileListPageCanBeShortedByNameTest(){
        //variables
        PandaHomePage homePage = new PandaHomePage(driver);
        MobilePage mobilePage = new MobilePage(driver);
        String expectedTitle = "Home page";
        String mobileExpectedTitle = "Mobile";

        LOGGER.info("2.Verify the title of the page");
        assertEquals(homePage.getPageTitle(), expectedTitle);

        LOGGER.info("3.Click on Mobile menu");
        homePage.clickMobileLink();

        LOGGER.info("4.Verify the tittle of the page");
        assertEquals(mobilePage.getPageTitle(), mobileExpectedTitle);

        LOGGER.info("5.In the list of all mobile, select SORT BY dropdown as name");
        mobilePage.clickSelectByNameDropdown();

        LOGGER.info("6.Verify all product are sorted by name");
        assertEquals(mobilePage.getMobileList(), mobilePage.getMobileList().stream().sorted().toList());

    }


}
