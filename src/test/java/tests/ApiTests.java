package tests;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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
    @Test(description = "")
    public void sendOTP() {
        RequestBodies.SendOtpRequest requestBody = new RequestBodies.SendOtpRequest(contactId);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getSendOtp(), requestBody);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.jsonPath().getString("message"), is("Contact Found"));
        assertThat(response.jsonPath().getBoolean("success"), is(true));
        String accessToken = response.jsonPath().getString("data.access_token");
        String refresh_token = response.jsonPath().getString("data.refresh_token");
        assertThat(accessToken, not(isEmptyOrNullString()));
        assertThat(refresh_token, not(isEmptyOrNullString()));
        assertThat(accessToken, startsWith("eyJ"));
        assertThat(refresh_token, startsWith("eyJ"));

    }


    @Test
    public void login() {
        RequestBodies.LoginRequest requestBody = new RequestBodies.LoginRequest(contactId, "0000");
        System.out.println("hyo"+ requestBody);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getLogin(), requestBody);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.jsonPath().getBoolean("success"), is(true));
    }


    @Test
    public void logout() {
        RequestBodies.LogoutRequest requestBody = new RequestBodies.LogoutRequest("your_client_id", "your_client_secret", accessToken);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getLogout(), requestBody);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.jsonPath().getBoolean("success"), is(true));
    }

    @Test
    public void refreshAccessToken() {
        RequestBodies.RefreshTokenRequest requestBody = new RequestBodies.RefreshTokenRequest(accessToken);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getRefreshToken(), requestBody);
        assertThat(response.getStatusCode(), is(200));
        accessToken = response.jsonPath().getString("data.access_token");
        assertThat(accessToken, not(isEmptyOrNullString()));
    }

    @Test
    public void verifyContact() {
        RequestBodies.VerifyContactRequest requestBody = new RequestBodies.VerifyContactRequest(contactId);
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getVerifyContact(), requestBody);
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.jsonPath().getBoolean("success"), is(true));
    }

    @Test
    public void getEnrollmentBalance() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getEnrollmentBalance(), contactId, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void getEnrolledDebt() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getEnrolledDebt(), contactId, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void getContactTransaction() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getContactTransactions(), contactId, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void getContactDocuments() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getContactDocuments(), contactId, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void getContactDetails() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getContactDetails(), contactId, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void getAllPaymentTransactions() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getPaymentTransactions(), contactId, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void getUploadedDocuments() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getUploadedDocuments(), contactId, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void getStatements() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getStatements(), contactId, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void getBudgetTracking() {
        Response response = ApiHelper.sendGetRequest(baseURI + ApiEndpoints.getBudgetTracking(), contactId, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void sendContactMessage() {
        RequestBodies.SendContactMessageRequest requestBody = new RequestBodies.SendContactMessageRequest("4083642", "Test message");
        Response response = ApiHelper.sendPostRequest(baseURI + ApiEndpoints.getSendContactMessage(), requestBody);
        assertThat(response.getStatusCode(), is(200));
    }

    @Test
    public void getFAQPage() {
        Response response = ApiHelper.sendGetRequestWithQueryParam(baseURI + ApiEndpoints.getFaqPage(), page, accessToken);
        assertThat(response.getStatusCode(), is(200));
    }

}
