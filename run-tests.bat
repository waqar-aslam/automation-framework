@echo off
echo ========================================
echo   Automation Framework Test Runner
echo ========================================
echo.

:menu
echo Select an option:
echo 1. Run All Tests
echo 2. Run Smoke Tests
echo 3. Run Regression Tests
echo 4. Generate Allure Report
echo 5. Serve Allure Report
echo 6. Exit
echo.

set /p choice="Enter your choice (1-6): "

if "%choice%"=="1" goto runAll
if "%choice%"=="2" goto runSmoke
if "%choice%"=="3" goto runRegression
if "%choice%"=="4" goto generateReport
if "%choice%"=="5" goto serveReport
if "%choice%"=="6" goto exit

:runAll
echo.
echo Running all tests...
mvn clean test
goto menu

:runSmoke
echo.
echo Running smoke tests...
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
goto menu

:runRegression
echo.
echo Running regression tests...
mvn clean test -Dgroups=regression
goto menu

:generateReport
echo.
echo Generating Allure report...
mvn allure:report
echo Report generated at: target/site/allure-maven-plugin/index.html
goto menu

:serveReport
echo.
echo Serving Allure report...
mvn allure:serve
goto menu

:exit
echo.
echo Exiting...
pause