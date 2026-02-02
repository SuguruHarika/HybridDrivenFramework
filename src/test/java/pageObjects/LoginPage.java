package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="input-email")
    private WebElement txtEmail;
    @FindBy(id="input-password")
    private WebElement txtPassword;
    @FindBy(xpath = "//input[@class='btn btn-primary']")
    private WebElement btnLogin;

    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }
    public void clickLogin(){
        btnLogin.click();
    }
}
