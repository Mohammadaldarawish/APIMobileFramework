package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.mapper.ObjectMapperType;

public class ApiHelper {

    public static String getBaseURI() {
        return RestAssured.baseURI;  // Using the global baseURI set in BaseTest
    }

    private static void logRequestAndResponse(String method, String baseURI, Object body, Response response) {
        long responseTime = response.getTime();  // Time taken for the request

        // Log request details
        System.out.println("=== REQUEST DETAILS ===");
        System.out.println("HTTP Method: " + method);
        System.out.println("Request Endpoint: " + baseURI);
        if (body != null) {
            System.out.println("Request Body: " + body.toString());
        } else {
            System.out.println("Request Body: No body content");
        }

        // Log response details
        System.out.println("\n=== RESPONSE DETAILS ===");
        System.out.println("HTTP Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Time: " + responseTime + " ms");

        // Log additional details for better traceability
        System.out.println("\n=== ADDITIONAL DETAILS ===");
        if (response.getStatusCode() >= 400) {
            System.out.println("Error Response: Status code indicates failure (>=400).");
        } else {
            System.out.println("Success Response: Status code indicates success (<400).");
        }
        System.out.println("Request Timestamp: " + java.time.LocalDateTime.now());
        System.out.println("Request Origin: " + System.getProperty("user.name") + "@" + System.getProperty("user.home"));
    }

    public static Response sendPostRequest(String baseURI, Object body) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body, ObjectMapperType.GSON)
                .when()
                .post(baseURI)
                .then()
                .extract().response();

        logRequestAndResponse("POST", baseURI, body, response);
        return response;
    }

    public static Response sendGetRequest(String fullURI, String accessToken) {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(fullURI)
                .then()
                .extract().response();

        logRequestAndResponse("GET", fullURI, null, response);
        return response;
    }


    public static Response sendGetRequestWithQueryParam(String baseURI, String page, String accessToken) {
        Response response = RestAssured.given()
                .queryParam("page", page)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(baseURI)
                .then()
                .extract().response();

        logRequestAndResponse("GET", baseURI + "?page=" + page, null, response);
        return response;
    }

    public static Response sendPutRequest(String baseURI, Object body, String accessToken) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(body, ObjectMapperType.GSON)
                .when()
                .put(baseURI)
                .then()
                .extract().response();

        logRequestAndResponse("PUT", baseURI, body, response);
        return response;
    }

    public static Response sendDeleteRequest(String baseURI, String contactId, String accessToken) {
        Response response = RestAssured.given()
                .pathParam("contactId", contactId)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(baseURI)
                .then()
                .extract().response();

        logRequestAndResponse("DELETE", baseURI, null, response);
        return response;
    }




}
