package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewProjectCreationPage {

    private WebDriver driver;

    @FindBy(id = "project_name")
    WebElement projectName;

    @FindBy(id = "project_body")
    WebElement projectDiscription;

    @FindBy(xpath = "//form/div[1]/details/summary")
    WebElement projectTemplateDropmenu;

    @FindBy(xpath = "//*[@id = \"project_template_NONE\"]/parent::label")
    WebElement projectTemplateNONE;

    @FindBy(id = "project_public_true")
    WebElement visibilityPublic;

    @FindBy(id = "project_public_false")
    WebElement visibilityPrivate;

    @FindBy(xpath = "//strong[text() = \"Linked repositories\"]/../..//input")
    WebElement linkedRepositories;

    @FindBy(xpath = "//form/div[4]/button")
    WebElement createProjectButton;


    public NewProjectCreationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProjectPage createNewProject(String projectBoardName, String projectDiscriptionText,
                                 String projectTemplate, String visibility, String link) {
        projectName.sendKeys(projectBoardName);
        projectDiscription.sendKeys(projectDiscriptionText);
        projectTemplateDropmenu.click();
        switch (projectTemplate){
            case "None":{
                projectTemplateNONE.click();
                break;
            }
            default:{break;}
        }
        switch (visibility){
            case "private":{
                visibilityPrivate.click();
                break;
            }
            case "public":{
                visibilityPublic.click();
                break;
            }
            default:{break;}
        }
        linkedRepositories.sendKeys(link);
        createProjectButton.click();

        return new ProjectPage(driver);

    }
}
