package commonLibs.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;

public class ReportUtils {

    ExtentHtmlReporter htmlReport;

    ExtentReports extentReports;

    ExtentTest extentTest;

    public ReportUtils(String htmlReportFilename) {
        htmlReportFilename = htmlReportFilename.trim();

        htmlReport = new ExtentHtmlReporter(htmlReportFilename);
        extentReports = new ExtentReports();

        extentReports.attachReporter(htmlReport);
    }

    public void createTestCase(String testcaseName){

        extentTest = extentReports.createTest(testcaseName);

    }

    public void addTestLog(Status status, String comment){
        extentTest.log(status,comment);
    }

    public void attachScreenshotToReport(String filename) throws Exception {
        extentTest.addScreenCaptureFromPath(filename);
    }

    public void flushReport(){
        extentReports.flush();
    }
}
