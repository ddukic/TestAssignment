package com.fourcreate.pages;

import com.fourcreate.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

public class GoogleResultsPage extends LoadableComponent<GoogleResultsPage> {

    @FindBy(css = "[id='rso'] a cite")
    private List<WebElement> searchResultLinks;

    @FindBy(css = "[id='rso'] a h3")
    private List<WebElement> searchResultTitles;

    private final WebDriver driver;
    private final PageUtils pageUtils;

    public GoogleResultsPage(final WebDriver driver) {
        this.driver = driver;
        this.pageUtils = new PageUtils(driver);

        PageFactory.initElements(driver, this);
        this.get();
    }

    /**
     * Checks does first google search result contain search string in link and in title
     * @param searchResult expected string
     * @return <code>true</code> if first result contains search string; <code>false</code> otherwise
     */
    public boolean isCorrectResultFirst(String searchResult) {
        return StringUtils.containsIgnoreCase(searchResultLinks.get(0).getText(), searchResult)
            && StringUtils.containsIgnoreCase(searchResultTitles.get(0).getText(), searchResult);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        pageUtils.waitForElementToAppear(searchResultLinks.get(0));
        pageUtils.waitForElementToAppear(searchResultTitles.get(0));
    }
}
