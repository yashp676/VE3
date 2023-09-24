# VE3 Automation Project

The project focuses on automating various test scenarios to ensure the proper functioning of the Ve3.global website. It covers aspects like homepage loading, search functionality, navigation, and form submissions with both valid and invalid data. Automated tests help maintain the website's reliability and user experience by catching issues early in the development cycle.

## Tools

- Maven (Necessary Repository)
- Java
- Selenium

## Test Documentation

You can find an Excel file containing test scenarios and test cases in the 'Test_Scenario' folder. Additionally, you can review the author's approach in the 'MyApproach' file, which includes the logic behind each scenario and accompanying notes.

## How to Run the Tests

### Prerequisites

- Java environment
- Maven

### Getting Started

1. Copy the repository to your local machine.

2. Run tests locally: Go to src/test/java/ -  Select Scenario 1 Package- TC01 - Right-click the feature file and select "Run" or "Debug" to start the test.

3. Run tests through the command line: As this project uses Maven, you can invoke the tests using Maven goals.

   ```shell
   cd path_to_project_directory
   mvn clean
   mvn compile
   mvn test

### Reporting
The default reporting provided by the framework is Extent Report.

By default, screenshots are taken after the completion of the @Test. You can find reports at eclipse-workspace\VE3\Reports and 
screenshots at eclipse-workspace\VE3\Screenshots.

### Contribution
If you want to contribute to the project and make it better, your help is very welcome.
