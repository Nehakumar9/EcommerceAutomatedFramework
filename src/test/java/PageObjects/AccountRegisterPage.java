package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage{

    public AccountRegisterPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstname;

    @FindBy(name = "lastname")
    WebElement lastName;

    @FindBy(css = "#input-email")
    WebElement email;

    @FindBy(id = "input-telephone")
    WebElement telephone;

    @FindBy(id = "input-password")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement conPwd;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continueBtn;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement privacy;

    public void clickPrivacy(){
        privacy.click();
    }

    public void setFirstname(String firstname1) {
        firstname.sendKeys(firstname1);
    }

    public void setLastName(String Lastname1) {
        lastName.sendKeys(Lastname1);
    }


    public void setEmail(String email1) {
        email.sendKeys(email1);
    }

    public void setTelephone(String num) {
        telephone.sendKeys(num);
    }


    public void setPassword(String pwd1) {
        password.sendKeys(pwd1);
    }

    public void setConPwd(String cpwd) {
        conPwd.sendKeys(cpwd);
    }

    public void clickBtn(){
        continueBtn.click();
    }

    public String getMessage(){
        try{
            return (msgConfirmation.getText());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
