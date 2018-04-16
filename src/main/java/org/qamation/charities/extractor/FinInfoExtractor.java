package org.qamation.charities.extractor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FinInfoExtractor extends AbstractExtractor {
    private static final String FIN_INFO_ELEMENT_XPATH = "//div[@id='stats']";
    private static final String FOND_NAME_XPATH = "//div[@id='name']/h1";
    private static final String SUMMARY_ELEMENT_XPATH = FIN_INFO_ELEMENT_XPATH+"/table[2]";
    private static final String SUMMARY_YEARS_XPATH = SUMMARY_ELEMENT_XPATH+"/thead/tr/th";
    private static final String REVENUES_YEAR_XPATH = "//*[contains(text(),'Total revenue')]/../../td";
    private static final String PROGRAM_COSTS_XPATH = "//*[contains(text(),'Program costs')]/../td";
    private static final String ADMIN_COSTS_XPATH = "//*[contains(text(),'Administrative costs')]/../td";
    private static final String FUNDRIZING_COSTS_XPATH = "//*[contains(text(),'Fundraising costs')]/../td";
    private static final String OTHER_COSTS_XPATH = "//*[contains(text(),'Other costs')]";
    private static final String SUMMARY_COSTS_XPATH = "//*/table[@class='stats_table summary']//*[contains(text(),' costs')]/../td";
    private static final String GRANTS_XPATH = "//*/table[@class='stats_table summary']//*[contains(text(),'Grant')]/../td";

    private Logger log = LoggerFactory.getLogger(FinInfoExtractor.class);

    private String[] years;
    private String[] revenue;
    private String[][] costGoups;
    private String fundName;


    public FinInfoExtractor(WebDriver driver,String url) {
        super(driver,url);
        log.info("\nParsing: "+url);
        this.fundName = getFund();
        this.years = extractFromPage(SUMMARY_YEARS_XPATH);
        this.revenue = getRevenuePerYear();
        this.costGoups = getFinSummaryGroups();
    }

    public int getNumberOfYears() {
        return years.length;
    }

    public String getFundName() {
        return this.fundName;
    }

    public String[] getRevenue() {
        return revenue;
    }

    public String[][] getCostGoups() {
        return costGoups;
    }

    public String[] getYears() {
        return years;
    }

    private String [] getRevenuePerYear() {
        return extractFromPage(REVENUES_YEAR_XPATH);
    }

    private String[][] getFinSummaryGroups() {
        String[] costs = extractFromPage(SUMMARY_COSTS_XPATH);
        String[] grants = extractFromPage(GRANTS_XPATH);
        int groupLength = years.length+1;
        ArrayList<ArrayList<String>> result = parseGroup(costs,groupLength);
        result.addAll(parseGroup(grants,groupLength));
        return convertListToArray(result);
    }

    private ArrayList<ArrayList<String>> parseGroup(String[] source, int groupLength) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i=0; i < source.length; i=i+groupLength) {
            ArrayList<String> group = new ArrayList<>();
            for (int j = 0; j <groupLength; j++) {
                group.add(source[i+j]);
            }
            result.add(group);
        }
        return result;
    }

    private String[][] convertListToArray(ArrayList<ArrayList<String>> source) {
        String[][] array = new String[source.size()][];
        for (int i = 0; i < source.size(); i++) {
            ArrayList<String> row = source.get(i);
            array[i] = row.toArray(new String[row.size()]);
        }
        return array;
    }

    private String getFund() {
        String[] list = extractFromPage(FOND_NAME_XPATH);
        return list[0];

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
