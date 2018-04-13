package org.qamation.charities.extractor;

import org.openqa.selenium.WebDriver;
import org.qamation.web.page.Page;
import org.qamation.web.page.WebPageFactory;

public class AbstractExtractor {
    protected WebDriver driver;
    protected Page page;

    public AbstractExtractor(WebDriver driver, String url) {
        this.driver = driver;
        Page page = WebPageFactory.createGeneralPageInstance(this.driver);
        page.openPage(url);
        page.isReady();
    }

}
