package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;


@Slf4j
public class PageObjectVerifyTheComparePopUpWindowTest extends BaseTestSetup {

    @Test(description = "4")
    public void verifyThatYouAreAbleToCompareTwoProduct() {
        //variables
        String[] phoneArray = {"IPhone", "Sony Xperia", "Samsung Galaxy"};
        String expectedPopupTitle = "COMPARE PRODUCTS";
        String expectedMobilePageTitle = "Mobile";
        PandaHomePage homePage = new PandaHomePage(driver);

        log.info("2. Click on the 'MOBILE' menu");
        homePage.clickMobileLink()
                .addToCompare(phoneArray)
                .clickOnCompareButton()
                .switchToPopUp()
                .assertPopUpPageTitle(expectedPopupTitle, "Check title")
                .assertPhonesOnPopUp(phoneArray, "Check Sony")
                .closePopUpWindow()
                .switchToMainWindow()
                .assertMobilePageTitle(expectedMobilePageTitle, "Check mobile title page");

        log.info("3. In mobile product list, click on the 'Add to Compare' for 2 mobiles");


        log.info("4. Click on the 'COMPARE' button");


        log.info("5. Verify the pop-up window and check that the products are reflected in it");


        log.info("6. Close the pop-up window");

    }


}
