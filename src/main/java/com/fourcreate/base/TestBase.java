package com.fourcreate.base;

import com.fourcreate.enums.BrowsersEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(CloseDriverExtension.class)
public class TestBase {

    protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    private static final BrowsersEnum BROWSER;
    private static final String OS;

    protected WebDriver driver;

    //load the config parameters in static block
    static {
        BROWSER = getBrowserTypeProperty(System.getProperty("browser"));
        OS = System.getProperty("os.name");

        logger.info("Browser set to: " + BROWSER);
        logger.info("OS is: " + OS);
    }

    @BeforeEach
    public void beforeTestBase() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private static BrowsersEnum getBrowserTypeProperty(String browserProperty)
        throws IllegalArgumentException {
        if (browserProperty == null || browserProperty.isEmpty()) {
            logger.info("Browser was null. Setting chrome as browser.");
            return BrowsersEnum.CHROME;
        }

        BrowsersEnum browser = BrowsersEnum.valueOf(browserProperty.toUpperCase());
        logger.info("Browser identified: {},", browser);
        return browser;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
