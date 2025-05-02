package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class CartPage extends BasePage{
    WebDriverWait cartPageLoad;
    WebElement cartCloseButton;
    WebElement cartPurchaseButton;


    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(LogInPage.class);

    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
        cartPageLoad = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Override
    public boolean waitForPageLoad() {
        return cartPageLoad.until(ExpectedConditions.visibilityOf(cartButton)).isDisplayed();
    }


    String homeUrl = "https://www.demoblaze.com/index.html";
    String cartUrl = "https://www.demoblaze.com/cart.html";

    @FindBy(xpath = "//a[text()='Cart']")
    WebElement cartButton;

    @FindBy(xpath = "//button[text()='Place Order']")
    WebElement placeOrderButton;

    @FindBy(id = "name")
    WebElement cartNameInput;

    @FindBy(id = "country")
    WebElement cartCountryInput;

    @FindBy(id = "city")
    WebElement cartCityInput;

    @FindBy(id = "card")
    WebElement cartCreditCardInput;

    @FindBy(id = "month")
    WebElement cartMonthInput;

    @FindBy(id = "year")
    WebElement cartYearInput;

//    @FindBy(id = "//button[text()='Purchase']/preceding-sibling::button")
//    WebElement cartCloseButton;

//    @FindBy(xpath = "//button[text()='Purchase']")
//    WebElement cartPurchaseButton;

    @FindBy(xpath = "//a[text()='Delete']")
    WebElement cartDeleteButton;

    @FindBy(xpath = "//a[text()='Add to cart']")
    WebElement addToCartButton;

    @FindBy(xpath = "//button[text()='OK']")
    WebElement okButton;


    public void verifyingCartButton(){
        cartPageLoad.until(ExpectedConditions.visibilityOf(cartButton)).isDisplayed();
        getWebClickCommands().clickElement(cartButton);
        getWebWaitCommands().waitFor(1000);
        logger.info("placing order button is visible");
    }

    public void clickPlaceOrderButton(){
        cartPageLoad.until(ExpectedConditions.visibilityOf(placeOrderButton)).isDisplayed();
        getWebWaitCommands().waitFor(5000);
        getWebClickCommands().clickElement(placeOrderButton);
        logger.info("place order button is working properly");
    }

    public void placingOrder(String name,String country,String city, String creditCard ,String month,String year){
        cartPageLoad.until(ExpectedConditions.visibilityOf(cartNameInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(cartNameInput,name);
        cartPageLoad.until(ExpectedConditions.visibilityOf(cartCountryInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(cartCountryInput, country);
        cartPageLoad.until(ExpectedConditions.visibilityOf(cartCityInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(cartCityInput, city);
        cartPageLoad.until(ExpectedConditions.visibilityOf(cartCreditCardInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(cartCreditCardInput, creditCard);
        cartPageLoad.until(ExpectedConditions.visibilityOf(cartMonthInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(cartMonthInput, month);
        cartPageLoad.until(ExpectedConditions.visibilityOf(cartYearInput)).isDisplayed();
        getWebTextCommands().clearAndEnterText(cartYearInput, year);
    }


    public void clickPurchaseButton(){
        cartPurchaseButton = driver.findElement(By.xpath("//button[text()='Purchase']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", cartPurchaseButton);
        cartPageLoad.until(ExpectedConditions.visibilityOf(cartPurchaseButton)).isDisplayed();
        getWebClickCommands().clickElement(cartPurchaseButton);
    }

    public void clickCartCloseButton(){
        cartCloseButton = driver.findElement(By.xpath("//button[text()='Purchase']/preceding-sibling::button"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", cartCloseButton);
        cartPageLoad.until(ExpectedConditions.visibilityOf(cartCloseButton)).isDisplayed();
        getWebClickCommands().clickElement(cartCloseButton);
    }

    public void clickAddToCartButtonAndGoToCartPage(){
        cartPageLoad.until(ExpectedConditions.visibilityOf(addToCartButton)).isDisplayed();
        getWebClickCommands().clickElement(addToCartButton);
        getWebWaitCommands().waitForAlertToBePresent(2000);
        getWebAlertCommands().acceptSimpleAlert();
    }

    public void verifyingProduct(String name){
        if(name.equals("Samsung galaxy s6")){
            logger.info("The required element is added to the cart");
            getWebClickCommands().clickElement(placeOrderButton);
        }
    }

    public void deleteTheOrder(){
        getWebClickCommands().clickElement(cartDeleteButton);
    }

}
