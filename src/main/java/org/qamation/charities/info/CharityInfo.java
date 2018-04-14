package org.qamation.charities.info;

import org.qamation.charities.extractor.FinInfoExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CharityInfo {

    private static Predicate<String[]> isProgramCost = p -> p[0].startsWith("Program");
    private static Predicate<String[]> isNotProgramCost = p -> !isProgramCost.test(p);

    private Logger log = LoggerFactory.getLogger(CharityInfo.class);

    private List<FinStats> finList = null;
    private int numberOfYears;
    private String name;

    public CharityInfo(FinInfoExtractor extractor) {
        log.info("Starting parsing charity info for " +extractor.getFundName());
        this.name = extractor.getFundName();
        this.numberOfYears = extractor.getNumberOfYears();
        this.finList = parseExtractor(extractor);
    }

    public String getFundName() {
        return this.name;
    }

    public List<FinStats> getFinStats() {
        return finList;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    private List<FinStats> parseExtractor(FinInfoExtractor extractor) {
        List<FinStats> list = new ArrayList<>();
        int numberOfYears = extractor.getNumberOfYears();
        int[] revenue = getIntValues(extractor.getRevenue());
        int[] programCost = getProgramCost(extractor.getCostGoups());
        int[] expences = getExpenses(extractor.getCostGoups());
        for (int yearIndex=0; yearIndex<numberOfYears; yearIndex++) {
            int year = Integer.parseInt(extractor.getYears()[yearIndex]);
            FinStats fs = new FinStats(extractor.getFundName(),year,revenue[yearIndex],programCost[yearIndex],expences[yearIndex]);
            list.add(fs);
        }
        return list;
    }

    private int[] getExpenses(String[][] costGoups) {
        return aggregateYearAmount(costGoups,isNotProgramCost);
    }

    private int[] getProgramCost(String[][] costGoups) {
        return aggregateYearAmount(costGoups,isProgramCost);
    }

    private int[] aggregateYearAmount(String[][] costGroups, Predicate<String[]> filter) {
        int[] sum = new int[numberOfYears];
        for (String[] group : costGroups) {
            if (filter.test(group)) {
                int[] groupYearValue = getIntValues(group);
                for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++) {
                    sum[yearIndex] = sum[yearIndex] + groupYearValue[yearIndex];
                }
            }
        }
        return sum;
    }


    private int[] getIntValues(String[] aLine) {
        if (aLine[0] instanceof String) {
            return getIntDataFromStringLine(Arrays.copyOfRange(aLine, 1, aLine.length));
        } else
            return getIntDataFromStringLine(aLine);
    }

    private int[] getIntDataFromStringLine(String[] aLine) {
        int[] data = new int[aLine.length];
        for (int i = 0; i < aLine.length; i++) {
            try {
                data[i] = Integer.parseInt(aLine[i].replaceAll(",", ""));
            } catch (NumberFormatException ex) {
                data[i] = -1;
                continue;
            }
        }
        return data;
    }


}
