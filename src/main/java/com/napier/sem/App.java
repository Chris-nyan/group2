package com.napier.sem;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
//importing Arraylist for using array list in cities

public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    public static void main(String[] args) {
        App app = new App();
        app.connect();
        ArrayList<City> sortCity = app.sortCity();
//        app.displaySortCity(sortCity);
        ArrayList<CityWorld> sortCityWorld = app.sortCityWorld();
//        app.displaySortCityWorld(sortCityWorld);
        ArrayList<CityRegion> sortCityRegion = app.sortCityRegion();
//        app.displaySortCityRegion(sortCityRegion);
        ArrayList<CityCountry> sortCityCountry = app.sortCityCountry();
        app.displaySortCityCountry(sortCityCountry);
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
            System.out.println("No cities found.");
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

    /**
     * Sorting cities IN THE COUNTRY according to Population
     */
    public ArrayList<CityCountry> sortCityCountry() {
        ArrayList<CityCountry> sortCityCountryList = new ArrayList<>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = 'Myanmar'" +
                "ORDER BY city.Population DESC";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                CityCountry sortCityCountry = new CityCountry();
                sortCityCountry.setCity_name(rs.getString("Name"));
                sortCityCountry.setCountry_name(rs.getString("country_name"));
                sortCityCountry.setDistrict(rs.getString("District"));
                sortCityCountry.setPopulation(rs.getInt("Population"));
                sortCityCountryList.add(sortCityCountry);
            }
            return sortCityCountryList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityCountry(ArrayList<CityCountry> sortCitiesCountry) {
        if (sortCitiesCountry != null && !sortCitiesCountry.isEmpty()) {
            System.out.println("Population of the cities in a country called Myanmar sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (CityCountry sortCityCountry : sortCitiesCountry) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityCountry.getCity_name(), sortCityCountry.getCountry_name(), sortCityCountry.getDistrict(), sortCityCountry.getPopulation());
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
    /**
     * Display country details.
     */
    public void displayCountry(ArrayList<Countries> countries) {
        if (countries != null && !countries.isEmpty()) {
            for (Countries c : countries) {
                System.out.println(
                        "Country Code: " + c.getCode() + "\n"
                                + "Name: " + c.getName() + "\n"
                                + "Continent: " + c.getContinent() + "\n"
                                + "Region: " + c.getRegion() + "\n"
                                + "Population: " + c.getPopulation() + "\n"
                                + "Capital: " + c.getCapital() + "\n"
                );
            }
        } else {
            System.out.println("No country details available");
        }
    }


    public ArrayList<Countries> getcountries()
    {
        ArrayList<Countries> a = new ArrayList<Countries>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT Code, Name, Continent, Region, Population, Capital " +
                            " FROM country " +
                            " ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Countries coun = new Countries();
                coun.setCode(rset.getString("Code"));
                coun.setName(rset.getString("Name"));
                coun.setContinent(rset.getString("Continent"));
                coun.setRegion(rset.getString("Region"));
                coun.setPopulation(rset.getString("Population"));
                coun.setCapital(rset.getString("Capital"));
                a.add(coun);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
        }
    }

    public ArrayList<Countries> getContinent()
    {
        ArrayList<Countries> a = new ArrayList<Countries>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT Continent " +
                            " FROM country " +
                            " ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Countries coun = new Countries();
                coun.setContinent(rset.getString("Continent"));
                a.add(coun);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries details");
            return null;
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