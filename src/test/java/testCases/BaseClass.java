package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    //earlier these all methods we created in account registration test class
    // but for maintenance and mulipurpose we created this class and written here
    public static WebDriver driver;
    public Logger logger;
    Properties prop;
    //@BeforeMethod(groups = "Regression")
    @BeforeMethod(groups = {"Sanity", "Master", "Regression", "DataDriven"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String br) throws IOException {

        FileInputStream fis = new FileInputStream("./src//test//resources//config.properties");
        prop = new Properties();
        prop.load(fis);            //PROPERTIES FILE

        logger = LogManager.getLogger(this.getClass()); //LOG4J2
        //instead of below line we are writing this for cross browser testing
        // for this we are passing parameters from testng.xml file so that we can simply modify that in which browser we want to run
        //WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();
        switch (br.toLowerCase()) {      //PARAMETERIZED BROWSER
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().forceDownload().setup();
                //WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("invalid browser name");
                return;

        }
        // no need to write code after default, but we are written becoz if given browser is invalid then
        //the sout will execute and return will exit from execution and stops further testing,
        // incase given browser is invalid then we no need to execute further steps so return will exit from execution
        driver.get(prop.getProperty("AppUrl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
//@AfterMethod(groups = "Regression")
    @AfterMethod(groups = {"Sanity", "Master", "Regression", "DataDriven"})
    public void tearDown() {
        driver.quit();
    }

    public String randomString() {//this method for generating randomstring
        String rString = RandomStringUtils.randomAlphabetic(6);
        return rString;
    }

    public String randomNumber() {//this method for generating randomnumber
        String rNumber = RandomStringUtils.randomAlphabetic(10);
        return rNumber;
    }

    public String randomAlphaNumber() {//this method for generating random combination of string and numeric
        String rString = RandomStringUtils.randomAlphabetic(3);
        String rNumber = RandomStringUtils.randomAlphabetic(3);

        return rString + "@" + rNumber;
    }

    public String captureScreeShot(String testCaseName) throws IOException {
        String timeStamp =  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

//        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//         FileUtils.copyFile(src,new File(System.getProperty("user.dir")+"\\screenshots\\" + testCaseName + "_" +timeStamp));


       TakesScreenshot takesScreenShot = (TakesScreenshot) driver;
       File sourceFile = takesScreenShot.getScreenshotAs(OutputType.FILE);
        String targetFilepath = System.getProperty("user.dir")+"\\screenshots\\" + testCaseName + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilepath);
        sourceFile.renameTo(targetFile);

        return targetFilepath;


    }
}
