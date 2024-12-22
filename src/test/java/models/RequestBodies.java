package models;

public class RequestBodies {

    // POJO for sending OTP request
    public static class SendOtpRequest {
        private String contact_id;

        public SendOtpRequest(String contact_id) {
            this.contact_id = contact_id;
        }

        public String getContact_id() {
            return contact_id;
        }

        public void setContact_id(String contact_id) {
            this.contact_id = contact_id;
        }
    }

    // POJO for login request
    public static class LoginRequest {
        private String contact_id;
        private String otp;

        public LoginRequest(String contact_id, String otp) {
            this.contact_id = contact_id;
            this.otp = otp;
        }

        public String getContact_id() {
            return contact_id;
        }

        public void setContact_id(String contact_id) {
            this.contact_id = contact_id;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }
    }

    // POJO for logout request
    public static class LogoutRequest {
        private String client_id;
        private String client_secret;
        private String refresh_token;

        public LogoutRequest(String client_id, String client_secret, String refresh_token) {
            this.client_id = client_id;
            this.client_secret = client_secret;
            this.refresh_token = refresh_token;
        }

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        public String getClient_secret() {
            return client_secret;
        }

        public void setClient_secret(String client_secret) {
            this.client_secret = client_secret;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }
    }

    // POJO for refresh token request
    public static class RefreshTokenRequest {
        private String refresh_token;

        public RefreshTokenRequest(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }
    }

    // POJO for verify contact request
    public static class VerifyContactRequest {
        private String contactId;

        public VerifyContactRequest(String contactId) {
            this.contactId = contactId;
        }

        public String getContactId() {
            return contactId;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }
    }

    // POJO for send contact message request
    public static class SendContactMessageRequest {
        private String contact_id;
        private String message;

        public SendContactMessageRequest(String contact_id, String message) {
            this.contact_id = contact_id;
            this.message = message;
        }

        public String getContact_id() {
            return contact_id;
        }

        public void setContact_id(String contact_id) {
            this.contact_id = contact_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
