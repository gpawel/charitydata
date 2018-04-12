package org.qamation.charities.extractor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.qamation.web.page.Page;
import org.qamation.web.page.WebPageFactory;
import org.qamation.webdriver.utils.WebDriverFactory;

import java.util.List;

public class CharityLinkExtractorTests extends BaseTest {


    @Test
    public void getCharitiesLinksTest() {
        page.openPage("https://charityintelligence.ca/research/a-z-charity-listing");
        page.isReady();
        List<String> links = CharityLinkExtractor.getCharityLinks(driver);
        for (String s : links) {
            System.out.println(s);
        }
    }


}
