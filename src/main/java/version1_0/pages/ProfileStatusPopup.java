package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileStatusPopup {

    private WebDriver driver;

    private final String formPopupXpath = "//details-menu/div[3]/div/details/details-dialog/form";
    @FindBy(xpath = formPopupXpath + "//input[@name=\"message\"]")
    private WebElement inputStatusMsg;
    @FindBy(xpath = formPopupXpath + "//button[contains(.,\"Set status\")]")
    private WebElement submitStatusButton;

    public ProfileStatusPopup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AccountHomePage setStatus(String statusMsg) {
        inputStatusMsg.clear();
        inputStatusMsg.sendKeys(statusMsg);
        submitStatusButton.click();
        return new AccountHomePage(driver);
    }
}
