package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;
@Slf4j
public class PageObjectVerifySortFunctionalityWorksAsExpectedTest extends BaseTestSetup {

    @Test(description = "additional test '4'")
    public void sortFunctionalityWorksAsExpected(){
        //variables
        PandaHomePage homePage = new PandaHomePage(driver);

        homePage.clickAdvanceSearchLink()
                .enterPriceRange("0", "150")
                .clickSearchButton()
                .getSearchResult()
                .clickAdvanceSearchLink()
                .enterPriceRange("151", "1000")
                .clickSearchButton()
                .getSearchResult();

    }
}
