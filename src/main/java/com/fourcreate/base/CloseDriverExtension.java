package com.fourcreate.base;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class CloseDriverExtension implements AfterEachCallback {

    private static final Logger logger = LoggerFactory.getLogger(CloseDriverExtension.class);

    //the WebDriver is closed in an AfterEachCallback to enable additional operations if needed
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        final TestBase testBase = (TestBase) context.getTestInstance()
            .orElseThrow(() -> new Exception("No test instance in test context."));

        if (testBase.getDriver() != null) {
            try {
                testBase.getDriver().quit();
                logger.info("Driver closed");
            } catch (WebDriverException e) {
                logger.error("Something went wrong while closing a driver", e);
            }
        } else {
            logger.info("Driver already closed");
        }
    }
}
