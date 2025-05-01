package pageObjectManager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageObjectManager {
    WebDriver driver;
    private HomePage homePage;
    private LogInPage logInPage;
    private AboutUsPage aboutUsPage;
    private SignUpPage signUpPage;
    private CartPage cartPage;

    public PageObjectManager(WebDriver driver){
        this.driver = driver;
    }

    public HomePage getHomePage(){
        return (homePage == null) ? homePage = new HomePage(driver): homePage;
    }

    public LogInPage getLogInPage(){
        return (logInPage==null) ? logInPage= new LogInPage(driver) : logInPage;
    }

    public AboutUsPage getAboutUsPage(){
        return (aboutUsPage==null) ? aboutUsPage= new AboutUsPage(driver) : aboutUsPage;
    }

    public SignUpPage getSignUpPage(){
        return (signUpPage==null)? signUpPage = new SignUpPage(driver) : signUpPage;
    }

    public CartPage getCartPage(){return (cartPage==null)? cartPage= new CartPage(driver) : cartPage;};
}
