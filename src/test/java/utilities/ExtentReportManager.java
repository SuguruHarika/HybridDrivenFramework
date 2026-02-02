package utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testCases.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    String reportName;
    ExtentSparkReporter sparkReporter;
    ExtentReports reports;
    ExtentTest test;

    public void onStart(ITestContext context) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//        Date date = new Date();
//        dateFormat.format(date);
// we can use above 3 lines of code or below 1 line of code both are same

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\ " + reportName);

        sparkReporter.config().setDocumentTitle("TutorialNinja Automation Report");
        sparkReporter.config().setReportName("TutorialNinja Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Application", "TutorialNinja");
        reports.setSystemInfo("Module", "Amin");
        reports.setSystemInfo("SubModule", "Customer");
        reports.setSystemInfo("UserName", System.getProperty("user.name"));//this user.name will give laptop user name
        reports.setSystemInfo("Environment", "QA");

        //getCurrentXmlTest method will return the xml file from whichever xml file we have run that xml will return
        String os = context.getCurrentXmlTest().getParameter("os");
        reports.setSystemInfo("Operating System", os);

        String browser = context.getCurrentXmlTest().getParameter("browser");
        reports.setSystemInfo("Browser", browser);

        // this below code for getting group names which are used in xml file for running code ex sanity master regression etc,
        // these groups names will display name in reports, this code will work when we used groups for excuting if we didnt run code using groups these code will not excute
        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            reports.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test = reports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to disply groups in report
        test.log(Status.PASS, result.getName() + " got successfully executed");

    }

    public void onTestFailure(ITestResult result) {
        test = reports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to disply groups in report

        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            //here we created object for base class so here created another driver, in baseclass already one driver was created,
            // we need to make base class driver as static,then only both driver will same code will run without error
            String imgPath = new BaseClass().captureScreeShot(result.getName());
            test.addScreenCaptureFromPath(imgPath); //this addScreenCaptureFromPath from extent reports and used to attach the screenshot to the extent report

        } catch (IOException e) {
            e.printStackTrace(); // this method display the exception message
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = reports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to disply groups in report

        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());

    }

    public void onFinish(ITestContext context) {
        reports.flush();

        //these below code for automatically open the report,
        // if testcase executed then report will open automatically after execution without any manual intervention

        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //these below code is for sending extent report to the someone automatically whenever report got generated without any manual intervention,
        // and this code is for only gmail account and only run these 1 to 2 time if we run more times java will block our gmail account in real projects there no problem

//        try {
//            URL url = new URL("file:///" + System.getProperty("user.dir") +"\\reports\\"+ reportName);
//
//            // Create the email message
//            ImageHtmlEmail email = new ImageHtmlEmail();
//            email.setDataSourceResolver(new DataSourceUrlResolver(url));
//            email.setHostName("smtp.googlemail.com");
//            email.setSmtpPort(465);
//            email.setAuthenticator(
//                    new DefaultAuthenticator("pavanoltraining@gmail.com", "password")
//            );
//            email.setSSLOnConnect(true);
//            email.setFrom("pavanoltraining@gmail.com"); // Sender
//            email.setSubject("Test Results");
//            email.setMsg("Please find Attached Report...");
//            email.addTo("pavankumar.busyqa@gmail.com"); // Receiver
//            email.attach(url, "extent report", "please check report...");
//            email.send(); // send the email
//        } catch (
//                Exception e) {
//            e.printStackTrace();
//        }


    }
}
