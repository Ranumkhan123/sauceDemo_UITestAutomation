## SauceDemo UI Test Automation Framework

This project is a UI Test Automation Framework built using Selenium WebDriver, Java, and TestNG.
It automates functional testing for the SauceDemo application, including both Positive and Negative scenarios.
The framework follows the Page Object Model (POM) design and generates rich Extent Reports with screenshots for easy debugging and reporting.

---

## Key Features

- Automated browser testing with Selenium WebDriver
- Page Object Model for clean code structure
- TestNG framework for test management and execution
- Extent Reports for interactive HTML reporting
- Automatic screenshot capture on test failures
- Separate suites for Positive and Negative scenarios
- Easy-to-maintain utilities and reusable methods
- Browser parameterization (Chrome, Edge, Firefox)


---


## Tools & Technologies Used

- Java (17 or higher) - Programming language
- Selenium WebDriver - Browser automation
- TestNG - Test framework for managing test cases
- Maven - Build and dependency management
- Extent Reports - HTML-based detailed reporting
- WebDriverManager - Auto management of browser drivers
- IntelliJ IDEA – Integrated Development Environment (IDE) for writing, running, and debugging tests


---


## Setup and Configuration

1. Clone the Repository
   git clone https://github.com/Ranumkhan123/sauceDemo_UITestAutomation.git
   cd sauceDemo_UITestAutomation

2. Import the Project
   - Open the folder in IntelliJ IDEA or Eclipse
   - Choose Import as Maven Project
   - Wait until all dependencies are downloaded automatically

3. Browser Configuration
   <parameter name="browser" value="chrome"/>
   Supported browsers: chrome, firefox, and edge.


----


## Reporting and Logs

Extent Report:
   Generated after every test run
   Location: test-output/reports/ExtentReport_<timestamp>.html


---


## Test Coverage

Positive Scenarios:
- Valid login
- Add single/multiple products
- Verify product sorting
- Checkout with valid details
- Logout verification

Negative Scenarios:
- Invalid login
- Wrong password
- Missing checkout fields
- Empty cart


---


## Future Enhancements

- Jenkins CI/CD integration
- Parallel browser execution
- Data-driven testing
- Allure Reports integration
- Logging with Log4j


---


## Author

Ranum Khan  
QA Engineer | Manual & Automation Testing  
LinkedIn: https://www.linkedin.com/in/ranum-khan-qaengineer  
GitHub: https://github.com/Ranumkhan123


---


## License

This project is for educational & portfolio purposes only.


---


Feel free to use, modify, and share it for learning or project reference.

