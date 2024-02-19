
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
                            " (SELECT SUM(country2.Population) FROM country country2 WHERE country2.Continent = country.Continent) AS Total_Population," +
                            " SUM(city.Population) AS Population_In_Cities, " +
                            " ((SELECT SUM(country3.Population) FROM country country3 WHERE country3.Continent = country.Continent) - SUM(city.Population)) AS Population_Not_In_Cities," +
                            " CONCAT(ROUND((SUM(city.Population) / (SELECT SUM(country4.Population) FROM country country4 WHERE country4.Continent = country.Continent)) * 100, 2)) AS Percentage_Population_In_Cities," +
                            " CONCAT(ROUND((((SELECT SUM(country5.Population) FROM country country5 WHERE country5.Continent = country.Continent) - SUM(city.Population)) / (SELECT SUM(country6.Population) FROM country country6 WHERE country6.Continent = country.Continent)) * 100, 2)) AS Percentage_Population_Not_In_Cities" +
                            " FROM " +
                            " country " +
                            " INNER JOIN " +
                            "     city ON country.Code = city.CountryCode " +
                            " GROUP BY " +
                            " country.Continent ";

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
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                    "Continent", "TotalPopulation", "PopulationInCities", "PopulationNotInCities", "PercentagePopulationInCities", "PercentagePopulationNotInCities");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Population pol : populations) {
                System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                        pol.getContinent(), pol.getTotal_Population(),pol.getPopulation_In_Cities(), pol.getPopulation_Not_In_Cities(), pol.getPercentage_Population_In_Cities(), pol.getPercentage_Population_Not_In_Cities());
            }

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
                    " SELECT " +
                            " co.Region AS Region," +
                            " (SELECT SUM(co2.Population) FROM country co2 WHERE co2.Region = co.Region) AS TotalPopulation," +
                            " SUM(ci.Population) AS PopulationInCities,\n" +
                            " ((SELECT SUM(co3.Population) FROM country co3 WHERE co3.Region = co.Region) - SUM(ci.Population)) AS PopulationNotInCities,\n" +
                            " CONCAT(ROUND((SUM(ci.Population) / (SELECT SUM(co4.Population) FROM country co4 WHERE co4.Region = co.Region)) * 100, 2)) AS PercentageInCities,\n" +
                            " CONCAT(ROUND((((SELECT SUM(co5.Population) FROM country co5 WHERE co5.Region = co.Region) - SUM(ci.Population)) / (SELECT SUM(co6.Population) FROM country co6 WHERE co6.Region = co.Region)) * 100, 2)) AS PercentageNotInCities\n" +
                            " FROM " +
                            " country co  " +
                            " INNER JOIN " +
                            "     city ci ON co.Code = ci.CountryCode " +
                            " GROUP BY " +
                            " co.Region ";
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
                pore.setTotalPopulation(rset.getLong("TotalPopulation"));
                pore.setPopulationInCities(rset.getLong("PopulationInCities"));
                pore.setPopulationNotInCities(rset.getLong("PopulationNotInCities"));
                pore.setPercentageInCities(new BigDecimal(rset.getString("PercentageInCities")));
                pore.setPercentageNotInCities(new BigDecimal(rset.getString("PercentageNotInCities")));
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
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                    "Region", "TotalPopulation", "PopulationInCities", "PopulationNotInCities", "PercentageInCities", "PercentageNotInCities");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (PopulationRegion pore : populationRegions) {
                System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                        pore.getRegion(), pore.getTotalPopulation(),pore.getPopulationInCities(), pore.getPopulationNotInCities(), pore.getPercentageInCities(), pore.getPercentageNotInCities());
            }

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
                    " SELECT " +
                            "    co.Name AS Country," +
                            "    (SELECT SUM(co2.Population) FROM country co2 WHERE co2.Name = co.Name) AS Total_Population," +
                            "    SUM(ci.Population) AS Population_In_Cities," +
                            "    ((SELECT SUM(co3.Population) FROM country co3 WHERE co3.Name = co.Name) - SUM(ci.Population)) AS Population_Not_In_Cities," +
                            "    CONCAT(ROUND((SUM(ci.Population) / (SELECT SUM(co4.Population) FROM country co4 WHERE co4.Name = co.Name)) * 100, 2)) AS Percentage_Population_In_Cities," +
                            "    CONCAT(ROUND((((SELECT SUM(co5.Population) FROM country co5 WHERE co5.Name = co.Name) - SUM(ci.Population)) / (SELECT SUM(co6.Population) FROM country co6 WHERE co6.Name = co.Name)) * 100, 2)) AS Percentage_Population_Not_In_Cities" +
                            "   FROM " +
                            "   country co " +
                            "   INNER JOIN " +
                            "       city ci ON co.Code = ci.CountryCode " +
                            "   GROUP BY " +
                            "    co.Name";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                PopulationCountry pocoun = new PopulationCountry();
//
                // Change the column names in your Java code to match the ones in the SQL query
                pocoun.setCountry(rset.getString("Country"));
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
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                    "Country", "TotalPopulation", "PopulationInCities", "PopulationNotInCities", "PercentagePopulationInCities", "PercentagePopulationNotInCities");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (PopulationCountry pocoun : populationCountries) {
                System.out.printf("| %-25s | %-40s | %-40s | %-40s | %-40s | %-40s |\n",
                        pocoun.getCountry(), pocoun.getTotal_Population(),pocoun.getPopulation_In_Cities(), pocoun.getPopulation_Not_In_Cities(), pocoun.getPercentage_Population_In_Cities(), pocoun.getPercentage_Population_Not_In_Cities());
            }

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No population details available");
        }
    }
}






