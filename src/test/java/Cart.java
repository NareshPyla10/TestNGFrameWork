import fileReader.Customer;
import fileReader.FileReaderManager;
import fileReader.JsonFileReader;
import fileReader.PropertyFileReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectManager.PageObjectManager;
import webDriverManager.WebDriverManager;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class Cart {
    PageObjectManager pageObjectManager;
    JsonFileReader jsonFileReader;
    PropertyFileReader propertyFileReader;
    Customer customer;

    @BeforeClass
    public void testSetUp(){
        jsonFileReader = FileReaderManager.getFileReaderManager().getJsonFileReader();
        propertyFileReader = FileReaderManager.getFileReaderManager().getPropertyFileReader();
        pageObjectManager = new PageObjectManager(WebDriverManager.getWebDriverManager().getDriver());
    }


//    @Test
//    //verifying and clicking and placing an order with click closing button
//    public void placingOrderWithCloseButton(){
//        pageObjectManager.getHomePage().launchWebsite(propertyFileReader.getProperty("url"));
//        pageObjectManager.getCartPage().verifyingCartButton();
//        pageObjectManager.getCartPage().clickPlaceOrderButton();
//        customer = jsonFileReader.getCustomerByName("Lakshay");
//        pageObjectManager.getCartPage().placingOrder(customer.name, customer.country, customer.city, customer.creditCard, customer.month, customer.year);
//        pageObjectManager.getCartPage().clickCartCloseButton();
//    }
//
//    @Test
//    //verifying and clicking and placing an order with click purchase button
//    public void placingOrderWithClickPurchaseButton(){
//        pageObjectManager.getHomePage().launchWebsite(propertyFileReader.getProperty("url"));
//        pageObjectManager.getCartPage().verifyingCartButton();
//        pageObjectManager.getCartPage().clickPlaceOrderButton();
//        customer = jsonFileReader.getCustomerByName("Lakshay");
//        pageObjectManager.getCartPage().placingOrder(customer.name, customer.country, customer.city, customer.creditCard, customer.month, customer.year);
//        pageObjectManager.getCartPage().clickPurchaseButton();
//    }

    @Test
    //adding product to the cart and click purchase button
    public void addingProductAndClickPurchaseButton(){
        pageObjectManager.getHomePage().launchWebsite(propertyFileReader.getProperty("url"));
        pageObjectManager.getHomePage().clickHomeButton();
        assertThat(pageObjectManager.getHomePage().verifyingLaptopsButton()).isTrue();
        pageObjectManager.getHomePage().clickOnLaptopsButton();
        pageObjectManager.getHomePage().clickingRequiredProduct("MacBook air");
//        pageObjectManager.getCartPage().clickAddToCartButtonAndGoToCartPage();
//        pageObjectManager.getCartPage().verifyingCartButton();
//        pageObjectManager.getCartPage().clickPlaceOrderButton();
//        customer = jsonFileReader.getCustomerByName("Lakshay");
//        pageObjectManager.getCartPage().placingOrder(customer.name, customer.country, customer.city, customer.creditCard, customer.month, customer.year);
//        pageObjectManager.getCartPage().clickPurchaseButton();
    }
}
