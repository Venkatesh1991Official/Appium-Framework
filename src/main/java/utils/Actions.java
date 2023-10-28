package utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;

public class Actions {
   AppiumDriver driver;
    WebDriverWait wait;
    static File source;
    static String destinationName;
    public Actions(AppiumDriver driver) {
        this.driver=driver;
    }
    public void clickElement(WebElement ele){
        ele.click();
    }
    private boolean eleIsDisplayed(WebElement ele) {
        try {
            if (ele.isDisplayed())
                return false;
        } catch (Exception e) {
            return true;
        }
        return true;
    }
    public void scroll() {
        Dimension size = driver.manage().window().getSize();
        int x1 = (int) (size.getWidth() * 0.5);
        int y1 = (int) (size.getHeight() * 0.8);
        int x0 = (int) (size.getWidth() * 0.5);
        int y0 = (int) (size.getHeight() * 0.2);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 0);
        sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x1, y1));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), x0, y0));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }
    public void scrollActions(WebElement ele){
        while (eleIsDisplayed(ele)) {
            scroll();
        }
    }
    public void swipe(WebElement ele){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", "left",
                "percent", 0.75
        ));
    }
    public void swipeActions(WebElement ele){
        while (eleIsDisplayed(ele)){
            swipe(ele);
        }
    }
    public void sendKeysValue(WebElement ele, String data){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.clear();
        ele.sendKeys(data);
    }
    public static String takeScreenshots(String testCaseName, AppiumDriver driver) throws IOException {
        source=driver.getScreenshotAs(OutputType.FILE);
        destinationName=System.getProperty("user.dir")+"//src//main//java//screenshots//"+testCaseName+".jpeg";
        FileUtils.copyFile(source, new File(destinationName));
        return destinationName;
    }
}
