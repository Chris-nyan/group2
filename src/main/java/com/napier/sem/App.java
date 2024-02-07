package com.napier.sem;


import java.sql.*;
import java.util.ArrayList;

import java.sql.*;
import java.util.ArrayList;

public class App {
    private Connection con = null;

    public static void main(String[] args) {
        App app = new App();
        app.connect();
        ArrayList<City> sortCity = app.sortCity();
        app.displaySortCity(sortCity);
        ArrayList<CityWorld> sortCityWorld = app.sortCityWorld();
        app.displaySortCityWorld(sortCityWorld);
        ArrayList<CityRegion> sortCityRegion = app.sortCityRegion();
        app.displaySortCityRegion(sortCityRegion);
        app.disconnect();
    }

    public ArrayList<City> sortCity() {
        ArrayList<City> sortCityList = new ArrayList<>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District, country.Continent " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = 'Asia' " +
                "ORDER BY city.Population DESC";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                City sortCity = new City();
                sortCity.setName(rs.getString("Name"));
                sortCity.setCountryName(rs.getString("country_name"));
                sortCity.setPopulation(rs.getInt("Population"));
                sortCity.setDistrict(rs.getString("District"));
                sortCity.setContinent(rs.getString("Continent"));
                sortCityList.add(sortCity);
            }
            return sortCityList;

        } catch (Exception e) {
            System.out.println("Error on sort city");
            e.printStackTrace();
        }
        return null;
    }

    public void displaySortCity(ArrayList<City> sortCities) {
        if (sortCities != null && !sortCities.isEmpty()) {
            System.out.println("Population of the cities in a continent, Asia, sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-15s | %-25s | %-15s |\n", "Name", "Country Name", "Population", "District", "Continent");

            for (City sortCity : sortCities) {
                System.out.printf("| %-25s | %-25s | %-15d | %-25s | %-15s |\n",
                        sortCity.getName(), sortCity.getCountryName(), sortCity.getPopulation(), sortCity.getDistrict(), sortCity.getContinent());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }

    /**
     * Sorting cities ON THE WORLD according to population
     */
    public ArrayList<CityWorld> sortCityWorld() {
        ArrayList<CityWorld> sortCityWorldList = new ArrayList<>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                CityWorld sortCityWorld = new CityWorld();
                sortCityWorld.setCity_name(rs.getString("Name"));
                sortCityWorld.setCountry_name(rs.getString("country_name"));
                sortCityWorld.setDistrict(rs.getString("District"));
                sortCityWorld.setPopulation(rs.getInt("Population"));
                sortCityWorldList.add(sortCityWorld);
            }
            return sortCityWorldList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityWorld(ArrayList<CityWorld> sortCitiesWorld) {
        if (sortCitiesWorld != null && !sortCitiesWorld.isEmpty()) {
            System.out.println("Population of the cities around the world sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (CityWorld sortCityWorld : sortCitiesWorld) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityWorld.getCity_name(), sortCityWorld.getCountry_name(), sortCityWorld.getDistrict(), sortCityWorld.getPopulation());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }
    /**
     * Sorting cities IN THE REGION according to Population
     */
    public ArrayList<CityRegion> sortCityRegion() {
        ArrayList<CityRegion> sortCityRegionList = new ArrayList<>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = 'Middle East'" +
                "ORDER BY city.Population DESC";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                CityRegion sortCityRegion = new CityRegion();
                sortCityRegion.setCity_name(rs.getString("Name"));
                sortCityRegion.setCountry_name(rs.getString("country_name"));
                sortCityRegion.setDistrict(rs.getString("District"));
                sortCityRegion.setPopulation(rs.getInt("Population"));
                sortCityRegionList.add(sortCityRegion);
            }
            return sortCityRegionList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityRegion(ArrayList<CityRegion> sortCitiesRegion) {
        if (sortCitiesRegion != null && !sortCitiesRegion.isEmpty()) {
            System.out.println("Population of the cities in a region called Middle East sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (CityRegion sortCityRegion : sortCitiesRegion) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityRegion.getCity_name(), sortCityRegion.getCountry_name(), sortCityRegion.getDistrict(), sortCityRegion.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }



    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(30000);
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
