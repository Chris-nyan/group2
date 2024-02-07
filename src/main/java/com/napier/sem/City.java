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
     * Country Name
     */
    private String CountryName;
    /**
     *District
     */
    private String District;
    /**
     *Population
     */
    private int population;

    /**
     * Continent
     */
    private String continent;

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

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

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
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
