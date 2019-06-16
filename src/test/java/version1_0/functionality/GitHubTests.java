package version1_0.functionality;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import version1_0.pages.*;
import version1_0.tools.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class GitHubTests {

    private WebDriver driver;

    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountHomePage accountHomePage;

    private RepositoryCreationPage repoCreationPage;
    private RepositoryPage repoPage;

    private ImportProjectPage importProjPage;
    private PreparingNewRepositoryPage prepareNewRepo;

    private NewProjectCreationPage newProjectCreation;
    private ProjectPage projectPage;

    private MarketPlacePage marketPlacePage;
    private MarketAppDiscriptionPage marketAppDiscriptionPage;
    private AuthorizeApplicationPage authorizeApplicationPage;

    private ProfileStatusPopup profileStatusPopup;


    @Before
    public void setUp(){

        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://github.com");

    }
    @After
    public void tearDown(){
        driver.close();
    }


    /* browserChoosing() to read from console which WebDriver to implement,
    but this function not in use, because I didn't find way where to place this function.
    * */
    private void browserChoosing(){
        System.out.println("Enter: \"1\" - For Chrome browser testing\n" +
                "Enter: \"2\" - For Firefox browser testing\n" +
                "Enter: \"3\" - For Exit\n" +
                "Your enter here:\n");
        String browser = (new Scanner(System.in)).nextLine();

        switch (browser){
            case "1": driver = new ChromeDriver();
            case "2": driver = new FirefoxDriver();
            //case "3": throw new RuntimeException();
            default: {
                System.err.print(" Wrong input.");
                System.out.println("PLease enter number from 1 to 3.");
                browserChoosing();
            }
        }
    }

    /*Logging to gitHub function*/
    private void loginGitHub(String username, String password){

        mainPage = new MainPage(driver);
        loginPage = mainPage.pressSignInButton();
        accountHomePage =  loginPage.loginIntoAcoount(username, password);
    }

    /* ---Sanity test
     * Create a Repository from "+" drop menu*/
    @Test
    public void createRepositoryTest(){

        loginGitHub(GithubUserAndPass.USERNAME.getValue(), GithubUserAndPass.PASSWORD.getValue());

        repoCreationPage = accountHomePage.gotoNewRepository();
        //args name for repo, discription, public?, readme?, .gitignore?, License?
        repoPage = repoCreationPage.createNewRepo("Hello-Word", "Create repo sanity test",
                false, true, false, false);
        Assert.assertTrue((repoPage.getTitlePage()).contains("Hello-Word"));
    }

    /* ---Test 1---
     Import repository test from "+" drop menu
    repoURL "https://github.com/daniel577/test-svcollege"
    repoName "test-svcollege"
     */
    @Test
    public void importProjectTest(){

        loginGitHub(GithubUserAndPass.USERNAME.getValue(), GithubUserAndPass.PASSWORD.getValue());

        importProjPage = accountHomePage.gotoImportRepository();
        prepareNewRepo = importProjPage.beginImport("https://github.com/daniel577/test-svcollege", true, "test-svcollege");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("importing"));

    }

    /* ---Test 2---
     * New project creation test from "+" drop menu */
    @Test
    public void newProjectTest(){

        String projectBoardName = "Hello word";
        loginGitHub(GithubUserAndPass.USERNAME.getValue(), GithubUserAndPass.PASSWORD.getValue());
        newProjectCreation = accountHomePage.gotoNewProject();
        /*Args: Project board name, discription, project template, visibility, linked repositories*/
        projectPage = newProjectCreation.createNewProject(projectBoardName, "this is Hello Word discrioption",
                "None" ,"private", "link");
        Assert.assertTrue(driver.getTitle().contains(projectBoardName));
    }

    /*---Text 3
    *Set account status
    */
    @Test
    public void setAccountStatus(){
        String statusMsg = "Well done";
        loginGitHub(GithubUserAndPass.USERNAME.getValue(), GithubUserAndPass.PASSWORD.getValue());
        profileStatusPopup = accountHomePage.openPopupStatusEdit();
        accountHomePage =  profileStatusPopup.setStatus(statusMsg);
        Assert.assertTrue(statusMsg.equals(accountHomePage.getStatusMessage()));


    }

    /* ---Test 4---
    * Install only free Apps
    * Steps:
    * 1. Login to account home page
    * 2. Go to market place via header link
    * 3. Find app by name. After search, we have search page class in findApp() to click/return specified app page what we need
    * 4. On description app page click on free plan(if not clicked) then click on install
    * 5. On review order page click complete install
    * 6. Here we have two options regular install or install via authorization, installOrAuthorizeThisApp() can check it.
    * and some time need to enter password so inside we have password page class.
    * 7. After all install we return to Home page.
    * 8. Checking if the app in account's installed apps list and/or in authorization apps list.
    * */
    @Test
    public void instalLAppFromMarketplace(){

        //--authorized app
        String appName = "Better Code Hub";
        //--regular app
        //String appName = "Azure Boards";
        loginGitHub(GithubUserAndPass.USERNAME.getValue(), GithubUserAndPass.PASSWORD.getValue());
        MarketPlacePage marketPlacePage = accountHomePage.openMarketPlace();
        marketAppDiscriptionPage = marketPlacePage.findApp(appName, FindingAppBySearchOrManual.BY_SEARCH, MarketAppsCategoriesFilter.DEPLOYMENT,
                MarketAppsCostFilter.FREE, MarketAppsVerificationFilter.VERIFIED);
        ReviewYourOrderPage reviewYourOrderPage = marketAppDiscriptionPage.installThisApp();
        authorizeApplicationPage = reviewYourOrderPage.completeAndInstall();
        accountHomePage = authorizeApplicationPage.installOrAuthorizeThisApp();

        Assert.assertTrue(VerifyTheAppInstalledProperly(appName));
    }

    /*Creating List() with all installed apps and checking if have our app name.
    * After if this app also authorized we check if this app in authorized list apps
    * */
    private boolean VerifyTheAppInstalledProperly(String appName){
        Boolean result = false;
        By appInstalled = By.xpath("//strong[@class =\"d-block\"]"); //one installed app block
        By appAuthorized = By.xpath("//a[@class=\"developer-app-name\"]"); //one authorized block
        List<WebElement> installedApps;
        List<WebElement> authorizedApps;

        driver.get("https://github.com/settings/billing");
        installedApps = driver.findElements(appInstalled);
        System.out.println("InstalledApps: " + installedApps.size());

        for(WebElement element:installedApps){
            if (element.getText().equals(appName)) {
                System.out.println("Installed name: "+ element.getText() );
                result = true;
                break;
            }
        }
        if(FlagRegularInstalledOrAuthoirized.isAuthoirized()){
            driver.get("https://github.com/settings/applications");
            authorizedApps = driver.findElements(appAuthorized);
            System.out.println("Authorized size: " + authorizedApps.size());

            for(WebElement element:authorizedApps){
                if (element.getText().equals(appName)) {
                    System.out.println("Authorized name: "+ element.getText() );
                    result = true;
                    break;
                }
            }
        }
        return result;
    }


    /* - ErrorHandeling test - 1. To pass a test we need to see div with error message of incorrect inputs
    * Steps:
    * Try login with incorrect inputs
    * if driver will find div with error message so the test will PASS
    * if driver will throws exception thats means we login  successful and Fail this test*/
    @Test
    public void verifyWrongloginErrorHandelingTest(){

        boolean pass = false;
        try {
            loginGitHub("user", "123");
            WebElement errorDiv = driver.findElement(By.xpath("//*[@id=\"js-flash-container\"]/div/div"));
            if(errorDiv.getText().contains("Incorrect username or password.")) pass =true;
        }catch (NoSuchElementException ex){
            System.out.println("---Incorrect username or password. Please check your enter");
            pass = false;
        }
        Assert.assertTrue(pass);

    }

    /*--ErrorHandeling test - 2. To pass a test we need to see div with error message of incorrect repo name
    * Steps:
    * Login
    * Navigate to repo creation page
    * Enter already repo name
    * if driver will find div with error message so the test PASS
     * if driver will throws exception thats means we successful created repo and Fail.
     * */
    @Test
    public void verifyWrongRepoNameOnNewCreationErrorHandelingTest(){
        boolean pass = false;
        String urlRepoCreationPage = "https://github.com/new";
        String existsRepoName = "Hello-Word";
        loginGitHub(GithubUserAndPass.USERNAME.getValue(), GithubUserAndPass.PASSWORD.getValue());
        driver.navigate().to(urlRepoCreationPage);
        (new RepositoryCreationPage(driver)).createNewRepo(existsRepoName, "Create repo sanity test",
                false, true, false, false);
        try{
            WebElement errorDiv = driver.findElement(By.xpath("//*[@id=\"new_repository\"]//auto-check/dl/dd[2]"));
            if(errorDiv.getText().contains("already exists on this account")) pass = true;
        }catch (NoSuchElementException ex){
            System.out.println("----Not found element or this repo name is allowed, please enter already exists name.");
        }
        Assert.assertTrue(pass);

    }

    /*--ErrorHandeling test - 3. To pass a test we need to see div with already purchased message
     * Steps:
     * Login
     * Navigate to already installed app page discription
     * if driver will find div with already purchased message so the test PASS
     * else throws exception thats means we didnt installed this app before.
     * PreCondition, https://github.com/marketplace/better-code-hub app installed
     * */
    @Test
    public void verifyInstallPurchasedAppErrorHandling(){
        boolean pass = false;
        String freePlanOptionXpath = "//div[@id =\"pricing-and-setup\"]//li[1]/a/div/h4";

        String XPath = "//p[text() =(\"You’ve already purchased this on all of your GitHub accounts.\")]";
        String linkToAlreadyPurchasedApp = "https://github.com/marketplace/better-code-hub";
        loginGitHub(GithubUserAndPass.USERNAME.getValue(), GithubUserAndPass.PASSWORD.getValue());
        driver.navigate().to(linkToAlreadyPurchasedApp);

        WebElement appFirstPricePlan = driver.findElement(By.xpath(freePlanOptionXpath));
        if(!appFirstPricePlan.isSelected()) {
            appFirstPricePlan.click();
        }

        try {

            WebElement error = driver.findElement(By.xpath(XPath));
            if(error.getText().equals("You’ve already purchased this on all of your GitHub accounts."))
            pass = true;

        }catch (NoSuchElementException ex){
            System.out.println("Try again install this app to see is already purchassed");
        }
        Assert.assertTrue(pass);

    }
}

















