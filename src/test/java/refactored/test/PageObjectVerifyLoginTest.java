package refactored.test;

import base.test.BaseTestSetup;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import panda.pages.MyAccountPage;
import panda.pages.PandaHomePage;
import utility.User;

import static org.testng.Assert.assertEquals;

@Slf4j
public class PageObjectVerifyLoginTest extends BaseTestSetup {

    User user1 = User.createUserFrom("Userdata1.json");
    User user2 = User.createUserFrom("Userdata2.json");
    String expectedWelcomeMsg = "Hello, Kornel Maybe Test!";

    @Test(groups = "login", priority = 1, description = "self-assignment")
    public void verifyIfUserIsLoggedSuccessfully() {
        PandaHomePage homePage = new PandaHomePage(driver);
        MyAccountPage myAccountPage = homePage.clickMyAccountLink()
                .fillingLoginData(user1)
                .clickLoginButton();

        log.info("Verify if user is logged successfully");
        assertEquals(myAccountPage.getWelcomeMSG(), expectedWelcomeMsg, "Check welcome MSG");
    }

    @Test(groups = "login", priority = 2)
    public void verifyUserLogout() {
        PandaHomePage homePage = new PandaHomePage(driver);
        String expectedPageTitle = "Home page";
        homePage.clickMyAccountLink()
                .fillingLoginData(user1)
                .clickLoginButton()
                .logOut();

        log.info("Verify if user is log out successfully");
        assertEquals(homePage.getPageTitle(), expectedPageTitle, "Check page title");
    }

    @Test(groups = "login", priority = 3)
    public void verifyEditOfUserInformation() {

        PandaHomePage homePage = new PandaHomePage(driver);
        String[] editInfoData = {user2.getFirstName(), user2.getMiddleName(), user2.getLastName(), user2.getPassword()};
        String expectedSuccessMsg = "The account information has been saved.";
        String expectedWelcomeMSG = String.format("Hello, %s %s %s!", editInfoData[0], editInfoData[1], editInfoData[2]);

        MyAccountPage myAccountPage = homePage.clickMyAccountLink()
                .fillingLoginData(user1)
                .clickLoginButton()
                .clickAccountInformationLink()
                .editUserInformation(editInfoData)
                .clickSaveButton();

        log.info("Verify edit of user information");
        assertEquals(myAccountPage.getSuccessEditInfoMSG(), expectedSuccessMsg, "Check success MSG");
        assertEquals(myAccountPage.getWelcomeMSG(), expectedWelcomeMSG, "Check welcome MSG");
    }

    @BeforeGroups("login")
    public void setUpUserInformation() {
        //This BeforeGroup test restore account information
        user1.setEmail("lenrok.test1@gmail.com");
        String[] editInfoData = {user1.getFirstName(), user1.getMiddleName(), user1.getLastName(), user1.getPassword()};

        log.info("Preparing account information");
        setUp();
        PandaHomePage homePage = new PandaHomePage(driver);
        MyAccountPage myAccountPage = homePage.clickMyAccountLink()
                .fillingLoginData(user1)
                .clickLoginButton()
                .clickAccountInformationLink()
                .editUserInformation(editInfoData)
                .clickSaveButton();

        log.info("Checking welcome msg");
        assertEquals(myAccountPage.getWelcomeMSG(), expectedWelcomeMsg, "Check welcome MSG");
        cleanUp();
    }
}