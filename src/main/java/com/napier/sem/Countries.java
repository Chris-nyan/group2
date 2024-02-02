package com.napier.sem;

/**
 * Represents a countries
 */
public class Countries
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
    private String Population;

    /**
     * Capital
     */
    private String Capital;

    public Countries(String code, String name, String continent, String region, String population, String capital) {
        Code = code;
        Name = name;
        Continent = continent;
        Region = region;
        Population = population;
        Capital = capital;
    }
    public Countries(){

    }

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

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }


}
