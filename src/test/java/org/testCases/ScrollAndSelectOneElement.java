package org.testCases;

import org.baseClass.BaseTest;
import org.testng.annotations.Test;
import pages.swagLabsPage.LoginPage;

public class ScrollAndSelectOneElement extends BaseTest {
    @Test(priority = 1,groups = {"Regression"})
    public void selectOneElement(){
        LoginPage page1=new LoginPage(driver);
        page1.login().scrollToProductAndClick().addToCart();
    }
}
