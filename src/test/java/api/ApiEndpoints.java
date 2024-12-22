package api;

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

    public static String getVerifyContact(String contactId) {
        return "/api/v1/auth/contacts/verify/" + contactId;
    }

    public static String getEnrollmentBalance(String contactId) {
        return "/api/v1/forth/enrollment/" + contactId + "/balance";
    }

    public static String getEnrolledDebt(String contactId) {
        return "/api/v1/forth/contacts/" + contactId + "/debts/enrolled";
    }

    public static String getContactTransactions(String contactId) {
        return "/api/v1/forth/contacts/" + contactId + "/transactions";
    }

    public static String getContactDocuments(String contactId) {
        return "/api/v1/forth/contacts/" + contactId + "/documents";
    }

    public static String getContactDetails(String contactId) {
        return "/api/v1/forth/contacts/" + contactId;
    }

    public static String getPaymentTransactions(String contactId) {
        return "/api/v1/forth/contacts/" + contactId + "/transactions/all-payments";
    }

    public static String getUploadedDocuments(String contactId) {
        return "/api/v1/forth/contacts/" + contactId + "/uploaded-documents";
    }

    public static String getStatements(String contactId) {
        return "/api/v1/forth/contacts/" + contactId + "/statements";
    }

    public static String getBudgetTracking(String contactId) {
        return "/api/v1/forth/contacts/" + contactId + "/budgetTracking";
    }

    public static String getSendContactMessage() {
        return "/api/v1/cms/send-contact";
    }

    public static String getFaqPage() {
        return "/api/v1/cms/faq";
    }
}
