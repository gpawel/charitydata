package org.qamation.charities.info;

public class FinStats {
    private int year;
    private int totalIncome;
    private int totalExpences;
    private int totalProgramCost;

    public FinStats (int year, int inc, int prog, int exp) {
        this.year = year;
        this.totalIncome = inc;
        this.totalExpences = exp;
        this.totalProgramCost = prog;
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

    @Override
    public String toString() {
        return String.valueOf(year)+"\t"+String.valueOf(totalIncome)+"\t"+String.valueOf(totalProgramCost)+"\t"+String.valueOf(totalExpences);
    }
}
