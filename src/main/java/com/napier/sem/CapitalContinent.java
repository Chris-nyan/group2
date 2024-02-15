package com.napier.sem;

import java.math.BigDecimal;

/**
 * Represents a countries
 */
public class CapitalContinent
{

    /**
     * Country continent
     */
    private String Capital_Name;

    /**
     * Country continent
     */
    private String Country_Name;

    /**
     * Country continent
     */
    private int Population;

    public String getCapital_Name() {
        return Capital_Name;
    }

    public void setCapital_Name(String capital_Name) {
        Capital_Name = capital_Name;
    }

    public String getCountry_Name() {
        return Country_Name;
    }

    public void setCountry_Name(String country_Name) {
        Country_Name = country_Name;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }
}
