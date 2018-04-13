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
    }

    @Test
    public void extractYearsTest1() {
        extractor = new FinInfoExtractor(driver,"https://charityintelligence.ca/charity-details/154-acclaim-health");
        String[] data = extractor.getYears();
        assertEquals("2016",data[0]);
        assertEquals("2015",data[1]);
        assertEquals("2014",data[2]);
    }

    @Test
    public void extractYearsTest2() {
        extractor = new FinInfoExtractor(driver,"https://charityintelligence.ca/charity-details/772-blue-sea-philanthropy");
        String[] data = extractor.getYears();
        assertEquals("2015",data[0]);
        assertEquals("2014",data[1]);
        assertEquals("2013",data[2]);
    }

    @Test
    public void extractRevenueTest1() {
        extractor = new FinInfoExtractor(driver,"https://charityintelligence.ca/charity-details/154-acclaim-health");
        String[] revenues = extractor.getRevenue();
        assertEquals("Total revenues",revenues[0]);
        assertEquals("18,854",revenues[1]);
        assertEquals("18,704",revenues[2]);
        assertEquals("17,084",revenues[3]);
        assertEquals(17084, Integer.parseInt(revenues[3].replaceAll(",","")));
    }

    @Test
    public void extactRevenueTest2() {
        extractor = new FinInfoExtractor(driver,"https://charityintelligence.ca/charity-details/629-ecotecture-centre-for-ecological-art-architecture");
        String[] revenues = extractor.getRevenue();
        assertEquals("Total revenues",revenues[0]);
        assertEquals("49,309",revenues[1]);
        assertEquals("34,620",revenues[2]);
        assertEquals("65,346",revenues[3]);
        assertEquals(65346, Integer.parseInt(revenues[3].replaceAll(",","")));
    }

    @Test
    public void extractCharityCost() {
        extractor = new FinInfoExtractor(driver,"https://charityintelligence.ca/charity-details/393-montreal-spca");
        String[][] groups = extractor.getCostGoups();
        for (int i=0; i<groups.length; i++) {
            for (int j=0; j<groups[i].length; j++) {
                System.out.print(groups[i][j]+"\t");
            }
            System.out.print("\n");
        }
        assertEquals("Program costs",groups[0][0]);
        assertEquals("210",groups[1][1]);
        assertEquals("2,192",groups[2][2]);
        assertEquals("193",groups[3][3]);
    }
}
