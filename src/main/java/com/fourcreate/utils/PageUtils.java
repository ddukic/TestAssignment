package com.fourcreate.utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageUtils {

    private static final Logger logger = LoggerFactory.getLogger(PageUtils.class);
    private final WebDriver driver;

    public PageUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waiting for web element to appear with additional checks. It doesn't fail immediately on {@link StaleElementReferenceException}
     * or on {@link TimeoutException}, instead it tries for 30 seconds to check again for the element.
     *
     * @param element a {@link WebElement} that we want to wait for
     * @return the element when finds it or throw an error after 30 seconds
     */
    public WebElement waitForElementToAppear(WebElement element) {
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(element);
        int count = 0;
        while (count < 10) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 3);
                return wait.until(condition);
            } catch (StaleElementReferenceException e) {
                logger.debug("Trying to recover from a stale element reference exception");
                count = count + 1;
            } catch (TimeoutException e) {
                count = count + 1;
            }
        }

        throw new AssertionError(condition);
    }
}
