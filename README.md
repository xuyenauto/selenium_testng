# selenium_testng
Automation Framework Using Selenium And TestNG

The framework is using TestNG + Selenium (Java) + Page Object Pattern (include page factory) + Maven to automate web application

What is this repository for? https://github.com/xuyenauto/selenium_testng.git

Configuration

1.Install Java 13 version
2.Install an IDE (Eclipse| IntelliJ)
3.Install Maven 3 version at least (I'm using version 3.8.0)
4.git clone https://github.com/xuyenauto/selenium_testng.git
5.Import the project in your installed IDE
6.run maven to download dependencies by command mvn test

Before run tests
Access to config.json file to set up something that you want to run like URL, browser and so on
browser: currently, it supports Chrome & Firefox.
url: the environment that you want to run
time_out_second: time out wait for element

How to run tests?

1.Refer the testng/testng.xml
2.Right click and run the test
3.Or run via maven by typing 'mvn test' on the terminal

How to view the report?
Assess folder report at ExtentReports/extent-report.html and explore the nice report by yourself.


--Contact Information If any idea/feedback please mail to khacxuyenit@gmail.com
