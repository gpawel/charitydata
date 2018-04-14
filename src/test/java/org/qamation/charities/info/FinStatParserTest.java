package org.qamation.charities.info;

import org.junit.Test;
import org.qamation.charities.BaseTest;
import org.qamation.charities.extractor.FinInfoExtractor;

import java.util.List;
import static org.junit.Assert.*;

public class FinStatParserTest extends BaseTest {
    private FinInfoExtractor extractor;
    private FinStatParser parser;

    @Test
    public void getFinDataTest() {
        extractor = new FinInfoExtractor(driver,"https://charityintelligence.ca/charity-details/393-montreal-spca");
        parser = new FinStatParser(extractor);
        List<FinStats> statsList = parser.getFinStats();
        for (FinStats fs: statsList) {
            System.out.println(fs.toString());
        }
    }
}
