package org.qamation.charities.extractor;

import org.junit.Test;
import org.qamation.charities.BaseTest;

import java.util.List;

public class CharityLinkExtractorTests extends BaseTest {


    @Test
    public void getCharitiesLinksTest() {
        CharityLinkExtractor extractor = new CharityLinkExtractor(driver,"https://charityintelligence.ca/research/a-z-charity-listing");
        List<String> links = extractor.getLinks();
        for (String s : links) {
            System.out.println(s);
        }
    }


}
