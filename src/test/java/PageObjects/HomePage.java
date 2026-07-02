package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[@title='My Account']")
    WebElement account;

    @FindBy(linkText = "Register")
    WebElement register;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement loginLink;


    public void clickAccount(){
        account.click();
    }
    public void clickRegister(){
        register.click();
    }
    public void clickLogin(){
        loginLink.click();
    }
}
