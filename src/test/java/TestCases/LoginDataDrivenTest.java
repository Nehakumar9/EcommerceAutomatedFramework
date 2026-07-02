package TestCases;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;
import Utilities.DataProviderUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginDataDrivenTest extends BaseClass {

    @Test(groups = "datadriven", dataProvider = "LoginData", dataProviderClass = DataProviderUtility.class)
    void verifyLoginTest(String email, String pwd, String exp) {
        logger.info("===Sarting Login test===");
        HomePage hp = new HomePage(driver);
        hp.clickAccount();
        hp.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        logger.info("===Enter Login details===");
        loginPage.enterEmail(email);
        loginPage.enterPwd(pwd);
        loginPage.clickLogin();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        Boolean targetPage = myAccountPage.isMyAccountExists();

        if (exp.equalsIgnoreCase("Valid")) {
            if (targetPage == true) {
                Assert.assertTrue(true);
                myAccountPage.clickLogOut();
            } else {
                Assert.assertTrue(false);
            }
        }
        if (exp.equalsIgnoreCase("Invalid")) {
            if (targetPage == true) {
                myAccountPage.clickLogOut();
                Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }
        }

    }
}
