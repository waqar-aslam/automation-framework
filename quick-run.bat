# For Windows (quick-run.bat)
mvn clean test
mvn allure:serve

# For Mac/Linux (quick-run.sh)  
#!/bin/bash
mvn clean test && mvn allure:serve