package com.napier.sem;

import java.math.BigDecimal;

/**
 * Represents a population in region
 */
public class PopulationRegion{

    /**
     * Population in each Region
     */
    private String Region;
    /**
     *Region
     */
    private long Total_Population;
    /**
     * Total_Population
     */
    private long Population_In_Cities;
    /**
     * Population In Cities
     */
    private long Population_Not_In_Cities;
    /**
     * Population Not In Cities
     */
    private BigDecimal Percentage_Population_In_Cities;
    /**
     * Percentage Population In Cities
     */
    private BigDecimal Percentage_Population_Not_In_Cities;
    /**
     * Percentage Population Not In Cities
     */

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public long getTotal_Population() {
        return Total_Population;
    }

    public void setTotal_Population(long total_Population) {
        Total_Population = total_Population;
    }

    public long getPopulation_In_Cities() {
        return Population_In_Cities;
    }

    public void setPopulation_In_Cities(long population_In_Cities) {
        Population_In_Cities = population_In_Cities;
    }

    public long getPopulation_Not_In_Cities() {
        return Population_Not_In_Cities;
    }

    public void setPopulation_Not_In_Cities(long population_Not_In_Cities) {
        Population_Not_In_Cities = population_Not_In_Cities;
    }

    public BigDecimal getPercentage_Population_In_Cities() {
        return Percentage_Population_In_Cities;
    }

    public void setPercentage_Population_In_Cities(BigDecimal percentage_Population_In_Cities) {
        Percentage_Population_In_Cities = percentage_Population_In_Cities;
    }

    public BigDecimal getPercentage_Population_Not_In_Cities() {
        return Percentage_Population_Not_In_Cities;
    }

    public void setPercentage_Population_Not_In_Cities(BigDecimal percentage_Population_Not_In_Cities) {
        Percentage_Population_Not_In_Cities = percentage_Population_Not_In_Cities;
    }
}