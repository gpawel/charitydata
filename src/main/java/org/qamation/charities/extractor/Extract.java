package org.qamation.charities.extractor;

import org.openqa.selenium.WebDriver;
import org.qamation.charities.info.Storage;
import org.qamation.charities.worker.Worker;
import org.qamation.webdriver.utils.WebDriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Extract {
    private static Logger log = LoggerFactory.getLogger(Extract.class);
    //public static final String HOST="https://charityintelligence.ca";
    public static final String HOST="https://charityintelligence.ca/research/a-z-charity-listing";
    private static int N = Runtime.getRuntime().availableProcessors();;
    public static void main(String args[]) {
        WebDriver driver = WebDriverFactory.createChromeWebDriver(getWebDriverPath());
        CharityLinkExtractor extractor = new CharityLinkExtractor(driver,HOST);
        driver.close();
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue(extractor.getLinks());
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        for (int i=0; i<N; i++) {
            driver = WebDriverFactory.createChromeWebDriver(getWebDriverPath());
            Worker w = new Worker(driver, queue);
            executorService.execute(w);
            Storage.toFile("charities_info.tsv");
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(60, TimeUnit.MINUTES);

        }
        catch(InterruptedException e) {
            executorService.shutdown();
        }


    }

    public static String getWebDriverPath() {
        String path;
        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
            path = System.getProperty("user.home")+"/workspace/Selenium/Chrome/chromedriver";
        }
        else {
            path = "C:\\TEST_ENV\\Selenium\\ChromeDriver\\chromedriver.exe";
        }
        return path;
    }
}
