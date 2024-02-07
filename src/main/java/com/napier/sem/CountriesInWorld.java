package com.napier.sem;

import java.math.BigDecimal;

/**
 * Represents a countries
 */
public class CountriesInWorld
{

    /**
     * Country code
     */
    private String Code;

    /**
     * Country Name
     */
    private String Name ;

    /**
     * Continent
     */
    private String Continent;

    /**
     * region
     */
    private String Region;

    /**
     * region
     */
    private BigDecimal SurfaceArea;

    /**
     * region
     */
    private int IndepYear;


    /**
     * Population
     */
    private int Population;

    /**
     * region
     */
    private BigDecimal LifeExpectancy;

    /**
     * region
     */
    private BigDecimal GNP;

    /**
     * region
     */
    private BigDecimal GNPOld;

    /**
     * region
     */
    private String LocalName;

    /**
     * region
     */
    private String GovernmentForm;

    /**
     * region
     */
    private String HeadOfState;

    /**
     * Capital
     */
    private int Capital;

    /**
     * region
     */
    private String Code2;

//    public Countries(String code, String name, String continent, String region, String population, String capital) {
//        Code = code;
//        Name = name;
//        Continent = continent;
//        Region = region;
//        Population = population;
//        Capital = capital;
//    }
//    public Countries(){
//
//    }


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

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public BigDecimal getSurfaceArea() {
        return SurfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        SurfaceArea = surfaceArea;
    }

    public int getIndepYear() {
        return IndepYear;
    }

    public void setIndepYear(int indepYear) {
        IndepYear = indepYear;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }

    public BigDecimal getLifeExpectancy() {
        return LifeExpectancy;
    }

    public void setLifeExpectancy(BigDecimal lifeExpectancy) {
        LifeExpectancy = lifeExpectancy;
    }

    public BigDecimal getGNP() {
        return GNP;
    }

    public void setGNP(BigDecimal GNP) {
        this.GNP = GNP;
    }

    public BigDecimal getGNPOld() {
        return GNPOld;
    }

    public void setGNPOld(BigDecimal GNPOld) {
        this.GNPOld = GNPOld;
    }

    public String getLocalName() {
        return LocalName;
    }

    public void setLocalName(String localName) {
        LocalName = localName;
    }

    public String getGovernmentForm() {
        return GovernmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        GovernmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return HeadOfState;
    }

    public void setHeadOfState(String headOfState) {
        HeadOfState = headOfState;
    }

    public int getCapital() {
        return Capital;
    }

    public void setCapital(int capital) {
        Capital = capital;
    }

    public String getCode2() {
        return Code2;
    }

    public void setCode2(String code2) {
        Code2 = code2;
    }
}
