package org.qamation.charities.worker;

import org.openqa.selenium.WebDriver;
import org.qamation.charities.extractor.Extract;
import org.qamation.charities.extractor.FinInfoExtractor;
import org.qamation.charities.info.CharityInfo;
import org.qamation.charities.info.Storage;
import org.qamation.webdriver.utils.WebDriverFactory;

import java.util.List;

public class Worker implements Runnable {

    private WebDriver driver;
    private String url;


    public Worker(String url) {
        this.driver = WebDriverFactory.createChromeWebDriver(Extract.getWebDriverPath());
        this.url = url;
    }



    @Override
    public void run() {
        FinInfoExtractor extractor = new FinInfoExtractor(driver,url);
        CharityInfo charity = new CharityInfo(extractor);
        Storage.addCharity(charity);
    }
}
