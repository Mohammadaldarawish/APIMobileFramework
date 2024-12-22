package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class BaseTest {
    public static String baseURI;

    @BeforeClass
    public static void setup() {
        // Set the global base URI here once for all tests
        RestAssured.baseURI = "https://dev.proxy.usclaritytech.com";
    }

    @BeforeMethod
    public static void beforeMethod() {
        baseURI = utils.ApiHelper.getBaseURI();
    }


    @DataProvider(name = "contactIdProvider")
    public Object[][] provideContactIds() {
        return new Object[][]{
                {"4083642"},
                {"1234567"}
        };
    }
}



