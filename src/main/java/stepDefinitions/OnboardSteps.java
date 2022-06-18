package stepDefinitions;

import base.BaseMethods;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import pageObjects.OnboardPagesElements;

public class OnboardSteps extends BaseMethods {

    OnboardPagesElements onboard;

    public OnboardSteps(OnboardPagesElements elements) {

        PageFactory.initElements(new AppiumFieldDecorator(BaseSteps.getDriver()), OnboardPagesElements.class);
        onboard = elements;
    }
/*


    public OnboardSteps(AppiumDriver<MobileElement> appiumDriver, OnboardPagesElements elements) {
        super(appiumDriver);
        onboard=elements;
    }
*/



    @When("I skip tutorial screen for")
    public void clickPermissionAllowButton() {
        //click(onboard.btnPermission);
        System.out.println("asdfg");
    }

}
