API Automation Testing:

This project contains automated tests for the system's API endpoints, written in Java using RestAssured and TestNG. The tests cover authentication, contact verification, enrollment balances, transaction details, and more.

* Prerequisites:

1- Java 8 or higher

2- Maven

3- RestAssured

4- TestNG

5- Allure (optional for reporting)

* Setup

1- Clone the repository: git clone <repository-url>

2- Install dependencies: mvn install

* Running Tests: 

Run the tests with Maven: mvn test

* Test Cases
Tests include:

1- Authentication (OTP, login, logout, token refresh)

2- Contact management (verify contact, retrieve balance, debts, transactions, documents)

3- Message handling (send messages)

* Reporting
Generate test reports using Allure: mvn allure:serve
This will display the test results in your browser.
