package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage extends BasePage{
    private WebDriverWait contactPageWait;


    public ContactPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        contactPageWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(id = "recipient-email")
    private WebElement emailInput;

    @FindBy(id = "recipient-name")
    private WebElement nameInput;

    @FindBy(id = "message-text")
    private  WebElement messageInput;

    @FindBy(xpath = "//button[text()='Send message']")
    private WebElement sendMessageButton;

    @FindBy(xpath = "//button[text()='Send message']/preceding-sibling::button")
    private WebElement closeButton;

    @FindBy(id = "exampleModalLabel")
    private WebElement newMessageTitle;


    @Override
    public boolean waitForPageLoad() {
        return contactPageWait.until(ExpectedConditions.visibilityOf(newMessageTitle)).isDisplayed();
    }


    public void enteringDetails(String email, String name, String message){
        contactPageWait.until(ExpectedConditions.visibilityOf(emailInput)).isDisplayed();
        getWebTextCommands().enterText(emailInput,email);
        contactPageWait.until(ExpectedConditions.visibilityOf(nameInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(nameInput,name);
        contactPageWait.until(ExpectedConditions.visibilityOf(messageInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(messageInput,message);


    }

    public void sendMessage(){
        getWebClickCommands().clickElement(sendMessageButton);
    }
}
