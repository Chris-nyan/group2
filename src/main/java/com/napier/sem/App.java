package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;

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
            con = app.connect("localhost:33060", 0);
        }else{
            con =app.connect(args[0], Integer.parseInt(args[1]));
        }

        //Retrieve continent details
        ArrayList<Language> language = app.sortLanguage(con);


        // Display result
        // Display result
//        app.displayCountry(country, "country.md");
        app.displayLanguage(language);


        app.disconnect(con);
    }

    public ArrayList<Language> sortLanguage(Connection con) {


        ArrayList<Language> sortLanguageList = new ArrayList<>();
        String query = "SELECT cl.Language AS Language,SUM(c.Population) AS TotalPopulation,ROUND((SUM(c.Population) / (SELECT SUM(Population) FROM country)) * 100, 2) AS PercentageOfWorldPopulation, 100 - ROUND((SUM(c.Population) / (SELECT SUM(Population) FROM country)) * 100, 2) AS PercentageNotInWorld FROM countrylanguage cl JOIN country c ON cl.CountryCode = c.Code GROUP BY cl.Language ORDER BY TotalPopulation DESC;";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                Language sortLanguage = new Language();
                sortLanguage.setLanguage(rs.getString("Language"));
                sortLanguage.setTotalPopulation(rs.getInt("TotalPopulation"));
                sortLanguage.setPercentWorld(rs.getDouble("PercentageOfWorldPopulation"));
                sortLanguage.setPercentNonWorld(rs.getDouble("PercentageNoInWorld"));
                sortLanguageList.add(sortLanguage);
//                City sortCity = new City();
//                sortCity.setName(rs.getString("Name"));
//                sortCity.setCountryName(rs.getString("country_name"));
//                sortCity.setPopulation(rs.getInt("Population"));
//                sortCity.setDistrict(rs.getString("District"));
//                sortCity.setContinent(rs.getString("Continent"));
//                sortCityList.add(sortCity);
            }
            return sortLanguageList;

        } catch (Exception e) {
            System.out.println("Error on sort city");
            e.printStackTrace();
        }
        return null;
    }

    public void displayLanguage(ArrayList<Language> languages) {
        if (languages != null && !languages.isEmpty()) {
            System.out.println("All the language in the world orginze by largest to smallest");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "language", "total_population", "percent_of_world_population", "percent_of_not_in_world_population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (Language language : languages) {
                System.out.printf("| %-25s |  %-25s |  %-25s |\n",
                        language.getLanguage(), language.getTotalPopulation(), language.getPercentNonWorld(), language.getPercentWorld());
            }

            System.out.println("----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No continent details available");
        }
    }

//    public void displaySortCity(ArrayList<Language> sortCities, String sortCity) {
//        if (sortCities != null && !sortCities.isEmpty()) {
//            try {
//                // Create the reports directory if it doesn't exist
//                new File("./reports/").mkdir();
//
//                // Create a BufferedWriter for writing to the file
//                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + sortCity)));
//
//                // Write header to the file
//                writer.write("Population of the cities in a continent, Asia, sorting from largest to smallest\n");
//                writer.write("---------------------------------------------------------------------------------------------------------");
//                writer.write("| %-25s | %-25s | %-15s | %-25s | %-15s |\n");
//                writer.write("----------------------------------------------------------------------------------------------------------\n");
//
//                // Loop over all cities in the list
//                for (City SortCity : sortCities) {
//                    if (sortCity == null)
//                        continue;
//
//                    // Write city information to the file
//                    writer.write(String.format("| %-25s | %-25s | %-15d | %-25s | %-15s |\n",
//                            SortCity.getName(), SortCity.getCountryName(), SortCity.getPopulation(), SortCity.getDistrict(), SortCity.getContinent()));
//                }
//
//                // Close the BufferedWriter
//                writer.close();
//                System.out.println("File created successfully: " + sortCity);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            System.out.println("No cities found.");
//        }
//    }




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
    public void disconnect(Connection con)
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

    public void setConnection(Connection mockConnection) {
    }
}







