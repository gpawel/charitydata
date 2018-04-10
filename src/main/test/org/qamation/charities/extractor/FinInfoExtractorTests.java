package org.qamation.charities.extractor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FinInfoExtractorTests extends BaseTest {
    // https://charityintelligence.ca/charity-details/772-blue-sea-philanthropy
    private FinInfoExtractor extractor;
    @Override
    @Before
    public void setUp() {
        super.setUp();
        extractor = new FinInfoExtractor(driver);
    }

    @Test
    public void extractYearsTest1() {
        page.openPage("https://charityintelligence.ca/charity-details/154-acclaim-health");
        page.isReady();
        String[] data = extractor.getYears();
        Assert.assertEquals("2016",data[0]);
        Assert.assertEquals("2015",data[1]);
        Assert.assertEquals("2014",data[2]);
    }

    @Test
    public void extractYearsTest2() {
        page.openPage("https://charityintelligence.ca/charity-details/772-blue-sea-philanthropy");
        page.isReady();
        String[] data = extractor.getYears();
        Assert.assertEquals("2015",data[0]);
        Assert.assertEquals("2014",data[1]);
        Assert.assertEquals("2013",data[2]);
    }
}
