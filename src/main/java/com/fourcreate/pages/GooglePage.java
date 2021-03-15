package com.fourcreate.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    private final WebDriver driver;

    public GooglePage(final WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://www.google.com/");

        PageFactory.initElements(driver, this);
    }

    public GoogleResultsPage searchFor(final String searchString) {
        searchField.clear();
        searchField.sendKeys(searchString);
        searchField.sendKeys(Keys.RETURN);

        return new GoogleResultsPage(driver);
    }
}
