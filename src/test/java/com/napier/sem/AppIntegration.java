package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegration {

    static App app;
    static Connection con;

    @BeforeAll
    static void init() {
        app = new App();
        con = app.connect("localhost:33061", 0);

        // Assuming you have a method to establish a database connection in your App class
//        try {
//            con = DriverManager.getConnection("your_database_connection_string");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            fail("Failed to connect to the database");
//        }
    }

    // Testing here
//    @Test
//    void testSortCity() {
////        ArrayList<City> sortedCities = app.sortCity(con);
//        ArrayList<City> sortCityList = app.sortCity(con);
//
//        // Add your assertions based on the expected results
////        assertNotNull(sortCityList);
//        assertFalse(sortCityList.isEmpty());
//
//        // You may need to adjust the assertions based on your specific requirements and data
//        // For example, assuming you know the expected data after sorting
//        assertEquals("Mumbai(Bombay)", sortCityList.get(0).getName());
//        assertEquals("India", sortCityList.get(0).getCountryName());
//        // Add more assertions as needed
//    }

    @Test
    void testSortCity() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<City> sortCityList = app.sortCity(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityList);
        assertFalse(sortCityList.isEmpty());

        // Validate each property of the first city in the list
        City firstCity = sortCityList.get(0);

        assertEquals("Mumbai (Bombay)", firstCity.getName());
        assertEquals("India", firstCity.getCountryName());
        assertEquals(10500000, firstCity.getPopulation());
        assertEquals("Maharashtra", firstCity.getDistrict());
        assertEquals("Asia", firstCity.getContinent());
    }

    @Test
    void testSortCityWorld() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<CityWorld> sortCityWorldList = app.sortCityWorld(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityWorldList);
        assertFalse(sortCityWorldList.isEmpty());

        // Validate each property of the first city in the list
        CityWorld firstCityWorld = sortCityWorldList.get(0);

        assertEquals("Mumbai (Bombay)", firstCityWorld.getCity_name());
        assertEquals("India", firstCityWorld.getCountry_name());
        assertEquals(10500000, firstCityWorld.getPopulation());
        assertEquals("Maharashtra", firstCityWorld.getDistrict());

    }

    @Test
    void testSortCityRegion() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<CityRegion> sortCityRegionList = app.sortCityRegion(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityRegionList);
        assertFalse(sortCityRegionList.isEmpty());

        // Validate each property of the first city in the list
        CityRegion firstCityRegion = sortCityRegionList.get(0);

        assertEquals("Istanbul", firstCityRegion.getCity_name());
        assertEquals("Turkey", firstCityRegion.getCountry_name());
        assertEquals(8787958, firstCityRegion.getPopulation());
        assertEquals("Istanbul", firstCityRegion.getDistrict());

    }

    @Test
    void testSortCityCountry() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<CityCountry> sortCityCountryList = app.sortCityCountry(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityCountryList);
        assertFalse(sortCityCountryList.isEmpty());

        // Validate each property of the first city in the list
        CityCountry firstCityCountry = sortCityCountryList.get(0);

        assertEquals("Rangoon (Yangon)", firstCityCountry.getCity_name());
        assertEquals("Myanmar", firstCityCountry.getCountry_name());
        assertEquals(3361700, firstCityCountry.getPopulation());
        assertEquals("Rangoon [Yangon]", firstCityCountry.getDistrict());

    }

    @Test
    void testSortCountriesInWorld() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<CountriesInWorld> sortCountriesInWorldList = app.getCountriesInWorld(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCountriesInWorldList);
        assertFalse(sortCountriesInWorldList.isEmpty());

        // Validate each property of the first city in the list
        CountriesInWorld firstCountriesInWorld = sortCountriesInWorldList.get(0);

        assertEquals("CHN", firstCountriesInWorld.getCode());
        assertEquals("China", firstCountriesInWorld.getName());
        assertEquals("Asia", firstCountriesInWorld.getContinent());
        assertEquals("Eastern Asia", firstCountriesInWorld.getRegion());
        assertEquals(1277558000, firstCountriesInWorld.getPopulation());
        assertEquals(Integer.parseInt("1891"), firstCountriesInWorld.getCapital());

    }

    @Test
    void testSortContinent() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<Continent> sortContinentList = app.getContinent(con);

        // Add your assertions based on the expected results
        assertNotNull(sortContinentList);
        assertFalse(sortContinentList.isEmpty());

        // Validate each property of the first city in the list
        Continent firstContinent = sortContinentList.get(0);

        assertEquals("CHN", firstContinent.getCode());
        assertEquals("China", firstContinent.getName());
        assertEquals("Eastern Asia", firstContinent.getRegion());
        assertEquals("Asia", firstContinent.getContinent());
        assertEquals(1277558000, firstContinent.getPopulation());

    }

    @Test
    void testSortRegion() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<Region> sortRegionList = app.getRegion(con);

        // Add your assertions based on the expected results
        assertNotNull(sortRegionList);
        assertFalse(sortRegionList.isEmpty());

        // Validate each property of the first city in the list
        Region firstRegion = sortRegionList.get(0);

        assertEquals("CHN", firstRegion.getCode());
        assertEquals("China", firstRegion.getName());
        assertEquals("Eastern Asia", firstRegion.getRegion());
        assertEquals("Asia", firstRegion.getContinent());
        assertEquals(1277558000, firstRegion.getPopulation());

    }

    @Test
    void testSortUserInputWorld() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<UserInputWorld> sortUserInputWorldList = app.getUserInputWorld(con);

        // Add your assertions based on the expected results
        assertNotNull(sortUserInputWorldList);
        assertFalse(sortUserInputWorldList.isEmpty());

        // Validate each property of the first city in the list
        UserInputWorld firstUserInputWorld = sortUserInputWorldList.get(0);

        assertEquals("CHN", firstUserInputWorld.getCode());
        assertEquals("China", firstUserInputWorld.getName());
        assertEquals("Eastern Asia", firstUserInputWorld.getRegion());
        assertEquals("Asia", firstUserInputWorld.getContinent());
        assertEquals(1277558000, firstUserInputWorld.getPopulation());

    }


    // Close the database connection after all tests are executed
    @AfterAll
    static void cleanup() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
