package org.qamation.charities.info;

import org.junit.Test;
import org.qamation.charities.BaseTest;
import org.qamation.charities.extractor.FinInfoExtractor;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.slf4j.Logger;

import static org.junit.Assert.*;

public class FinStatParserTest extends BaseTest {
    private FinInfoExtractor extractor;
    private FinStatParser parser;
    Logger log = LoggerFactory.getLogger(FinStatParserTest.class);

    @Test
    public void getFinDataTest() {
        log.info("Test Started");
        extractor = new FinInfoExtractor(driver,"https://charityintelligence.ca/charity-details/393-montreal-spca");
        parser = new FinStatParser(extractor);
        List<FinStats> statsList = parser.getFinStats();
        for (FinStats fs: statsList) {
            System.out.println(fs.toString());
        }
        log.info("Test Finished");
    }
}
