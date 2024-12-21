package utils;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiAssertions {

    // Assert status code dynamically
    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertThat("Status code mismatch", response.getStatusCode(), is(expectedStatusCode));
    }

    // Assert success flag dynamically
    public static void assertSuccessFlag(Response response, boolean expectedSuccessFlag) {
        assertThat("Success flag mismatch", response.jsonPath().getBoolean("success"), is(expectedSuccessFlag));
    }

    // Assert message dynamically
    public static void assertMessage(Response response, String expectedMessage) {
        assertThat("Message mismatch", response.jsonPath().getString("message"), is(expectedMessage));
    }

    // Assert token structure dynamically (e.g., JWT token format)
    public static void assertTokenStructure(String token) {
        assertThat("Token is empty or null", token, not(isEmptyOrNullString()));
        assertThat("Token format is invalid", token, startsWith("eyJ"));
    }

    // Assert response contains data (for API responses with data field)
    public static void assertResponseContainsData(Response response) {
        assertThat("Response does not contain 'data' field", response.jsonPath().get("data"), notNullValue());
    }

    // Assert response contains a specific field
    public static void assertResponseContainsField(Response response, String field) {
        assertThat("Response does not contain field: " + field, response.jsonPath().get(field), notNullValue());
    }
}
