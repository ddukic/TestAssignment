package com.fourcreate.pages;

import com.fourcreate.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

public class GmailPage extends LoadableComponent<GmailPage> {

    @FindBy(css = "tr[class='zA zE']")
    private List<WebElement> unreadEmails;

    @FindBy(css = "a[href*='SignOut']")
    private WebElement userIcon;

    @FindBy(css = "a[href*='Logout']")
    private WebElement logoutButton;

    private final WebDriver driver;
    private final PageUtils pageUtils;

    public GmailPage(WebDriver driver) {
        this.driver = driver;
        this.pageUtils = new PageUtils(driver);

        PageFactory.initElements(driver, this);
        this.get();
    }

    /**
     * @return <code>true</code> if there are unread emails; <code>false</code> otherwise
     */
    public boolean isThereUnreadEmails() {
        return unreadEmails.size() > 0;
    }

    /**
     * Signs out from the Google
     * @return new {@link GooglePage}
     */
    public GooglePage signOut() {
        userIcon.click();
        logoutButton.click();

        return new GooglePage(driver);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        pageUtils.waitForElementToAppear(userIcon);
    }
}
