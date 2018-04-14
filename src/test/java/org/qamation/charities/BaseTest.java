package org.qamation.charities;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.qamation.webdriver.utils.WebDriverFactory;


public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        String path;
        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
            path = System.getProperty("user.home")+"/workspace/Selenium/Chrome/chromedriver";
        }
        else {
           path = "C:\\TEST_ENV\\Selenium\\ChromeDriver\\chromedriver.exe";
        }
        driver = WebDriverFactory.createChromeWebDriver(path);

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
