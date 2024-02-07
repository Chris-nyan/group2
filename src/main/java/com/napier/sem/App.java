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

        // Display result
        a.displayCountry(country);
        a.displayContinent(continent);
        a.displayRegion(regions);
        a.displayUserInputWorld(userInputWorlds);


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
                    " SELECT * FROM country " +
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
                coun.setContinent(rset.getString("Continent"));
                coun.setRegion(rset.getString("Region"));
                coun.setSurfaceArea(new BigDecimal(rset.getString("SurfaceArea")));
                coun.setIndepYear(rset.getInt("IndepYear"));
                coun.setPopulation(rset.getInt("Population"));
                // Check for null value before converting to BigDecimal
                String lifeExpectancyString = rset.getString("LifeExpectancy");
                if (lifeExpectancyString != null) {
                    coun.setLifeExpectancy(new BigDecimal(lifeExpectancyString));
                } else {
                    coun.setLifeExpectancy(null);
                }
                String gnpString = rset.getString("GNP");
                if (gnpString != null) {
                    coun.setGNP(new BigDecimal(gnpString));
                } else {
                    coun.setGNP(null);
                }
                String gnpoString = rset.getString("GNPOld");
                if (gnpoString != null) {
                    coun.setGNPOld(new BigDecimal(gnpoString));
                } else {
                    coun.setGNPOld(null);
                }
                coun.setLocalName(rset.getString("LocalName"));
                coun.setGovernmentForm(rset.getString("GovernmentForm"));
                coun.setHeadOfState(rset.getString("HeadOfState"));
                coun.setCapital(rset.getInt("Capital"));
                coun.setCode2(rset.getString("Code2"));
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
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-40s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |\n",
                    "Code", "Country Name", "Continent", "Region", "Surface Area", "Indep Year",
                    "Population","LifeExpectancy","GNP","GNPOld","Local Name","Government Form","Head of State", "Capital", "Code2");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (CountriesInWorld coun : countries) {
                System.out.printf("| %-25s | %-40s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |\n",
                        coun.getCode(), coun.getName(), coun.getContinent(), coun.getRegion(), coun.getSurfaceArea(), coun.getIndepYear(), coun.getPopulation(), coun.getLifeExpectancy(),
                        coun.getGNP(), coun.getGNPOld(), coun.getLocalName(), coun.getGovernmentForm(), coun.getHeadOfState(), coun.getCapital(), coun.getCode2());
            }

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
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
                    " SELECT Code,Name,Region,Continent, Population " +
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

    public void displayContinent(ArrayList<Continent> continent) {
        if (continent != null && !continent.isEmpty()) {
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s |  %-25s |  %-25s | \n",
                     "Code", "Name", "Region", "Continent", "Population");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");

            for (Continent continent1 : continent) {
                System.out.printf("| %-25s |  %-25s |  %-25s |  %-25s |  %-25s | \n",
                        continent1.getCode(), continent1.getName(), continent1.getRegion(), continent1.getContinent(), continent1.getPopulation());
            }

            System.out.println("-----------------------------------------------------------------------------------------------------------------");
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
                    " SELECT Code,Name,Region,Continent,Population " +
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
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s | %-25s | \n",
                    "Code", "Name", "Region", "Continent", "Population");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Region region : regions) {
                System.out.printf("| %-25s |%-25s | %-25s | %-25s | %-25s |\n",
                        region.getCode(), region.getName(), region.getRegion(), region.getContinent(), region.getPopulation());
            }

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
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
                    " SELECT country.Code, country.Name AS country_name, country.Region, country.Continent, country.Population " +
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
                userinputworld.setCountry_name(rset.getString("Country_Name"));
                userinputworld.setRegion(rset.getString("Region"));
                userinputworld.setContinent(rset.getString("Continent"));
                userinputworld.setPopulation(rset.getInt("Population"));
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
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s | %-25s | \n",
                    "Code", "Country_Name", "Region", "Continent", "Population");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (UserInputWorld userInputWorld : userInputWorlds) {
                System.out.printf("| %-25s |%-25s | %-25s | %-25s | %-25s |\n",
                        userInputWorld.getCode(), userInputWorld.getCountry_name(), userInputWorld.getRegion(), userInputWorld.getContinent(), userInputWorld.getPopulation());
            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No Population in world by users details available");
        }
    }



}