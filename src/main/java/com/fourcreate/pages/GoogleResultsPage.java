package com.fourcreate.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleResultsPage {

    private final WebDriver driver;
    @FindBy(css = "[id='rso'] a cite")
    private List<WebElement> searchResultLinks;
    @FindBy(css = "[id='rso'] a h3")
    private List<WebElement> searchResultTitles;

    public GoogleResultsPage(final WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean isCorrectResultFirst(String searchResult) {
        return StringUtils.containsIgnoreCase(searchResultLinks.get(0).getText(), searchResult)
            && StringUtils.containsIgnoreCase(searchResultTitles.get(0).getText(), searchResult);
    }
}
