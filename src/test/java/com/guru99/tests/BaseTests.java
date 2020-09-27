package com.guru99.tests;

import com.aventstack.extentreports.Status;
import com.guru99.pages.LoginPage;
import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigUtils;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.TestResult;

import java.util.Properties;

public class BaseTests {

    CommonDriver cmnDriver;

    String url;

    WebDriver driver;

    LoginPage loginPage;

    String configFileName;

    String currentWorkingDirectory;

    Properties configProperty;

    String reportFilename;
    ReportUtils reportUtils;

    ScreenshotUtils screenshot;


    @BeforeSuite
    public void preSetup() throws Exception {
        currentWorkingDirectory = System.getProperty("user.dir");

        configFileName = currentWorkingDirectory+"/config/config.properties";

        reportFilename = currentWorkingDirectory+ "/reports/guru99TestReport.html";

        configProperty = ConfigUtils.readProperties(configFileName);

        reportUtils = new ReportUtils(reportFilename);

    }


    @BeforeClass
    public void setup() throws Exception {


        url = configProperty.getProperty("baseUrl");

        String browserType = configProperty.getProperty("browserType");

        cmnDriver = new CommonDriver(browserType);

        driver = cmnDriver.getDriver();

        loginPage = new LoginPage(driver);

        screenshot = new ScreenshotUtils(driver);

        cmnDriver.navigateToUrl(url);

    }

    @AfterMethod
    public void postTestAction(ITestResult result) throws Exception {

        String testCaseName = result.getName();
        long executionTime = System.currentTimeMillis();

        String screenshotFilename = currentWorkingDirectory+"/screenshots/"+testCaseName + executionTime +".jpeg";

        if (result.getStatus() == ITestResult.FAILURE){
            reportUtils.addTestLog(Status.FAIL, "One or more steps failed");

            screenshot.captureAndSaveScreenshot(screenshotFilename);

            reportUtils.attachScreenshotToReport(screenshotFilename);
        }

    }

    @AfterClass
    public void tearDown(){

        cmnDriver.closeAllBrowser();

    }

    @AfterSuite
    public void postTearDown(){

        reportUtils.flushReport();

    }
}

