package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    private Driver() {
    }

    public static AppiumDriver<MobileElement> appiumDriver;
    public static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    public static AppiumDriverLocalService appiumService;

    public static AppiumDriver<MobileElement> get() throws MalformedURLException, InterruptedException {
        if ( appiumDriver== null) {
            String browser = ConfigReader.read("os");
            System.out.println("os = " + browser);


            switch (browser) {
                case "android":
                    appiumService = AppiumDriverLocalService.buildDefaultService();
                    appiumService.start();
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
                    desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "trendyol.com");
                    desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.trendyol.common.splash.impl.ui.SplashActivity");
                    appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
                    Thread.sleep(2000);
                    break;


                case "ios":
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 13");
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.0");
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.getir.ios.dev.v2");
                    desiredCapabilities.setCapability(IOSMobileCapabilityType.PROCESS_ARGUMENTS, "{\n" +
                            "    \"args\": [\n" +
                            "      \"getir-is-running-ui-tests\",\n" +
                            "      \"-AppleLanguages\",\n" +
                            "      \"(tr)\",\n" +
                            "      \"-AppleLocale\",\n" +
                            "      \"tr_TR\"\n" +
                            "    ]\n" +
                            "  }".toString());
                    appiumDriver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
                    break;

            }
        }

        return appiumDriver;
    }

    public static void closeDriver() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }

}
