package com.fourcreate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StackOverflowPage {

    private final WebDriver driver;
    @FindBy(css = "a[href*='login']")
    private WebElement loginButton;
    @FindBy(css = "button[data-provider='google']")
    private WebElement loginWithGoogleButton;

    public StackOverflowPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://stackoverflow.com/");

        PageFactory.initElements(driver, this);
    }

    public void loginToStackOverflowUsingGoogleAuth(final String email, final String password) {
        loginButton.click();
        loginWithGoogleButton.click();
        GoogleSignInPage googleSignInPage = new GoogleSignInPage(driver);
        googleSignInPage.doSignInProcess(email, password);
    }
}
