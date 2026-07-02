
# Hybrid Framework E-Commerce Test Automation

A comprehensive test automation framework built with **Selenium WebDriver**, **TestNG**, and **Apache POI** for testing e-commerce applications. This hybrid framework follows the Page Object Model (POM) design pattern and includes data-driven testing capabilities.

## 📋 Table of Contents
- [Project Overview](#project-overview)
- [Technologies & Dependencies](#technologies--dependencies)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Configuration](#configuration)
- [Test Execution](#test-execution)
- [Framework Features](#framework-features)
- [Test Data Management](#test-data-management)
- [Reports](#reports)

## 🎯 Project Overview

This framework automates testing for TutorialsNinja E-Commerce application (https://tutorialsninja.com/demo/). It includes:
- User login and account creation tests
- Data-driven test scenarios
- Cross-browser testing (Chrome, Edge)
- Comprehensive logging and reporting
- Screenshot capture on test failures

## 🛠 Technologies & Dependencies

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 11+ | Programming Language |
| Selenium | 4.45.0 | Web Automation |
| TestNG | 7.10.2 | Test Framework |
| Apache POI | 5.2.5 | Excel Data Reading |
| Extent Reports | 5.1.1 | Test Reporting |
| Log4j | 2.24.1 | Logging |
| Gradle | 8.x | Build Tool |
| Apache Commons Lang | 3.14.0 | Utility Functions |

## 📁 Project Structure

```
HybridFrameWorkEcommerce/
├── src/
│   ├── main/
│   │   └── java/org/example/
│   │       └── Main.java
│   └── test/
│       ├── java/
│       │   ├── PageObjects/          # Page Object Model classes
│       │   │   ├── HomePage.java
│       │   │   ├── LoginPage.java
│       │   │   ├── AccountRegisterPage.java
│       │   │   ├── MyAccountPage.java
│       │   │   └── BasePage.java
│       │   ├── TestBase/             # Base class for all tests
│       │   │   └── BaseClass.java
│       │   ├── TestCases/            # Test case implementations
│       │   │   ├── LoginTest.java
│       │   │   ├── LoginDataDrivenTest.java
│       │   │   └── AccountRegisterTest.java
│       │   └── Utilities/            # Helper utilities
│       │       ├── ExcelUtility.java
│       │       ├── DataProviderUtility.java
│       │       └── ExtentReportManager.java
│       └── resources/
│           ├── config.properties     # Application configuration
│           ├── testng.xml           # TestNG configuration
│           ├── log4j2.xml           # Logging configuration
│           ├── parallelTesting.xml  # Parallel execution config
│           └── groupingTestng.xml   # Test grouping config
├── testData/
│   └── Data.xlsx                    # Excel test data
├── screenshots/                      # Captured failure screenshots
├── logs/                            # Application logs
├── reports/                         # HTML test reports
├── build.gradle                     # Gradle build configuration
└── README.md
```

## ✅ Prerequisites

- **Java Development Kit (JDK)** 11 or higher
- **Gradle** 8.x (wrapper included)
- **WebDrivers** (ChromeDriver, EdgeDriver) - automatically managed by Selenium
- **Apache POI compatible Excel** (.xlsx format)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

## 🚀 Setup & Installation

### 1. Clone/Extract the Project
```bash
cd C:\Learning\HybridFrameWorkEcommerce
```

### 2. Install Dependencies
```bash
# Using Gradle wrapper (Windows)
gradlew.bat build

# Or using Gradle directly
gradle build
```

### 3. Verify Installation
```bash
gradlew.bat --version
```

## ⚙️ Configuration

### config.properties
Located at `src/test/resources/config.properties`

```properties
appUrl=https://tutorialsninja.com/demo/
email=anuk1234@gmail.com
password=anu@1234
searchProduct=MacBook
```

**Update these values as needed:**
- `appUrl` - Target application URL
- `email` - Test user email
- `password` - Test user password
- `searchProduct` - Product name for search tests

### TestNG Configuration
- **testng.xml** - Default single-thread execution
- **parallelTesting.xml** - Parallel test execution
- **groupingTestng.xml** - Tests grouped by tags (Sanity, Master, etc.)

## 🧪 Test Execution

### Run All Tests (Default Configuration)
```bash
gradlew.bat test
```

### Run Tests with Specific Browser
Update the `browser` parameter in `src/test/resources/testng.xml`:
```xml
<parameter name="browser" value="chrome"></parameter>
<!-- or -->
<parameter name="browser" value="edge"></parameter>
```

### Run Tests in Parallel
```bash
# Use parallelTesting.xml
# Update build.gradle test task or run manually via TestNG
```

### Run Specific Test Groups
```
# Tests tagged with @Test(groups = {"Sanity"}) will run
# Modify groupingTestng.xml to select specific groups
```

### Run Individual Test Class
```bash
# Update testng.xml to include specific class
<class name="TestCases.LoginTest"></class>
```

## 🎨 Framework Features

### 1. **Page Object Model (POM)**
Each page is represented as a separate class with:
- Page elements (locators)
- Page actions (methods)
- Inheritance from `BasePage` for common functionality

### 2. **Base Test Class**
`BaseClass.java` provides:
- WebDriver initialization
- Cross-browser support (Chrome, Edge)
- Implicit waits and implicit waits
- Configuration property loading
- Screenshot capture on failure
- Random data generation utilities
- Log4j logging

### 3. **Data-Driven Testing**
- `ExcelUtility.java` - Reads/writes Excel files
- `DataProviderUtility.java` - TestNG @DataProvider for parameterized tests
- Test data stored in `testData/Data.xlsx`

### 4. **Advanced Logging**
- Log4j2 configured via `log4j2.xml`
- Logs stored in `logs/` directory
- Test execution traces with timestamps

### 5. **Test Reporting**
- Extent Reports 5.1.1 integration
- HTML reports with screenshots
- Reports generated in `reports/` directory

## 📊 Test Data Management

### Excel File Structure
`testData/Data.xlsx` contains test data with:
- **Sheet1** - Login credentials and test parameters
- Columns: Email, Password (or custom parameters)
- Rows: Individual test scenarios

### Using Data Provider
```java
@Test(dataProvider = "LoginData", dataProviderClass = DataProviderUtility.class)
public void loginDataDrivenTest(String email, String password) {
    // Test logic using email and password parameters
}
```

## 📈 Reports

### Test Reports Location
- **HTML Reports**: `reports/Test-Report-*.html`
- **Failure Screenshots**: `screenshots/*.png`
- **Logs**: `logs/automation.log`

### Viewing Reports
1. Navigate to `reports/` folder
2. Open the latest HTML file in a browser
3. Reports include:
   - Test execution timeline
   - Pass/Fail status
   - Screenshots for failed tests
   - Exception details

## 🔧 Troubleshooting

### Issue: WebDriver Not Found
**Solution:** Ensure Java PATH is set correctly and Selenium WebDriver is installed via Gradle.

### Issue: Excel File Not Found
**Solution:** Verify `testData/Data.xlsx` exists and path in code matches: `.\\testData\\Data.xlsx`

### Issue: Application URL Not Accessible
**Solution:** Update `appUrl` in `config.properties` and verify internet connectivity.

### Issue: Test Data Not Loading
**Solution:** 
- Check Excel file format is `.xlsx` (not `.xls`)
- Verify sheet name matches in code ("Sheet1")
- Ensure data starts from row 1 (headers in row 0)

## 📝 Writing New Tests

### Step 1: Create Page Object
```java
public class NewPage extends BasePage {
    public NewPage(WebDriver driver) {
        super(driver);
    }
    
    By element = By.id("elementId");
    
    public void clickElement() {
        driver.findElement(element).click();
    }
}
```

### Step 2: Create Test Case
```java
public class NewTest extends BaseClass {
    @Test(groups = {"Sanity"})
    public void testNewFeature() {
        logger.info("Starting new test");
        NewPage page = new NewPage(driver);
        page.clickElement();
        logger.info("Test completed");
    }
}
```

### Step 3: Add Test Data (Optional)
Insert rows in `testData/Data.xlsx` and use `@DataProvider` annotation.


## ✍️ Author
Neha K


