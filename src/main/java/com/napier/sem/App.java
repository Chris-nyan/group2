package com.napier.sem;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
//importing Arraylist for using array list in cities

public class App
{


    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    public static void main(String[] args) {
        App app = new App();
        app.connect();
        ArrayList<City> sortCity = app.sortCity(app.con);
//        app.displaySortCity(sortCity);
        ArrayList<CityWorld> sortCityWorld = app.sortCityWorld(app.con);
//        app.displaySortCityWorld(sortCityWorld);
        ArrayList<CityRegion> sortCityRegion = app.sortCityRegion(app.con);
//        app.displaySortCityRegion(sortCityRegion);
        ArrayList<CityCountry> sortCityCountry = app.sortCityCountry(app.con);


        // Retrieve country in world details
        ArrayList<CountriesInWorld> country = app.getCountriesInWorld(app.con);

        //Retrieve continent details
        ArrayList<Continent> continent = app.getContinent(app.con);

        //Retrieve continent details
        ArrayList<Region> regions = app.getRegion(app.con);

        //Retrieve continent details
        ArrayList<UserInputWorld> userInputWorlds = app.getUserInputWorld(app.con);

        // Display result
        app.displayCountry(country);
        app.displayContinent(continent);
        app.displayRegion(regions);
        app.displayUserInputWorld(userInputWorlds);
        app.displaySortCity(sortCity);
        app.displaySortCityWorld(sortCityWorld);
        app.displaySortCityRegion(sortCityRegion);
        app.displaySortCityCountry(sortCityCountry);


        app.disconnect();
    }

    public ArrayList<City> sortCity(Connection con) {
        ArrayList<City> sortCityList = new ArrayList<>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District, country.Continent " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = 'Asia' " +
                "ORDER BY city.Population DESC";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                City sortCity = new City();
                sortCity.setName(rs.getString("Name"));
                sortCity.setCountryName(rs.getString("country_name"));
                sortCity.setPopulation(rs.getInt("Population"));
                sortCity.setDistrict(rs.getString("District"));
                sortCity.setContinent(rs.getString("Continent"));
                sortCityList.add(sortCity);
            }
            return sortCityList;

        } catch (Exception e) {
            System.out.println("Error on sort city");
            e.printStackTrace();
        }
        return null;
    }

    public void displaySortCity(ArrayList<City> sortCities) {
        if (sortCities != null && !sortCities.isEmpty()) {
            System.out.println("Population of the cities in a continent, Asia, sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-15s | %-25s | %-15s |\n", "Name", "Country Name", "Population", "District", "Continent");

            for (City sortCity : sortCities) {
                if (sortCity != null)
                    continue;
                System.out.printf("| %-25s | %-25s | %-15d | %-25s | %-15s |\n",
                        sortCity.getName(), sortCity.getCountryName(), sortCity.getPopulation(), sortCity.getDistrict(), sortCity.getContinent());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities found.");
        }
    }

    /**
     * Sorting cities ON THE WORLD according to population
     */
    public ArrayList<CityWorld> sortCityWorld(Connection con) {
        ArrayList<CityWorld> sortCityWorldList = new ArrayList<>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                CityWorld sortCityWorld = new CityWorld();
                sortCityWorld.setCity_name(rs.getString("Name"));
                sortCityWorld.setCountry_name(rs.getString("country_name"));
                sortCityWorld.setDistrict(rs.getString("District"));
                sortCityWorld.setPopulation(rs.getInt("Population"));
                sortCityWorldList.add(sortCityWorld);
            }
            return sortCityWorldList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityWorld(ArrayList<CityWorld> sortCitiesWorld) {
        if (sortCitiesWorld != null && !sortCitiesWorld.isEmpty()) {
            System.out.println("Population of the cities around the world sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (CityWorld sortCityWorld : sortCitiesWorld) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityWorld.getCity_name(), sortCityWorld.getCountry_name(), sortCityWorld.getDistrict(), sortCityWorld.getPopulation());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }
    /**
     * Sorting cities IN THE REGION according to Population
     */
    public ArrayList<CityRegion> sortCityRegion(Connection con) {
        ArrayList<CityRegion> sortCityRegionList = new ArrayList<>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = 'Middle East'" +
                "ORDER BY city.Population DESC";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                CityRegion sortCityRegion = new CityRegion();
                sortCityRegion.setCity_name(rs.getString("Name"));
                sortCityRegion.setCountry_name(rs.getString("country_name"));
                sortCityRegion.setDistrict(rs.getString("District"));
                sortCityRegion.setPopulation(rs.getInt("Population"));
                sortCityRegionList.add(sortCityRegion);
            }
            return sortCityRegionList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityRegion(ArrayList<CityRegion> sortCitiesRegion) {
        if (sortCitiesRegion != null && !sortCitiesRegion.isEmpty()) {
            System.out.println("Population of the cities in a region called Middle East sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (CityRegion sortCityRegion : sortCitiesRegion) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityRegion.getCity_name(), sortCityRegion.getCountry_name(), sortCityRegion.getDistrict(), sortCityRegion.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }

    /**
     * Sorting cities IN THE COUNTRY according to Population
     */
    public ArrayList<CityCountry> sortCityCountry(Connection con) {
        ArrayList<CityCountry> sortCityCountryList = new ArrayList<>();
        String query = "SELECT city.Name, country.Name AS country_name, city.Population, city.District " +
                "FROM city " +
                "INNER JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = 'Myanmar'" +
                "ORDER BY city.Population DESC";
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                CityCountry sortCityCountry = new CityCountry();
                sortCityCountry.setCity_name(rs.getString("Name"));
                sortCityCountry.setCountry_name(rs.getString("country_name"));
                sortCityCountry.setDistrict(rs.getString("District"));
                sortCityCountry.setPopulation(rs.getInt("Population"));
                sortCityCountryList.add(sortCityCountry);
            }
            return sortCityCountryList;

        } catch (Exception e) {
            System.out.println("Error on sort city on the world");
            e.printStackTrace();
        }
        return null;
    }
    public void displaySortCityCountry(ArrayList<CityCountry> sortCitiesCountry) {
        if (sortCitiesCountry != null && !sortCitiesCountry.isEmpty()) {
            System.out.println("Population of the cities in a country called Myanmar sorting from largest to smallest");
            System.out.printf("| %-25s | %-25s | %-25s | %-25s |\n", "Name", "Country Name", "District", "Population");

            for (CityCountry sortCityCountry : sortCitiesCountry) {
                System.out.printf("| %-25s | %-25s | %-25s | %-25d |\n",
                        sortCityCountry.getCity_name(), sortCityCountry.getCountry_name(), sortCityCountry.getDistrict(), sortCityCountry.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities to display.");
        }
    }



    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(30000);
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }


    /**
     * Disconnect from the MySQL database.
     */


    /**
     * All the countries in the world organised by largest population to smallest.
     */
    public ArrayList<CountriesInWorld> getCountriesInWorld(Connection con)
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
    public ArrayList<Continent> getContinent(Connection con)
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
    public ArrayList<Region> getRegion(Connection con)
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
    public ArrayList<UserInputWorld> getUserInputWorld (Connection con)
    {
        ArrayList<UserInputWorld> a = new ArrayList<UserInputWorld>();
        try {
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
            while (rset.next()) {
                UserInputWorld userinputworld = new UserInputWorld();
                userinputworld.setCode(rset.getString("Code"));
                userinputworld.setCountry_name(rset.getString("Country_Name"));
                userinputworld.setRegion(rset.getString("Region"));
                userinputworld.setContinent(rset.getString("Continent"));
                userinputworld.setPopulation(rset.getInt("Population"));
                a.add(userinputworld);
            }
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get User Input details");
            return null;
        }
    }

    public void displayUserInputWorld (ArrayList < UserInputWorld > userInputWorlds) {
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



    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public void setConnection(Connection mockConnection) {
    }
}