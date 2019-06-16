package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PreparingNewRepositoryPage {

    private WebDriver driver;

    public PreparingNewRepositoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
