import fileReader.Customer;
import fileReader.FileReaderManager;
import fileReader.JsonFileReader;
import fileReader.PropertyFileReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectManager.PageObjectManager;
import webDriverManager.WebDriverManager;
import static org.assertj.core.api.Assertions.assertThat;


public class SignUp {
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
    public void signUpFunctionality(){
        pageObjectManager.getHomePage().launchWebsite(propertyFileReader.getProperty("url"));
        assertThat(pageObjectManager.getSignUpPage().verifySigInButton()).isTrue();
        pageObjectManager.getSignUpPage().clickSignInButton();
        customer = jsonFileReader.getCustomerByName("Virender");
        pageObjectManager.getSignUpPage().signUp(customer.userName,customer.password);
        pageObjectManager.getSignUpPage().clickingSignUpButton();
        pageObjectManager.getSignUpPage().handlingExistingUserAlert();
        pageObjectManager.getSignUpPage().closeSignUpPrompt();
        pageObjectManager.getHomePage().clickLogInButton();
        pageObjectManager.getLogInPage().logIn(customer.userName,customer.password);
        pageObjectManager.getSignUpPage().clickLogInPopUpButton();
        assertThat(pageObjectManager.getSignUpPage().verifyWelcomeButton()).isTrue();
        pageObjectManager.getSignUpPage().verifyWelcomeButton();
    }
}
