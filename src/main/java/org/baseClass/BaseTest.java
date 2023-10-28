package org.baseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public AndroidDriver driver;
    AppiumDriverLocalService service;
    public  DesiredCapabilities capabilities;

    public FileInputStream fis;
    public Properties prop;

    public void readConfigFile() throws IOException, IOException {
        fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//config.properties");
        prop=new Properties();
        prop.load(fis);
    }
    @BeforeSuite(alwaysRun = true)
    public void startAppiumServer(){
                service=new AppiumServiceBuilder().
                withAppiumJS(new File("C:\\Users\\Venkatesh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).
                withIPAddress("0.0.0.0").
                usingPort(4723).build();
        service.start();
        System.out.println("starting appium server");
    }
    @BeforeMethod(alwaysRun = true)
    @Parameters({"automationName","deviceName","udid"})
    public void startApp(String automationName,String deviceName,String udid) throws IOException {
        readConfigFile();
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability("appPackage",prop.getProperty("appPackage"));
        capabilities.setCapability("appActivity", prop.getProperty("appActivity"));
//        capabilities.setChromedriverExecutable(System.getProperty("user.dir")+"\\src\\main\\java\\utils\\chromedriver.exe");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

//    @AfterMethod(alwaysRun = true)
//    public void closeApp() throws InterruptedException {
//        Thread.sleep(4000);
//        driver.quit();
//    }
    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer(){
        service.stop();
    }
}
