package TestCases;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
    @Test(groups = {"Sanity","Master"})
    void verifyLoginTest(){
        logger.info("===Starting Login test===");
        HomePage hp = new HomePage(driver);
        hp.clickAccount();
        hp.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        logger.info("===Enter Login details===");
        loginPage.enterEmail(p.getProperty("email"));
        loginPage.enterPwd(p.getProperty("password"));
        loginPage.clickLogin();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        Boolean status = myAccountPage.isMyAccountExists();
        Assert.assertEquals(status,true,"Login Failed");
        logger.info("===Finished Login test===");
    }
}
