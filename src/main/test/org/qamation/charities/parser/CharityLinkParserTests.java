package org.qamation.charities.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.qamation.web.page.Page;
import org.qamation.web.page.WebPageFactory;
import org.qamation.webdriver.utils.WebDriverFactory;

import java.util.List;

public class CharityLinkParserTests {
    WebDriver driver;
    @Before
    public void setUp() {
        driver = WebDriverFactory.createChromeWebDriver("C:\\TEST_ENV\\Selenium\\ChromeDriver\\chromedriver.exe");
        Page page = WebPageFactory.createGeneralPageInstance(driver);
        page.openPage("https://charityintelligence.ca/research/a-z-charity-listing");
        page.isReady();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void getCharitiesLinksTest() {
        List<String> links = CharityLinkParser.getCharityLinks(driver);
        for (String s : links) {
            System.out.println(s);
        }
    }


}
