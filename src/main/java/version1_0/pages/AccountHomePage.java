package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountHomePage {

    private WebDriver driver;

    @FindBy(xpath = "//summary[@aria-label=\"Create new…\"]")
    private WebElement buttonDropMenuCreateNew;

    @FindBy(xpath = "//details-menu/a[@data-ga-click=\"Header, create new repository\"]")
    private WebElement menuCreateNewRepo;

    @FindBy(xpath = "//a[@data-ga-click=\"Header, import a repository\"]")
    private WebElement menuImportRepo;

    @FindBy(xpath = "//a[@data-ga-click=\"Header, create new project\"]")
    private WebElement menuNewProject;

    @FindBy(xpath = "//a[contains(., \"Marketplace\")]")
    private WebElement marketPlaceLink;

    @FindBy(xpath = "//header[@role=\"banner\"]//summary[@aria-label=\"View profile and more\"]")
    private WebElement viewProfileAndMoreHeaderButton;
    @FindBy(xpath = "//summary[@aria-haspopup=\"dialog\"]//span")
    private WebElement profileStatusButtonInHeaderAccMenu;

    public AccountHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RepositoryCreationPage gotoNewRepository() {

        buttonDropMenuCreateNew.click();
        menuCreateNewRepo.click();
        return new RepositoryCreationPage(driver);
    }

    public ImportProjectPage gotoImportRepository() {

        buttonDropMenuCreateNew.click();
        menuImportRepo.click();
        return new ImportProjectPage(driver);
    }

    public NewProjectCreationPage gotoNewProject(){

        buttonDropMenuCreateNew.click();
        menuNewProject.click();
        return new NewProjectCreationPage(driver);
    }

    public MarketPlacePage openMarketPlace(){

        marketPlaceLink.click();
        return new MarketPlacePage(driver);
    }


    public ProfileStatusPopup openPopupStatusEdit() {

        viewProfileAndMoreHeaderButton.click();
        profileStatusButtonInHeaderAccMenu.click();
        return new ProfileStatusPopup(driver);
    }

    public String getStatusMessage() {

        driver.navigate().refresh();
        viewProfileAndMoreHeaderButton.click();
        return profileStatusButtonInHeaderAccMenu.getText();

    }
}
