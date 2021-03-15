package com.fourcreate.pages;

import com.fourcreate.constants.Constants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GooglePage {

    protected static final Logger logger = LoggerFactory.getLogger(GooglePage.class);

    private final WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    @FindBy(css = "a[href*='ServiceLogin']")
    private WebElement signInButton;


    public GooglePage(WebDriver driver) {
        this(driver, "en");
    }

    public GooglePage(final WebDriver driver, final String language) {
        this.driver = driver;
        switch (language) {
            case "en":
                this.driver.get(Constants.GOOGLE_ENGLISH);
                break;
            case "sr":
                this.driver.get(Constants.GOOGLE_SERBIAN);
                break;
            default:
                throw new AssertionError("Given language is not supported.");
        }

        PageFactory.initElements(driver, this);
    }

    /**
     * Search google for the given string
     * @param searchString string to search for
     * @return new instance of {@link GoogleResultsPage}
     */
    public GoogleResultsPage searchFor(final String searchString) {
        logger.info("Searching google for: {}", searchString);
        searchField.clear();
        searchField.sendKeys(searchString);
        searchField.sendKeys(Keys.RETURN);

        return new GoogleResultsPage(driver);
    }

    /**
     * Sings in to the gmail account
     *
     * @param email an email
     * @param password a password
     */
    public void signInToGmail(final String email, final String password) {
        StackOverflowPage stackOverflowPage = new StackOverflowPage(driver);
        stackOverflowPage.loginToStackOverflowUsingGoogleAuth(email, password);
    }

    public GmailPage openGmail() {
        logger.info("Opening gmail.");
        driver.get("https://mail.google.com/");
        return new GmailPage(driver);
    }
}
