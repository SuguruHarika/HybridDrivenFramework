package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class AccountRegistrationTestCase_1 extends BaseClass {

    //sapce is imp in between 2 tags means in between master and regression if space is there code will not work
   @Test(groups = {"Master", "Regression"})
    public void verify_account_registration() throws InterruptedException {
        logger.info("****AccountRegistration testcase started****** ");
        HomePage hPage = new HomePage(driver);
        hPage.clickMyAccount();
        logger.info("clicked on my account option");

        hPage.clickRegister();
        logger.info("clicked on register option");

        logger.info("-----providing customer details------");
        AccountRegistrationPage rPage = new AccountRegistrationPage(driver);
        //instead of creating hardcoding data below we generated random string and numbers using java metho
        //       rPage.setFirstname("harika");
//        rPage.setLastname("suguru");
//        //rPage.setEmail("sddwqwqqqqqqqqqdq12d@gmail.com");
//        rPage.setTelephone("2133232323");
//        rPage.setPassword("abc1234");
//        rPage.setConfirmPwd("abc1234");
        rPage.setFirstname(randomString().toUpperCase());
        rPage.setLastname(randomString().toUpperCase());
        rPage.setEmail(randomString()+"@gmail.com");
        rPage.setTelephone(randomNumber());
        String passWord = randomAlphaNumber();

        rPage.setPassword(passWord);
        rPage.setConfirmPwd(passWord);
        rPage.setPrivacyPolicy();
        rPage.clickContinue();

        logger.info("validating expected data");
        String confirmMsg = rPage.getConfirmationMsg();
        Assert.assertEquals(confirmMsg,"Your Account Has Been Created!");

        logger.info("****AccountRegistration testcase execution finished****** ");
    }

}
