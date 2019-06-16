package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RepositoryCreationPage {

    private WebDriver driver;

    @FindBy(id = "repository_name")
    private WebElement input_repository_name;

    @FindBy(id = "repository_description")
    private WebElement input_repository_description;

    @FindBy(id = "repository_visibility_public")
    private WebElement radioBtn_repository_visibility_public;

    @FindBy(id = "repository_visibility_private")
    private WebElement radioBtn_repository_visibility_private;

    @FindBy(id = "repository_auto_init")
    private WebElement checkbox_init_with_readme;

    @FindBy(xpath = "//*[@id=\"new_repository\"]/div[3]/button")
    private WebElement btn_submit;


    public RepositoryCreationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public RepositoryPage createNewRepo(String repoName, String discription,
                                        boolean privateRepo, boolean readme, boolean gitignore, boolean license) {
        input_repository_name.sendKeys(repoName);
        input_repository_description.sendKeys(discription);
        selectRadioBtnRepositoryVisibilityPrivate(privateRepo);
        selectOptionInitWithReadme(readme);
        btn_submit.click();
        return new RepositoryPage(driver);
    }

    private void selectOptionInitWithReadme(boolean select){

        if(!checkbox_init_with_readme.isSelected() || select){
            checkbox_init_with_readme.click();
        } else if (checkbox_init_with_readme.isSelected() || !select){
            checkbox_init_with_readme.click();
        }
    }

    private void selectRadioBtnRepositoryVisibilityPrivate(boolean select){

        if(!radioBtn_repository_visibility_public.isSelected() || select){
            radioBtn_repository_visibility_public.click();
        } else if (!radioBtn_repository_visibility_private.isSelected() || !select){
            radioBtn_repository_visibility_private.click();
        }
    }
}
