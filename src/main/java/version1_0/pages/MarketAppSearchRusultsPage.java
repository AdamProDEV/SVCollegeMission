package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarketAppSearchRusultsPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@data-hydro-click][1]")
    private WebElement appLink;

    public MarketAppSearchRusultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void linkDriverToAppPadeDiscripstion() {

        appLink.click();
    }
}
