package org.qamation.charities.extractor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FinInfoExtractor {
    private static final String FIN_INFO_ELEMENT_XPATH = "//div[@id='stats']";
    private static final String SUMMARY_ELEMENT_XPATH = FIN_INFO_ELEMENT_XPATH+"/table[2]";
    private static final String SUMMARY_YEARS_XPATH = SUMMARY_ELEMENT_XPATH+"/thead/tr/th";
    private static final String REVENUES_YEAR_XPATH = "//*[contains(text(),'Total revenue')]/../../td";
    private static final String PROGRAM_COSTS_XPATH = "//*[contains(text(),'Program costs')]/../td";
    private static final String ADMIN_COSTS_XPATH = "//*[contains(text(),'Administrative costs')]/../td";
    private static final String FUNDRIZING_COSTS_XPATH = "//*[contains(text(),'Fundraising costs')]/../td";
    private static final String OTHER_COSTS_XPATH = "//*[contains(text(),'Other costs')]";
    private static final String SUMMARY_COSTS_XPATH = "//*/table[@class='stats_table summary']//*[contains(text(),' costs')]/../td";

    private WebDriver driver;
    private String[] years;

    public FinInfoExtractor(WebDriver driver) {
        this.driver = driver;
        years = extractFromPage(SUMMARY_YEARS_XPATH);
    }


    public String[] getYears() {
        return years;
    }

    public int getNumberOfYears() {
        return years.length;
    }

    public String [] getRevenuePerYear() {
        return extractFromPage(REVENUES_YEAR_XPATH);
    }

    public int[] getCostPerYear() {
        String[] costLines = extractFromPage(SUMMARY_COSTS_XPATH);
        int[] costsPerYear = new int[getNumberOfYears()+1];
        for (int i=0; i < getNumberOfYears(); i=i+getNumberOfYears()+1) {
            for (int j=0; j < getNumberOfYears()+1; j++) {
                costsPerYear[j] = costsPerYear[j]+Integer.parseInt(costLines[i+j].replaceAll(",",""));
            }
        }
        return costsPerYear;
    }






    private String[] extractFromPage(String x) {
        By xpath = By.xpath(x);
        List<WebElement> els = driver.findElements(xpath);
        return getElementsContent(els);
    }

    private  String[] getElementsContent(List<WebElement> els) {
        if (els.size()==0) return new String[] {};
        String[] result = new String[els.size()];
        for (int i=0; i<result.length; i++) {
            result[i] = getText(els.get(i));
        }
        return result;
    }

    private String getText(WebElement el) {
        return el.getText();
    }



}
