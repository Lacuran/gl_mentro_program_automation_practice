package refactored.test;

import base.test.BaseTestSetup;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import panda.pages.MobilePage;
import panda.pages.PandaHomePage;
import utility.PhoneTypes;

import static org.testng.Assert.assertEquals;

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
    public void verifyCostOfProductInListPageAndDetailsPageTest (String phoneName){
        PandaHomePage homePage = new PandaHomePage(driver);
        MobilePage mobilePage = new MobilePage(driver);
        PhoneTypes phoneTypes = new PhoneTypes(driver);



        LOGGER.info("2. Click on the 'MOBILE' menu");
        homePage.clickMobileLink();

        LOGGER.info("3. In the list of all mobile, read the cost of Sony XPeria mobile. Note this value.");
        String phoneCostOnMobilePage = mobilePage.getPhoneCostString(phoneName);

        LOGGER.info("4. Click on the Sony XPeria mobile");
        mobilePage.clickPhoneCssSelector(phoneName);

        LOGGER.info("5. Read the cost of Sony XPeria mobile in detail page");
        String phoneCostOnMobileDetailsPage = phoneTypes.getPhoneCostOnPhonePage(phoneName);

        LOGGER.info("6. Compare value in Step 3 and Step 5");
        assertEquals(phoneCostOnMobilePage, phoneCostOnMobileDetailsPage, "Compare value in Step 3 and Step 5");



    }



}

