package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webCore.WebVerifyCommands;

public class FormPage {


    WebDriver driver;

    private WebVerifyCommands webVerifyCommands;

    private static final Logger logger = LogManager.getLogger(FormPage.class);

    public String getReqSampleDynamicLocator(String name){
        return "//div[@id='Test']/div[text()='"+name+"']";
    }


    public boolean isNameDisplayed(String name){
        return webVerifyCommands.verifyElementIsDisplayed(By.xpath(getReqSampleDynamicLocator(name)));
    }

    public static void main(String[] args) {
        logger.info("Test");
    }



}
