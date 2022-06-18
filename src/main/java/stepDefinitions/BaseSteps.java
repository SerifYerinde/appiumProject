package stepDefinitions;

import base.BaseMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.net.MalformedURLException;

public class BaseSteps extends BaseMethods {

    /*
    public BaseSteps(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }
*/
    public static AppiumDriver<MobileElement> appiumDriver;
    public WebDriverWait wait;

    @Before()
    public void setUp(Scenario scenario) throws MalformedURLException, InterruptedException {
        BaseMethods.folderName = scenario.getName();
        appiumDriver = Driver.get();
        wait = new WebDriverWait(appiumDriver, 10);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {

            final byte[] screenshot = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);
            String _path =
                    "exportFile/screenshot/"
                            + getFolderName()
                            + "-"
                            + get_currentTime()
                            + "-"
                            + get_randomNumber()
                            + ".png";
            scenario.attach(screenshot, _path, scenario.getName());
            System.out.println("ScreenShot fail.");
        }
        Driver.closeDriver();
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return appiumDriver;
    }
}