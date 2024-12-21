package tests;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import utils.ApiAssertions;
import utils.ApiHelper;
import api.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import models.RequestBodies;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiTests extends BaseTest {

    public String page = "1";
    public String contactId = "4083642";
    public String accessToken;  // Replace with actual token
    public String refresh_token;

    @Feature("mnv")
    @Story("")

    @Test(description = "Test sending OTP and receiving tokens", priority = 1)
    public void sendOTP() {
        RequestBodies.SendOtpRequest requestBody = new RequestBodies.SendOtpRequest(contactId);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getSendOtp(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Contact Found");
        ApiAssertions.assertSuccessFlag(response, true);
        String accessToken = response.jsonPath().getString("data.access_token");
        String refresh_token = response.jsonPath().getString("data.refresh_token");
        ApiAssertions.assertTokenStructure(accessToken);
        ApiAssertions.assertTokenStructure(refresh_token);
    }

    @Test(description = "Test Login functionality", priority = 2)
    public void login() {
        RequestBodies.LoginRequest requestBody = new RequestBodies.LoginRequest(contactId, "0000");
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getLogin(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertSuccessFlag(response, true);
        ApiAssertions.assertMessage(response, "Login successful");
    }

    @Test(description = "Test Logout functionality", priority = 3)
    public void logout() {
        RequestBodies.LogoutRequest requestBody = new RequestBodies.LogoutRequest("your_client_id", "your_client_secret", accessToken);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getLogout(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertSuccessFlag(response, true);
        ApiAssertions.assertMessage(response, "Logout successful");
    }

    @Test(description = "Test to refresh the access token using a valid refresh token and verify the new access token is returned successfully", priority = 4)
    public void refreshAccessToken() {
        RequestBodies.RefreshTokenRequest requestBody = new RequestBodies.RefreshTokenRequest(accessToken);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getRefreshToken(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        accessToken = response.jsonPath().getString("data.access_token");
        ApiAssertions.assertTokenStructure(accessToken);
    }

    @Test(description = "Test to verify the contact information using the contact ID and ensure the verification is successful.", priority = 5)
    public void verifyContact() {
        RequestBodies.VerifyContactRequest requestBody = new RequestBodies.VerifyContactRequest(contactId);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getVerifyContact(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertSuccessFlag(response, true);
        ApiAssertions.assertMessage(response, "Contact verified");
    }

    @Test(description = "Test to retrieve the enrollment balance for the enrolled debt using the access token and ensure the response is successful.", priority = 6)
    public void getEnrollmentBalance() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getEnrolledDebt(), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Enrollment balance retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }

    @Test(description = "Test to retrieve the enrolled debt details using the access token and ensure the response is successful.", priority = 7)
    public void getEnrolledDebt() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getEnrolledDebt(), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Enrolled debt details retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }

    @Test(description = "Test to retrieve the contact transaction details using the access token and ensure the response is successful.", priority = 8)
    public void getContactTransaction() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getContactTransactions(), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Contact transactions retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }

    @Test(description = "Test to retrieve the contact documents using the access token and ensure the response is successful.", priority = 9)
    public void getContactDocuments() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getContactDocuments(), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Contact documents retrieved");
        ApiAssertions.assertResponseContainsData(response);    }

    @Test(description = "Test to retrieve the contact details using the access token and ensure the response is successful.", priority = 10)
    public void getContactDetails() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getContactDetails(), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Contact details retrieved");
        ApiAssertions.assertResponseContainsData(response);    }

    @Test(description = "Test to retrieve all payment transactions using the access token and ensure the response is successful.", priority = 11)
    public void getAllPaymentTransactions() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getPaymentTransactions(), accessToken);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.jsonPath().getString("message"), is("Payment transactions retrieved"));
    }

    @Test(description = "Test to retrieve the uploaded documents using the access token and ensure the response is successful.", priority = 12)
    public void getUploadedDocuments() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getUploadedDocuments(), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Uploaded documents retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }

    @Test(description = "Test to retrieve the statements using the access token and ensure the response is successful.", priority = 13)
    public void getStatements() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getStatements(), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Statements retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }

    @Test(description = "Test to retrieve the budget tracking details using the access token and ensure the response is successful.", priority = 14)
    public void getBudgetTracking() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getBudgetTracking(), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Budget tracking details retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }

    @Test(description = "Test to send a message to a contact and ensure the response is successful.", priority = 15)
    public void sendContactMessage() {
        RequestBodies.SendContactMessageRequest requestBody = new RequestBodies.SendContactMessageRequest("4083642", "Test message");
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getSendContactMessage(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Message sent");
        ApiAssertions.assertResponseContainsData(response);

    }

    @Test(description = "Test to retrieve the FAQ page with a query parameter and ensure the response is successful.", priority = 16)
    public void getFAQPage() {
        Response response = ApiHelper.sendGetRequestWithQueryParam(baseURI + ApiEndpoints.getFaqPage(), page, accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "FAQ page retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }
}
