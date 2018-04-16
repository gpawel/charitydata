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
/*
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
        queue.add("https://charityintelligence.ca/charity-details/125-416-community-support-for-women");
        queue.add("https://charityintelligence.ca/charity-details/692-alberta-adolescent-recovery-centre");
        queue.add("https://charityintelligence.ca/charity-details/539-ywca-toronto");
        queue.add("https://charityintelligence.ca/charity-details/569-canadian-olympic-foundation");
*/
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        for (int i=0; i<N; i++) {
            Worker w = new Worker(queue);
            executorService.execute(w);
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.MINUTES);
            Storage.toFile("charities_info.tsv");

        }
        catch(InterruptedException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            executorService.shutdownNow();
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
