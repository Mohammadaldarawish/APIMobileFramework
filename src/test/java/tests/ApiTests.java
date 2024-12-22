package tests;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import utils.ApiAssertions;
import utils.ApiHelper;
import api.ApiEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import models.RequestBodies;

import static org.hamcrest.MatcherAssert.assertThat;

public class ApiTests extends BaseTest {

    public  String accessToken;
    public  String refresh_token;

    @Feature("mnv")
    @Story("")
    @Owner("Mohammad Al-Darawish")


    @Test(description = "Test sending OTP and receiving tokens", dataProvider = "contactIdProvider", priority = 1, groups = {"auth"})
    public void sendOTP(String contactId) {
        RequestBodies.SendOtpRequest requestBody = new RequestBodies.SendOtpRequest(contactId);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getSendOtp(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Contact Found");
        ApiAssertions.assertSuccessFlag(response, true);
        accessToken = response.jsonPath().getString("data.access_token");
        refresh_token = response.jsonPath().getString("data.refresh_token");
        ApiAssertions.assertTokenStructure(accessToken);
        ApiAssertions.assertTokenStructure(refresh_token);
    }
    @Test(description = "Test to verify the contact information", dataProvider = "contactIdProvider", priority = 2, groups = {"auth"})
    public void login(String contactId) {
        RequestBodies.LoginRequest requestBody = new RequestBodies.LoginRequest(contactId, "0000");
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getLogin(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertSuccessFlag(response, true);
        ApiAssertions.assertMessage(response, "Login successful");
    }


    @Test(description = "Test Logout functionality", priority = 3, groups = {"auth"})
    public void logout() {
        RequestBodies.LogoutRequest requestBody = new RequestBodies.LogoutRequest("client_id", "client_secret", accessToken);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getLogout(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertSuccessFlag(response, true);
        ApiAssertions.assertMessage(response, "Logout successful");
    }



    @Test(description = "Test to refresh the access token using a valid refresh token", priority = 4, groups = {"auth"})
    public void refreshAccessToken() {
        RequestBodies.RefreshTokenRequest requestBody = new RequestBodies.RefreshTokenRequest(refresh_token);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getRefreshToken(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        accessToken = response.jsonPath().getString("data.access_token");
        ApiAssertions.assertTokenStructure(accessToken);
    }



    @Test(description = "Test to verify the contact information using the contact ID", priority = 5, dataProvider = "contactIdProvider", groups = {"auth"})
    public void verifyContact(String contactId) {
        RequestBodies.VerifyContactRequest requestBody = new RequestBodies.VerifyContactRequest(contactId);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getVerifyContact(contactId), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertSuccessFlag(response, true);
        ApiAssertions.assertMessage(response, "Contact verified");
    }



    @Test(description = "Test to retrieve the enrollment balance for the enrolled debt", priority = 6, groups = {"Forth"}, dataProvider = "contactIdProvider")
    public void getEnrollmentBalance(String contactId) {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getEnrollmentBalance(contactId), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Enrollment balance retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }



    @Test(description = "Test to retrieve the enrolled debt details", priority = 7, groups = {"Forth"}, dataProvider = "contactIdProvider")
    public void getEnrolledDebt(String contactId) {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getEnrolledDebt(contactId), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Enrolled debt details retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }


    @Test(description = "Test to retrieve the contact transaction details", priority = 8, groups = {"Forth"}, dataProvider = "contactIdProvider")
    public void getContactTransaction(String contactId) {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getContactTransactions(contactId), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Contact transactions retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }


    @Test(description = "Test to retrieve the contact documents", priority = 9, groups = {"Forth"}, dataProvider = "contactIdProvider")
    public void getContactDocuments(String contactId) {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getContactDocuments(contactId), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Contact documents retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }



    @Test(description = "Test to retrieve the contact details", priority = 10,groups = {"Forth"}, dataProvider = "contactIdProvider")
    public void getContactDetails(String contactId) {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getContactDetails(contactId), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Contact details retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }



    // Positive Test: Test to retrieve all payment transactions
    @Test(description = "Test to retrieve all payment transactions", priority = 11, groups = {"Forth"}, dataProvider = "contactIdProvider")
    public void getAllPaymentTransactions(String contactId) {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getPaymentTransactions(contactId), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Payment transactions retrieved");
    }

    @Test(description = "Test to retrieve the uploaded documents using the access token and ensure the response is successful.", priority = 12, groups = {"Forth"}, dataProvider = "contactIdProvider")
    public void getUploadedDocuments(String contactId) {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getUploadedDocuments(contactId), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Uploaded documents retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }

    @Test(description = "Test to retrieve the statements using the access token and ensure the response is successful.", priority = 13, groups = {"Forth"}, dataProvider = "contactIdProvider")
    public void getStatements(String contactId) {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getStatements(contactId), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Statements retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }

    @Test(description = "Test to retrieve the budget tracking details using the access token and ensure the response is successful.", priority = 14, groups = {"Forth"}, dataProvider = "contactIdProvider")
    public void getBudgetTracking(String contactId) {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getBudgetTracking(contactId), accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Budget tracking details retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }




    @Test(description = "Test to send a message to a contact and ensure the response is successful.", priority = 15, groups = {"CMS"})
    public void sendContactMessage() {
        RequestBodies.SendContactMessageRequest requestBody = new RequestBodies.SendContactMessageRequest("4083642", "Test message");
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getSendContactMessage(), requestBody);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "Message sent");
        ApiAssertions.assertResponseContainsData(response);
    }

    @Test(description = "Test to retrieve the FAQ page with a query parameter and ensure the response is successful.", priority = 16, groups = {"CMS"})
    public void getFAQPage() {
        Response response = ApiHelper.sendGetRequestWithQueryParam(baseURI + ApiEndpoints.getFaqPage(), "1", accessToken);
        ApiAssertions.assertStatusCode(response, 200);
        ApiAssertions.assertMessage(response, "FAQ page retrieved");
        ApiAssertions.assertResponseContainsData(response);
    }


}
