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


        app.displayLanguage(language);


        app.disconnect(con);
    }

    public ArrayList<TotalRegion> sortTotalRegion(Connection con, String region){
        ArrayList<TotalRegion> sortTotalRegionList = new ArrayList<>();
        String query = "SELECT Region, SUM(Population) AS region_total_population FROM country WHERE Region=? GROUP BY Region;";
        try{

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,region);
            ResultSet rs =ps.executeQuery();

            while (rs.next()){
                TotalRegion sortRegion = new TotalRegion();
                sortRegion.setRegion(rs.getString("Region"));
                sortRegion.setTotalPopulation(rs.getInt("region_total_population"));
                sortTotalRegionList.add(sortRegion);
            }
            return sortTotalRegionList;
        }catch (Exception e){
            System.out.println("Error");
        }
        return null;
    }

    public void displayTotalRegion(ArrayList<TotalRegion> totalRegions) {
        if (totalRegions != null && !totalRegions.isEmpty()) {
            System.out.println("Display the total Region of population");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Region", "Population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (TotalRegion totalRegion : totalRegions) {
                System.out.printf("| %-25s |  %-25s |  %-25s |\n",
                        totalRegion.getRegion(),
                        totalRegion.getTotalPopulation());
            }

            System.out.println("----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No Region details available");
        }
    }


    public ArrayList<DistrictPopulation> sortDistrictPopulation(Connection con, String district){
        ArrayList<DistrictPopulation> sortDistrictList = new ArrayList<>();
        String query = "SELECT city.District, SUM(Population) AS population FROM city WHERE District=?;";
        try{

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,district);
            ResultSet rs =ps.executeQuery();

            while (rs.next()){
                DistrictPopulation sortDistrict = new DistrictPopulation();
                sortDistrict.setDistrict(rs.getString("District"));
                sortDistrict.setPopulation(rs.getInt("population"));
                sortDistrictList.add(sortDistrict);
            }
            return sortDistrictList;
        }catch (Exception e){
            System.out.println("Error");
        }
        return null;
    }

    public void displayDistrictPopulation(ArrayList<DistrictPopulation> districts) {
        if (districts != null && !districts.isEmpty()) {
            System.out.println("Display the District of Population");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "District", "Population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (DistrictPopulation district : districts) {
                System.out.printf("| %-25s |  %-25s |  %-25s |\n",
                        district.getDistrict(),
                        district.getPopulation());
            }

            System.out.println("----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No Disctrict details available");
        }
    }


    public ArrayList<CityPopulation> sortCityPopulation(Connection con, String city){
        ArrayList<CityPopulation> sortCityList = new ArrayList<>();
        String query = "SELECT city.Name, SUM(Population) AS population FROM city WHERE Name=?;";
        try{

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,city);
            ResultSet rs =ps.executeQuery();

            while (rs.next()){
                CityPopulation sortCity = new CityPopulation();
                sortCity.setName(rs.getString("Name"));
                sortCity.setPopulation(rs.getInt("population"));
                sortCityList.add(sortCity);
            }
            return sortCityList;
        }catch (Exception e){
            System.out.println("Error");
        }
        return null;
    }

    public void displayCityPopulation(ArrayList<CityPopulation> cities) {
        if (cities != null && !cities.isEmpty()) {
            System.out.println("Display the city Population");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "City", "Population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (CityPopulation city : cities) {
                System.out.printf("| %-25s |  %-25s |  %-25s |\n",
                        city.getName(),
                        city.getPopulation());
            }

            System.out.println("----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No City details available");
        }
    }


    public ArrayList<CountryPopulation> sortCountryPopulation(Connection con, String country){
        ArrayList<CountryPopulation> sortCountryPopulationList = new ArrayList<>();
        String query = "SELECT Name, Population FROM country WHERE Name=?;";
        try{

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,country);
            ResultSet rs =ps.executeQuery();

            while (rs.next()){
                CountryPopulation sortCountry = new CountryPopulation();
                sortCountry.setName(rs.getString("Name"));
                sortCountry.setPopulation(rs.getInt("Population"));
                sortCountryPopulationList.add(sortCountry);
            }
            return sortCountryPopulationList;
        }catch (Exception e){
            System.out.println("Error");
        }
        return null;
    }

    public void displayCountryPopulation(ArrayList<CountryPopulation> countryPopulations) {
        if (countryPopulations != null && !countryPopulations.isEmpty()) {
            System.out.println("Display the country Population");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Country", "total_population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (CountryPopulation countryPopulation : countryPopulations) {
                System.out.printf("| %-25s |  %-25s |  %-25s |\n",
                        countryPopulation.getName(),
                        countryPopulation.getPopulation());
            }

            System.out.println("----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No Country details available");
        }
    }


    public ArrayList<TotalPopulation> sortToalPopulation(Connection con){
        ArrayList<TotalPopulation> sortTotalPopulationList = new ArrayList<>();
        String query = "SELECT SUM(country.Population) AS total_population FROM country;";
        try{
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()){
                TotalPopulation sortTotal = new TotalPopulation();
                sortTotal.setTotalPopulation(rs.getInt("total_population"));
                sortTotalPopulationList.add(sortTotal);
            }
            return sortTotalPopulationList;
        }catch (Exception e){
            System.out.println("Error");
        }
        return null;
    }

    public void displayTotalPopulation(ArrayList<TotalPopulation> totalPopulations) {
        if (totalPopulations != null && !totalPopulations.isEmpty()) {
            System.out.println("Display the Total Population of the world");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Total Popluation of the World");
            System.out.println("---------------------------------------------------------------------------------------");

            for (TotalPopulation totalPopulation : totalPopulations) {
                System.out.printf("| %-25s |  %-25s |  %-25s |\n",
                        totalPopulation.getTotalPopulation());
            }

            System.out.println("----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No Data details available");
        }
    }

    public ArrayList<TotalContinent> sortTotalContinent(Connection con){
        ArrayList<TotalContinent> sortTotalContinentList = new ArrayList<>();
        String query = "SELECT Continent, SUM(Population) AS total_population FROM country GROUP BY Continent;";
        try{
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()){
                TotalContinent sortTotalContinent = new TotalContinent();
                sortTotalContinent.setContinent(rs.getString("Continent"));
                sortTotalContinent.setTotalPopulation(rs.getInt("total_population"));
                sortTotalContinentList.add(sortTotalContinent);
            }
            return sortTotalContinentList;
        }catch (Exception e){
            System.out.println("Error");
        }
        return null;
    }

    public void displayTotalContinent(ArrayList<TotalContinent> totalContinents) {
        if (totalContinents != null && !totalContinents.isEmpty()) {
            System.out.println("Display the Continent of Population");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.printf("| %-25s |  %-25s |  %-25s | \n",
                    "Continent", "total_population");
            System.out.println("---------------------------------------------------------------------------------------");

            for (TotalContinent totalContinent : totalContinents) {
                System.out.printf("| %-25s |  %-25s |  %-25s |\n",
                        totalContinent.getContinent(),
                        totalContinent.getTotalPopulation());
            }

            System.out.println("----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No continent details available");
        }
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







