package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement pwd;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginBtn;

    public void enterEmail(String emailid){
        email.sendKeys(emailid);
    }

    public void enterPwd(String pwds){
        pwd.sendKeys(pwds);
    }

    public void clickLogin() {
        loginBtn.click();
    }
}

