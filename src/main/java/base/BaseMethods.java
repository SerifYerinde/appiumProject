package base;

import com.opencsv.CSVWriter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.BaseSteps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BaseMethods {
    protected static String folderName;
    private AppiumDriver<MobileElement> appiumDriver = BaseSteps.getDriver();
/*
    public BaseMethods(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(20)), this);
        appiumDriver = driver;
    }
 */
    protected void click(MobileElement element) {
        try {
             WebDriverWait wait = new WebDriverWait(appiumDriver, 50);
            wait.until(ExpectedConditions.visibilityOf(element)).click();

        } catch (Exception e) {
            takeScreenshot();
            e.printStackTrace();
        }
    }
    protected void sendKeys(MobileElement element, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, 50);
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);


        } catch (Exception e) {
            takeScreenshot();
            e.printStackTrace();
        }
    }

    protected String getText(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, 50);
            return wait.until(ExpectedConditions.visibilityOf(element)).getText();

        } catch (Exception e) {
            takeScreenshot();
            e.printStackTrace();
            return null;
        }
    }

    protected void clearText(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, 50);
            wait.until(ExpectedConditions.visibilityOf(element)).clear();

        } catch (Exception e) {
            takeScreenshot();
            e.printStackTrace();
        }
    }

    protected boolean isDisplayed(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, 50);
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

        } catch (Exception e) {
            takeScreenshot();
            e.printStackTrace();
            return false;
        }
    }


    protected void takeScreenshot() {
        try {
            File scrFile = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
            String _path =
                    "exportFile/screenshot/"
                            + getFolderName()
                            + "-"
                            + get_currentTime()
                            + "-"
                            + get_randomNumber()
                            + ".png";
            FileUtils.copyFile(scrFile, new File(_path));
        } catch (IOException e) {
            System.out.println("ScreenShot fail." + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void writeCsvFile(List<String[]> list, String path) {
        try {
            String _path = "exportFile/" + path;
            String _path2 = _path + "/" + getFolderName() + "-" + get_currentTime() + "-" + get_randomNumber() + ".csv";
            Boolean result = new File(_path).mkdirs();
            System.out.println(result);
            CSVWriter writer = new CSVWriter(new FileWriter(_path2), ';', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(list);
            writer.close();
        } catch (IOException e) {
            System.out.println("ScreenShot fail." + e.getMessage());
            takeScreenshot();
            e.printStackTrace();
        }
    }

    public String getFolderName() {
        return folderName;
    }

    public String get_currentTime() {
        String pattern = "yyyy-MM-dd-hh-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public String get_randomNumber() {
        return UUID.randomUUID().toString();
    }
}
