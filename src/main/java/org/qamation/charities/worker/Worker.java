package org.qamation.charities.worker;

import org.openqa.selenium.WebDriver;
import org.qamation.charities.extractor.Extract;
import org.qamation.charities.extractor.FinInfoExtractor;
import org.qamation.charities.info.CharityInfo;
import org.qamation.charities.info.Storage;
import org.qamation.webdriver.utils.WebDriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
    private static Logger log = LoggerFactory.getLogger(Worker.class);
    private WebDriver driver;
    private ConcurrentLinkedQueue<String> queue;


    public Worker(ConcurrentLinkedQueue<String> queue ) {
        this.driver = WebDriverFactory.createChromeWebDriver(Extract.getWebDriverPath());
        this.queue = queue;
    }



    @Override
    public void run() {
        while(!queue.isEmpty()) {
            String url="";
            try {
                url = queue.poll();
                FinInfoExtractor extractor = new FinInfoExtractor(driver, url);
                CharityInfo charity = new CharityInfo(extractor);
                Storage.addCharity(charity);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                log.error("Problem parsing info for <"+url+">");
                run();
            }
        }
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
