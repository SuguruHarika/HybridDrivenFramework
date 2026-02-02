package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class LoginDDT_Testcase extends BaseClass {
    @Test(dataProvider = "loginData",dataProviderClass = DataProviders.class,groups = "DataDriven")
    public void verifyLoginData(String email, String password, String expectedResult){

        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLogin();

        LoginPage lp = new LoginPage(driver);
        lp.setEmail(email);
        lp.setPassword(password);
        lp.clickLogin();

        MyAccountPage mAcc = new MyAccountPage(driver);
        boolean targetPage = mAcc.isMyAccountPageExists();

        if(expectedResult.equalsIgnoreCase("valid")) {
            if (targetPage == true) {
                mAcc.clickLogout();
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);

            }
        }
        if (expectedResult.equalsIgnoreCase("invalid")){
            if (targetPage == true) {
                mAcc.clickLogout();
                Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);

            }
        }



    }
}
