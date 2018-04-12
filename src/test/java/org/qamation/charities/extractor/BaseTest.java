package org.qamation.charities.extractor;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.qamation.web.page.Page;
import org.qamation.web.page.WebPageFactory;
import org.qamation.webdriver.utils.WebDriverFactory;

public class BaseTest {
    WebDriver driver;
    Page page;
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
        page = WebPageFactory.createGeneralPageInstance(driver);

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
