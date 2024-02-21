package com.napier.sem;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
//importing Arraylist for using array list in cities

public class App
{

    /**
     * Connection to MySQL database.
     */

    public static void main(String[] args) {
        App app = new App();
        Connection con;
        if(args.length < 1){
            con = app.connect("localhost:33061", 0);
        }else{
            con =app.connect(args[0], Integer.parseInt(args[1]));
        }

        ArrayList<City> sortCity = app.sortCity(con);

        ArrayList<CityWorld> sortCityWorld = app.sortCityWorld(con);

        ArrayList<CityRegion> sortCityRegion = app.sortCityRegion(con);

        ArrayList<CityCountry> sortCityCountry = app.sortCityCountry(con);

        // Retrieve country in world details
        ArrayList<CountriesInWorld> country = app.getCountriesInWorld(con);

        //Retrieve continent details
        ArrayList<Continent> continent = app.getContinent(con);

        //Retrieve continent details
        ArrayList<Region> regions = app.getRegion(con);

        //Retrieve continent details
        ArrayList<UserInputWorld> userInputWorlds = app.getUserInputWorld(con);

        //Retrieve continent details
        ArrayList<UserInputContinent> userInputContinent = app.getUserInputContinent(con);

        //Retrieve continent details
        ArrayList<UserInputRegion> userInputRegion = app.getUserInputRegion(con);

        //Retrieve continent details
        ArrayList<Capitalcity> capitalcities = app.getCapitalCity(con);

        //Retrieve continent details
        ArrayList<CapitalContinent> capitalContinents = app.getCapitalContinent(con);

        //Retrieve continent details
        ArrayList<CapitalRegion> capitalRegions = app.getCapitalRegion(con);

        //Retrieve continent details
        ArrayList<InputCapitalWorld> inputCapitalWorlds = app.getInputCapitalWorld(con);

        //Retrieve continent details
        ArrayList<InputCapitalContinent> inputCapitalContinents = app.getInputCapitalContinent(con);

        //Retrieve continent details
        ArrayList<InputCapitalRegion> inputCapitalRegions = app.getInputCapitalRegion(con);

        // Retrieve population in continent details
        ArrayList<Population> population = app.getPopulation(con);

        //Retrieve population in region details
        ArrayList<PopulationRegion> population_region = app.getPopulationRegion(con);

        //Retrieve population in country details
        ArrayList<PopulationCountry> population_country = app.getPopulationCountry(con);

        /**
         * Displaying for sorting based on district
         */
        ArrayList<CityDistrict> sortCityDistrict = app.sortCityDistrict(con);

        /**
         * Displaying for top 5 populated cities on the world
         */
        ArrayList<TopCity> sortCityTopWorld = app.sortCityTopWorld(con);

        /**
         * Displaying for top 5 populated cities in a continent called Europe
         */
        ArrayList<TopCity> sortCityTopContinent = app.sortCityTopContinent(con);

        /**
         * Displaying for top 5 populated cities in a country called United Kingdom
         */
        ArrayList<TopCity> sortCityTopCountry = app.sortCityTopCountry(con);

        /**
         * Displaying for top 5 populated cities in a region called Middle East
         */
        ArrayList<TopCity> sortCityTopRegion = app.sortCityTopRegion(con);

        /**
         * Displaying for top 5 populated cities in a district called England
         */
        ArrayList<TopCity> sortCityTopDistrict = app.sortCityTopDistrict(con);

        // Display result
        app.displayCountry(country);
        app.displayContinent(continent);
        app.displayRegion(regions);
        app.displayUserInputWorld(userInputWorlds);
        app.displaySortCity(sortCity);
        app.displaySortCityWorld(sortCityWorld);
        app.displaySortCityRegion(sortCityRegion);
        app.displaySortCityCountry(sortCityCountry);
        app.displayUserInputContinent(userInputContinent);
        app.displayUserInputRegion(userInputRegion);
        app.displayCapital(capitalcities);
        app.displayCapitalContinent(capitalContinents);
        app.displayCapitalRegion(capitalRegions);
        app.displayInputCapitalWorld(inputCapitalWorlds);
        app.displayInputCapitalContinent(inputCapitalContinents);
        app.displayInputCapitalRegion(inputCapitalRegions);
        app.displayPopulation(population);
        app.displayPopulationRegion(population_region);
        app.displayPopulationCountry(population_country);
        app.displaySortCityDistrict(sortCityDistrict);
        app.displaySortCityTopWorld(sortCityTopWorld);
        app.displaySortCityTopContinent(sortCityTopContinent);
        app.displaySortCityTopCountry(sortCityTopCountry);
        app.displaySortCityTopRegion(sortCityTopRegion);
        app.displaySortCityTopDistrict(sortCityTopDistrict);


        app.disconnect(con);
    }
//sortCity
    public ArrayList<City> sortCity(Connection con) {


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
//                if (sortCity != null)
//                    continue;
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
    public ArrayList<CityWorld> sortCityWorld(Connection con) {
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
    public ArrayList<CityRegion> sortCityRegion(Connection con) {
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
     * Connect to the MySQL database.
     */
    public ArrayList<CityCountry> sortCityCountry(Connection con) {
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


    //Connect
    public Connection connect(String location, int delay) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        Connection con = null;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(delay);
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected...");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
                return null;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
                return null;
            }
        }
        return con;
    }


    /**
     * Disconnect from the MySQL database.
     */


    /**
     * All the countries in the world organised by largest population to smallest.
     */
    public ArrayList<CountriesInWorld> getCountriesInWorld(Connection con)
    {
        ArrayList<CountriesInWorld> a = new ArrayList<CountriesInWorld>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT Code,Name,Region,Continent,Population,Capital FROM country " +
                            " ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CountriesInWorld coun = new CountriesInWorld();
                coun.setCode(rset.getString("Code"));
                coun.setName(rset.getString("Name"));
                coun.setRegion(rset.getString("Region"));
                coun.setContinent(rset.getString("Continent"));
                coun.setPopulation(rset.getInt("Population"));
                coun.setCapital(rset.getInt("Capital"));
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
    /**
     * Display country details.
     */
    public void displayCountry(ArrayList<CountriesInWorld> countries) {
        if (countries != null && !countries.isEmpty()) {
            System.out.println("All the countries in the world organized by largest population to smallest");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-40s | %-30s | %-20s | %-15s | %-10s | \n",
                    "Code", "Country Name", "Region", "Continent", "Population", "Capital");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (CountriesInWorld coun : countries) {
                System.out.printf("| %-5s | %-40s | %-30s | %-20s | %-15s | %-10s | \n",
                        coun.getCode(), coun.getName(), coun.getRegion(), coun.getContinent(), coun.getPopulation(), coun.getCapital());
            }

            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("No country details available");
        }
    }
    /**
     * All the countries in a continent organised by largest population to smallest.
     */
    public ArrayList<Continent> getContinent(Connection con)
    {
        ArrayList<Continent> a = new ArrayList<Continent>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT Code,Name,Region,Continent, Population, Capital " +
                            " FROM country WHERE Continent = \"Asia\" " +
                            " ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Continent continent = new Continent();
                continent.setCode(rset.getString("Code"));
                continent.setName(rset.getString("Name"));
                continent.setRegion(rset.getString("Region"));
                continent.setContinent(rset.getString("Continent"));
                continent.setPopulation(rset.getInt("Population"));
                continent.setCapital(rset.getInt("Capital"));

                a.add(continent);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get continents details");
            return null;
        }
    }

    public void displayContinent(ArrayList<Continent> continents) {
        if (continents != null && !continents.isEmpty()) {
            System.out.println("All the countries in a continent organized by largest population to smallest");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                    "Code", "Name", "Region", "Continent", "Population", "Capital");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Continent continent : continents) {
                System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                        continent.getCode(), continent.getName(), continent.getRegion(), continent.getContinent(), continent.getPopulation(), continent.getCapital());
            }

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("No continent details available");
        }
    }

    /**
     * All the countries in Region organised by largest population to smallest.
     */
    public ArrayList<Region> getRegion(Connection con)
    {
        ArrayList<Region> a = new ArrayList<Region>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT Code,Name,Region,Continent,Population, Capital " +
                            " FROM country WHERE Region = \"Eastern Asia\" " +
                            " ORDER BY Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Region region = new Region();
                region.setCode(rset.getString("Code"));
                region.setName(rset.getString("Name"));
                region.setRegion(rset.getString("Region"));
                region.setContinent(rset.getString("Continent"));
                region.setPopulation(rset.getInt("Population"));
                region.setCapital(rset.getInt("Capital"));
                a.add(region);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get regions details");
            return null;
        }
    }

    public void displayRegion(ArrayList<Region> regions) {
        if (regions != null && !regions.isEmpty()) {
            System.out.println("All the countries in Region organized by largest population to smallest");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                    "Code", "Name", "Region", "Continent", "Population", "Capital");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Region region : regions) {
                System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                        region.getCode(), region.getName(), region.getRegion(), region.getContinent(), region.getPopulation(), region.getCapital());
            }

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("No Region details available");
        }
    }


    /**
     * The top N populated countries in the world where N is provided by the user.
     */
    public ArrayList<UserInputWorld> getUserInputWorld(Connection con)
    {
        ArrayList<UserInputWorld> a = new ArrayList<UserInputWorld>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT country.Code, country.Name AS country_name, country.Region, country.Continent, country.Population, country.Capital " +
                            " FROM country " +
                            " ORDER BY country.Population DESC LIMIT 5 ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                UserInputWorld userinputworld = new UserInputWorld();
                userinputworld.setCode(rset.getString("Code"));
                userinputworld.setName(rset.getString("Country_Name"));
                userinputworld.setRegion(rset.getString("Region"));
                userinputworld.setContinent(rset.getString("Continent"));
                userinputworld.setPopulation(rset.getInt("Population"));
                userinputworld.setCapital(rset.getInt("Capital"));
                a.add(userinputworld);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get User Input details");
            return null;
        }
    }

    public void displayUserInputWorld(ArrayList<UserInputWorld> userInputWorlds) {
        if (userInputWorlds != null && !userInputWorlds.isEmpty()) {
            System.out.println("The top N populated countries in the world where N is provided by the user");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                    "Code", "Country Name", "Region", "Continent", "Population","Capital");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (UserInputWorld userInputWorld : userInputWorlds) {
                System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                        userInputWorld.getCode(), userInputWorld.getName(), userInputWorld.getRegion(), userInputWorld.getContinent(), userInputWorld.getPopulation(), userInputWorld.getCapital());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("No Population in world by users details available");
        }
    }

    /**
     * The top N populated countries in the continent where N is provided by the user.
     */
    public ArrayList<UserInputContinent> getUserInputContinent(Connection con)
    {
        ArrayList<UserInputContinent> a = new ArrayList<UserInputContinent>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT country.Code, country.Name AS country_name, country.Region, country.Continent, country.Population, country.Capital " +
                            "FROM country WHERE country.Continent = \"Asia\" " +
                            "ORDER BY country.Population DESC LIMIT 5;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                UserInputContinent userinputcontinent = new UserInputContinent();
                userinputcontinent.setCode(rset.getString("Code"));
                userinputcontinent.setName(rset.getString("Country_Name"));
                userinputcontinent.setRegion(rset.getString("Region"));
                userinputcontinent.setContinent(rset.getString("Continent"));
                userinputcontinent.setPopulation(rset.getInt("Population"));
                userinputcontinent.setCapital(rset.getInt("Capital"));
                a.add(userinputcontinent);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get User Input details");
            return null;
        }
    }

    public void displayUserInputContinent(ArrayList<UserInputContinent> userInputContinents) {
        if (userInputContinents != null && !userInputContinents.isEmpty()) {
            System.out.println("The top N populated countries in the Continent where N is provided by the user");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                    "Code", "Country Name", "Region", "Continent", "Population", "Capital");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (UserInputContinent userInputContinent : userInputContinents) {
                System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                        userInputContinent.getCode(), userInputContinent.getName(), userInputContinent.getRegion(), userInputContinent.getContinent(), userInputContinent.getPopulation(), userInputContinent.getCapital());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("No Population in continent by users details available");
        }
    }
    /**
     * The top N populated countries in the continent where N is provided by the user.
     */
    public ArrayList<UserInputRegion> getUserInputRegion(Connection con)
    {
        ArrayList<UserInputRegion> a = new ArrayList<UserInputRegion>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name AS country_name, country.Region, country.Continent, country.Population, country.Capital " +
                            " FROM country WHERE country.Region = \"Eastern Asia\" " +
                            " ORDER BY country.Population DESC LIMIT 5 ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                UserInputRegion userinputregion = new UserInputRegion();
                userinputregion.setCode(rset.getString("Code"));
                userinputregion.setName(rset.getString("Country_Name"));
                userinputregion.setRegion(rset.getString("Region"));
                userinputregion.setContinent(rset.getString("Continent"));
                userinputregion.setPopulation(rset.getInt("Population"));
                userinputregion.setCapital(rset.getInt("Capital"));
                a.add(userinputregion);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get User Input details");
            return null;
        }
    }

    public void displayUserInputRegion(ArrayList<UserInputRegion> userInputRegions) {
        if (userInputRegions != null && !userInputRegions.isEmpty()) {
            System.out.println("The top N populated countries in the Region where N is provided by the user");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                    "Code", "Country Name", "Region", "Continent", "Population", "Capital");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (UserInputRegion userInputRegion : userInputRegions) {
                System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-15s | %-10s |\n",
                        userInputRegion.getCode(), userInputRegion.getName(), userInputRegion.getRegion(), userInputRegion.getContinent(), userInputRegion.getPopulation(), userInputRegion.getCapital());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("No Population in continent by users details available");
        }
    }

    /**
     * All the countries in a continent organised by largest population to smallest.
     */
    public ArrayList<Capitalcity> getCapitalCity(Connection con)
    {
        ArrayList<Capitalcity> a = new ArrayList<Capitalcity>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT city.Name AS Capital_Name, country.Name AS Country_Name, city.Population " +
                            " FROM city INNER JOIN country ON city.CountryCode = country.Code WHERE city.ID = country.Capital AND country.Continent = \"Asia\" " +
                            " ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Capitalcity capitalcity = new Capitalcity();
                capitalcity.setCapital_Name(rset.getString("Capital_Name"));
                capitalcity.setCountry_Name(rset.getString("Country_Name"));
                capitalcity.setPopulation(rset.getInt("Population"));


                a.add(capitalcity);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital details");
            return null;
        }
    }

    public void displayCapital(ArrayList<Capitalcity> capitalcities) {
        if (capitalcities != null && !capitalcities.isEmpty()) {
            System.out.println("All the capital cities in a world organised by largest population to smallest.");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Capital_Name", "Country_Name", "Population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (Capitalcity capitalcity : capitalcities) {
                System.out.printf("| %-25s |  %-25s |  %-25s |\n",
                        capitalcity.getCapital_Name(), capitalcity.getCountry_Name(), capitalcity.getPopulation());
            }

            System.out.println("----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No continent details available");
        }
    }

    /**
     * All the countries in a continent organised by largest population to smallest.
     */
    public ArrayList<CapitalContinent> getCapitalContinent(Connection con)
    {
        ArrayList<CapitalContinent> a = new ArrayList<CapitalContinent>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT city.Name AS Capital_Name, country.Name AS Country_Name, city.Population " +
                            " FROM city INNER JOIN country ON city.CountryCode = country.Code " +
                            " WHERE city.ID = country.Capital AND country.Continent = \"Asia\" " +
                            " ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CapitalContinent capitalcontinent = new CapitalContinent();
                capitalcontinent.setCapital_Name(rset.getString("Capital_Name"));
                capitalcontinent.setCountry_Name(rset.getString("Country_Name"));
                capitalcontinent.setPopulation(rset.getInt("Population"));


                a.add(capitalcontinent);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital details");
            return null;
        }
    }

    public void displayCapitalContinent(ArrayList<CapitalContinent> capitalContinents) {
        if (capitalContinents != null && !capitalContinents.isEmpty()) {
            System.out.println("All the capital cities in a continent organised by largest population to smallest.");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Capital_Name", "Country_Name", "Population");
            System.out.println("--------------------------------------------------------------------------------------");

            for (CapitalContinent capitalContinent : capitalContinents) {
                System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                        capitalContinent.getCapital_Name(), capitalContinent.getCountry_Name(), capitalContinent.getPopulation());
            }

            System.out.println("---------------------------------------------------------------------------------------");
        } else {
            System.out.println("No continent details available");
        }
    }

    /**
     * All the capital cities in a region organised by largest population to smallest.
     */
    public ArrayList<CapitalRegion> getCapitalRegion(Connection con)
    {
        ArrayList<CapitalRegion> a = new ArrayList<CapitalRegion>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT city.Name AS Capital_Name, country.Name AS Country_Name, city.Population " +
                            " FROM city INNER JOIN country ON city.CountryCode = country.Code " +
                            " WHERE city.ID = country.Capital AND country.Region = \"Middle East\" " +
                            " ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CapitalRegion capitalregion = new CapitalRegion();
                capitalregion.setCapital_Name(rset.getString("Capital_Name"));
                capitalregion.setCountry_Name(rset.getString("Country_Name"));
                capitalregion.setPopulation(rset.getInt("Population"));


                a.add(capitalregion);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital details");
            return null;
        }
    }

    public void displayCapitalRegion(ArrayList<CapitalRegion> capitalRegions) {
        if (capitalRegions != null && !capitalRegions.isEmpty()) {
            System.out.println("All the capital cities in a region organised by largest population to smallest.");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Capital_Name", "Country_Name", "Population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (CapitalRegion capitalRegion : capitalRegions) {
                System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                        capitalRegion.getCapital_Name(), capitalRegion.getCountry_Name(), capitalRegion.getPopulation());
            }

            System.out.println("--------------------------------------------------------------------------------------");
        } else {
            System.out.println("No regions details available");
        }
    }

    /**
     * The top N populated capital cities in the world where N is provided by the user.
     */
    public ArrayList<InputCapitalWorld> getInputCapitalWorld(Connection con)
    {
        ArrayList<InputCapitalWorld> a = new ArrayList<InputCapitalWorld>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT city.Name AS Capital_Name, country.Name AS Country_Name, city.Population " +
                            " FROM city INNER JOIN country ON city.CountryCode = country.Code " +
                            " WHERE city.ID = country.Capital " +
                            " ORDER BY city.Population DESC LIMIT 5 ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                InputCapitalWorld inputCapitalWorld = new InputCapitalWorld();
                inputCapitalWorld.setCapital_Name(rset.getString("Capital_Name"));
                inputCapitalWorld.setCountry_Name(rset.getString("Country_Name"));
                inputCapitalWorld.setPopulation(rset.getInt("Population"));


                a.add(inputCapitalWorld);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital details");
            return null;
        }
    }

    public void displayInputCapitalWorld(ArrayList<InputCapitalWorld> inputCapitalWorlds) {
        if (inputCapitalWorlds != null && !inputCapitalWorlds.isEmpty()) {
            System.out.println("The top N populated capital cities in the world where N is provided by the user.");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Capital_Name", "Country_Name", "Population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (InputCapitalWorld inputCapitalWorld : inputCapitalWorlds) {
                System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                        inputCapitalWorld.getCapital_Name(), inputCapitalWorld.getCountry_Name(), inputCapitalWorld.getPopulation());
            }

            System.out.println("--------------------------------------------------------------------------------------");
        } else {
            System.out.println("No regions details available");
        }
    }

    /**
     * The top N populated capital cities in the continent where N is provided by the user.
     */
    public ArrayList<InputCapitalContinent> getInputCapitalContinent(Connection con)
    {
        ArrayList<InputCapitalContinent> a = new ArrayList<InputCapitalContinent>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT city.Name AS Capital_Name, country.Name AS Country_Name, city.Population " +
                            " FROM city INNER JOIN country ON city.CountryCode = country.Code " +
                            " WHERE city.ID = country.Capital AND country.Continent = \"Asia\" " +
                            " ORDER BY city.Population DESC LIMIT 5 ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                InputCapitalContinent inputCapitalContinent = new InputCapitalContinent();
                inputCapitalContinent.setCapital_Name(rset.getString("Capital_Name"));
                inputCapitalContinent.setCountry_Name(rset.getString("Country_Name"));
                inputCapitalContinent.setPopulation(rset.getInt("Population"));


                a.add(inputCapitalContinent);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital details");
            return null;
        }
    }

    public void displayInputCapitalContinent(ArrayList<InputCapitalContinent> inputCapitalContinents) {
        if (inputCapitalContinents != null && !inputCapitalContinents.isEmpty()) {
            System.out.println("The top N populated capital cities in the continent where N is provided by the user.");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Capital_Name", "Country_Name", "Population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (InputCapitalContinent inputCapitalContinent : inputCapitalContinents) {
                System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                        inputCapitalContinent.getCapital_Name(), inputCapitalContinent.getCountry_Name(), inputCapitalContinent.getPopulation());
            }

            System.out.println("--------------------------------------------------------------------------------------");
        } else {
            System.out.println("No regions details available");
        }
    }

    /**
     * The top N populated capital cities in the region where N is provided by the user.
     */
    public ArrayList<InputCapitalRegion> getInputCapitalRegion(Connection con)
    {
        ArrayList<InputCapitalRegion> a = new ArrayList<InputCapitalRegion>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT city.Name AS Capital_Name, country.Name AS Country_Name, city.Population " +
                            " FROM city INNER JOIN country ON city.CountryCode = country.Code " +
                            " WHERE city.ID = country.Capital AND country.Region = \"Middle East\" " +
                            " ORDER BY city.Population DESC LIMIT 5 ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                InputCapitalRegion inputCapitalRegion = new InputCapitalRegion();
                inputCapitalRegion.setCapital_Name(rset.getString("Capital_Name"));
                inputCapitalRegion.setCountry_Name(rset.getString("Country_Name"));
                inputCapitalRegion.setPopulation(rset.getInt("Population"));


                a.add(inputCapitalRegion);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital details");
            return null;
        }
    }

    public void displayInputCapitalRegion(ArrayList<InputCapitalRegion> inputCapitalRegions) {
        if (inputCapitalRegions != null && !inputCapitalRegions.isEmpty()) {
            System.out.println("The top N populated capital cities in the region where N is provided by the user.");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Capital_Name", "Country_Name", "Population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (InputCapitalRegion inputCapitalRegion : inputCapitalRegions) {
                System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                        inputCapitalRegion.getCapital_Name(), inputCapitalRegion.getCountry_Name(), inputCapitalRegion.getPopulation());
            }

            System.out.println("--------------------------------------------------------------------------------------");
        } else {
            System.out.println("No regions details available");
        }
    }



    public void disconnect(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Population of people who living in cities and people who not living in cities in each continent
     * */
    public ArrayList<Population> getPopulation(Connection con)
    {
        ArrayList<Population> a = new ArrayList<Population>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT " +
                            " country.Continent," +
                            " SUM(country.Population) AS Total_Population," +
                            " SUM(city.Population) AS Population_In_Cities," +
                            " SUM(country.Population) - SUM(city.Population) AS Population_Not_In_Cities," +
                            " ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS Percentage_Population_In_Cities," +
                            " ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) " +
                            " AS Percentage_Population_Not_In_Cities " +
                            " FROM " +
                            " country " +
                            "INNER JOIN " +
                            "    city ON country.Code = city.CountryCode\n" +
                            "GROUP BY " +
                            "    country.Continent ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Population pol = new Population();
//
                // Change the column names in your Java code to match the ones in the SQL query
                pol.setContinent(rset.getString("Continent"));
                pol.setTotal_Population(rset.getLong("Total_Population"));
                pol.setPopulation_In_Cities(rset.getLong("Population_In_Cities"));
                pol.setPopulation_Not_In_Cities(rset.getLong("Population_Not_In_Cities"));
                pol.setPercentage_Population_In_Cities(new BigDecimal(rset.getString("Percentage_Population_In_Cities")));
                pol.setPercentage_Population_Not_In_Cities(new BigDecimal(rset.getString("Percentage_Population_Not_In_Cities")));

                // Check for null value before converting to BigDecimal
                a.add(pol);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population in each continent details");
            return null;
        }
    }
    /**
     * Display population details.
     */
    public void displayPopulation(ArrayList<Population> populations) {
        if (populations != null && !populations.isEmpty()) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                    "Continent", "TotalPopulation", "PopulationInCities", "PopulationNotInCities", "PercentagePopulationInCities", "PercentagePopulationNotInCities");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            for (Population pol : populations) {
                System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                        pol.getContinent(), pol.getTotal_Population(),pol.getPopulation_In_Cities(), pol.getPopulation_Not_In_Cities(), pol.getPercentage_Population_In_Cities(), pol.getPercentage_Population_Not_In_Cities());
            }

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No population details available");
        }
    }




    /**
     * Population of people who living in cities and people who not living in cities in each continent
     * */
    public ArrayList<PopulationRegion> getPopulationRegion(Connection con)
    {
        ArrayList<PopulationRegion> a = new ArrayList<PopulationRegion>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT country.Region, SUM(country.Population) AS Total_Population, SUM(city.Population) AS Population_In_Cities, SUM(country.Population) - SUM(city.Population) AS Population_Not_In_Cities, ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS Percentage_Population_In_Cities, ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS Percentage_Population_Not_In_Cities FROM country INNER JOIN city ON country.Code = city.CountryCode GROUP BY country.Region";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                PopulationRegion pore = new PopulationRegion();
//
                // Change the column names in your Java code to match the ones in the SQL query
                pore.setRegion(rset.getString("Region"));
                pore.setTotal_Population(rset.getLong("Total_Population"));
                pore.setPopulation_In_Cities(rset.getLong("Population_In_Cities"));
                pore.setPopulation_Not_In_Cities(rset.getLong("Population_Not_In_Cities"));
                pore.setPercentage_Population_In_Cities(new BigDecimal(rset.getString("Percentage_Population_In_Cities")));
                pore.setPercentage_Population_Not_In_Cities(new BigDecimal(rset.getString("Percentage_Population_Not_In_Cities")));
                // Check for null value before converting to BigDecimal
                a.add(pore);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population in each region details");
            return null;
        }
    }
    /**
     * Display population details.
     */
    public void displayPopulationRegion(ArrayList<PopulationRegion> populationRegions) {
        if (populationRegions != null && !populationRegions.isEmpty()) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                    "Region", "TotalPopulation", "PopulationInCities", "PopulationNotInCities", "PercentagePopulationInCities", "PercentagePopulationNotInCities");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            for (PopulationRegion pore : populationRegions) {
                System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                        pore.getRegion(), pore.getTotal_Population(),pore.getPopulation_In_Cities(), pore.getPopulation_Not_In_Cities(), pore.getPercentage_Population_In_Cities(), pore.getPercentage_Population_Not_In_Cities());
            }

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No population details available");
        }
    }

    /**
     * Population of people who living in cities and people who not living in cities in each country
     * */
    public ArrayList<PopulationCountry> getPopulationCountry(Connection con)
    {
        ArrayList<PopulationCountry> a = new ArrayList<PopulationCountry>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT country.Name, SUM(country.Population) AS Total_Population, SUM(city.Population) AS Population_In_Cities, SUM(country.Population) - SUM(city.Population) AS Population_Not_In_Cities, ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS Percentage_Population_In_Cities, ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS Percentage_Population_Not_In_Cities FROM country INNER JOIN city ON country.Code = city.CountryCode GROUP BY country.Name";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                PopulationCountry pocoun = new PopulationCountry();
//
                // Change the column names in your Java code to match the ones in the SQL query
                pocoun.setName(rset.getString("Name"));
                pocoun.setTotal_Population(rset.getLong("Total_Population"));
                pocoun.setPopulation_In_Cities(rset.getLong("Population_In_Cities"));
                pocoun.setPopulation_Not_In_Cities(rset.getLong("Population_Not_In_Cities"));
                pocoun.setPercentage_Population_In_Cities(new BigDecimal(rset.getString("Percentage_Population_In_Cities")));
                pocoun.setPercentage_Population_Not_In_Cities(new BigDecimal(rset.getString("Percentage_Population_Not_In_Cities")));
                // Check for null value before converting to BigDecimal
                a.add(pocoun);
            }
            return a;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population in each country details");
            return null;
        }
    }
    /**
     * Display population details.
     */
    public void displayPopulationCountry(ArrayList<PopulationCountry> populationCountries) {
        if (populationCountries != null && !populationCountries.isEmpty()) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                    "Name", "TotalPopulation", "PopulationInCities", "PopulationNotInCities", "PercentagePopulationInCities", "PercentagePopulationNotInCities");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            for (PopulationCountry pocoun : populationCountries) {
                System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                        pocoun.getName(), pocoun.getTotal_Population(),pocoun.getPopulation_In_Cities(), pocoun.getPopulation_Not_In_Cities(), pocoun.getPercentage_Population_In_Cities(), pocoun.getPercentage_Population_Not_In_Cities());
            }

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No population details available");
        }
    }

    /**
     * Sorting cities IN A DISTRICT according to population
     */

    public ArrayList<CityDistrict> sortCityDistrict(Connection con) {
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
    public ArrayList<TopCity> sortCityTopWorld(Connection con) {
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
    public ArrayList<TopCity> sortCityTopContinent(Connection con) {
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
    public ArrayList<TopCity> sortCityTopCountry(Connection con) {
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
    public ArrayList<TopCity> sortCityTopRegion(Connection con) {
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
    public ArrayList<TopCity> sortCityTopDistrict(Connection con) {
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
    public void setConnection(Connection mockConnection) {
    }
}






