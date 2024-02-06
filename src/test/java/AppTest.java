import com.napier.sem.App;
import com.napier.sem.Countries;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private static App app;

    @BeforeAll
    public static void setUp() {
        // Initialize the app object before any tests
        app = new App();
    }

    @Test
    public void testGetCountries() {
        // Ensure app is not null before calling its methods
        assertNotNull(app, "App should not be null");

        ArrayList<Countries> countries = app.getcountries();
        assertNotNull("Countries list should not be null", (Supplier<String>) countries);
    }




}
