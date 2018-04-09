package org.qamation.charities.extractor;

import org.junit.Assert;
import org.junit.Test;

public class FinInfoExtractorTests extends BaseTest {
    // https://charityintelligence.ca/charity-details/772-blue-sea-philanthropy

    @Test
    public void extractYearsTest1() {
        page.openPage("https://charityintelligence.ca/charity-details/154-acclaim-health");
        page.isReady();
        String[] data = FinInfoExtractor.getYears(driver);
        Assert.assertEquals("2016",data[0]);
        Assert.assertEquals("2015",data[1]);
        Assert.assertEquals("2014",data[2]);
    }

    @Test
    public void extractYearsTest2() {
        page.openPage("https://charityintelligence.ca/charity-details/772-blue-sea-philanthropy");
        page.isReady();
        String[] data = FinInfoExtractor.getYears(driver);
        Assert.assertEquals("2015",data[0]);
        Assert.assertEquals("2014",data[1]);
        Assert.assertEquals("2013",data[2]);
    }
}
