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

        homePage.clickMobileLink()
                .addToCompare(phoneArray)
                .clickOnCompareButton()
                .switchToPopUp()
                .assertPopUpPageTitle(expectedPopupTitle, "Check title")
                .assertPhonesOnPopUp(phoneArray, "Check Sony")
                .closePopUpWindow()
                .switchToMainWindow()
                .assertMobilePageTitle(expectedMobilePageTitle, "Check mobile title page");


    }


}
