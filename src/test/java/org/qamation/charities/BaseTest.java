package org.qamation.charities;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.qamation.charities.extractor.Extract;
import org.qamation.webdriver.utils.WebDriverFactory;


public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        String path = Extract.getWebDriverPath();
        driver = WebDriverFactory.createChromeWebDriver(path);

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
