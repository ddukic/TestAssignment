package com.fourcreate.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GmailPage {

    @FindBy(css = "tr[class='zA zE']")
    private List<WebElement> unreadEmails;

    @FindBy(css = ".bsU")
    private WebElement numberOfUnreadEmailsValue;

    @FindBy(css = "a[href*='SignOut']")
    private WebElement userIcon;

    @FindBy(css = "a[href*='Logout']")
    private WebElement logoutButton;

    private final WebDriver driver;

    public GmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isThereUnreadEmails() {
        int numberOfUnreadEmails = Integer.parseInt(numberOfUnreadEmailsValue.getText());

        Assertions.assertEquals(numberOfUnreadEmails, unreadEmails.size(),
            "Number of unread emails is not correct.");

        return unreadEmails.size() > 0;
    }

    public GooglePage signOut() {
        userIcon.click();
        logoutButton.click();

        return new GooglePage(driver);
    }
}
