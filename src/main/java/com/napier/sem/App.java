package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Retrieve country details
        ArrayList<Countries> country = a.getcountries();
        ArrayList<Countries> continent = a.getContinent();

        // Display result
        a.displayCountry(continent);

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