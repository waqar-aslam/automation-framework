# 🚀 Automation Testing Framework

[![Java](https://img.shields.io/badge/Java-11%2B-orange)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.15.0-green)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-red)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9.16-blue)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure-2.24.0-purple)](https://allure.qameta.io/)

A robust, scalable hybrid test automation framework built with Selenium WebDriver, TestNG, and Page Object Model (POM) for web application testing.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running Tests](#running-tests)
- [Generating Reports](#generating-reports)
- [Test Execution Results](#test-execution-results)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

---

## 📖 Overview

This framework provides a comprehensive solution for automated web testing with:

- **Hybrid Framework** - Combines Data-Driven, Keyword-Driven, and Modular frameworks
- **Page Object Model** - Maintainable and reusable code structure
- **Cross-Browser Testing** - Chrome, Firefox, Edge support
- **Parallel Execution** - Run tests in parallel for faster execution
- **Comprehensive Reporting** - Allure reports with screenshots
- **Logging** - Detailed logs with Log4j2

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| ✅ **Page Object Model** | Clean separation of test logic and page elements |
| ✅ **Data-Driven Testing** | Excel/CSV support for test data |
| ✅ **Cross-Browser Testing** | Chrome, Firefox, Edge, Headless |
| ✅ **Parallel Execution** | Multi-threaded test execution |
| ✅ **Allure Reporting** | Rich, interactive test reports |
| ✅ **Logging Framework** | Log4j2 for comprehensive logging |
| ✅ **Screenshot Capture** | Automatic screenshots on failures |
| ✅ **Retry Mechanism** | Flaky test retry support |
| ✅ **Environment Config** | Easy configuration management |
| ✅ **CI/CD Ready** | Integrates with Jenkins/GitHub Actions |

---

## 🛠️ Technology Stack

| Tool/Library | Version | Purpose |
|--------------|---------|---------|
| **Java** | 11+ | Programming Language |
| **Selenium WebDriver** | 4.15.0 | Browser Automation |
| **TestNG** | 7.8.0 | Testing Framework |
| **Maven** | 3.9.16 | Build Automation |
| **Log4j2** | 2.20.0 | Logging |
| **Allure** | 2.24.0 | Reporting |
| **WebDriverManager** | 5.6.2 | Driver Management |
| **Apache POI** | 5.2.5 | Excel Data Reading |
| **Java Faker** | 1.0.2 | Test Data Generation |

---

## 📁 Project Structure
automation-framework/
├── pom.xml # Maven configuration
├── README.md # Project documentation
├── .gitignore # Git ignore file
├── run-tests.bat # Windows test runner
├── run-tests.sh # Mac/Linux test runner
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── framework/
│ │ │ ├── base/ # Base classes
│ │ │ │ ├── BaseClass.java # Test setup/teardown
│ │ │ │ └── BasePage.java # Page object base
│ │ │ ├── config/ # Configuration
│ │ │ │ └── ConfigReader.java
│ │ │ ├── pages/ # Page Objects (POM)
│ │ │ │ ├── LoginPage.java
│ │ │ │ ├── HomePage.java
│ │ │ │ ├── CartPage.java
│ │ │ │ ├── CheckoutPage.java
│ │ │ │ ├── CheckoutOverviewPage.java
│ │ │ │ └── CheckoutCompletePage.java
│ │ │ ├── utils/ # Utility classes
│ │ │ │ ├── BrowserFactory.java
│ │ │ │ └── WebDriverHelper.java
│ │ │ └── listeners/ # Test Listeners
│ │ │ ├── TestListener.java
│ │ │ └── AllureListener.java
│ │ └── resources/
│ │ ├── config.properties # Configuration file
│ │ └── log4j2.properties # Logging configuration
│ └── test/
│ ├── java/
│ │ └── com/
│ │ └── framework/
│ │ └── testcases/ # Test Classes
│ │ ├── LoginTest.java
│ │ └── HomeTest.java
│ └── resources/
│ └── testng.xml # TestNG Suite
├── logs/ # Application logs
└── target/ # Build output
├── surefire-reports/ # Test reports
└── allure-results/ # Allure results


---

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

| Software | Version | Installation Guide |
|----------|---------|-------------------|
| **Java JDK** | 11 or higher | [Download](https://www.oracle.com/java/technologies/downloads/) |
| **Maven** | 3.9.x | [Download](https://maven.apache.org/download.cgi) |
| **Git** | Latest | [Download](https://git-scm.com/downloads) |
| **IDE** | IntelliJ/Eclipse | [IntelliJ](https://www.jetbrains.com/idea/download/) |
| **Chrome Browser** | Latest | [Download](https://www.google.com/chrome/) |

### Verify Installation

```bash
# Check Java
java -version

# Check Maven
mvn -version

# Check Git
git --version

