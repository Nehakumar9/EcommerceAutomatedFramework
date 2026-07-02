package TestCases;

import PageObjects.AccountRegisterPage;
import PageObjects.HomePage;
import TestBase.BaseClass;
import com.beust.ah.A;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

public class AccountRegisterTest extends BaseClass {
    @Test(groups = {"Regression","Master"})
    public void verifyRegisterTest() {
        try {
            logger.info("===Starting Register test===");
            HomePage hm = new HomePage(driver);
            hm.clickAccount();
            hm.clickRegister();

            logger.info("===Entering details for registration===");
            AccountRegisterPage reg = new AccountRegisterPage(driver);
            reg.setFirstname(randomString().toUpperCase());
            reg.setLastName(randomString().toUpperCase());
            reg.setEmail(randomString() + "@gmail.com");
            reg.setTelephone(randomNumber());

            String pwd = randomAlphaNumeric();
            reg.setPassword(pwd);
            reg.setConPwd(pwd);
            reg.clickPrivacy();
            reg.clickBtn();

            logger.info("===Validation of registration===");
            String congmsg = reg.getMessage();
            if(congmsg.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            }
            else{
                logger.error("===Test Failed===");
                Assert.assertTrue(false);
            }
        }
        catch (Exception e){
            Assert.fail();
        }
        logger.info("===Registration Completed===");
    }
}
