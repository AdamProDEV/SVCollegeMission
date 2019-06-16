package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReviewYourOrderPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[text()=\"Complete order and begin installation\"]")
    private WebElement beginInstallButton;

    public ReviewYourOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AuthorizeApplicationPage completeAndInstall(){

        beginInstallButton.click();
        return new AuthorizeApplicationPage(driver);
    }

}

