package com.fourcreate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSignInPage {

    private final WebDriver driver;
    @FindBy(css = "input[type='email']")
    private WebElement emailInput;
    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;
    @FindBy(id = "identifierNext")
    private WebElement nextButton;

    public GoogleSignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doSignInProcess(final String email, final String password) {
        emailInput.sendKeys(email);
        nextButton.click();
        passwordInput.sendKeys(password);
        nextButton.click();
    }
}
