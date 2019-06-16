package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImportProjectPage {

    private WebDriver driver;

    @FindBy(id = "vcs_url")
    WebElement repositoryLink;

    @FindBy(id = "repository_name")
    WebElement repositoryName;

    @FindBy(id = "repository_visibility_public")
    WebElement repository_visibility_public;

    @FindBy(id = "repository_visibility_private")
    WebElement repository_visibility_private;

    @FindBy(xpath = "//*[@id=\"new_repository\"]/div[4]/button")
    WebElement beginImportButton;



    public ImportProjectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PreparingNewRepositoryPage beginImport(String repoLink, boolean isPublic, String repoName) {

        repositoryLink.sendKeys(repoLink);
        if(!isPublic) repository_visibility_private.click();
        repositoryName.sendKeys(repoName);
        beginImportButton.click();
        return new PreparingNewRepositoryPage(driver);
    }
}
