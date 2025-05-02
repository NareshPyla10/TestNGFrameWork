import fileReader.FileReaderManager;
import fileReader.JsonFileReader;
import fileReader.PropertyFileReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjectManager.PageObjectManager;
import webDriverManager.WebDriverManager;
import static org.assertj.core.api.Assertions.assertThat;

public class AboutUs {
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
    public void verifyingAndClickingAboutUsButton(){
        pageObjectManager.getHomePage().launchWebsite(propertyFileReader.getProperty("url"));
        pageObjectManager.getHomePage().clickAboutUsButton();
        pageObjectManager.getAboutUsPage().closingButton();
    }

}
