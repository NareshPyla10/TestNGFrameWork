import fileReader.Customer;
import fileReader.FileReaderManager;
import fileReader.JsonFileReader;
import fileReader.PropertyFileReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectManager.PageObjectManager;
import webDriverManager.WebDriverManager;
import static org.assertj.core.api.Assertions.assertThat;

public class Contact {
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

    @Test
    public void contactFunctionality(){
        pageObjectManager.getHomePage().launchWebsite(propertyFileReader.getProperty("url"));
        assertThat(pageObjectManager.getHomePage().waitForPageLoad()).isTrue();
        pageObjectManager.getHomePage().clickContactButton();
        customer = jsonFileReader.getCustomerByName("Lakshay");
        pageObjectManager.getContactPage().enteringDetails(customer.emailAddress , customer.name , customer.message);
        pageObjectManager.getContactPage().sendMessage();
    }
}
