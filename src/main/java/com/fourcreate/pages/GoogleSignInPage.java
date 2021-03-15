package com.fourcreate.pages;

import com.fourcreate.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleSignInPage {
    protected static final Logger logger = LoggerFactory.getLogger(GoogleSignInPage.class);

    private final WebDriver driver;
    private final PageUtils pageUtils;

    @FindBy(css = "input[type='email']")
    private WebElement emailInput;
    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;
    @FindBy(id = "identifierNext")
    private WebElement nextButton;
    @FindBy(id = "passwordNext")
    private WebElement passwordNext;

    public GoogleSignInPage(WebDriver driver) {
        this.driver = driver;
        logger.info("Opening google sign in page.");
        this.pageUtils = new PageUtils(driver);

        PageFactory.initElements(driver, this);
    }

    /**
     * Do actual google signin process
     * @param email an email
     * @param password a password
     */
    public void doSignInProcess(final String email, final String password) {
        logger.info("Signing in to google using {} / {} credentials.", email, password);
        emailInput.sendKeys(email);
        nextButton.click();
        pageUtils.waitForElementToAppear(passwordInput).sendKeys(password);
        nextButton.click();
    }
}
