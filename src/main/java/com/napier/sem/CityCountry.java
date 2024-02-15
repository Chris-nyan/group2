package com.napier.sem;

public class CityCountry {
    /**
     * City Name
     */
    private String City_name;
    /**
     * District Name
     */
    private String District;
    /**
     * Country Name
     */
    private String Country_name;
    /**
     * Population
     */
    private int Population;

    public String getCity_name() {
        return City_name;
    }

    public void setCity_name(String city_name) {
        City_name = city_name;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getCountry_name() {
        return Country_name;
    }

    public void setCountry_name(String country_name) {
        Country_name = country_name;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }
}
