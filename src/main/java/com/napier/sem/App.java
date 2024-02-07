
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

        // Retrieve population in continent details
        ArrayList<Population> population = a.getPopulation();

        //Retrieve population in region details
        ArrayList<PopulationRegion> population_region = a.getPopulationRegion();

        //Retrieve population in country details
        ArrayList<PopulationCountry> population_country = a.getPopulationCountry();

        // Display result
        a.displayPopulation(population);
        a.displayPopulationRegion(population_region);
        a.displayPopulationCountry(population_country);

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
                System.out.println("Failed to connect to database attempt " + i);
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
     * Population of people who living in cities and people who not living in cities in each continent
     * */
    public ArrayList<Population> getPopulation()
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
    public ArrayList<PopulationRegion> getPopulationRegion()
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
    public ArrayList<PopulationCountry> getPopulationCountry()
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
}






