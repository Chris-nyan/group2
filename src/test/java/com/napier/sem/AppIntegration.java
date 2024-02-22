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

    @Test
    void testSortUserInputContinent() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<UserInputContinent> sortUserInputContinentList = app.getUserInputContinent(con);

        // Add your assertions based on the expected results
        assertNotNull(sortUserInputContinentList);
        assertFalse(sortUserInputContinentList.isEmpty());

        // Validate each property of the first city in the list
        UserInputContinent firstUserInputContinent = sortUserInputContinentList.get(0);

        assertEquals("CHN", firstUserInputContinent.getCode());
        assertEquals("China", firstUserInputContinent.getName());
        assertEquals("Eastern Asia", firstUserInputContinent.getRegion());
        assertEquals("Asia", firstUserInputContinent.getContinent());
        assertEquals(1277558000, firstUserInputContinent.getPopulation());
        assertEquals(1891, firstUserInputContinent.getCapital());

    }

    @Test
    void testSortUserInputRegion() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<UserInputRegion> sortUserInputRegionList = app.getUserInputRegion(con);

        // Add your assertions based on the expected results
        assertNotNull(sortUserInputRegionList);
        assertFalse(sortUserInputRegionList.isEmpty());

        // Validate each property of the first city in the list
        UserInputRegion firstUserInputRegion = sortUserInputRegionList.get(0);

        assertEquals("CHN", firstUserInputRegion.getCode());
        assertEquals("China", firstUserInputRegion.getName());
        assertEquals("Eastern Asia", firstUserInputRegion.getRegion());
        assertEquals("Asia", firstUserInputRegion.getContinent());
        assertEquals(1277558000, firstUserInputRegion.getPopulation());
        assertEquals(1891, firstUserInputRegion.getCapital());

    }

    @Test
    void testSortCapitalCity() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<Capitalcity> sortCapitalCityList = app.getCapitalCity(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCapitalCityList);
        assertFalse(sortCapitalCityList.isEmpty());

        // Validate each property of the first city in the list
        Capitalcity firstCapitalCity = sortCapitalCityList.get(0);

        assertEquals("Seoul", firstCapitalCity.getCapital_Name());
        assertEquals("South Korea", firstCapitalCity.getCountry_Name());
        assertEquals(9981619, firstCapitalCity.getPopulation());


    }

    @Test
    void testSortCapitalContinent() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<CapitalContinent> sortCapitalContinentList = app.getCapitalContinent(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCapitalContinentList);
        assertFalse(sortCapitalContinentList.isEmpty());

        // Validate each property of the first city in the list
        CapitalContinent firstCapitalContinent = sortCapitalContinentList.get(0);

        assertEquals("Seoul", firstCapitalContinent.getCapital_Name());
        assertEquals("South Korea", firstCapitalContinent.getCountry_Name());
        assertEquals(9981619, firstCapitalContinent.getPopulation());

    }

    @Test
    void testSortCapitalRegion() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<CapitalRegion> sortCapitalRegionList = app.getCapitalRegion(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCapitalRegionList);
        assertFalse(sortCapitalRegionList.isEmpty());

        // Validate each property of the first city in the list
        CapitalRegion firstCapitalRegion = sortCapitalRegionList.get(0);

        assertEquals("Baghdad", firstCapitalRegion.getCapital_Name());
        assertEquals("Iraq", firstCapitalRegion.getCountry_Name());
        assertEquals(4336000, firstCapitalRegion.getPopulation());

    }

    @Test
    void testInputCapitalWorld() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<InputCapitalWorld> sortInputCapitalWorldList = app.getInputCapitalWorld(con);

        // Add your assertions based on the expected results
        assertNotNull(sortInputCapitalWorldList);
        assertFalse(sortInputCapitalWorldList.isEmpty());

        // Validate each property of the first city in the list
        InputCapitalWorld firstInputCapitalWorld = sortInputCapitalWorldList.get(0);

        assertEquals("Seoul", firstInputCapitalWorld.getCapital_Name());
        assertEquals("South Korea", firstInputCapitalWorld.getCountry_Name());
        assertEquals(9981619, firstInputCapitalWorld.getPopulation());

    }

    @Test
    void testInputCapitalContinent() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<InputCapitalContinent> sortInputCapitalContinentList = app.getInputCapitalContinent(con);

        // Add your assertions based on the expected results
        assertNotNull(sortInputCapitalContinentList);
        assertFalse(sortInputCapitalContinentList.isEmpty());

        // Validate each property of the first city in the list
        InputCapitalContinent firstInputCapitalContinent = sortInputCapitalContinentList.get(0);

        assertEquals("Seoul", firstInputCapitalContinent.getCapital_Name());
        assertEquals("South Korea", firstInputCapitalContinent.getCountry_Name());
        assertEquals(9981619, firstInputCapitalContinent.getPopulation());

    }

    @Test
    void testInputCapitalRegion() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<InputCapitalRegion> sortInputCapitalRegionList = app.getInputCapitalRegion(con);

        // Add your assertions based on the expected results
        assertNotNull(sortInputCapitalRegionList);
        assertFalse(sortInputCapitalRegionList.isEmpty());

        // Validate each property of the first city in the list
        InputCapitalRegion firstInputCapitalRegion = sortInputCapitalRegionList.get(0);

        assertEquals("Baghdad", firstInputCapitalRegion.getCapital_Name());
        assertEquals("Iraq", firstInputCapitalRegion.getCountry_Name());
        assertEquals(4336000, firstInputCapitalRegion.getPopulation());

    }

    @Test
    void testPopulation() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<Population> sortPopulationList = app.getPopulation(con);


        // Add your assertions based on the expected results
        assertNotNull(sortPopulationList);
        assertFalse(sortPopulationList.isEmpty());
       // Validate each property of the first city in the list
        Population firstPopulation = sortPopulationList.get(0);

        assertEquals("Asia", firstPopulation.getContinent());
        assertEquals(900937599400L, firstPopulation.getTotal_Population());
        assertEquals(697604103, firstPopulation.getPopulation_In_Cities());
        assertEquals(900239995297L, firstPopulation.getPopulation_Not_In_Cities());
        assertEquals(BigDecimal.valueOf(0.08), firstPopulation.getPercentage_Population_In_Cities());
        assertEquals(BigDecimal.valueOf(99.92), firstPopulation.getPercentage_Population_Not_In_Cities());
    }

    @Test
    void testPopulationRegion() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<PopulationRegion> sortPopulationRegionList = app.getPopulationRegion(con);

        // Add your assertions based on the expected results
        assertNotNull(sortPopulationRegionList);
        assertFalse(sortPopulationRegionList.isEmpty());

        // Validate each property of the first city in the list
        PopulationRegion firstPopulationRegion = sortPopulationRegionList.get(0);

        assertEquals("Southern and Central Asia", firstPopulationRegion.getRegion());
        assertEquals(363665421000L, firstPopulationRegion.getTotal_Population());
        assertEquals(207688970, firstPopulationRegion.getPopulation_In_Cities());
        assertEquals(363457732030L, firstPopulationRegion.getPopulation_Not_In_Cities());
        assertEquals(BigDecimal.valueOf(0.06), firstPopulationRegion.getPercentage_Population_In_Cities());
        assertEquals(BigDecimal.valueOf(99.94), firstPopulationRegion.getPercentage_Population_Not_In_Cities());

    }

    @Test
    void testPopulationCountry() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<PopulationCountry> sortPopulationCountryList = app.getPopulationCountry(con);

        // Add your assertions based on the expected results
        assertNotNull(sortPopulationCountryList);
        assertFalse(sortPopulationCountryList.isEmpty());

        // Validate each property of the first city in the list
        PopulationCountry firstPopulationCountry = sortPopulationCountryList.get(0);

        assertEquals("Afghanistan", firstPopulationCountry.getName());
        assertEquals(90880000L, firstPopulationCountry.getTotal_Population());
        assertEquals(2332100, firstPopulationCountry.getPopulation_In_Cities());
        assertEquals(88547900L, firstPopulationCountry.getPopulation_Not_In_Cities());
        assertEquals(BigDecimal.valueOf(2.57), firstPopulationCountry.getPercentage_Population_In_Cities());
        assertEquals(BigDecimal.valueOf(97.43), firstPopulationCountry.getPercentage_Population_Not_In_Cities());

    }

    @Test
    void testCityDistrict() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<CityDistrict> sortCityDistrictList = app.sortCityDistrict(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityDistrictList);
        assertFalse(sortCityDistrictList.isEmpty());

        // Validate each property of the first city in the list
        CityDistrict firstCityDistrict = sortCityDistrictList.get(0);

        assertEquals("Mandalay", firstCityDistrict.getCity_name());
        assertEquals("Myanmar", firstCityDistrict.getCountry_name());
        assertEquals(885300, firstCityDistrict.getPopulation());
        assertEquals("Mandalay", firstCityDistrict.getDistrict());


    }

    @Test
    void testCityTopWorld() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<TopCity> sortCityTopWorld = app.sortCityTopWorld(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityTopWorld);
        assertFalse(sortCityTopWorld.isEmpty());

        // Validate each property of the first city in the list
        TopCity firstCityTopWorld = sortCityTopWorld.get(0);

        assertEquals("Mumbai (Bombay)", firstCityTopWorld.getCity_name());
        assertEquals("India", firstCityTopWorld.getCountry_name());
        assertEquals(10500000, firstCityTopWorld.getPopulation());
        assertEquals("Maharashtra", firstCityTopWorld.getDistrict());


    }

    @Test
    void testCityTopContinent() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<TopCity> sortCityTopContinent = app.sortCityTopContinent(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityTopContinent);
        assertFalse(sortCityTopContinent.isEmpty());

        // Validate each property of the first city in the list
        TopCity firstCityTopContinent = sortCityTopContinent.get(0);

        assertEquals("Moscow", firstCityTopContinent.getCity_name());
        assertEquals("Russian Federation", firstCityTopContinent.getCountry_name());
        assertEquals(8389200, firstCityTopContinent.getPopulation());
        assertEquals("Moscow (City)", firstCityTopContinent.getDistrict());


    }

    @Test
    void testCityTopCountry() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<TopCity> sortCityTopCountry = app.sortCityTopCountry(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityTopCountry);
        assertFalse(sortCityTopCountry.isEmpty());

        // Validate each property of the first city in the list
        TopCity firstCityTopCountry = sortCityTopCountry.get(0);

        assertEquals("London", firstCityTopCountry.getCity_name());
        assertEquals("United Kingdom", firstCityTopCountry.getCountry_name());
        assertEquals(7285000, firstCityTopCountry.getPopulation());
        assertEquals("England", firstCityTopCountry.getDistrict());


    }

    @Test
    void testCityTopRegion() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<TopCity> sortCityTopRegion = app.sortCityTopRegion(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityTopRegion);
        assertFalse(sortCityTopRegion.isEmpty());

        // Validate each property of the first city in the list
        TopCity firstCityTopRegion = sortCityTopRegion.get(0);

        assertEquals("Istanbul", firstCityTopRegion.getCity_name());
        assertEquals("Turkey", firstCityTopRegion.getCountry_name());
        assertEquals(8787958, firstCityTopRegion.getPopulation());
        assertEquals("Istanbul", firstCityTopRegion.getDistrict());


    }

    @Test
    void testCityTopDistrict() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<TopCity> sortCityTopDistrict = app.sortCityTopDistrict(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityTopDistrict);
        assertFalse(sortCityTopDistrict.isEmpty());

        // Validate each property of the first city in the list
        TopCity firstCityTopDistrict = sortCityTopDistrict.get(0);

        assertEquals("London", firstCityTopDistrict.getCity_name());
        assertEquals("United Kingdom", firstCityTopDistrict.getCountry_name());
        assertEquals(7285000, firstCityTopDistrict.getPopulation());
        assertEquals("England", firstCityTopDistrict.getDistrict());


    }

    @Test
    void testTotalRegion() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<TotalRegion> sortTotalRegion = app.sortTotalRegion(con);

        // Add your assertions based on the expected results
        assertNotNull(sortTotalRegion);
        assertFalse(sortTotalRegion.isEmpty());

        // Validate each property of the first city in the list
        TotalRegion firstTotalRegion = sortTotalRegion.get(0);

        assertEquals("Middle East", firstTotalRegion.getRegion());
        assertEquals(188380700, firstTotalRegion.getTotalPopulation());


    }

    @Test
    void testDistrictPopulation() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<DistrictPopulation> sortDistrictPopulation = app.sortDistrictPopulation(con);

        // Add your assertions based on the expected results
        assertNotNull(sortDistrictPopulation);
        assertFalse(sortDistrictPopulation.isEmpty());

        // Validate each property of the first city in the list
        DistrictPopulation firstDistrictPopulation = sortDistrictPopulation.get(0);

        assertEquals("England", firstDistrictPopulation.getDistrict());
        assertEquals(19978543, firstDistrictPopulation.getPopulation());


    }

    @Test
    void testCityPopulation() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<CityPopulation> sortCityPopulation = app.sortCityPopulation(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCityPopulation);
        assertFalse(sortCityPopulation.isEmpty());

        // Validate each property of the first city in the list
        CityPopulation firstCityPopulation = sortCityPopulation.get(0);

        assertEquals("Kabul", firstCityPopulation.getName());
        assertEquals(1780000, firstCityPopulation.getPopulation());


    }

    @Test
    void testCountryPopulation() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<CountryPopulation> sortCountryPopulation = app.sortCountryPopulation(con);

        // Add your assertions based on the expected results
        assertNotNull(sortCountryPopulation);
        assertFalse(sortCountryPopulation.isEmpty());

        // Validate each property of the first city in the list
        CountryPopulation firstCountryPopulation = sortCountryPopulation.get(0);

        assertEquals("Bahamas", firstCountryPopulation.getName());
        assertEquals(307000, firstCountryPopulation.getPopulation());


    }

    @Test
    void testTotalPopulation() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<TotalPopulation> sortTotalPopulation = app.sortTotalPopulation(con);

        // Add your assertions based on the expected results
        assertNotNull(sortTotalPopulation);
        assertFalse(sortTotalPopulation.isEmpty());

        // Validate each property of the first city in the list
        TotalPopulation firstTotalPopulation = sortTotalPopulation.get(0);

        assertEquals(6078749450L, firstTotalPopulation.getTotalPopulation());


    }

    @Test
    void testTotalContinent() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<TotalContinent> sortTotalContinent = app.sortTotalContinent(con);

        // Add your assertions based on the expected results
        assertNotNull(sortTotalContinent);
        assertFalse(sortTotalContinent.isEmpty());

        // Validate each property of the first city in the list
        TotalContinent firstTotalContinent = sortTotalContinent.get(0);

        assertEquals("North America", firstTotalContinent.getContinent());
        assertEquals(482993000L, firstTotalContinent.getTotal_population());


    }

    @Test
    void testLanguage() {
        // Assuming sortCity method returns a list of cities for testing
        ArrayList<Language> sortLanguage = app.sortLanguage(con);

        // Add your assertions based on the expected results
        assertNotNull(sortLanguage);
        assertFalse(sortLanguage.isEmpty());

        // Validate each property of the first city in the list
        Language firstLanguage = sortLanguage.get(0);

        assertEquals("Chinese", firstLanguage.getLanguage());
        assertEquals(1191843539L, firstLanguage.getTotalPopulation());
        assertEquals(19.61, firstLanguage.getPercentageOfLanguage());


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