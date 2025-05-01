package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{
    private  WebDriverWait homePageWait;
    private static final Logger logger = LogManager.getLogger(HomePage.class);


    @FindBy(xpath = "//a[text()='Home ']")
    private WebElement homeButton;

    @FindBy(xpath = "//a[text()='Laptops']")
    private WebElement laptopsButtons;

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(xpath = "//a[text()='Phones']")
    private WebElement phoneButton;

    @FindBy(xpath = "//a[text()='Monitors']")
    private WebElement monitorsButton;

    @FindBy(xpath = "//a[text()='About us']")
    private WebElement aboutButton;

    private String getRequiredElementXpath(String reqElement){
        return "//a[contains(text(),'"+ reqElement + "')]";
    }


    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        homePageWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Override
    public boolean waitForPageLoad() {
        return  homePageWait.until(ExpectedConditions.visibilityOf(homeButton)).isDisplayed();
    }



    public boolean verifyHomeButton(){
        return getWebVerifyCommands().verifyElementIsDisplayed(homeButton);
    }

    public String launchWebsite(String url){
        getWebLaunchCommands().openingBrowser(url);
        return  getWebLaunchCommands().getTitle();
    }

    public void clickHomeButton(){
        getWebClickCommands().clickElement(homeButton);
    }


    public boolean verifyAboutUsButton(){
        return getWebVerifyCommands().verifyElementIsDisplayed(aboutButton);
    }


    public void clickAboutUsButton(){
        getWebClickCommands().clickElement(aboutButton);
    }


    public boolean verifyLogInButton(){
        return getWebVerifyCommands().verifyElementIsDisplayed(loginButton);
    }


    public void clickLogInButton(){
        getWebClickCommands().clickElement(loginButton);
    }



    public void clickReqCategory(String category){
        switch (category.toLowerCase()){
            case "phones":
                homePageWait.until(ExpectedConditions.visibilityOf(phoneButton)).isDisplayed();
                getWebClickCommands().clickElement(phoneButton);
                break;
            case "laptops":
                homePageWait.until(ExpectedConditions.visibilityOf(laptopsButtons)).isDisplayed();
                getWebClickCommands().clickElement(laptopsButtons);
                break;
            case "monitors":
                homePageWait.until(ExpectedConditions.visibilityOf(monitorsButton)).isDisplayed();
                getWebClickCommands().clickElement(monitorsButton);
                break;
            default:
                logger.error("Provide a valid category");
        }

    }

    public boolean isRequiredProductDisplayed(String reqProduct){
        homePageWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getRequiredElementXpath(reqProduct))));
        return getWebVerifyCommands().verifyElementIsDisplayed(By.xpath(getRequiredElementXpath(reqProduct)));
    }

    public void clickTheReqProduct(String product){
        getWebClickCommands().clickElement(By.xpath(getRequiredElementXpath(product)));
    }


}
