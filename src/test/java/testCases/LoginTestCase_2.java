package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class LoginTestCase_2 extends BaseClass{
    //@Test(groups = "Sanity")
    @Test(groups = {"Sanity", "Master"})
    public void verify_login() {

        logger.info("****LoginTestCase_2 execution started");

        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();

        LoginPage lp = new LoginPage(driver);
        lp.setEmail(prop.getProperty("Email"));
        lp.setPassword(prop.getProperty("password"));
        lp.clickLogin();

        MyAccountPage mAcc = new MyAccountPage(driver);
        boolean targetPage = mAcc.isMyAccountPageExists();

        Assert.assertTrue(targetPage);
        logger.info("****LoginTestCase_2 execution finished");

    }


}
