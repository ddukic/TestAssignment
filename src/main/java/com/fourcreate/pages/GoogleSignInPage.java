package com.fourcreate.pages;

import com.fourcreate.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSignInPage {

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
        this.pageUtils = new PageUtils(driver);

        PageFactory.initElements(driver, this);
    }

    public void doSignInProcess(final String email, final String password) {
        emailInput.sendKeys(email);
        nextButton.click();
        pageUtils.waitForElementToAppear(passwordInput).sendKeys(password);
        nextButton.click();
    }
}
