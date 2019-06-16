package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarketAppDiscriptionPage {

    private WebDriver driver;

    @FindBy(xpath = ("//a[@href=\"#pricing-and-setup\"]"))
    private WebElement setUpPlanButton;

    @FindBy(xpath = "//button[contains(.,'Install it for free')]")
    WebElement installForFreeButton;

    @FindBy(xpath = "//div[@id =\"pricing-and-setup\"]//li[1]/a/div/h4")
    private WebElement appFirstPricePlan;

    public MarketAppDiscriptionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ReviewYourOrderPage installThisApp(){

        if(!appFirstPricePlan.isSelected()) {
            appFirstPricePlan.click();
        }

        if(installForFreeButton.getText().contains("Install it for free")){
            installForFreeButton.click();
            return new ReviewYourOrderPage(driver);
        }else {
            System.out.println("----For now only Free apps, other apps tests did not coded yet");
            return null;
        }


    }
}
