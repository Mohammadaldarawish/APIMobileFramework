package api;

import static tests.ApiTests.contactId;

public class ApiEndpoints {

    public static String getSendOtp() {
        return "/api/v1/auth/send-otp";
    }

    public static String getLogin() {
        return "/api/v1/auth/login";
    }

    public static String getLogout() {
        return "/api/v1/auth/logout";
    }

    public static String getRefreshToken() {
        return "/api/v1/auth/refresh-token";
    }

    public static String getVerifyContact() {
        return "/api/v1/auth/contacts/verify/" + contactId;
    }

    public static String getEnrollmentBalance() {
        return "/api/v1/forth/enrollment/" + contactId + "/balance";
    }

    public static String getEnrolledDebt() {
        return "/api/v1/forth/contacts/" + contactId + "/debts/enrolled";
    }

    public static String getContactTransactions() {
        return "/api/v1/forth/contacts/" + contactId + "/transactions";
    }

    public static String getContactDocuments() {
        return "/api/v1/forth/contacts/" + contactId + "/documents";
    }

    public static String getContactDetails() {
        return "/api/v1/forth/contacts/" + contactId;
    }

    public static String getPaymentTransactions() {
        return "/api/v1/forth/contacts/" + contactId + "/transactions/all-payments";
    }

    public static String getUploadedDocuments() {
        return "/api/v1/forth/contacts/" + contactId + "/uploaded-documents";
    }

    public static String getStatements() {
        return "/api/v1/forth/contacts/" + contactId + "/statements";
    }

    public static String getBudgetTracking() {
        return "/api/v1/forth/contacts/" + contactId + "/budgetTracking";
    }

    public static String getSendContactMessage() {
        return "/api/v1/cms/send-contact";
    }

    public static String getFaqPage() {
        return "/api/v1/cms/faq";
    }
}
