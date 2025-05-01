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

public class SignUpPage extends BasePage{
    private WebDriverWait signInPageLoad;


    private static final Logger logger = LogManager.getLogger(SignUpPage.class);

    public SignUpPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        signInPageLoad = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Override
    public boolean waitForPageLoad() {
        return signInPageLoad.until(ExpectedConditions.visibilityOf(signInButton)).isDisplayed();
    }

    @FindBy(id = "signin2")
    WebElement signInButton;

    @FindBy(id = "signInModalLabel")
    WebElement headingSigUp;

    @FindBy(id = "sign-username")
    WebElement userNameButton;

    @FindBy(id = "sign-password")
    WebElement userPassword;

    @FindBy(xpath = "//button[text()='Sign up']")
    WebElement signUpButton;

    @FindBy(id = "nameofuser")
    WebElement welcomeButton;

    @FindBy(xpath = "//button[text()='Sign up']/preceding-sibling::button")
    WebElement closeButton;

    @FindBy(id = "#login2")
    WebElement logInButton;




    public boolean verifySigInButton(){
        return getWebVerifyCommands().verifyElementIsDisplayed(signInButton);
    }

    public void clickSignInButton()  {
        logger.info("button is displayed or not :" + signInButton.isDisplayed());
        logger.info(signInPageLoad.until(ExpectedConditions.elementToBeClickable(signInButton)));
        getWebClickCommands().clickElement(signInButton);
    }


    public void signUp(String name, String password) {
        getWebWaitCommands().waitFor(1000);
        getWebTextCommands().clearAndEnterText(userNameButton, name);
        getWebWaitCommands().waitFor(1000);
        getWebTextCommands().clearAndEnterText(userPassword, password);
        clickingSignUpButton();
    }

    public void clickingSignUpButton(){
        logger.info("sign up button is clicking : " + signUpButton);
        getWebClickCommands().clickElement(signUpButton);
    }

    public void handlingExistingUserAlert(){
        signInPageLoad.until(ExpectedConditions.alertIsPresent());
        if(getWebAlertCommands().getTextFromAlert().contains("This user already exist")){
            getWebAlertCommands().acceptSimpleAlert();

        }
    }

    public void handlingNewUserAlert(){
        signInPageLoad.until(ExpectedConditions.alertIsPresent());
        if(getWebAlertCommands().getTextFromAlert().contains("Sign up successful")){
            getWebAlertCommands().acceptSimpleAlert();
        }
    }

    public void closeSignUpPrompt(){
        signInPageLoad.until(ExpectedConditions.visibilityOf(closeButton)).isDisplayed();
        getWebClickCommands().clickElement(closeButton);
    }

}
