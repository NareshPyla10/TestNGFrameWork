package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutUsPage extends BasePage{
    private WebDriverWait aboutUsPageWait;

    private static final Logger logger = LogManager.getLogger(AboutUsPage.class);


    @FindBy(xpath = "//h5[@id='videoModalLabel']/../following-sibling::div[@class='modal-footer']/button")
    private WebElement closeButton;

    @FindBy(id = "videoModalLabel")
    private WebElement aboutUsTitle;





    public AboutUsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        aboutUsPageWait = new WebDriverWait(driver,Duration.ofSeconds(5));
    }

    @Override
    public boolean waitForPageLoad() {
        return aboutUsPageWait.until(ExpectedConditions.visibilityOf(aboutUsTitle)).isDisplayed();
    }



    public void closingButton(){
        getWebClickCommands().clickElement(closeButton);
    }

}
