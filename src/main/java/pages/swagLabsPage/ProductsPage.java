package pages.swagLabsPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Actions;

public class ProductsPage extends Actions {
    AppiumDriver driver;
    public ProductsPage(AppiumDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    private WebElement products_validation;

    @AndroidFindBy(xpath = "//*[@text='Test.allTheThings() T-Shirt (Red)']")
    private WebElement tShirtProduct;
    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]")
    private WebElement addToCart;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]")
    private WebElement cartButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    private WebElement loginErrorMessage;

    public ProductsPage validateLoginSuccess(){
        Assert.assertTrue(products_validation.isDisplayed());
        return this;
    }
    public void multiValiateLoginSuccess(String user){
        if(user.equalsIgnoreCase("standard_user")){
            Assert.assertTrue(products_validation.isDisplayed());
        } else if (user.equalsIgnoreCase("locked_out_user")) {
//            System.out.println(loginErrorMessage.getAttribute("text"));
            Assert.assertEquals(loginErrorMessage.getAttribute("text"),"Sorry, this user has been locked out.");
        } else if (user.equalsIgnoreCase("problem_user")) {
            Assert.assertTrue(products_validation.isDisplayed());
        }else {
            System.out.println("no error message validated");
        }
    }
    public ProductsPage scrollToProductAndClick(){
        scrollActions(tShirtProduct);
        if(tShirtProduct.isDisplayed()){
            clickElement(tShirtProduct);
        }
        return this;
    }
    public ProductsPage addToCart(){
        scrollActions(addToCart);
        if(cartButton.isDisplayed()){
            clickElement(addToCart);
        }
        return this;
    }
    public void clickCartButton(){
        clickElement(cartButton);
    }
}
