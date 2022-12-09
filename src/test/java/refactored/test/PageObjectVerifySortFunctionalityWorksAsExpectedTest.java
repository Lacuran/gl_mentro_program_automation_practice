package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import panda.pages.PandaHomePage;

@Slf4j
public class PageObjectVerifySortFunctionalityWorksAsExpectedTest extends BaseTestSetup {

    @Ignore
    @Test(description = "additional test '4'")
    public void sortFunctionalityWorksAsExpected() {

        PandaHomePage homePage = new PandaHomePage(decorated);

        homePage.clickAdvanceSearchLink()
                .enterPriceRange("0", "150")
                .clickSearchButton()
                .getSearchResult()
                .clickAdvanceSearchLink()
                .enterPriceRange("151", "1000")
                .clickSearchButton()
                .getSearchResult();
        //What does it method tests?

    }
}
