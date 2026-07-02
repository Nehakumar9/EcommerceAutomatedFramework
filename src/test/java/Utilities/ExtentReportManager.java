package Utilities;

import TestBase.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.xml.transform.sax.SAXResult;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

        public ExtentSparkReporter sparkReporter;
        public ExtentReports extent;
        public ExtentTest test;

        String repName;

        @Override
        public void onStart(ITestContext testContext) {
            // Generate a unique timestamp for every test execution run
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            repName = "Test-Report-" + timeStamp + ".html";

            // Save report file into a folder named "reports" at the project root
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);

            sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); // Report Title
            sparkReporter.config().setReportName("OpenCart Functional Testing"); // Sub Heading
            sparkReporter.config().setTheme(Theme.DARK);

            // Attach environment metadata
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Application", "OpenCart");
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User", "Neha");

            String browser = testContext.getCurrentXmlTest().getParameter("browser");
            extent.setSystemInfo("Browser", browser);

            List<String> groups = testContext.getCurrentXmlTest().getIncludedGroups();
            if(!groups.isEmpty()){
                extent.setSystemInfo("Groups", groups.toString());
            }
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            test = extent.createTest(result.getName());
            test.assignCategory(result.getMethod().getGroups()); // Captures TestNG groups if used
            test.log(Status.PASS, "Test Passed");
        }

        @Override
        public void onTestFailure(ITestResult result) {
            test = extent.createTest(result.getName());
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.FAIL, "Test Failed");
            test.log(Status.FAIL, result.getThrowable().getMessage());// Logs the specific exception error message
            try {
                String screenshotPath = new BaseClass().captureScreen(result.getName());
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.log(Status.FAIL, "Failed to attach screenshot: " + e.getMessage());
            }
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            test = extent.createTest(result.getName());
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.SKIP, "Test Skipped");
            test.log(Status.SKIP, result.getThrowable().getMessage());
        }

        @Override
        public void onFinish(ITestContext testContext) {
            // Critical: Writes all recorded execution data out to the physical file
            if (extent != null) {
                extent.flush();
            }
        }
    }


