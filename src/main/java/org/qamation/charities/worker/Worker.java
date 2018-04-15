package org.qamation.charities.worker;

import org.openqa.selenium.WebDriver;
import org.qamation.charities.extractor.Extract;
import org.qamation.charities.extractor.FinInfoExtractor;
import org.qamation.charities.info.CharityInfo;
import org.qamation.charities.info.Storage;
import org.qamation.webdriver.utils.WebDriverFactory;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {

    private WebDriver driver;
    private ConcurrentLinkedQueue<String> queue;


    public Worker(WebDriver driver, ConcurrentLinkedQueue<String> queue ) {
        this.driver = WebDriverFactory.createChromeWebDriver(Extract.getWebDriverPath());
        this.queue = queue;
    }



    @Override
    public void run() {
        String url = queue.poll();
        if (url == null) {
            driver.quit();
            return;
        }
        FinInfoExtractor extractor = new FinInfoExtractor(driver,url);
        CharityInfo charity = new CharityInfo(extractor);
        Storage.addCharity(charity);
    }
}
