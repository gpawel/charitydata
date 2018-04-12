package org.qamation.charities.extractor;

import static org.junit.Assert.*;
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
        assertEquals("2016",data[0]);
        assertEquals("2015",data[1]);
        assertEquals("2014",data[2]);
    }

    @Test
    public void extractYearsTest2() {
        page.openPage("https://charityintelligence.ca/charity-details/772-blue-sea-philanthropy");
        page.isReady();
        String[] data = extractor.getYears();
        assertEquals("2015",data[0]);
        assertEquals("2014",data[1]);
        assertEquals("2013",data[2]);
    }

    @Test
    public void extractRevenueTest1() {
        page.openPage("https://charityintelligence.ca/charity-details/154-acclaim-health");
        page.isReady();
        String[] revenues = extractor.getRevenuePerYear();
        assertEquals("Total revenues",revenues[0]);
        assertEquals("18,854",revenues[1]);
        assertEquals("18,704",revenues[2]);
        assertEquals("17,084",revenues[3]);
        assertEquals(17084, Integer.parseInt(revenues[3].replaceAll(",","")));
    }

    @Test
    public void extactRevenueTest2() {
        //https://charityintelligence.ca/charity-details/629-ecotecture-centre-for-ecological-art-architecture
        page.openPage("https://charityintelligence.ca/charity-details/629-ecotecture-centre-for-ecological-art-architecture");
        page.isReady();
        String[] revenues = extractor.getRevenuePerYear();
        assertEquals("Total revenues",revenues[0]);
        assertEquals("49,309",revenues[1]);
        assertEquals("34,620",revenues[2]);
        assertEquals("65,346",revenues[3]);
        assertEquals(65346, Integer.parseInt(revenues[3].replaceAll(",","")));
    }

    @Test
    public void extractCharityCost() {
        page.openPage("https://charityintelligence.ca/charity-details/393-montreal-spca");
        page.isReady();
        int[] cost = extractor.getCostPerYear();
        assertEquals(3,cost.length);
        assertEquals(cost[0],7382);
        assertEquals(cost[1],7225);
        assertEquals(cost[2],7428);
    }
}
