package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        DatabaseConnection a = new DatabaseConnection();

        // Connect to database
        a.connect();

        // Retrieve country details
        ArrayList<Countries> country = a.getcountries();

        // Display result
        a.displayCountry(country);

        // Disconnect from database
        a.disconnect();


    }


}