package com.napier.sem;

import java.math.BigDecimal;

/**
 * Represents a countries
 */
public class UserInputWorld
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
     * Population
     */
    private int Population;

    /**
     * Capital
     */
    private int Capital;


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


    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }


    public int getCapital() {
        return Capital;
    }

    public void setCapital(int capital) {
        Capital = capital;
    }


}