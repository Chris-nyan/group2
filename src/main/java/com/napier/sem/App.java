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
        ArrayList<Capitalcity> capitacity = a.getCapitalCity();

        // Display result
        a.displayCapital(capitacity);

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
                    " SELECT city.Name AS Capital_City, country.Name AS Country_Name, city.Population, country.Continent " +
                            " FROM city INNER JOIN country ON city.CountryCode = country.Code WHERE city.ID = country.Capital AND country.Continent = \"Asia\" " +
                            " ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Capitalcity capitalcity = new Capitalcity();
                capitalcity.setCapital_City(rset.getString("Capital_City"));
                capitalcity.setCountry_Name(rset.getString("Country_Name"));
                capitalcity.setPopulation(rset.getInt("Population"));
                capitalcity.setContinent(rset.getString("Continent"));


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
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s |  %-25s | \n",
                    "Capital_City", "Country_Name", "Population", "Continent");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");

            for (Capitalcity capitalcity : capitalcities) {
                System.out.printf("| %-25s |  %-25s |  %-25s |  %-25s | \n",
                        capitalcity.getCapital_City(), capitalcity.getCountry_Name(), capitalcity.getPopulation(), capitalcity.getContinent());
            }

            System.out.println("-----------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No continent details available");
        }
    }




}