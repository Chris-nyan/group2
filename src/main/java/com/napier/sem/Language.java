package com.napier.sem;

public class Language {
    private String language;
    private int totalPopulation;
    private double percentWorld, percentNonWorld;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(int totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public double getPercentWorld() {
        return percentWorld;
    }

    public void setPercentWorld(double percentWorld) {
        this.percentWorld = percentWorld;
    }

    public double getPercentNonWorld() {
        return percentNonWorld;
    }

    public void setPercentNonWorld(double percentNonWorld) {
        this.percentNonWorld = percentNonWorld;
    }
}
