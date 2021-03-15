package com.fourcreate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackOverflowPage {

    private static final Logger logger = LoggerFactory.getLogger(StackOverflowPage.class);

    private final WebDriver driver;
    @FindBy(css = "a[href*='login']")
    private WebElement loginButton;
    @FindBy(css = "button[data-provider='google']")
    private WebElement loginWithGoogleButton;

    public StackOverflowPage(WebDriver driver) {
        this.driver = driver;
        logger.info("Opening stackoverflow page.");
        this.driver.get("https://stackoverflow.com/");

        PageFactory.initElements(driver, this);
    }

    /**
     * Login in to the Stackoverflow using google auth since google blocks automation scripts.
     * This is not guaranty that will work always but it worked for me.
     */
    public void loginToStackOverflowUsingGoogleAuth(final String email, final String password) {
        loginButton.click();
        loginWithGoogleButton.click();
        GoogleSignInPage googleSignInPage = new GoogleSignInPage(driver);
        googleSignInPage.doSignInProcess(email, password);
    }
}
