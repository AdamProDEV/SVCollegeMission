package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import version1_0.tools.FlagRegularInstalledOrAuthoirized;

public class AuthorizeApplicationPage {

    private WebDriver driver;
    private ConfirmPasswordPage confirmPasswordPage;

    @FindBy(xpath = "//button[contains(.,\"Authorize\")]")
    private WebElement authorizeButton;

    @FindBy(xpath = "//button[contains(.,\"Install\")]")
    private WebElement installButton;

    public AuthorizeApplicationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public AccountHomePage installOrAuthorizeThisApp(){
        if(driver.getTitle().contains("Authorize")){ //checking if this authorized app
            if(!authorizeButton.isEnabled()) { //have some problems with clicking, need to wait
               installOrAuthorizeThisApp(); //recursion
            }else{
                authorizeButton.click();
                FlagRegularInstalledOrAuthoirized.setIsAuthoirized(true); //Flag to know if this app with authorization
                if(driver.getTitle().contains("Confirm password")){ //Some times need enter password again
                    confirmPasswordPage = new ConfirmPasswordPage(driver);
                    confirmPasswordPage.confirmPass();
                }
            }
        }else if(driver.getTitle().contains("Installing")){ //checking if this regular installation app
            installButton.click();
        }
        return new AccountHomePage(driver);
    }

}
