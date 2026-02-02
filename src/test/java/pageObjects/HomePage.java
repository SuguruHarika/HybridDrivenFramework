package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "My Account")
    private WebElement AccountOption;

    @FindBy(linkText = "Register")
    private WebElement registerOption;
    @FindBy(linkText = "Login")
    private WebElement loginOption;

    public void clickMyAccount() {
        AccountOption.click();
    }

    public void clickRegister() {
        registerOption.click();
    }
    public void clickLogin(){
        loginOption.click();
    }
}



