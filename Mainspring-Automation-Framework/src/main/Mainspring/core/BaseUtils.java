package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * This full class is created and maintained by-@author Faiz Ahmed Siddiqh K
 */
public class BaseUtils {

	private static Properties properties = new Properties();
	private static WebDriver driver;
	private static ExtentReports extentreport;
	private static ExtentTest test;
	public static String className;
	public static String methodName;

	public static class ProjectProperties {

		/**
		 * To Load a properties file
		 * 
		 * @param filePath-Path of the properties file
		 */
		public static void loadPropertiesFile(String filePath) {
			try {
				properties = new Properties();
				properties.load(new FileReader(System.getProperty("user.dir") + filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		/**
		 * Read the properties of a particular module
		 * 
		 * @param propertyName
		 * @return value of the particular property
		 */
		public static String readProjectVariables(String propertyName) {
			properties = new Properties();
			loadPropertiesFile("\\ExecutionFiles\\Risks\\module.properties"); // Here the module name has to be
																				// specified manually.Yet to update
																				// this method.
			return properties.getProperty(propertyName);

		}

		/**
		 * To read a property from config properties file
		 * 
		 * @param propertyName
		 * @return property value from golbal config value
		 */
		public static String readFromGlobalConfigFile(String propertyName) {
			loadPropertiesFile("//CommonFiles//config.properties");
			return properties.getProperty(propertyName);

		}

	}

	public static class common {

		/**
		 * To Login to the baseURL of the App
		 */
		public static void appLogin() {
			try {
				String url = ProjectProperties.readFromGlobalConfigFile("URL");
				logInfo("Fetching URl");
				launch(url);
			} catch (Exception e) {
				logInfo(e.getMessage());
				cleanUp();
			}

		}

		/**
		 * To launch or Navigate to the specified url
		 * 
		 * @param url
		 */
		public static void launch(String url) {
			try {
				// Initiate driver if not present
				if (driver == null) {
					BaseUtils.setUpDriver();
				}

				driver.get(url);
				logInfo("Navigating to -" + url);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				waitForThePageToLoad();

			} catch (Exception e) {
				logInfo(e.getMessage());
				cleanUp();
			}
		}

		/**
		 * Set classname to fecilatated naming of extentreport file
		 * 
		 * @param className
		 */
		public static void setClassName(String className) {
			BaseUtils.className = className;
		}

		/**
		 * To set name of the method currently in execution.This method is necessary to
		 * start a new ExtentTest
		 * 
		 * @param methodName
		 */
		public static void setMethodName(String methodName) {
			BaseUtils.methodName = methodName;
		}

		/**
		 * To create a new instance of Extent report.
		 */
		public static void getExtentReportInstance() {

			String path = System.getProperty("user.dir") + "//TestResults//" + className;
			File resultsFile = new File(path);
			// if the extent report already exists delete.else create a new directory of
			// that
			// module
			if (resultsFile.exists()) {
				resultsFile.delete();
			}
			resultsFile.mkdir();
			extentreport = new ExtentReports(path + "//" + "ExtentReport.html", false);// to create a new extent report
																						// for every module ,change to
																						// true.
			extentreport.addSystemInfo("Selenium Version", "3.141.59").addSystemInfo("Platform", "Windows");
			// extent.addSystemInfo("Selenium Version",
			// "3.141.59").addSystemInfo("Platform", System.getProperty("os.name"));

		}

		/**
		 * Do clean Up on failure of testcase/testmethod [THIS METHOD HAS PROBLEM.YET TO
		 * IMPLEMENT THE SOLUTION]
		 */
		public static void cleanUpOnFailure() {

			if (driver != null) {
				driver.quit();
			}
//			common.logInfo("This Test failed.Capturing Screenshot.");
//			String screenshotPath = BaseUtils.Screenshot.takeScreenshot();
//
//			test.log(LogStatus.FAIL, "Test Failed", screenshotPath);
//			driver.quit();
//			BaseUtils.common.getExtentReport().endTest(test);
//			BaseUtils.common.getExtentReport().flush();

		}

		/**
		 * CleanUp on exceptions or test failures
		 */
		public static void cleanUp() {
			common.logInfo("This Test Step failed, Capturing Screenshot.");
			String screenshotPath = BaseUtils.Screenshot.takeScreenshot();

			test.log(LogStatus.FAIL, "Test Failed", screenshotPath);
			driver.close();
			BaseUtils.common.getExtentReport().endTest(test);
			BaseUtils.common.getExtentReport().flush();

		}

		/**
		 * CleanUp after Successful run of a testcase
		 */
		public static void cleanUpOnSuccess() {
			String screenshotPath = BaseUtils.Screenshot.takeScreenshot();// capture screenshot
			driver.quit();
			test.log(LogStatus.PASS, "Test Passed", screenshotPath);
			BaseUtils.common.getExtentReport().endTest(test);
			BaseUtils.common.getExtentReport().flush();
		}

		/**
		 * return an instance of extent report
		 * 
		 * @return ExtentReport
		 */
		public static ExtentReports getExtentReport() {
			return extentreport;
		}

		/**
		 * 
		 * @return WebDriver
		 */
		public static WebDriver getDriver() {
			return driver;
		}

		/**
		 * Start an extent test report
		 * 
		 * @param testName
		 */

		public static void setExtentTest(String testName) {
			test = extentreport.startTest(testName);
			test.log(LogStatus.INFO, "Setting log report");
			test.log(LogStatus.INFO, "Starting Test-" + testName);

		}

		/**
		 * 
		 * @return extentTest
		 */
		public static ExtentTest getExtentTest() {
			return test;
		}

		/**
		 * Logs the information to the extent report
		 * 
		 * @param log-Info to log into the extent report
		 */

		public static void logInfo(String log) {
			test.log(LogStatus.INFO, log);
		}

	}

	public static class locators {
		private static Document doc;
		private static XPath xpath;
		private static XPathExpression expr;

		/**
		 * Set up the locators file for entire project. [MODULE SPECIFIC LOCATORS SETUP
		 * IS YET TO BE IMPLEMENTED]
		 * 
		 * @param moduleLocatorFileName
		 */
		public static void setUpLocatorsFile(String moduleLocatorFileName) {

			// READING THE PATH OF LOCATORS FILE FROM MODULE LOCATOR FILE
			String locatorsFileLocation = ProjectProperties.readProjectVariables(moduleLocatorFileName);
			File file = new File(System.getProperty("user.dir") + locatorsFileLocation);

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = documentFactory.newDocumentBuilder();
				doc = builder.parse(file);
				XPathFactory xpathFactory = XPathFactory.newInstance();
				xpath = xpathFactory.newXPath();
			} catch (ParserConfigurationException e) {
				System.out.println("Xml file parsing failed");
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		/**
		 * 
		 * @param locatorname
		 * @return locator value of that unique specified locator name passed.
		 */
		public static String getLocator(String locatorname) {
			String locator = null;
			try {
				expr = xpath.compile("//element[@name='" + locatorname + "']/@*");
				NodeList result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
				common.logInfo("Get Locator for " + locatorname);
				Attr attr = (Attr) result.item(0);
				locator = attr.getNodeValue();
				common.logInfo("Get Locator successful- " + locator);
				// return attr.getTextContent();

			} catch (XPathExpressionException e) {
				// System.out.println("check the locatorname input value");
				common.logInfo("Get Locator unsuccessful- " + locator);
				// common.logInfo(e.getMessage());
				BaseUtils.common.cleanUp();
			}

			return locator;
		}

	}

	public static class testData {
		public static XSSFWorkbook ExcelWBook;
		private static XSSFSheet ExcelWSheet;
		public static String filePath;

		/**
		 * To set up the test file from which the testdata has to be read.
		 * 
		 * @param fileName
		 */
		public static void setTestFile(String fileName) {
			try {
				// Open the Excel file
				filePath = System.getProperty("user.dir") + "//ExecutionFiles//Run//" + fileName + ".xlsx";
				FileInputStream ExcelFile = new FileInputStream(filePath);

				// Access the excel data sheet
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				ExcelWSheet = ExcelWBook.getSheet("TestData"); // SHEET NAME TO TESTDATA IS SAME FOR ALL THE MODULES
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 
		 * @return the testdata file
		 */
		public static XSSFWorkbook getExcelWorkBook() {

			return ExcelWBook;
		}

		/**
		 * To fetch the testdata from the Excelfile .PLEASE REFER THE TESTDATA FILE ON
		 * THE COLUMN VALUE OF VARIABLENAME,VARIABLE VALUE
		 * 
		 * @param testVariable
		 * @return testdata for the specific value passed
		 */
		public static String getTestData(String testVariable) {
			try {
				// LOOPING THROUGH ALL THE ROWS OF THE EXCELSHEET
				for (org.apache.poi.ss.usermodel.Row eachRow : ExcelWSheet) {

					XSSFCell Cell = (XSSFCell) eachRow.getCell(4); // GET CELL WHICH HAS METHOD NAME
					XSSFCell variableCell = (XSSFCell) eachRow.getCell(5);// GET CELL WHICH HAS VARIABLE NAME
					XSSFCell variableValueCell = (XSSFCell) eachRow.getCell(6);// GET CELL WHICH HAS VARIABLE VALUE

					// The value is fetched only if the current method name and variable name
					// matches the value in the cell
					if (Cell.getStringCellValue().equals(methodName)
							&& variableCell.getStringCellValue().equals(testVariable)) {
						common.logInfo("LookUp for testdata -" + testVariable);

						if (variableValueCell.getCellType() == CellType.STRING) {
							common.logInfo("LookUp for testdata " + testVariable + " successful.value = "
									+ variableValueCell.getStringCellValue());

							return variableValueCell.getStringCellValue();

						} else if (variableValueCell.getCellType() == CellType.NUMERIC) {

							common.logInfo("LookUp for testdata " + testVariable + " successful.value = "
									+ String.valueOf(variableValueCell.getNumericCellValue()));
							return String.valueOf(variableValueCell.getNumericCellValue());
						}

						// THE TESTDATA FOR CELL TYPE OTHER THAN STRING OR NUMERIC HAS TO BE IMPLEMENTED
					}

				}
				common.logInfo("LookUp for testdata failed.Testdata not found");

			} catch (Exception e) {
				common.logInfo("LookUp for testdata failed.");
				common.logInfo(e.getMessage());
				common.cleanUp();
			}
			return null;

		}

	}

	public static class Screenshot {

		/**
		 * To capture a screenshot and return the path of the screenshot captured
		 * 
		 * @return the path of the screenshot captured.
		 */
		public static String takeScreenshot() {

			String path = System.getProperty("user.dir") + "//Screenshots";
			String fullPath = path + "//" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.tsv'").format(new Date())
					+ ".png"; // U CAN CHANGE THE NAME OF THE SCREENSHOT FILE .
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // CAPTURE SCREENSHOT AS A
																							// FILE
			try {
				FileUtils.copyFile(sourceFile, new File(fullPath)); // copy the screenshot to the specified path
			} catch (Exception e) {
				test.log(LogStatus.WARNING, e.getMessage());
			}
			return test.addScreenCapture(fullPath);

		}

		public static String takeScreenshot(WebElement element) {

			String path = System.getProperty("user.dir") + "//Screenshots";
			String fullPath = path + "//" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.tsv'").format(new Date())
					+ ".png"; // U CAN CHANGE THE NAME OF THE SCREENSHOT FILE .
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // CAPTURE SCREENSHOT AS A
			Point point = element.getLocation();
			int xcordinate = point.getX();
			int ycordinate = point.getY();
			// Used selenium getSize() method to get height and width of element.
			// Retrieve width of element.
			int imageWidth = element.getSize().getWidth();
			// Retrieve height of element.
			int imageHeight = element.getSize().getHeight();
			try {
				// Reading full image screenshot.
				BufferedImage img = ImageIO.read(sourceFile);

				// cut Image using height, width and x y coordinates parameters.
				BufferedImage destination = img.getSubimage(xcordinate, ycordinate, imageWidth, imageHeight);
				ImageIO.write(destination, "png", sourceFile);// FILE

				FileUtils.copyFile(sourceFile, new File(fullPath)); // copy the screenshot to the specified path
			} catch (Exception e) {
				test.log(LogStatus.WARNING, e.getMessage());
			}
			return test.addScreenCapture(fullPath);

		}

		/**
		 * 
		 * @param length -length of the random string to be returned
		 * @return a random string of length specified.
		 */
		public static String getRandomString(int length) {
			StringBuilder sb = new StringBuilder();
			String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

			for (int i = 0; i < str.length(); i++) {
				int index = (int) (Math.random() * str.length());
				sb.append(str.charAt(index));
			}

			return sb.toString();
		}
	}

	/**
	 * Initial SETUP of the module -before class/suite .
	 * 
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void setUp() {

		// setting up an extent report
		common.getExtentReportInstance(); // setting up an extent report
		/*
		 * Setting up Locators File
		 */
		locators.setUpLocatorsFile("locatorsfile");

	}

	/**
	 * SetUp the WEBDRIVER of the type specified in the config file . IMPLEMNETED
	 * FOR CHROME AND FIREFOX BROWSERS ONLY AND FOR BOTH MAC OS AND WINDOWS
	 */
	public static void setUpDriver() {
		// Load the properties file using this method which contains baseURL and
		// WebDriverType
		String driverLocation;
		common.logInfo("Setting Up WebDriver");
		String driverName = ProjectProperties.readFromGlobalConfigFile("driver");
		// String baseURL = projectDetails.getProperty("baseURL");
		common.logInfo("WebDriver chosen =" + driverName);

		if (driverName.equalsIgnoreCase("Chrome")) {
			// Set System Property to instantiate ChromeDriver with the path of
			// chromedriver.

			driverLocation = ProjectProperties.readFromGlobalConfigFile("chromedriver");
			if (System.getProperty("os.name").startsWith("Windows")) {// the path varies for windows and Mac
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + driverLocation);
			} else {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + driverLocation.replaceAll(".exe", ""));
			}
			// Set Options using for chrome using the below commented line

			ChromeOptions options = new ChromeOptions();
			// options.addArguments("--headless");
			driver = new ChromeDriver(options);
			common.logInfo("Launching Chrome");

		} else if (driverName.equalsIgnoreCase("FireFox")) {
			// Set System Property to instantiate ChromeDriver with the path of
			// firefoxdriver.
			driverLocation = ProjectProperties.readFromGlobalConfigFile("firefoxdriver");

			if (System.getProperty("os.name").startsWith("Windows")) {// the path varies for windows and Mac
				System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + driverLocation);
			} else {
				System.setProperty("webdriver.firefox.driver",
						System.getProperty("user.dir") + driverLocation.replaceAll(".exe", ""));
			}

			// Set Options using for Firefox

			org.openqa.selenium.firefox.ProfilesIni profile = new org.openqa.selenium.firefox.ProfilesIni();
			FirefoxProfile Automationprofile = profile.getProfile("Automation");// Create a profile with Automation in
																				// Firefox on
																				// your machine and login your cognizant
																				// credentials
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(Automationprofile);
			driver = new FirefoxDriver(options);
			common.logInfo("Launching Firefox");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		common.logInfo("Maximizing the window");

	}

	/**
	 * Wait for the page to load completely
	 */
	public static void waitForThePageToLoad() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);

			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					common.logInfo("Waiting for page to Load Completely.");
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			});
			// driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);

		} catch (Exception e) {
			common.logInfo("WebPage took more time to Load.");
			common.cleanUp();
		}
	}

	/**
	 * THIS IS A PROJECT SPECIFIC METHOD: To navigate to Risks page in Mainspring
	 * application
	 */
	public static void navigateToRisksPage() {
		try {
			common.logInfo("Navigating to risks page");
			Thread.sleep(15000);
			WebElement menuBtn = getElementByXpath(locators.getLocator("homepage-menuHover-btn"));
			clickAndWait(menuBtn, "Hover over the menu button");

//			hoverOverElement(getElementByXpath(locators.getLocator("homepage-menuHover-btn")),
//					"Hover over the menu button");

			clickAndWait(getElementByXpath(locators.getLocator("homepage-MyProjects")),
					"Click And Wait on View My Projects/Programs button");
			waitForThePageToLoad();
			clickAndWait(getElementByXpath(locators.getLocator("homepage-SelectProject")),
					"Click And Wait on Aug8th Release_DummyProj 1 with id 57942");
			waitForThePageToLoad();
			clickAndWait(getElementByXpath(locators.getLocator("homepage-Monitor")),
					"Click on Monitor Option in Menu Bar");
//			hoverOverElement(getElementByXpath(locators.getLocator("homepage-Monitor")),
//					"Click on Monitor Option in Menu Bar");
			clickAndWait(getElementByXpath(locators.getLocator("homepage-Risks")), "Click And Wait on Risks");
			waitForThePageToLoad();

		} catch (Exception e) {
			common.logInfo("Unable to navigate to risks page");
			common.cleanUp();

		}
	}

	/**
	 * THIS IS A PROJECT SPECIFIC METHOD: To navigate to particular Risks using
	 * risk_id or risk_name in Mainspring application
	 */
	public static void navigateToARisk(String riskName) {
		try {
			navigateToRisksPage();
			waitForTheElementToBePresent(10, getElementByXpath(locators.getLocator("Riskpage-Search-Input")),
					"Wait and enter the risk name in search field");
			clickAndTypeAndWait(getElementByXpath(locators.getLocator("Riskpage-Search-Input")), riskName,
					"Entering the risk name");
			clickAndWait(getElementByXpath(locators.getLocator("Riskpage-Search-Button")),
					"Click on the search button");
			String riskToBeSelected = locators.getLocator("Riskpage-SelectRisk").replaceAll("Risk", riskName);

			clickAndWait(getElementByXpath(riskToBeSelected), "Click and Wait on the risk");
			waitForThePageToLoad();

		} catch (Exception e) {
			common.logInfo("Unable to navigate to risks page");
			common.cleanUp();

		}
	}

	/**
	 * Capture Screenshot at a specific instance and log it to the report
	 */
	public static void captureScreenshot() {
		String screenshotPath = BaseUtils.Screenshot.takeScreenshot();
		test.log(LogStatus.INFO, "Capturing ScreenShot", screenshotPath);

	}

	/**
	 * Capture Screenshot at a specific webelement and log it to the report
	 */
	public static void captureScreenshotAtWebElement(WebElement element) {
		String screenshotPath = BaseUtils.Screenshot.takeScreenshot(element);
		test.log(LogStatus.INFO, "Capturing ScreenShot at given element", screenshotPath);

	}

	/**
	 * THIS IS A PROJECT SPECIFIC METHOD: To select date from the calender
	 */
	public static void selectDateFromCalender(WebElement calendericon, String dateToBeSelected) {
		clickAndWait(calendericon);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			Date expectedDate = dateFormat.parse(dateToBeSelected);
			String day = new SimpleDateFormat("d").format(expectedDate);
			String month = new SimpleDateFormat("MMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);

			selectFromDropdown(BaseUtils.getElementByXpath(locators.getLocator("Riskpage-SelectDate-Month")), month);
			selectFromDropdown(BaseUtils.getElementByXpath(
					locators.getLocator("Riskpage-SelectDate-Month").replaceAll("month", "year")), year);
			String dateLocator = locators.getLocator("Riskpage-SelectDate-Date").replaceAll("date", day);
			clickAndWait(getElementByXpath(dateLocator));

		} catch (Exception ex) {
			common.logInfo("Cannot fill the date");
			common.cleanUp();
		}

	}

	/**
	 * THIS IS A PROJECT SPECIFIC METHOD: To select date from the calender by
	 * navigating to and fro
	 * 
	 * @param date
	 * 
	 */
	public void selectDateInCalender(String date) {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date expectedDate = dateFormat.parse(date);

			String day = new SimpleDateFormat("d").format(expectedDate);
			String month = new SimpleDateFormat("MMMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);

			String expectedMonthYear = month + " " + year;
			while (true) {

				String displayDate = BaseUtils.getElementByXpath(locators.getLocator("Riskpage-SelectDate-Month"))
						.getText()
						+ " "
						+ BaseUtils
								.getElementByXpath(
										locators.getLocator("Riskpage-SelectDate-Month").replaceAll("month", "year"))
								.getText();

				if (expectedMonthYear.equals(displayDate)) {
					String dateLocator = locators.getLocator("Riskpage-SelectDate-Date").replaceAll("date", day);
					clickAndWait(getElementByXpath(dateLocator));
					break;
				} else if (expectedDate.compareTo(currentDate) > 0) {
					clickAndWait(
							BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-SelectDate-next")));
				} else {
					clickAndWait(
							BaseUtils.getElementByXpath(BaseUtils.locators.getLocator("Riskpage-SelectDate-prev")));
				}
			}

		} catch (Exception e) {
			common.logInfo("Roor selecting date from the calender");
			common.cleanUp();
		}
	}

	public static WebElement findElementInWebElement(WebElement element, String locator) {
		WebElement elementToBeFound = null;
		try {
			elementToBeFound = element.findElement(By.xpath(locator));

		} catch (Exception e) {
			common.logInfo("Element not found -" + locator);
			common.logInfo("Locator not supported or check type");
			// common.logInfo(e.getMessage());
			common.cleanUp();
		}
		common.logInfo("Lookup for Element successful");
		return elementToBeFound;
	}

	/**
	 * Find Element by locator and type
	 * 
	 * @param locator
	 * @param type
	 * @return WebElement
	 */
	public static WebElement getElement(String locator, String type) {
		WebElement element = null;
		type = type.toLowerCase();
		common.logInfo("Lookup for Element-" + locator);
		try {
			if (type.equals("id")) {
				element = driver.findElement(By.id(locator));
			} else if (type.equals("xpath")) {
				element = driver.findElement(By.xpath(locator));
			} else if (type.equals("cssselector")) {
				element = driver.findElement(By.cssSelector(locator));
			} else if (type.equals("name")) {
				element = driver.findElement(By.name(locator));
			} else if (type.equals("classname")) {
				element = driver.findElement(By.className(locator));
			} else if (type.equals("tagname")) {
				element = driver.findElement(By.tagName(locator));
			} else if (type.equals("linktext")) {
				element = driver.findElement(By.linkText(locator));
			}
		} catch (Exception e) {
			common.logInfo("Element not found -" + locator);
			common.logInfo("Locator not supported or check type");
			// common.logInfo(e.getMessage());
			common.cleanUp();
		}
		common.logInfo("Lookup for Element successful");
		return element;
	}

	public static WebElement getElementByXpath(String locator) {
		WebElement element = null;
		try {
			common.logInfo("Lookup for Element-" + locator);
			element = driver.findElement(By.xpath(locator));
			common.logInfo("Lookup for Element successful");

		} catch (Exception e) {
			common.logInfo("Element not found -" + locator);
			common.logInfo(e.getMessage());
			common.cleanUp();
		}

		return element;
	}

	/**
	 * To check if the element is present and log the message
	 * 
	 * @param locator
	 * @param message
	 * @return true if the element is present else false
	 */
	public static boolean isElementPresent(String locator, String message) {
		try {
			if (getElementByXpath(locator).isDisplayed()) {
				common.logInfo(message);
				return true;
			}
		} catch (Exception e) {
			common.logInfo(e.getMessage());
			common.cleanUp();
		}

		return false;
	}

	public static boolean isElementEnabled(String locator) {
		try {
			if (getElementByXpath(locator).isEnabled()) {
				common.logInfo("Element is Enabled-" + locator);
				return true;
			}
		} catch (Exception e) {
			common.logInfo(e.getMessage());
			common.cleanUp();
		}

		return false;
	}

	/**
	 * wait for the element specified to be present based on visiblity of the
	 * element
	 * 
	 * @param timeOutInSeconds
	 * @param element
	 * @param message
	 */
	public static void waitForTheElementToBePresent(long timeOutInSeconds, WebElement element, String message) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			common.logInfo(message);
		} catch (Exception e) {
			common.logInfo("Element is not present");
			common.logInfo(e.getMessage());
			common.cleanUp();
		}
	}

	/**
	 * Wait for the element to be present .If the element is present on DOM
	 * 
	 * @param timeOutInSeconds
	 * @param locator
	 * @param message
	 */
	public static void waitForTheElementToBePresent(long timeOutInSeconds, String locator, String message) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.getLocator(locator))));
			common.logInfo(message);
		} catch (Exception e) {
			common.logInfo("Element is not present");
			common.logInfo(e.getMessage());
			common.cleanUp();
		}
	}

	/**
	 * wait for the element to be clickable .
	 * 
	 * @param timeOutInSeconds
	 * @param element
	 */
	public static void waitForTheElementToBeClickable(long timeOutInSeconds, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			common.logInfo("Waiting for the element to be clickable");
		} catch (Exception e) {
			common.logInfo("Element not clickable OR available");
			common.logInfo(e.getMessage());
			// common.cleanUp();
		}
	}

	/**
	 * Get List of WebELements based on the tagName .
	 * 
	 * @param element-WebElement
	 * @param tagname
	 * @return WebElements for the passed WebElements
	 */
	public static List<WebElement> getElementsByTagname(WebElement element, String tagname) {
		try {
			common.logInfo("Lookup for Elements by tagName -" + tagname);
			return element.findElements(By.tagName(tagname));
		} catch (Exception e) {
			common.logInfo("Elements not found -check locator and type");
			// common.logInfo(e.getMessage());
			common.cleanUp();
		}

		return null;

	}

	/**
	 * Get List of WebElements based on the locator and type specified
	 * 
	 * @param locator
	 * @param type
	 * @return list of Webelements for the specified locator
	 */
	public static List<WebElement> getElements(String locator, String type) {

		type = type.toLowerCase();
		List<WebElement> list = new ArrayList<WebElement>();
		try {
			if (type.equals("id")) {
				list = driver.findElements(By.id(locator));

			} else if (type.equals("xpath")) {
				list = driver.findElements(By.xpath(locator));
			} else if (type.equals("cssselector")) {
				list = driver.findElements(By.cssSelector(locator));
			} else if (type.equals("name")) {
				list = driver.findElements(By.name(locator));
			} else if (type.equals("classname")) {
				list = driver.findElements(By.className(locator));
			} else if (type.equals("tagname")) {
				list = driver.findElements(By.tagName(locator));
			} else if (type.equals("linktext")) {
				list = driver.findElements(By.linkText(locator));
			}
		} catch (Exception e) {
			common.logInfo("Locator not supported or check type");
			common.logInfo(e.getMessage());
			common.cleanUp();
		}

		common.logInfo("Lookup for Elements successful");
		return list;
	}

	/**
	 * 
	 * @param list
	 * @param requiredText-Text to be clicked
	 */
	public static void findElementAndClick(List<WebElement> list, String requiredText) {
		try {
			for (WebElement eachElement : list) {
				if (eachElement.getText().contains(requiredText)) {
					clickAndWait(eachElement);
					common.logInfo("clicked on " + requiredText);
					break;
				}
			}
		} catch (Exception e) {
			common.logInfo(e.getMessage());
			common.cleanUp();
		}

	}

	/**
	 * To check if the WebElement is present in DOM and clickable .
	 * 
	 * @param element
	 * @return boolean
	 */
	public static boolean isElementPresentAndClickable(WebElement element) {

		if (element.isDisplayed() && element.isEnabled()) {
			return true;
		}

		return false;
	}

	/**
	 * Click And Wait on specific WebElement and Log the message in extent report
	 * 
	 * @param element
	 * @param message
	 */
	public static void clickAndWait(WebElement element, String message) {
		try {
			waitForTheElementToBeClickable(10, element);
			element.click();
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			common.logInfo(message);

		} catch (Exception e) {
			common.logInfo("Element not clickable");
			common.logInfo(e.getMessage());
			common.cleanUp();
		}

	}

	/**
	 * Click And Wait on the WebElement
	 * 
	 * @param element
	 */
	public static void clickAndWait(WebElement element) {
		try {
			element.click();
			Thread.sleep(3000);
			common.logInfo("Click and Wait");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		} catch (Exception e) {
			common.logInfo(e.getMessage());
//			driver.quit();
//			extentreport.endTest(test);
//			extentreport.flush();
			common.cleanUp();
		}

	}

	/**
	 * Click And Wait on the specified WebELement & Type and Wait the Keys .Log the
	 * message to the report
	 * 
	 * @param element
	 * @param keysToSend
	 * @param message
	 */
	public static void clickAndTypeAndWait(WebElement element, String keysToSend, String message) {
		try {
			clickAndWait(element);
			element.sendKeys(keysToSend);
			common.logInfo(message);
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			common.logInfo(e.getMessage());
			common.cleanUp();
		}
	}

	/**
	 * Click And Clear an input or text Area field .
	 * 
	 * @param element
	 * @param message
	 */
	public static void clickAndClearAndWait(WebElement element, String message) {
		try {
			clickAndWait(element);
			element.clear();
			common.logInfo(message);
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			common.logInfo(e.getMessage());
			common.cleanUp();
		}
	}

	/**
	 * Click And Clear And Type And Wait an input are textarea field
	 * 
	 * @param element
	 * @param keysToSend
	 * @param message
	 */
	public static void clickAndClearAndTypeAndWait(WebElement element, String keysToSend, String message) {
		try {
			clickAndWait(element);
			element.clear();
			element.sendKeys(keysToSend);
			common.logInfo(message);
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} catch (Exception e) {
			common.logInfo(e.getMessage());
			common.cleanUp();
		}
	}

	/**
	 * Drag and Drop from a element to a specified element
	 * 
	 * @param fromElement
	 * @param toElement
	 */
	public static void dragAndDrop(WebElement fromElement, WebElement toElement) {
		try {
			Actions action = new Actions(driver);
			// 1)
			action.dragAndDrop(fromElement, toElement).build().perform();
		} catch (Exception e) {
			common.logInfo(e.getMessage());
			common.cleanUp();
		}

		/*
		 * 2)
		 * action.clickAndHold(fromElement).moveToElement(toElement).build().perform();
		 */
	}

	/**
	 * Slide a webelement to a specific offset
	 * 
	 * @param sliderElement
	 * @param xOffset
	 * @param yOffset
	 */
	public static void slider(WebElement sliderElement, int xOffset, int yOffset) {
		try {
			Actions action = new Actions(driver);

			action.dragAndDropBy(sliderElement, xOffset, yOffset).perform();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} catch (Exception e) {
			common.logInfo(e.getMessage());
			common.cleanUp();
		}
	}

	/**
	 * Scroll to View
	 * 
	 * @param offset
	 */
	public static void scrollToView(int offset) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0," + offset + ")", "");
			common.logInfo(" Scroll Down");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			common.logInfo("Unable to Scroll Down");
			common.cleanUp();
		}
	}

	/**
	 * Scroll to a specific WebElement view
	 * 
	 * @param element
	 */
	public static void scrollToView(WebElement element) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			common.logInfo(" Scroll Down");
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			common.logInfo("Unable to Scroll Down");
			common.cleanUp();
		}

	}

	/**
	 * Select the text from the dropDown
	 * 
	 * @param element
	 * @param textToBeSelected
	 */
	public static void selectFromDropdown(WebElement element, String textToBeSelected) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(textToBeSelected);
			common.logInfo(textToBeSelected + " selected");

		} catch (Exception e) {
			common.logInfo(e.getMessage());
			common.cleanUp();
		}

	}

	/**
	 * Switch from parent/current handle to child handle/Window
	 */
	public static void switchToHandle() {

		String parentHandle = driver.getWindowHandle();

		// Get all Handles

		Set<String> handles = driver.getWindowHandles();

		// Switching between handles

		for (String handle : handles) {

			if (!handle.equals(parentHandle)) {
				common.logInfo("Switching to another window");
				driver.switchTo().window(handle);
				break;
			}
		}

	}

	/**
	 * Class to handle alerts
	 * 
	 * @author Faiz-Siddiqh
	 *
	 */
	public static class alerts {
		private static Alert alert;

		/**
		 * Handling the alerts
		 */
		public static void switchToAlert() {
			alert = driver.switchTo().alert();

		}

		/**
		 * Accept an alert
		 */
		public static void acceptAlert() {
			switchToAlert();
			alert.accept();
		}

		/**
		 * Dismiss a aLert
		 */
		public static void dismissAlert() {
			switchToAlert();
			alert.dismiss();
		}

		/**
		 * Switch to a alert and return the alert message
		 * 
		 * @return
		 */
		public static String getAlertMessage() {
			switchToAlert();
			return alert.getText();
		}

		/**
		 * Switch to a alert,get text accept and return the message
		 * 
		 * @return the alert message
		 */
		public static String returnMessageAndAccept() {
			switchToAlert();
			String message = alert.getText();
			alert.accept();
			return message;
		}

		/**
		 * Switch to a alert,Send Keys to an input alert message
		 * 
		 * @param keysToSend
		 */
		public static void sendKeysToTheAlert(String keysToSend) {
			switchToAlert();
			alert.sendKeys(keysToSend);
		}

	}

	/**
	 * Hover a WebElement And Wait
	 */
	public static void hoverOverElement(WebElement element, String message) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
			Thread.sleep(3000);
			common.logInfo(message);
		} catch (Exception e) {
			common.logInfo("Unable to hover over the element");
			common.cleanUp();
		}

	}

	/**
	 * Get the List of the clickable links from the current WebPage
	 * 
	 * @return list of All clickable WebElements.
	 */
	public static List<WebElement> clickableLinks() {

		List<WebElement> linksToClick = new ArrayList<WebElement>();
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		elements.addAll(driver.findElements(By.tagName("img")));

		for (WebElement e : elements) {
			if (e.getAttribute("href") != null) {
				linksToClick.add(e);
			}
		}

		return linksToClick;
	}

	/**
	 * Return to the parent Handle from the current Child Handle/Window after
	 * closing the child window
	 */
	public static void returnToParentHandle() {
		String currentHandle = driver.getWindowHandle();
		String parentHandle = null;
		// Get all Handles

		Set<String> handles = driver.getWindowHandles();

		// Switching between handles

		for (String handle : handles) {

			if (handle.equals(currentHandle)) {
				common.logInfo("Closing the child window");
				driver.switchTo().window(handle).close();
			} else {
				parentHandle = handle;
			}
		}

		driver.switchTo().window(parentHandle);
		common.logInfo("Switching to parent window");

	}

	/**
	 * To arrange the values in a map in an ascending order[Including duplicate
	 * Keys]and return a Sorted Set
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return A SortedSet of sorted Map based on its Value of a entry
	 */
	public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> sortByValue(Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				int res = e1.getValue().compareTo(e2.getValue());
				return res != 0 ? res : 1;
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

}