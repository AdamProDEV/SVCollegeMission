package version1_0.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;

public class RepositoryPage {

    private WebDriver driver;

    public RepositoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitlePage() {

        return driver.getTitle();
    }
}
