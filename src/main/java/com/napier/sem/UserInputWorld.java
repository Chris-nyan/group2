package com.napier.sem;

import java.math.BigDecimal;

/**
 * Represents a countries
 */
public class UserInputWorld
{

    /**
     * Country continent
     */
    private String Code;

    /**
     * Country continent
     */
    private String country_name;

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

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
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
