package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;

import static org.testng.Assert.assertEquals;
@Slf4j
public class PageObjectVerifyCostOfProductTest extends BaseTestSetup {

    @DataProvider(name = "smartphone")
    public Object[][] dpMethod() {
        return new Object[][]{
                {"Sony Xperia"},
                {"IPhone"},
                {"Samsung Galaxy"}
        };
    }

    @Test(dataProvider = "smartphone", description = "2")
    public void verifyCostOfProductInListPageAndDetailsPageTest(String phoneName) {
        PandaHomePage homePage = new PandaHomePage(driver);
        
        log.info("2. Click on the 'MOBILE' menu");
        homePage.clickMobileLink();

        log.info("3. In the list of all mobile, read the cost of Sony XPeria mobile. Note this value.");
        String phoneCostOnMobilePage = homePage.getMobilePage().getPhoneCostString(phoneName);

        log.info("4. Click on the Sony XPeria mobile");
        homePage.getMobilePage().clickPhoneCssSelector(phoneName);

        log.info("5. Read the cost of Sony XPeria mobile in detail page");
        String phoneCostOnMobileDetailsPage = homePage.getMobilePage().getPhoneTypes().getPhoneCostOnPhonePage(phoneName);

        log.info("6. Compare value in Step 3 and Step 5");
        assertEquals(phoneCostOnMobilePage, phoneCostOnMobileDetailsPage, "Compare value in Step 3 and Step 5");

    }


}

