package org.testCases;

import io.appium.java_client.AppiumDriver;
import org.baseClass.BaseTest;
import org.testng.annotations.Test;
import pages.swagLabsPage.LoginPage;

public class LoginScenarioValidation extends BaseTest {
    @Test(priority = 0,groups = {"Regression"})
    public void loginValidations(){
        LoginPage page1=new LoginPage(driver);
        page1.login().validateLoginSuccess();

    }
}
