package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    ExtentReports extent=ExtentReporterNG.getReports();
    ExtentTest test;
    AppiumDriver driver;
    @Override
    public void onTestStart(ITestResult result) {
        test=extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        try{
            driver=(AppiumDriver) result.getTestClass().
                                         getRealClass().
                                         getField("driver").
                                         get(result.getInstance());
        }catch (Exception ae){
            ae.printStackTrace();
        }
        try {
            test.addScreenCaptureFromPath(Actions.takeScreenshots(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
        }catch (Exception ae){
            ae.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
