package org.qamation.charities.extractor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qamation.charities.info.FinStats;

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

    private WebDriver driver;

    public FinInfoExtractor(WebDriver driver) {
        this.driver = driver;
    }


    /*
    public static List<FinStats> extactFinInfo(WebDriver driver) {
        String[] years = getYears(driver);
    }
*/
    public String[] getYears() {
        return getLine(SUMMARY_YEARS_XPATH);
    }

    public String [] getRevenuePerYear() {
        return getLine(REVENUES_YEAR_XPATH);

    }

    public String[] getProgramCostPerYear() {
        return getLine(PROGRAM_COSTS_XPATH);
    }






    private String[] getLine(String x) {
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
