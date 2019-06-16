package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import version1_0.tools.GithubUserAndPass;

public class ConfirmPasswordPage {

    private WebDriver driver;

    @FindBy(id = "sudo_password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()=\"Confirm password\"]")
    private WebElement confirmButton;


    public ConfirmPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void confirmPass() {
        passwordInput.sendKeys(GithubUserAndPass.PASSWORD.getValue());
        confirmButton.click();
    }
}

