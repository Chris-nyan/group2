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
        ArrayList<Capitalcity> capital = a.getCapitalcity();

        // Display result
        a.displayCountry(capital);

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
            System.out.println("Connecting to database... successfully");
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
    public void displayCountry(ArrayList<Capitalcity> countries) {
        if (countries != null && !countries.isEmpty()) {
            for (Capitalcity c : countries) {
                System.out.println(
                                "Name: " + c.getName() + "\n"
                                + "Population: " + c.getPopulation() + "\n"
                                + "Capital: " + c.getCapital() + "\n"
                );
            }
        } else {
            System.out.println("No country details available");
        }
    }


    public ArrayList<Capitalcity> getCapitalcity()
    {
        ArrayList<Capitalcity> a = new ArrayList<Capitalcity>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.Name AS CapitalCity, co.Name AS Country, c.Population " +
                            "FROM city c JOIN country co ON c.ID = co.Capital " +
                            "ORDER BY c.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Capitalcity capi = new Capitalcity();

                capi.setName(rset.getString("Name"));


                capi.setPopulation(rset.getString("Population"));
                capi.setCapital(rset.getString("Capital"));
                a.add(capi);
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