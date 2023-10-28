package org.testCases;

import org.baseClass.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.swagLabsPage.LoginPage;

public class MultipleDataSetUsingDataProvider extends BaseTest {
    @Test(dataProvider ="testData",priority = 2,groups = {"Regression"})
    public void multipleData(String userName,String passWord){
        LoginPage page1=new LoginPage(driver);
        page1.loginData(userName,passWord).multiValiateLoginSuccess(userName);
    }


    @DataProvider(name="testData")
    public Object[][] fetchData(){
        return new Object[][] {
                               {"standard_user","secret_sauce"},
                               {"locked_out_user","secret_sauce"},
                               {"problem_user","secret_sauce"}
        };
    }
}
