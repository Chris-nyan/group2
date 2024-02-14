package com.napier.sem;

import com.napier.sem.Capitalcity;

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
        ArrayList<Capitalcity> capitalcity = a.getCapitalCity();

        // Retrieve country in world details
        ArrayList<CapitalContinent> capitalContinents = a.getCapitalContinent();

        // Retrieve country in world details
        ArrayList<CapitalRegion> capitalRegions = a.getCapitalRegion();

        // Display result
        a.displayCapital(capitalcity);
        a.displayCapitalContinent(capitalContinents);
        a.displayCapitalRegion(capitalRegions);

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
        * All the countries in a continent organised by largest population to smallest.
        */
    public ArrayList<Capitalcity> getCapitalCity()
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
    public ArrayList<CapitalContinent> getCapitalContinent()
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
    public ArrayList<CapitalRegion> getCapitalRegion()
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




}