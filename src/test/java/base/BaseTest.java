package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public static String baseURI;
    private static Properties properties;

    static {
        String configPath = System.getProperty("configPath", "src/main/resources/config.properties");
        try (FileInputStream fileInputStream = new FileInputStream(configPath)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file from: " + configPath, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    @BeforeClass
    public static void setup() {
        // Set the global base URI here once for all tests
        baseURI = properties.getProperty("baseURI");
        RestAssured.baseURI = baseURI;

    }

    @DataProvider(name = "contactIdProvider")
    public Object[][] provideContactIds() {
        String contactId1 = properties.getProperty("contactId1");
        String contactId2 = properties.getProperty("contactId2");

        return new Object[][]{
                {contactId1},
                {contactId2},
        };
    }
}



