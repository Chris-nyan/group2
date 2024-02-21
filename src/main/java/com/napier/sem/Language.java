package com.napier.sem;

public class Language {
    private String Language;
    private int TotalPopulation;
    private double PercentageOfWorldPopulation;

    private double percentNotInWorld;

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public int getTotalPopulation() {
        return TotalPopulation;
    }

    public void setTotalPopulation(int totalPopulation) {
        TotalPopulation = totalPopulation;
    }

    public double getPercentageOfWorldPopulation() {
        return PercentageOfWorldPopulation;
    }

    public void setPercentageOfWorldPopulation(double percentageOfWorldPopulation) {
        PercentageOfWorldPopulation = percentageOfWorldPopulation;
    }

    public double getPercentNotInWorld() {
        return percentNotInWorld;
    }

    public void setPercentNotInWorld(double percentNotInWorld) {
        this.percentNotInWorld = percentNotInWorld;
    }
}
