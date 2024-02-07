package com.napier.sem;

import java.math.BigDecimal;

/**
 * Represents a countries
 */
public class Region
{

    /**
     * Country continent
     */
    private String Code;

    /**
     * Country continent
     */
    private String Name;

    /**
     * Country continent
     */
    private String Region;

    /**
     * Country continent
     */
    private String Continent;

    /**
     * Country continent
     */
    private int Population;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }
}
