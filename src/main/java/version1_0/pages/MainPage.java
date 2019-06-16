package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@href =\"/login\"]")
    private WebElement signinButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage pressSignInButton() {

        signinButton.click();
        return new LoginPage(driver);
    }
}
