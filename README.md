API Automation Testing

This project contains a suite of automated tests for the API endpoints used in the system. The tests are written in Java using RestAssured and TestNG, and they cover various aspects of the API, including authentication, contact verification, enrollment balances, transaction details, and more.

Project Structure
api: Contains the ApiEndpoints class, which defines the various API endpoints used throughout the tests.
base: Contains the BaseTest class, which sets up the base URI and other global configurations for the tests.
models: Contains the RequestBodies class, which defines the POJOs for the request bodies used in the API tests.
tests: Contains the ApiTests class, which includes all the API test cases.
Prerequisites
Java 8 or higher
Maven
RestAssured
TestNG
Allure for test reporting (optional)
Setup
Clone the repository:

bash
Copy code
git clone <repository-url>
Navigate to the project directory:

bash
Copy code
cd <project-directory>
Install the required dependencies:

bash
Copy code
mvn install
Running the Tests
You can run the tests using Maven:

bash
Copy code
mvn test
The tests will automatically execute, and results will be displayed in the console.

Test Cases
The test cases include:

Authentication Tests:

Test sending OTP and receiving tokens
Test login functionality
Test logout functionality
Test refresh access token
Contact Management:

Test verifying contact information
Test retrieving enrollment balance
Test retrieving enrolled debt details
Test retrieving contact transactions
Test retrieving contact documents
Test retrieving contact details
Test retrieving payment transactions
Test retrieving uploaded documents
Test retrieving statements
Test retrieving budget tracking details
Message Handling:

Test sending a message to a contact
API Endpoints
The following endpoints are tested in this project:

/api/v1/auth/send-otp: Send OTP for authentication
/api/v1/auth/login: Login using contact ID and OTP
/api/v1/auth/logout: Logout using access token
/api/v1/auth/refresh-token: Refresh access token using a valid refresh token
/api/v1/auth/contacts/verify/{contactId}: Verify contact information
/api/v1/forth/enrollment/{contactId}/balance: Retrieve enrollment balance for the contact
/api/v1/forth/contacts/{contactId}/debts/enrolled: Retrieve enrolled debt details for the contact
/api/v1/forth/contacts/{contactId}/transactions: Retrieve contact transaction details
/api/v1/forth/contacts/{contactId}/documents: Retrieve contact documents
/api/v1/forth/contacts/{contactId}: Retrieve contact details
/api/v1/forth/contacts/{contactId}/transactions/all-payments: Retrieve all payment transactions for the contact
/api/v1/forth/contacts/{contactId}/uploaded-documents: Retrieve uploaded documents for the contact
/api/v1/forth/contacts/{contactId}/statements: Retrieve statements for the contact
/api/v1/forth/contacts/{contactId}/budgetTracking: Retrieve budget tracking details for the contact
/api/v1/cms/send-contact: Send a message to a contact
/api/v1/cms/faq: Retrieve FAQ page
Reporting
Test execution results are captured using Allure for detailed reporting. After running the tests, generate the report with:

bash
Copy code
mvn allure:serve
This will start a server and open the Allure report in your browser.

