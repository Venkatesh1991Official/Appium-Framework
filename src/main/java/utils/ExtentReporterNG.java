package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    static ExtentSparkReporter sparkReporter;
    static ExtentReports report;
    public static ExtentReports getReports(){
        String path=System.getProperty("user.dir")+"//src//main//java//reports//report.html";
        sparkReporter=new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("Appium automation reports");
        report=new ExtentReports();
        report.attachReporter(sparkReporter);
        report.setSystemInfo("Tester","Kaushik");
        return report;
    }
}
