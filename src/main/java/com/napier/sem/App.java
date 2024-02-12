package com.napier.sem;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
//adding necessary libraries, modules

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();

        // Retrieve country in world details
        ArrayList<CountriesInWorld> country = a.getCountriesInWorld();

        //Retrieve continent details
        ArrayList<Continent> continent = a.getContinent();

        //Retrieve continent details
        ArrayList<Region> regions = a.getRegion();

        //Retrieve continent details
        ArrayList<UserInputWorld> userInputWorlds = a.getUserInputWorld();

        //Retrieve continent details
        ArrayList<UserInputContinent> userInputContinents = a.getUserInputContinent();

        //Retrieve continent details
        ArrayList<UserInputRegion> userInputRegions = a.getUserInputRegion();

        // Display result
        a.displayCountry(country);
        a.displayContinent(continent);
        a.displayRegion(regions);
        a.displayUserInputWorld(userInputWorlds);
        a.displayUserInputContinent(userInputContinents);
        a.displayUserInputRegion(userInputRegions);


        // Disconnect from database
        a.disconnect();


    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
//                con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected jgghkgu");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }


    /**
     * All the countries in the world organised by largest population to smallest.
     */
    public ArrayList<CountriesInWorld> getCountriesInWorld()
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
            System.out.printf("| %-5s | %-40s | %-20s | %-10s | %-15s | %-10s | \n",
                    "Code", "Country Name", "Region", "Continent", "Population", "Capital");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (CountriesInWorld coun : countries) {
                System.out.printf("| %-5s | %-40s | %-20s | %-10s | %-15s | %-10s | \n",
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
    public ArrayList<Continent> getContinent()
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
            System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
                    "Code", "Name", "Region", "Continent", "Population", "Capital");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Continent continent : continents) {
                System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
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
    public ArrayList<Region> getRegion()
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
            System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
                    "Code", "Name", "Region", "Continent", "Population", "Capital");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Region region : regions) {
                System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
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
    public ArrayList<UserInputWorld> getUserInputWorld()
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
            System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
                    "Code", "Country Name", "Region", "Continent", "Population","Capital");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (UserInputWorld userInputWorld : userInputWorlds) {
                System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
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
    public ArrayList<UserInputContinent> getUserInputContinent()
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
            System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
                    "Code", "Country Name", "Region", "Continent", "Population", "Capital");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (UserInputContinent userInputContinent : userInputContinents) {
                System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
                        userInputContinent.getCode(), userInputContinent.getName(), userInputContinent.getRegion(), userInputContinent.getContinent(), userInputContinent.getPopulation(), userInputContinent.getCapital());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("No Population in continent by users details available");
        }
    }

    public ArrayList<UserInputRegion> getUserInputRegion()
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
            System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
                    "Code", "Country Name", "Region", "Continent", "Population", "Capital");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (UserInputRegion userInputRegion : userInputRegions) {
                System.out.printf("| %-5s | %-20s | %-20s | %-10s | %-15s | %-10s |\n",
                        userInputRegion.getCode(), userInputRegion.getName(), userInputRegion.getRegion(), userInputRegion.getContinent(), userInputRegion.getPopulation(), userInputRegion.getCapital());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("No Population in continent by users details available");
        }
    }




    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

}