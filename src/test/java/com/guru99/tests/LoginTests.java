package com.guru99.tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests{

    @Parameters({"username", "userPassword"})
    @Test
    public void verifyUserLoginWithValidCredentials(String username, String password){

        reportUtils.createTestCase("verify User Login With Valid Credentials");
        reportUtils.addTestLog(Status.INFO, "Performing Login");

        loginPage.loginToApplication(username,password);

        String expectedTitle = "Guru99 Bank Manager Homepage";
        String actualTitle = cmnDriver.getTitleOfThePage();

        reportUtils.addTestLog(Status.INFO, "Comparing expected and actual title");
        Assert.assertEquals(actualTitle, expectedTitle);

    }
}
