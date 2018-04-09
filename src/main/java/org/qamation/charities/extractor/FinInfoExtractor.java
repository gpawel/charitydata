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

    /*
    public static List<FinStats> extactFinInfo(WebDriver driver) {
        String[] years = getYears(driver);
    }
*/
    public static String[] getYears(WebDriver driver) {
        By xpath = By.xpath(SUMMARY_YEARS_XPATH);
        List<WebElement> els = driver.findElements(xpath);
        if (els.size()==0) return new String[] {};
        String[] result = new String[els.size()];
        for (int i=0; i<result.length; i++) {
            result[i] = els.get(i).getText();
        }
        return result;
    }

}
