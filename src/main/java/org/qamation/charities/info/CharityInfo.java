package org.qamation.charities.info;

import java.util.ArrayList;
import java.util.List;

public class CharityInfo {

    private String charityName;
    private List<FinStats> stats;

    public CharityInfo(String name) {
        stats = new ArrayList<FinStats>();
        charityName = name;
    }

    public void addFinStats (FinStats s) {
        stats.add(s);
    }

    public String getCharityName() {
        return charityName;
    }

    public List<FinStats> getStats() {
        return stats;
    }
}
