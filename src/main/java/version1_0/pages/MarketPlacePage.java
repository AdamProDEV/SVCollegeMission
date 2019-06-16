package version1_0.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import version1_0.tools.FindingAppBySearchOrManual;
import version1_0.tools.MarketAppsCategoriesFilter;
import version1_0.tools.MarketAppsCostFilter;
import version1_0.tools.MarketAppsVerificationFilter;

public class MarketPlacePage {

    private WebDriver driver;
    private MarketAppSearchRusultsPage marketAppSearchRusultsPage;


//    @FindBy(xpath = "//input[@name=\"query\"]")
//    private WebElement seachInput;

    @FindBy(xpath = "//input[@aria-label=\"Search for apps\"]")
    private WebElement seachInput;

    @FindBy(xpath = "//a[text()=\"Explore apps\"]")
    private WebElement allAppsButton;

    public MarketPlacePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MarketAppDiscriptionPage findApp(String appName, FindingAppBySearchOrManual searchOrManual,
                                            MarketAppsCategoriesFilter deployment,
                                            MarketAppsCostFilter free,
                                            MarketAppsVerificationFilter verified) {

        switch (searchOrManual){
            case BY_SEARCH:{
                seachInput.sendKeys(appName);
                seachInput.sendKeys(Keys.ENTER);

                //switch driver from searach results to app page dicrisption
                marketAppSearchRusultsPage = new MarketAppSearchRusultsPage(driver);
                marketAppSearchRusultsPage.linkDriverToAppPadeDiscripstion();
                break;
            }
            case BY_MANUAL:
                //no code yet
                break;
        }


        return new MarketAppDiscriptionPage(driver);
    }
}
