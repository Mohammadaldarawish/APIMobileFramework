API Automation Testing
This project contains automated tests for the system's API endpoints, written in Java using RestAssured and TestNG. The tests cover authentication, contact verification, enrollment balances, transaction details, and more.

Prerequisites
Java 8 or higher
Maven
RestAssured
TestNG
Allure (optional for reporting)
Setup
Clone the repository:

bash
git clone <repository-url>
Install dependencies:

bash
mvn install
Running Tests
Run the tests with Maven:

bash
mvn test
Test Cases
Tests include:

Authentication (OTP, login, logout, token refresh)
Contact management (verify contact, retrieve balance, debts, transactions, documents)
Message handling (send messages)
Reporting
Generate test reports using Allure:

bash
mvn allure:serve
This will display the test results in your browser.
