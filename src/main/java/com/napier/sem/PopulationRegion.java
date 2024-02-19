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
    private long TotalPopulation;
    /**
     * Total_Population
     */
    private long PopulationInCities;
    /**
     * Population In Cities
     */
    private long PopulationNotInCities;
    /**
     * Population Not In Cities
     */
    private BigDecimal PercentageInCities;
    /**
     * Percentage Population In Cities
     */
    private BigDecimal PercentageNotInCities;
    /**
     * Percentage Population Not In Cities
     */

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public long getTotalPopulation() {
        return TotalPopulation;
    }

    public void setTotalPopulation(long totalPopulation) {
        TotalPopulation = totalPopulation;
    }

    public long getPopulationInCities() {
        return PopulationInCities;
    }

    public void setPopulationInCities(long populationInCities) {
        PopulationInCities = populationInCities;
    }

    public long getPopulationNotInCities() {
        return PopulationNotInCities;
    }

    public void setPopulationNotInCities(long populationNotInCities) {
        PopulationNotInCities = populationNotInCities;
    }

    public BigDecimal getPercentageInCities() {
        return PercentageInCities;
    }

    public void setPercentageInCities(BigDecimal percentageInCities) {
        PercentageInCities = percentageInCities;
    }

    public BigDecimal getPercentageNotInCities() {
        return PercentageNotInCities;
    }

    public void setPercentageNotInCities(BigDecimal percentageNotInCities) {
        PercentageNotInCities = percentageNotInCities;
    }
}