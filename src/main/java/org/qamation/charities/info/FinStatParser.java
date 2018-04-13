package org.qamation.charities.info;

import org.qamation.charities.extractor.FinInfoExtractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FinStatParser {

    private static Predicate<String[]> isProgramCost = p -> p[0].startsWith("Program");
    private static Predicate<String[]> isNotProgramCost = p -> !isProgramCost.test(p);

    private List<FinStats> finList = null;
    private int numberOfYears;

    public FinStatParser(FinInfoExtractor extractor) {
        this.finList = new ArrayList<>();
        this.numberOfYears = extractor.getNumberOfYears();
        this.finList = parseExtractor(extractor);
    }

    public List<FinStats> getFinStats() {
        return finList;
    }

    private List<FinStats> parseExtractor(FinInfoExtractor extractor) {
        List<FinStats> list = new ArrayList<>();
        int numberOfYears = extractor.getNumberOfYears();
        int[] revenue = getIntValues(extractor.getRevenue());
        int[] programCost = getProgramCost(extractor.getCostGoups());
        int[] expences = getExpenses(extractor.getCostGoups());
        for (int yearIndex=0; yearIndex<numberOfYears; yearIndex++) {
            int year = Integer.parseInt(extractor.getYears()[yearIndex]);
            FinStats fs = new FinStats(year,revenue[yearIndex],programCost[yearIndex],expences[yearIndex]);
            list.add(fs);
        }
        return list;
    }

    private int[] getExpenses(String[][] costGoups) {
        return aggregateYearAmount(costGoups,isNotProgramCost);
    }

    private int[] getProgramCost(String[][] costGoups) {
        return aggregateYearAmount(costGoups,isNotProgramCost);
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
