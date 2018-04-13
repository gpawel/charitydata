package org.qamation.charities.info;

public class FinStats {
    private int year;
    private int totalIncome;
    private int totalExpences;
    private int totalProgramCost;

    public FinStats (String year, String inc, String exp) {
        this.year = Integer.parseInt(year);
        this.totalIncome = Integer.parseInt(inc);
        this.totalExpences = Integer.parseInt(exp);
    }

    public int getYear() {
        return year;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public int getTotalExpences() {
        return totalExpences;
    }
}
