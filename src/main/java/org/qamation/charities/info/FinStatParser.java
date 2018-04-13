package org.qamation.charities.info;

import org.qamation.charities.extractor.FinInfoExtractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinStatParser {

    private List<FinStats> finList = null;

    public FinStatParser(FinInfoExtractor extractor) {
        this.finList = new ArrayList<>();
        this.finList = parseExtractor(extractor);
    }

    public List<FinStats> getFinStats() {
        return finList;
    }

    private List<FinStats> parseExtractor(FinInfoExtractor extractor) {
        int finDataLength = extractor.getNumberOfYears();
        int[] revenue = getIntFromFinLine(extractor.getRevenue());
        int[] programCost = getProgramCost(extractor.getCostGoups());
        int[] expences = getExpenses(extractor.getCostGoups());


    }

    private int[] getIntFromFinLine(String[] aLine) {
        if (aLine[0] instanceof String) {
            return getIntDataFromStringLine(Arrays.copyOfRange(aLine, 1, aLine.length));
        }
        else
            return getIntDataFromStringLine(aLine);
    }

    private int[] getIntDataFromStringLine(String[] aLine) {
        int[] data = new int[aLine.length];
        for(int i=0; i<aLine.length; i++) {
            try{
                data[i] = Integer.parseInt(aLine[i].replaceAll(",",""));
            }
            catch(NumberFormatException ex) {
                data[i]=-1;
                continue;
            }
        }
        return data;

    }



}
