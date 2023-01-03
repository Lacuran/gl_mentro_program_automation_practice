package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;
import panda.pages.ProductListingPage;

import static org.testng.Assert.assertEquals;

@Slf4j
public class PageObjectVerifyTheItemsInMobileListTest extends BaseTestSetup {

    @Test(description = "1")
    public void verifyItemInTheMobileListPageCanBeShortedByNameTest() {

        PandaHomePage homePage = new PandaHomePage(driver);
        String expectedTitle = "Home page";
        String mobileExpectedTitle = "Mobile";

        log.info("2.Verify the title of the page");
        assertEquals(homePage.getPageTitle(), expectedTitle);

        log.info("3.Click on Mobile menu");
        ProductListingPage productListingPage = homePage.clickMobileLink();

        log.info("4.Verify the tittle of the page");
        assertEquals(productListingPage.getPageTitle(), mobileExpectedTitle);

        log.info("5.In the list of all mobile, select SORT BY dropdown as name");
        productListingPage.clickSelectByNameDropdown();

        log.info("6.Verify all product are sorted by name");
        assertEquals(productListingPage.getMobileList(), productListingPage.getMobileList().stream().sorted().toList());
    }
}
