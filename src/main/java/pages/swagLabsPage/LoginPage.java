package pages.swagLabsPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Actions;

public class LoginPage extends Actions {
    AppiumDriver driver;
    public LoginPage(AppiumDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Username\"]")
    private WebElement username;
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
    private WebElement password;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")
    private WebElement login_button;

    public ProductsPage login(){
        sendKeysValue(username,"standard_user");
        sendKeysValue(password,"secret_sauce");
        clickElement(login_button);
        return new ProductsPage(driver);
    }
    public ProductsPage loginData(String userName, String passWord){
        sendKeysValue(username,userName);
        sendKeysValue(password,passWord);
        clickElement(login_button);
        return new ProductsPage(driver);
    }
}
