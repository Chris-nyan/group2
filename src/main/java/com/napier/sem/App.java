package com.napier.sem;


import java.sql.*;
import java.util.ArrayList;

/**
 * Main function where it calls out all of the features and run in it
 */
public class App {
    private Connection con = null;

    public static void main(String[] args) {
        App app = new App();
        app.connect();
        /**
         * Displaying for sorting based on continent
         */
        ArrayList<City> sortCity = app.sortCity();
        app.displaySortCity(sortCity);
        /**
         * Displaying for sorting based on world
         */
        ArrayList<CityWorld> sortCityWorld = app.sortCityWorld();
        app.displaySortCityWorld(sortCityWorld);
        /**
         * Displaying for sorting based on Region
         */
        ArrayList<CityRegion> sortCityRegion = app.sortCityRegion();
        app.displaySortCityRegion(sortCityRegion);
        /**
         * Displaying for sorting based on Country
         */
        ArrayList<CityCountry> sortCityCountry = app.sortCityCountry();
        app.displaySortCityCountry(sortCityCountry);
        /**
         * Displaying for sorting based on district
         */
        ArrayList<CityDistrict> sortCityDistrict = app.sortCityDistrict();
        app.displaySortCityDistrict(sortCityDistrict);
        /**
         * Displaying for top 5 populated cities on the world
         */
        ArrayList<TopCity> sortCityTopWorld = app.sortCityTopWorld();
        app.displaySortCityTopWorld(sortCityTopWorld);
        /**
         * Displaying for top 5 populated cities in a continent called Europe
         */
        ArrayList<TopCity> sortCityTopContinent = app.sortCityTopContinent();
        app.displaySortCityTopContinent(sortCityTopContinent);
        /**
         * Displaying for top 5 populated cities in a country called United Kingdom
         */
        ArrayList<TopCity> sortCityTopCountry = app.sortCityTopCountry();
        app.displaySortCityTopCountry(sortCityTopCountry);
        /**
         * Displaying for top 5 populated cities in a region called Middle East
         */
        ArrayList<TopCity> sortCityTopRegion = app.sortCityTopRegion();
        app.displaySortCityTopRegion(sortCityTopRegion);
        /**
         * Displaying for top 5 populated cities in a district called England
         */
        ArrayList<TopCity> sortCityTopDistrict = app.sortCityTopDistrict();
        app.displaySortCityTopDistrict(sortCityTopDistrict);

        app.disconnect();
    }

    /**
     * Sorting cities in a continent called "Asia"
     * @return Sorting result of cities based on a continent
     */
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

    /**
     * Displaying sorted cities for a continent called Asia
     * @param sortCities
     */
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

    /**
     * Displaying cities all around the world
     * @param sortCitiesWorld
     */
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

    /**
     * Sorting cities IN A DISTRICT according to population
     */

    public ArrayList<CityDistrict> sortCityDistrict() {
        ArrayList<CityDistrict> sortCityDistrictList = new ArrayList<>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = 'Mandalay'" +
                "ORDER BY city.Population DESC";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                CityDistrict sortCityDistrict = new CityDistrict();
                sortCityDistrict.setCity_name(rs.getString("Name"));
                sortCityDistrict.setCountry_name(rs.getString("country_name"));
                sortCityDistrict.setDistrict(rs.getString("District"));
                sortCityDistrict.setPopulation(rs.getInt("Population"));
                sortCityDistrictList.add(sortCityDistrict);
            }
            return sortCityDistrictList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityDistrict(ArrayList<CityDistrict> sortCitiesDistrict) {
        if (sortCitiesDistrict != null && !sortCitiesDistrict.isEmpty()) {
            System.out.println("Population of the cities in a District called Mandalay sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (CityDistrict sortCityDistrict : sortCitiesDistrict) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityDistrict.getCity_name(), sortCityDistrict.getCountry_name(), sortCityDistrict.getDistrict(), sortCityDistrict.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }

    /**
     *  The top 5 populated cities in the world
     */
    public ArrayList<TopCity> sortCityTopWorld() {
        ArrayList<TopCity> sortCityTopWorldList = new ArrayList<TopCity>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC LIMIT 5";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                TopCity sortCityTopWorld = new TopCity();
                sortCityTopWorld.setCity_name(rs.getString("Name"));
                sortCityTopWorld.setCountry_name(rs.getString("country_name"));
                sortCityTopWorld.setDistrict(rs.getString("District"));
                sortCityTopWorld.setPopulation(rs.getInt("Population"));
                sortCityTopWorldList.add(sortCityTopWorld);
            }
            return sortCityTopWorldList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityTopWorld(ArrayList<TopCity> sortCitiesTopWorld) {
        if (sortCitiesTopWorld != null && !sortCitiesTopWorld.isEmpty()) {
            System.out.println("Population of the top 5 populated cities sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (TopCity sortCityTopWorld : sortCitiesTopWorld) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityTopWorld.getCity_name(), sortCityTopWorld.getCountry_name(), sortCityTopWorld.getDistrict(), sortCityTopWorld.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }

    /**
     *  The top 5 populated cities in the a continent called Europe
     */
    public ArrayList<TopCity> sortCityTopContinent() {
        ArrayList<TopCity> sortCityTopContientList = new ArrayList<TopCity>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = 'Europe'" +
                "ORDER BY city.Population DESC LIMIT 5";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                TopCity sortCityTopContinent = new TopCity();
                sortCityTopContinent.setCity_name(rs.getString("Name"));
                sortCityTopContinent.setCountry_name(rs.getString("country_name"));
                sortCityTopContinent.setDistrict(rs.getString("District"));
                sortCityTopContinent.setPopulation(rs.getInt("Population"));
                sortCityTopContientList.add(sortCityTopContinent);
            }
            return sortCityTopContientList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityTopContinent(ArrayList<TopCity> sortCitiesTopContinent) {
        if (sortCitiesTopContinent != null && !sortCitiesTopContinent.isEmpty()) {
            System.out.println("Population of the top 5 populated cities in a continent called Europe sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (TopCity sortCityTopContinent : sortCitiesTopContinent) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityTopContinent.getCity_name(), sortCityTopContinent.getCountry_name(), sortCityTopContinent.getDistrict(), sortCityTopContinent.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }

    /**
     *  The top 5 populated cities in the a country called United Kingdom
     */
    public ArrayList<TopCity> sortCityTopCountry() {
        ArrayList<TopCity> sortCityTopCountryList = new ArrayList<TopCity>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = 'United Kingdom'" +
                "ORDER BY city.Population DESC LIMIT 5";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                TopCity sortCityTopCountry = new TopCity();
                sortCityTopCountry.setCity_name(rs.getString("Name"));
                sortCityTopCountry.setCountry_name(rs.getString("country_name"));
                sortCityTopCountry.setDistrict(rs.getString("District"));
                sortCityTopCountry.setPopulation(rs.getInt("Population"));
                sortCityTopCountryList.add(sortCityTopCountry);
            }
            return sortCityTopCountryList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityTopCountry(ArrayList<TopCity> sortCitiesTopCountry) {
        if (sortCitiesTopCountry != null && !sortCitiesTopCountry.isEmpty()) {
            System.out.println("Population of the top 5 populated cities in a region called Middle East sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (TopCity sortCityTopRegion : sortCitiesTopCountry) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityTopRegion.getCity_name(), sortCityTopRegion.getCountry_name(), sortCityTopRegion.getDistrict(), sortCityTopRegion.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }
    /**
     *  The top 5 populated cities in the a region called Middle East
     */
    public ArrayList<TopCity> sortCityTopRegion() {
        ArrayList<TopCity> sortCityTopRegionList = new ArrayList<TopCity>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = 'Middle East'" +
                "ORDER BY city.Population DESC LIMIT 5";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                TopCity sortCityTopRegion = new TopCity();
                sortCityTopRegion.setCity_name(rs.getString("Name"));
                sortCityTopRegion.setCountry_name(rs.getString("country_name"));
                sortCityTopRegion.setDistrict(rs.getString("District"));
                sortCityTopRegion.setPopulation(rs.getInt("Population"));
                sortCityTopRegionList.add(sortCityTopRegion);
            }
            return sortCityTopRegionList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityTopRegion(ArrayList<TopCity> sortCitiesTopRegion) {
        if (sortCitiesTopRegion != null && !sortCitiesTopRegion.isEmpty()) {
            System.out.println("Population of the top 5 populated cities in a region called Middle East sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (TopCity sortCityTopRegion : sortCitiesTopRegion) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityTopRegion.getCity_name(), sortCityTopRegion.getCountry_name(), sortCityTopRegion.getDistrict(), sortCityTopRegion.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }

    /**
     *  The top 5 populated cities in a district called England
     */
    public ArrayList<TopCity> sortCityTopDistrict() {
        ArrayList<TopCity> sortCityTopDistrictList = new ArrayList<TopCity>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = 'England'" +
                "ORDER BY city.Population DESC LIMIT 5";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                TopCity sortCityTopDistrict = new TopCity();
                sortCityTopDistrict.setCity_name(rs.getString("Name"));
                sortCityTopDistrict.setCountry_name(rs.getString("country_name"));
                sortCityTopDistrict.setDistrict(rs.getString("District"));
                sortCityTopDistrict.setPopulation(rs.getInt("Population"));
                sortCityTopDistrictList.add(sortCityTopDistrict);
            }
            return sortCityTopDistrictList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityTopDistrict(ArrayList<TopCity> sortCitiesTopDistrict) {
        if (sortCitiesTopDistrict != null && !sortCitiesTopDistrict.isEmpty()) {
            System.out.println("Population of the top 5 populated cities in a district called England sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (TopCity sortCityTopDistrict : sortCitiesTopDistrict) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityTopDistrict.getCity_name(), sortCityTopDistrict.getCountry_name(), sortCityTopDistrict.getDistrict(), sortCityTopDistrict.getPopulation());
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
