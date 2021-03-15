package com.fourcreate.pages;

import com.fourcreate.constants.Constants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {

    private final WebDriver driver;
    @FindBy(name = "q")
    private WebElement searchField;
    @FindBy(name = "btnK")
    private WebElement searchButton;

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

    public GoogleResultsPage searchFor(final String searchString) {
        searchField.clear();
        searchField.sendKeys(searchString);
        searchField.sendKeys(Keys.RETURN);

        return new GoogleResultsPage(driver);
    }

}
