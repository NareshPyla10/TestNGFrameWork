package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage extends BasePage{
    private WebDriverWait logInPageWait;


    private static final Logger logger = LogManager.getLogger(LogInPage.class);


    @FindBy(id = "logInModalLabel")
    private WebElement loginModelTitle;


    @FindBy(id = "loginusername")
    WebElement loginUserInput;

    @FindBy(id = "loginpassword")
    WebElement loginPasswordInput;

    @FindBy(id = "login2")
    WebElement homeLogInButton;

    @FindBy(xpath = "//button[text()='Log in']")
    WebElement logInButton;

    @FindBy(xpath = "//button[text()='Log in']/preceding-sibling::button")
    WebElement closeLogInButton;

    @FindBy(id = "nameofuser")
    WebElement welcomeButton;

    @FindBy(id = "logout2")
    WebElement logoutButton;

    public LogInPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        logInPageWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Override
    public boolean waitForPageLoad() {
        return logInPageWait.until(ExpectedConditions.visibilityOf(loginModelTitle)).isDisplayed();
    }



    public boolean verifyLogInButton(){
        return getWebVerifyCommands().verifyElementIsDisplayed(loginModelTitle);
    }

    public void clickLogInButton(){
        logInPageWait.until(ExpectedConditions.visibilityOf(homeLogInButton)).isDisplayed();
        getWebClickCommands().clickElement(homeLogInButton);
    }

    public void passingDetails(String name, String password){
        logInPageWait.until(ExpectedConditions.visibilityOf(loginUserInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(loginUserInput,name);
        logInPageWait.until(ExpectedConditions.visibilityOf(loginPasswordInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(loginPasswordInput,password);
        getWebClickCommands().clickElement(logInButton);
    }

    public void logIn(String userName, String password){
        passingDetails(userName, password);
        clickLogInButton();
    }

    public boolean verifyingWelcomeButton(){
        return logInPageWait.until(ExpectedConditions.visibilityOf(welcomeButton)).isDisplayed();
    }

    public void verifyingLogoutButton(){
        logInPageWait.until(ExpectedConditions.visibilityOf(logoutButton)).isDisplayed();
        getWebWaitCommands().waitFor(1000);
        getWebClickCommands().clickElement(logoutButton);
        logInPageWait.until(ExpectedConditions.visibilityOf(logInButton)).isDisplayed();
    }

}
