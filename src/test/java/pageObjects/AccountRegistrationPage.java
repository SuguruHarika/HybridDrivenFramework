package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstname;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastname;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPolicy;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;
    public void setFirstname(String fName){
        txtFirstname.sendKeys(fName);
    }
    public void setLastname(String lName){
        txtLastname.sendKeys(lName);
    }
    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void setTelephone(String tPhone){
        txtTelephone.sendKeys(tPhone);
    }
    public void setPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }
    public void setConfirmPwd(String cPwd){
        txtConfirmPassword.sendKeys(cPwd);
    }
    public void setPrivacyPolicy(){
        chkPolicy.click();
    } public void clickContinue(){
        btnContinue.click();
    }
    public String getConfirmationMsg() {
        try {
            return msgConfirmation.getText();
        } catch (Exception e) {
            return (e.getMessage());
        }


        }



}
