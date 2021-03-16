package com.fourcreate.base;

import com.fourcreate.enums.BrowsersEnum;
import com.fourcreate.enums.ModeEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

@ExtendWith(CloseDriverExtension.class)
public class TestBase {

    protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    private static final BrowsersEnum BROWSER;
    private static final ModeEnum MODE;
    private static final String OS;

    //load the config parameters in static block
    static {
        MODE = getTestModeProperty(System.getProperty("mode"));
        BROWSER = getBrowserTypeProperty(System.getProperty("browser"));
        OS = System.getProperty("os.name");

        logger.info("Mode set to: " + MODE);
        logger.info("Browser set to: " + BROWSER);
        logger.info("OS is: " + OS);
    }

    protected WebDriver driver;

    /**
     * Loads browser using system properties.
     *
     * @param browserProperty a browser name
     * @return the {@link BrowsersEnum} if it is supported
     * @throws IllegalArgumentException in case browserProperty is empty or null
     */
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

    private static ModeEnum getTestModeProperty(String testModeProperty)
        throws IllegalArgumentException {
        if (testModeProperty == null || testModeProperty.isEmpty()) {
            logger.info("Test mode was null. Setting LOCAL mode.");
            return ModeEnum.LOCAL;
        }

        ModeEnum mode = ModeEnum.valueOf(testModeProperty.toUpperCase());
        logger.info("Test mode identified: {}D", mode);
        return mode;
    }

    /**
     * Web driver initialization.
     * Supports 2 local browsers Chrome and Firefox
     * and remote browser, in this case Chrome. Check docker-compose file
     */
    @BeforeEach
    public void beforeTestBase() {
        ChromeOptions options = new ChromeOptions();

        switch (MODE) {
            case LOCAL:
                switch (BROWSER) {
                    case CHROME:
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case FIREFOX:
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + BROWSER);
                }
                break;
            case REMOTE:
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + MODE);
        }

        driver.manage().window().maximize();
        logger.info("Window size after maximization: {}", driver.manage().window().getSize());
    }

    public WebDriver getDriver() {
        return driver;
    }
}