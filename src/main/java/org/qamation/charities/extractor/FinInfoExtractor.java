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
        By xpath = By.xpath(SUMMARY_YEARS_XPATH);
        List<WebElement> els = driver.findElements(xpath);
        return getElementsContent(els);
    }

    public String [] getRevenuePerYear() {
        By xpath = By.xpath(REVENUES_YEAR_XPATH);
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
