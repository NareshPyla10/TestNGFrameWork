import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import fileReader.FileReaderManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import webDriverManager.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class TestNGListeners implements ITestListener {

    static ExtentTest test;
    static ExtentReports report;

    private static final Logger logger = LogManager.getLogger(TestNGListeners.class);
    private final String screenShotDirectory = System.getProperty("user.dir").concat(File.separator).concat("src").concat(File.separator).concat("testArtifacts").concat(File.separator).concat("screenShots");
    private final String extentReportDirectory = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "extentReports"
            + File.separator + "ExtentReport_" + getDateTime() + ".html";



    public void onStart(ITestContext context) {
        logger.info("This is a start method");

        WebDriverManager.getWebDriverManager().createDriver(
                FileReaderManager.getFileReaderManager().getPropertyFileReader().getProperty("browser")
        );

        ExtentSparkReporter spark = new ExtentSparkReporter(extentReportDirectory);

        // Load XML configuration file
        spark.loadXMLConfig(System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "test" + File.separator + "resources"
                + File.separator + "extent-config.xml");

        report = new ExtentReports();
        report.attachReporter(spark);
    }


    public void onTestStart(ITestResult result) {
        logger.info("This is a Test start method");
        logger.info("Test case name : " + result.getName());
        test = report.createTest(result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.info("This is a test failure method");

            byte[] screenshot = getWebScreenshotInByteArray();
            String screenshotPath = screenShotDirectory + File.separator + "Failure_" + getDateTime() + ".png";

            test.fail("Test Failed: " + result.getThrowable());

            try {
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e) {
                logger.error("Screenshot attach failed: " + e.getMessage());
            }
        }
    }

    public void onTestSkipped(ITestResult result) {
        if(result.getStatus() == ITestResult.SKIP){
            logger.info("The test is skipped");
        }
    }

    public void onFinish(ITestContext context) {
        logger.info("This is a finish method");
        report.flush();
        WebDriverManager.getWebDriverManager().quitDriver();
    }



    public byte[] getWebScreenshotInByteArray() {
        byte[] fileContent = new byte[0];
        try {
            // Capture screenshot and save it to a file
            File src = ((TakesScreenshot) WebDriverManager.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenShotDirectory  + getDateTime() + ".png");
            FileUtils.copyFile(src, destFile);

            // Read file content into byte array
            fileContent = FileUtils.readFileToByteArray(destFile);
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
        return fileContent;
    }

    protected String getDateTime() {
        return (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
    }

}
