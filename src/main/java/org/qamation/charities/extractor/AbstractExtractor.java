package org.qamation.charities.extractor;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.qamation.web.page.Page;
import org.qamation.web.page.WebPageFactory;

public class AbstractExtractor {
    protected WebDriver driver;
    protected Page page;

    public AbstractExtractor(WebDriver driver, String url) {
        this.driver = driver;
        page = WebPageFactory.createGeneralPageInstance(this.driver);
        try {
            openUrl(url);
        }
        catch(TimeoutException ex) {
            openUrl(url);
        }

    }

    private void openUrl(String url) {
        page.openPage(url);
        page.isReady();
    }

}
