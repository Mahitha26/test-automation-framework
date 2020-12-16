package com.wpa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wpa.utilities.ExtentManager;

public class TestBase {

	/*
	 * WebDriver Properties Logs ExtentReports ExcelReading Mail DB (Not needed now)
	 */

	public static WebDriver driver;

	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static Properties testData = new Properties();
	public static FileInputStream fis;
	public static FileInputStream fis1;
	public static FileInputStream fis2;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static int random = (int) (Math.random() * 34145);

	@BeforeSuite
	public void setup() throws IOException {

		if (driver == null)

		{

			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/properties/config.properties");
			config.load(fis);
			log.debug("Config File Loaded!!");

			FileInputStream fis1 = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			OR.load(fis1);
			log.debug("OR File Loaded!!");

			FileInputStream fis2 = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/properties/testData.properties");
			testData.load(fis2);
			log.debug("testData File Loaded!!");

			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver");
				
				/****Headless option for chrome****/
				
//				ChromeOptions chromeOptions = new ChromeOptions();
//
//				chromeOptions.addArguments("--headless");
//				chromeOptions.addArguments("--window-size=1325x744");
//				chromeOptions.addArguments("disable-infobars"); // disabling infobars
//                chromeOptions.addArguments("--disable-extensions"); // disabling extensions
//                chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
//                chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//                chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
//
//				driver = new ChromeDriver(chromeOptions);				
			
								/********/
				
				driver = new ChromeDriver();

				log.debug("Chrome Launched!!");

			} else if (config.getProperty("browser").equals("safari")) {

				System.setProperty("webdriver.safari.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/safaridriver");
				driver = new SafariDriver();

			}

			driver.get(config.getProperty("testsiteurl"));

			log.debug("Navigated to the site");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		}

	}

	public void click(String locator) {

		driver.findElement(By.xpath(OR.getProperty(locator))).click();

		test.log(LogStatus.INFO, "Clicked on : " + locator);

		System.out.println("Clicked on :" + locator);

	}

	public void type(String locator, String value) {

		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(testData.getProperty(value));

		test.log(LogStatus.INFO, "Typed in : " + locator + " and entered value as " + value);

		System.out.println("Typed in :" + locator);

	}

	public void typeval(String locator, String value) {

		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);

		test.log(LogStatus.INFO, "Typed in : " + locator + " and entered value as " + value);
		System.out.println("Typed in :" + locator);

	}

	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}

	@AfterSuite
	public void teardown() {

		if (driver != null) {
			driver.quit();
		}

		log.debug("Test Execution completed");

	}
}
