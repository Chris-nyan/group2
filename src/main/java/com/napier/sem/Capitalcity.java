package com.napier.sem;

/**
        * Represents a country with capital city details
 */
public class Capitalcity {

    /**
            * Country Name
     */
    private String Name;

    /**
            * Capital City
     */
    private String Capital;

    /**
            * Population of the Capital City
     */
    private String Population;

    public Capitalcity(){

    }
    public Capitalcity(String name, String capital, String population) {
        Name = name;
        Capital = capital;
        Population = population;
    }

// Other fields and methods remain the same...

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    // Other methods remain the same...
}