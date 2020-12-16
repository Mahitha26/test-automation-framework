# test-automation-framework
Java Maven project designed in Selenium + TestNG framework to directly get started with adding test cases

# Create your very own automation script to test the flows of your application

1. Under src/test/resources/properties - config.properties, give the URL of the application and the browser to be launched in (this script supports chrome and safari)
2. Under src/test/java/com.pa.tescases, include various Java classes to define your test cases
3. TestBase consists of initialisation and execution of Driver, methods for Type and Click
4. Make sure to extend TestBase class ( Refer Sample testcase : Registration.java )
5. Define your Objects by giving suitable xPaths to the object names
6. Use "click("<object name>")" for performing a click action
7. Use "type("<object name>", "<testData name>")" to type values given in the testData.properties file
8. Use "typeval("<object name>", "<value>")" to directly type a value and without reading from a file
9. Define the flows in testNG.xml giving the test case sequence
10. Give relevant Mail info under com.wpa.utilities - TestConfig.java for report generation


  
