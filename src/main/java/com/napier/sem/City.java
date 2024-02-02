package com.napier.sem;

public class City {
    /**
     * City ID
     */
    private int ID;

    /**
     * City name
     */
    private String Name;
    /**
     * Country code
     */
    private String CountryCode;
    /**
     *District
     */
    private String District;
    /**
     *Population
     */
    private int population;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
