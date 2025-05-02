import fileReader.FileReaderManager;
import fileReader.JsonFileReader;
import fileReader.PropertyFileReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectManager.PageObjectManager;
import webDriverManager.WebDriverManager;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class Home {
    PageObjectManager pageObjectManager;
    JsonFileReader jsonFileReader;
    PropertyFileReader propertyFileReader;


    @BeforeClass
    public void testSetUp(){
        jsonFileReader = FileReaderManager.getFileReaderManager().getJsonFileReader();
        propertyFileReader = FileReaderManager.getFileReaderManager().getPropertyFileReader();
        pageObjectManager = new PageObjectManager(WebDriverManager.getWebDriverManager().getDriver());
    }

    @Test
    public void homePageFunctionality(){
        pageObjectManager.getHomePage().launchWebsite(propertyFileReader.getProperty("url"));
        assertThat(pageObjectManager.getHomePage().verifyHomeButton()).isTrue();
        pageObjectManager.getHomePage().clickHomeButton();
        assertThat(pageObjectManager.getHomePage().verifyingLaptopsButton()).isTrue();
        pageObjectManager.getHomePage().clickOnLaptopsButton();
        //pageObjectManager.getHomePage().clickingTheMacBook();
        pageObjectManager.getHomePage().clickingRequiredProduct("MacBook air");
    }
}
