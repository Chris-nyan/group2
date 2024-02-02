package com.napier.sem;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Disconnect from database
        //a.disconnect();
        ArrayList<City> cities = a.getcity();
        a.displayCities(cities);

    }
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;
    /**
     * Display SQL results
     */
    public void displayCities(ArrayList<City> cities) {
        // Check if the list is not empty
        if (cities != null && !cities.isEmpty()) {
            for (City city : cities) {
                System.out.println("Name: " + city.getName());
                System.out.println("District: " + city.getDistrict());
                System.out.println("Country Name: " + city.getCountryCode());
                System.out.println("Population: " + city.getPopulation());
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("No cities found.");
        }
    }

    /**
     * Sorting countries in the world based on population largest to smallest
      */
    public ArrayList<City> getcity()
    {
        try
        {
            ArrayList<City> city_list = new ArrayList<City>(); //cities list to stored data extracted by SQL
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                            " FROM city " +
                            " INNER JOIN country ON city.CountryCode = country.Code " +
                            " ORDER BY city.Population DESC "; //country table and city table is joined internally with country code and ID
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                City cty = new City();
                cty.setName(rset.getString("Name"));
                cty.setDistrict(rset.getString("District"));
                cty.setCountryCode(rset.getString("country_name"));
                cty.setPopulation(Integer.parseInt(rset.getString("Population")));
                city_list.add(cty);
            }
        return city_list;
//            else
//                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City information");
            return null;
        }
    }
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
                System.out.println("Successfully connected");
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

}