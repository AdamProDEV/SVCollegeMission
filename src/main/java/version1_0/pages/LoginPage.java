package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;
    @FindBy(xpath = "//input[@name = \"login\"]")
    private WebElement username;

    @FindBy(xpath = "//input[@name = \"password\"]")
    private WebElement password;

    @FindBy(xpath = "//input[@name = \"commit\"]")
    private WebElement signinButton;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AccountHomePage loginIntoAcoount(String user, String pass) {

        username.sendKeys(user);
        password.sendKeys(pass);
        signinButton.click();
        return new AccountHomePage(driver);
    }
}
