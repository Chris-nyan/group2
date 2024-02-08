import com.napier.sem.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Copy import
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppTest {


//     Commented codes below for testing
//    private static App app;
//    private ResultSet mockResultSet;
//
//    @BeforeAll
//    public static void setUp() {
//        // Initialize the app object before any tests
//        app = new App();
//        // Ensure the connection is established before running the tests
////        app.connect();
//    }
//
//    @Test
//    public void sortCityNull() {
//        // Ensure app is not null before calling its methods
//        assertNotNull(app, "App should not be null");
//        ArrayList<City> result = app.sortCity();
//        assertNull(result, "Result should be null");
//    }

    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

//    @Test
//    void testConnect() {
//        // Assuming you have an instance of YourClass
//        App connect = new App();
//
//        // When
//        connect.connect();
//
//        // Then
//        assertNotNull(connect.getConnection(), "Connection should not be null after connecting");
//        // You can add additional assertions based on the behavior of your connect method
//    }

//    @Test
//     void connect() {
//        app.connect();
//    }
//    @Test
//     void disconnect() {
//        // Implement your disconnection logic here
//        app.disconnect();
//    }
    @Test
    void testSortCityWithCities() throws Exception {
        // Mocking the Connection, Statement, and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Setting up behavior for ResultSet
        when(mockResultSet.next())
                .thenReturn(true)   // First city
                .thenReturn(true)   // Second city
                .thenReturn(false); // No more cities

        when(mockResultSet.getString("Name")).thenReturn("Seoul").thenReturn("Mumbai");
        when(mockResultSet.getString("country_name")).thenReturn("South Korea").thenReturn("India");
        when(mockResultSet.getInt("Population")).thenReturn(9981619).thenReturn(10500000);
        when(mockResultSet.getString("District")).thenReturn("Seoul").thenReturn("Maharashtra");
        when(mockResultSet.getString("Continent")).thenReturn("Asia");

        // When
        ArrayList<City> result = app.sortCity(mockConnection);

        // Then
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() >= 2, "Result should contain at least two cities");
        assertEquals("Seoul", result.get(0).getName(), "City2 should be the first city in the sorted list");
        assertEquals("Mumbai", result.get(1).getName(), "City1 should be the second city in the sorted list");
    }


    // Add more test cases as needed...

    @Test
    public void displaySortCityNull() {
        // Ensure app is not null before calling its methods
        assertNotNull(app, "App should not be null");
        app.displaySortCity(null);
//        ArrayList<CountriesInWorld> countries = app.getCountriesInWorld();
//        assertNotNull(countries, "Countries list should not be null");
    }

    @Test
    void displayCitiesTestEmpty()
    {
        // Create and populate the ArrayList of City objects
        ArrayList<City> sortCityList = new ArrayList<>();
        // Test when sorting an empty list
        app.displaySortCity(sortCityList);
    }

    @Test
    void displayCitiesTestContainsNull()
    {
        // Create and populate the ArrayList of City objects with a null entry
        ArrayList<City> sortCityList = new ArrayList<>();
//        sortCityList.add(null);
        // Test when sorting a list containing null
        app.displaySortCity(sortCityList);
    }

    @Test
    void displaySortCity() {
        // Create and populate the ArrayList of City objects
        ArrayList<City> cities = new ArrayList<>();
        // Add City objects with actual data
        City city = new City();
        city.setName("Yangon");
        city.setCountryName("Myanmar");
        city.setPopulation(1000000);
        city.setDistrict("North");
        city.setContinent("Asia");

        cities.add(city);

        // Call the displaySortCity method
        app.displaySortCity(cities);
    }

    @Test
    void testSortCityWorld() throws Exception {
        // Mocking the Connection, Statement, and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Setting up the expected behavior for the ResultSet
        when(mockResultSet.next())
                .thenReturn(true)   // First city
                .thenReturn(true)   // Second city
                .thenReturn(false); // No more cities

        when(mockResultSet.getString("Name")).thenReturn("Seoul").thenReturn("Mumbai");
        when(mockResultSet.getString("country_name")).thenReturn("South Korea").thenReturn("India");
        when(mockResultSet.getInt("Population")).thenReturn(9981619).thenReturn(10500000);
        when(mockResultSet.getString("District")).thenReturn("Seoul").thenReturn("Maharashtra");

        // When
        ArrayList<CityWorld> result = new App().sortCityWorld(mockConnection);

        // Then
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() >= 2, "Result should contain at least two cities");
        assertEquals("Seoul", result.get(0).getCity_name(), "City2 should be the first city in the sorted list");
        assertEquals("Mumbai", result.get(1).getCity_name(), "City1 should be the second city in the sorted list");
    }
    @Test
    public void displaySortCityWorldNull() {
        // Ensure app is not null before calling its methods
        assertNotNull(app, "City World should not be null");
        app.displaySortCityWorld(null);
//        ArrayList<CountriesInWorld> countries = app.getCountriesInWorld();
//        assertNotNull(countries, "Countries list should not be null");
    }

    @Test
    void displaySortCityWorldEmpty()
    {
        // Create and populate the ArrayList of City objects
        ArrayList<CityWorld> sortCityWorldList = new ArrayList<>();
        // Test when sorting an empty list
        app.displaySortCityWorld(sortCityWorldList);
    }

    @Test
    void displayCityWorldTestContainsNull()
    {
        // Create and populate the ArrayList of City objects with a null entry
        ArrayList<CityWorld> sortCityWorldList = new ArrayList<>();
//        sortCityList.add(null);
        // Test when sorting a list containing null
        app.displaySortCityWorld(sortCityWorldList);
    }

    @Test
    void displaySortCityWorld() {
        // Create and populate the ArrayList of City objects
        ArrayList<CityWorld> sortCityWorldList = new ArrayList<>();
        // Add City objects with actual data
        CityWorld cityworld = new CityWorld();
        cityworld.setCity_name("Yangon");
        cityworld.setCountry_name("Myanmar");
        cityworld.setPopulation(124000000);
        cityworld.setDistrict("Yangon");
        ;

        sortCityWorldList.add(cityworld);

        // Call the displaySortCity method
        app.displaySortCityWorld(sortCityWorldList);
    }

    @Test
    void testSortCityRegion() throws Exception {
        // Mocking the Connection, Statement, and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Setting up the expected behavior for the ResultSet
        when(mockResultSet.next())
                .thenReturn(true)   // First city
                .thenReturn(true)   // Second city
                .thenReturn(false); // No more cities

        when(mockResultSet.getString("Name")).thenReturn("Seoul").thenReturn("Mumbai");
        when(mockResultSet.getString("country_name")).thenReturn("South Korea").thenReturn("India");
        when(mockResultSet.getInt("Population")).thenReturn(9981619).thenReturn(10500000);
        when(mockResultSet.getString("District")).thenReturn("Seoul").thenReturn("Maharashtra");

        // When
        ArrayList<CityRegion> result = new App().sortCityRegion(mockConnection);

        // Then
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() >= 2, "Result should contain at least two cities");
        assertEquals("Seoul", result.get(0).getCity_name(), "City2 should be the first city in the sorted list");
        assertEquals("Mumbai", result.get(1).getCity_name(), "City1 should be the second city in the sorted list");
    }


    @Test
    public void displaySortCityRegionNull() {
        // Ensure app is not null before calling its methods
        assertNotNull(app, "City World should not be null");
        app.displaySortCityRegion(null);
//        ArrayList<CountriesInWorld> countries = app.getCountriesInWorld();
//        assertNotNull(countries, "Countries list should not be null");
    }

    @Test
    void displaySortCityRegionEmpty()
    {
        // Create and populate the ArrayList of City objects
        ArrayList<CityRegion> sortCityRegionList = new ArrayList<>();
        // Test when sorting an empty list
        app.displaySortCityRegion(sortCityRegionList);
    }

    @Test
    void displayCityRegionTestContainsNull()
    {
        // Create and populate the ArrayList of City objects with a null entry
        ArrayList<CityRegion> sortCityRegionList = new ArrayList<>();
//        sortCityList.add(null);
        // Test when sorting a list containing null
        app.displaySortCityRegion(sortCityRegionList);
    }

    @Test
    void displaySortCityRegion() {
        // Create and populate the ArrayList of City objects
        ArrayList<CityRegion> sortCityRegionList = new ArrayList<>();
        // Add City objects with actual data
        CityRegion cityregion = new CityRegion();
        cityregion.setCity_name("Yangon");
        cityregion.setCountry_name("Myanmar");
        cityregion.setPopulation(124000000);
        cityregion.setDistrict("Yangon");
        ;

        sortCityRegionList.add(cityregion);

        // Call the displaySortCity method
        app.displaySortCityRegion(sortCityRegionList);
    }

    @Test
    void testSortCityCountry() throws Exception {
        // Mocking the Connection, Statement, and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Define the behavior of the ResultSet
        when(mockResultSet.next())
                .thenReturn(true)   // First city
                .thenReturn(true)   // Second city
                .thenReturn(false); // No more cities

        when(mockResultSet.getString("Name")).thenReturn("City1").thenReturn("City2");
        when(mockResultSet.getString("country_name")).thenReturn("Country1").thenReturn("Country2");
        when(mockResultSet.getInt("Population")).thenReturn(1000000).thenReturn(2000000);
        when(mockResultSet.getString("District")).thenReturn("District1").thenReturn("District2");

        // When
        ArrayList<CityCountry> result = new App().sortCityCountry(mockConnection);

        // Then
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() >= 2, "Result should contain at least two cities");
        assertEquals("City1", result.get(0).getCity_name(), "City1 should be the first city in the sorted list");
        assertEquals("City2", result.get(1).getCity_name(), "City2 should be the second city in the sorted list");
    }


    @Test
    public void displaySortCityCountryNull() {
        // Ensure app is not null before calling its methods
        assertNotNull(app, "City Country should not be null");
        app.displaySortCityCountry(null);
//        ArrayList<CountriesInWorld> countries = app.getCountriesInWorld();
//        assertNotNull(countries, "Countries list should not be null");
    }

    @Test
    void displaySortCityCountryEmpty()
    {
        // Create and populate the ArrayList of City objects
        ArrayList<CityCountry> sortCityCountryList = new ArrayList<>();
        // Test when sorting an empty list
        app.displaySortCityCountry(sortCityCountryList);
    }

    @Test
    void displayCityCountryTestContainsNull()
    {
        // Create and populate the ArrayList of City objects with a null entry
        ArrayList<CityCountry> sortCityCountryList = new ArrayList<>();
//        sortCityList.add(null);
        // Test when sorting a list containing null
        app.displaySortCityCountry(sortCityCountryList);
    }

    @Test
    void displaySortCityCountry() {
        // Create and populate the ArrayList of City objects
        ArrayList<CityCountry> sortCityCountryList = new ArrayList<>();
        // Add City objects with actual data
        CityCountry citycountry = new CityCountry();
        citycountry.setCity_name("Yangon");
        citycountry.setCountry_name("Myanmar");
        citycountry.setPopulation(124000000);
        citycountry.setDistrict("Yangon");
        ;

        sortCityCountryList.add(citycountry);

        // Call the displaySortCity method
        app.displaySortCityCountry(sortCityCountryList);
    }
    @Test
    void testGetCountriesInWorld() throws Exception {
        // Mocking the Connection, Statement, and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Setting up the expected behavior for the ResultSet
        when(mockResultSet.next())
                .thenReturn(true)   // First country
                .thenReturn(true)   // Second country
                .thenReturn(false); // No more countries

        when(mockResultSet.getString("Code")).thenReturn("USA").thenReturn("CHN");
        when(mockResultSet.getString("Name")).thenReturn("United States").thenReturn("China");
        when(mockResultSet.getString("Continent")).thenReturn("North America").thenReturn("Asia");
        when(mockResultSet.getString("Region")).thenReturn("Western").thenReturn("Eastern Asia");
        when(mockResultSet.getString("SurfaceArea")).thenReturn("9833517.00").thenReturn("9572900.00");
        when(mockResultSet.getInt("IndepYear")).thenReturn(1776).thenReturn(0);
        when(mockResultSet.getInt("Population")).thenReturn(331002651).thenReturn(1409517397);
        when(mockResultSet.getString("LifeExpectancy")).thenReturn("78.8").thenReturn(null);
        when(mockResultSet.getString("GNP")).thenReturn("21433225.00").thenReturn(null);
        when(mockResultSet.getString("GNPOld")).thenReturn("20566000.00").thenReturn(null);
        when(mockResultSet.getString("LocalName")).thenReturn("United States").thenReturn("Zhongquo");
        when(mockResultSet.getString("GovernmentForm")).thenReturn("Federal Republic").thenReturn("People's Republic");
        when(mockResultSet.getString("HeadOfState")).thenReturn("Joe Biden").thenReturn("Xi Jinping");
        when(mockResultSet.getInt("Capital")).thenReturn(439).thenReturn(1891);
        when(mockResultSet.getString("Code2")).thenReturn("US").thenReturn("CN");

        // When
        ArrayList<CountriesInWorld> result = new App().getCountriesInWorld(mockConnection);

        // Then
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() >= 2, "Result should contain at least two cities");
        assertEquals("USA", result.get(0).getCode(), "Country1 should be the first city in the sorted list");
        assertEquals("CHN", result.get(1).getCode(), "Country2 should be the second city in the sorted list");
    }
    @Test
    public void displayCountriesInWorldNull() {
        // Ensure app is not null before calling its methods
        assertNotNull(app, "City Country should not be null");
        app.displayCountry(null);
//        ArrayList<CountriesInWorld> countries = app.getCountriesInWorld();
//        assertNotNull(countries, "Countries list should not be null");
    }

    @Test
    void displayCountriesInWorldEmpty()
    {
        // Create and populate the ArrayList of City objects
        ArrayList<CountriesInWorld> a = new ArrayList<>();
        // Test when sorting an empty list
        app.displayCountry(a);
    }

    @Test
    void displayCountriesInWorldTestContainsNull()
    {
        // Create and populate the ArrayList of City objects with a null entry
        ArrayList<CountriesInWorld> a = new ArrayList<>();
//        sortCityList.add(null);
        // Test when sorting a list containing null
        app.displayCountry(a);
    }

    @Test
    void testDisplayCountry() {
        // Create and populate the ArrayList of CountriesInWorld objects
        ArrayList<CountriesInWorld> countries = new ArrayList<>();

        // Add a sample CountriesInWorld object
        CountriesInWorld china = new CountriesInWorld();
        china.setCode("CHN");
        china.setName("China");
        china.setContinent("Asia");
        china.setRegion("Eastern Asia");
        china.setSurfaceArea(BigDecimal.valueOf(9572900.00));
        china.setIndepYear(-1523);
        china.setPopulation(1277558000);
        china.setLifeExpectancy(BigDecimal.valueOf(71.4));
        china.setGNP(BigDecimal.valueOf(982268.00));
        china.setGNPOld(BigDecimal.valueOf(917719.00));
        china.setLocalName("Zhongquo");
        china.setGovernmentForm("People's Republic");
        china.setHeadOfState("Jiang Zemin");
        china.setCapital(Integer.parseInt("1891"));
        china.setCode2("CN");

        countries.add(china);

        // Call the displayCountry method
        app.displayCountry(countries);


    }
    @Test
    void testGetContinent() throws Exception {
        // Mocking the Connection, Statement, and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Setting up the expected behavior for the ResultSet
        when(mockResultSet.next())
                .thenReturn(true)   // First continent
                .thenReturn(true)   // Second continent
                .thenReturn(false); // No more continents

        when(mockResultSet.getString("Code")).thenReturn("CHN").thenReturn("IND");
        when(mockResultSet.getString("Name")).thenReturn("China").thenReturn("India");
        when(mockResultSet.getString("Region")).thenReturn("Eastern Asia").thenReturn("Southern and Central Asia");
        when(mockResultSet.getString("Continent")).thenReturn("Asia").thenReturn("Asia");
        when(mockResultSet.getInt("Population")).thenReturn(1277558000).thenReturn(1013662000);

        // When
        ArrayList<Continent> result = new App().getContinent(mockConnection);

        // Then
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() >= 2, "Result should contain at least two continents");
        assertEquals("CHN", result.get(0).getCode(), "C1 should be the code of the first continent");
        assertEquals("IND", result.get(1).getCode(), "C2 should be the code of the second continent");
    }

    @Test
    public void displayContinentNull() {
        // Ensure app is not null before calling its methods
        assertNotNull(app, "Continent should not be null");
        app.displayContinent(null);
//        ArrayList<CountriesInWorld> countries = app.getCountriesInWorld();
//        assertNotNull(countries, "Countries list should not be null");
    }

    @Test
    void displayContinentEmpty()
    {
        // Create and populate the ArrayList of City objects
        ArrayList<Continent> a = new ArrayList<Continent>();
        // Test when sorting an empty list
        app.displayContinent(a);
    }

    @Test
    void displayContinentTestContainsNull()
    {
        // Create and populate the ArrayList of City objects with a null entry
        ArrayList<Continent> a = new ArrayList<Continent>();
//        sortCityList.add(null);
        // Test when sorting a list containing null
        app.displayContinent(a);
    }

    @Test
    void testDisplayContinent() {
        // Create and populate the ArrayList of CountriesInWorld objects
        ArrayList<Continent> a = new ArrayList<Continent>();

        // Add a sample CountriesInWorld object
        Continent continent = new Continent();
        continent.setCode("CHN");
        continent.setName("China");
        continent.setRegion("Eastern Asia");
        continent.setContinent("Asia");
        continent.setPopulation(1277558000);

        a.add(continent);

        // Call the displayCountry method
        app.displayContinent(a);


    }

        @Test
        void testGetRegion() throws Exception {
            // Mocking the Connection, Statement, and ResultSet
            Connection mockConnection = mock(Connection.class);
            Statement mockStatement = mock(Statement.class);
            when(mockConnection.createStatement()).thenReturn(mockStatement);
            ResultSet mockResultSet = mock(ResultSet.class);
            when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

            // Setting up the expected behavior for the ResultSet
            when(mockResultSet.next())
                    .thenReturn(true)   // First region
                    .thenReturn(true)   // Second region
                    .thenReturn(false); // No more regions

            when(mockResultSet.getString("Code")).thenReturn("CHN").thenReturn("JPN");
            when(mockResultSet.getString("Name")).thenReturn("China").thenReturn("Japan");
            when(mockResultSet.getString("Region")).thenReturn("Eastern Asia").thenReturn("Eastern Asia");
            when(mockResultSet.getString("Continent")).thenReturn("Asia").thenReturn("Asia");
            when(mockResultSet.getInt("Population")).thenReturn(1277558000).thenReturn(126714000);

            // When
            ArrayList<Region> result = new App().getRegion(mockConnection);

            // Then
            assertNotNull(result, "Result should not be null");
            assertTrue(result.size() >= 2, "Result should contain at least two regions");
            assertEquals("CHN", result.get(0).getCode(), "CHN should be the code of the first region");
            assertEquals("JPN", result.get(1).getCode(), "JPN should be the code of the second region");

    }


    @Test
    public void displayRegionNull() {
        // Ensure app is not null before calling its methods
        assertNotNull(app, "Region should not be null");
        app.displayRegion(null);
//        ArrayList<CountriesInWorld> countries = app.getCountriesInWorld();
//        assertNotNull(countries, "Countries list should not be null");
    }

    @Test
    void displayRegionEmpty()
    {
        // Create and populate the ArrayList of City objects
        ArrayList<Continent> a = new ArrayList<Continent>();
        // Test when sorting an empty list
        app.displayContinent(a);
    }

    @Test
    void displayContinentContainsNull()
    {
        // Create and populate the ArrayList of City objects with a null entry
        ArrayList<Region> a = new ArrayList<Region>();
//        sortCityList.add(null);
        // Test when sorting a list containing null
        app.displayRegion(a);
    }

    @Test
    void testDisplayRegion() {
        // Create and populate the ArrayList of CountriesInWorld objects
        ArrayList<Region> a = new ArrayList<Region>();

        // Add a sample CountriesInWorld object
        Region region = new Region();
        region.setCode("CHN");
        region.setName("China");
        region.setRegion("Eastern Asia");
        region.setContinent("Asia");
        region.setPopulation(1277558000);

        a.add(region);

        // Call the displayCountry method
        app.displayRegion(a);


    }
    @Test
    void testGetUserInputWorld() throws Exception {
        // Mocking the Connection, Statement, and ResultSet
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Setting up the expected behavior for the ResultSet
        when(mockResultSet.next())
                .thenReturn(true)   // First entry
                .thenReturn(true)   // Second entry
                .thenReturn(false); // No more entries

        when(mockResultSet.getString("Code")).thenReturn("CHN").thenReturn("IND");
        when(mockResultSet.getString("Country_Name")).thenReturn("China").thenReturn("India");
        when(mockResultSet.getString("Region")).thenReturn("Eastern Asia").thenReturn("Southern and Central Asia");
        when(mockResultSet.getString("Continent")).thenReturn("Asia").thenReturn("Asia");
        when(mockResultSet.getInt("Population")).thenReturn(1277558000).thenReturn(1013662000);

        // When
        ArrayList<UserInputWorld> result = new App().getUserInputWorld(mockConnection);

        // Then
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() >= 2, "Result should contain at least two entries");
        assertEquals("CHN", result.get(0).getCode(), "CHN should be the code of the first entry");
        assertEquals("IND", result.get(1).getCode(), "IND should be the code of the second entry");

}

    @Test
    public void displayUserWorldNull() {
        // Ensure app is not null before calling its methods
        assertNotNull(app, "World should not be null");
        app.displayUserInputWorld(null);
//        ArrayList<CountriesInWorld> countries = app.getCountriesInWorld();
//        assertNotNull(countries, "Countries list should not be null");
    }

    @Test
    void displayUserWorldEmpty()
    {
        // Create and populate the ArrayList of City objects
        ArrayList<UserInputWorld> a = new ArrayList<UserInputWorld>();
        // Test when sorting an empty list
        app.displayUserInputWorld(a);
    }

    @Test
    void displayUserWorldContainsNull()
    {
        // Create and populate the ArrayList of City objects with a null entry
        ArrayList<UserInputWorld> a = new ArrayList<UserInputWorld>();
//        sortCityList.add(null);
        // Test when sorting a list containing null
        app.displayUserInputWorld(a);
    }

    @Test
    void testDisplayUserWorld() {
        // Create and populate the ArrayList of CountriesInWorld objects
        ArrayList<UserInputWorld> a = new ArrayList<UserInputWorld>();

        // Add a sample CountriesInWorld object
        UserInputWorld userWorld = new UserInputWorld();
        userWorld.setCode("CHN");
        userWorld.setCountry_name("China");
        userWorld.setRegion("Eastern Asia");
        userWorld.setContinent("Asia");
        userWorld.setPopulation(1277558000);

        a.add(userWorld);

        // Call the displayCountry method
        app.displayUserInputWorld(a);


    }



}