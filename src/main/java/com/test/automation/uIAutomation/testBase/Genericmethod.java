package com.test.automation.uIAutomation.testBase;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.uIAutomation.data.ObjectRepository;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/**
 * @Description This class contains method which handle exception found during runtime. It also contains method for
 * taking screenshot, explicit wait and invoking different browsers.
 * @author Kaustubh.Damle
 */
public class Genericmethod
{
	public static WebDriver driver = null;
	public WebDriverWait wait;
	static boolean acceptNextAlert = true;

	/**
	 * @Description : This method handles element present during execution of test script
	 * @param by
	 * @return true / false
	 */
	public static boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}

	/**
	 * @Description : This method handles the alert
	 * @return true / false
	 */
	public static boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e)
		{
			return false;
		}
	}

	/**
	 * @Description : This method handle alert and return the value
	 * @return Alert accept / Alert dismiss
	 */
	public static String closeAlertAndGetItsText()
	{
		try
		{
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert)
			{
				alert.accept();
			}
			else
			{
				alert.dismiss();
			}
			return alertText;
		}
		finally
		{
			acceptNextAlert = true;
		}
	}

	/**
	 * @Description : This method takes screenshot of the system where ever it fails during execution process
	 * @param driver
	 * @param testCaseID
	 * @throws Exception
	 */
	public static void takeScreenshot(WebDriver driver, String testCaseID) throws Exception
	{
		try
		{
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String systime = getTimeStampValue().replace(":", "-");
			FileUtils.copyFile(scrFile, new File(ObjectRepository.Path_ScreenShot + testCaseID + " " + systime + ".jpg"));
			Log.test.log(LogStatus.ERROR, "Image", "Screenshot : " + Log.test.addScreenCapture(testCaseID + " " + systime + ".jpg"));
		}
		catch (Exception e)
		{
			Log.error("Class Genericmethod | Method takeScreenshot | Exception occured while capturing ScreenShot : " + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @Description : This method returns the date and time.
	 * @return time
	 * @throws IOException
	 */
	public static String getTimeStampValue() throws IOException
	{
		Calendar cal = Calendar.getInstance();
		Date time = cal.getTime();
		String timestamp = time.toString();
		return timestamp;
	}

	/**
	 * @Description : This method invokes the driver based on the value passed through config.properties file
	 * @param CONFIG - Fetch the value for the browser to be invoked
	 * @return Driver
	 * @throws Exception
	 */
	public static WebDriver OpenBrowser(Properties CONFIG) throws Exception
	{
		String sBrowserName;
		String url;
		try
		{
			sBrowserName = CONFIG.getProperty("BrowserType");
			System.out.println(sBrowserName);
			url = CONFIG.getProperty("TestURL");
			if (sBrowserName.equals("Mozilla"))
			{
				System.setProperty("webdriver.gecko.driver","Driver\\geckodriver.exe");
				driver = new FirefoxDriver();
				Log.info1("<======================================================================================>");
				Log.info1("<============================= Execution of New Test Script ===========================>");
				Log.info1("<======================================================================================>");
				Log.info1("New driver instantiated.");
				Log.info1(url + "==> is used for performing testing.");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.get(CONFIG.getProperty("TestURL"));
				Log.info1("Web application launched successfully.");
			}
			else if (sBrowserName.equals("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-popup-blocking");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				Log.info1("<======================================================================================>");
				Log.info1("<============================= Execution of New Test Script ===========================>");
				Log.info1("<======================================================================================>");
				Log.info1("New driver instantiated.");
				Log.info1(url + "==> is used for performing testing.");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.get(CONFIG.getProperty("TestURL"));
				Log.info1("Web application launched successfully.");
			}
			else if (sBrowserName.equals("IE"))
			{
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				System.setProperty("webdriver.ie.driver", "Driver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);
				Log.info1("<======================================================================================>");
				Log.info1("<============================= Execution of New Test Script ===========================>");
				Log.info1("<======================================================================================>");
				Log.info1("New driver instantiated.");
				Log.info1(url + "==> is used for performing testing.");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.get(CONFIG.getProperty("TestURL"));
				Log.info1("Web application launched successfully.");
			}
		}
		catch (Exception e)
		{
			Log.error1("Class Genericmethod | Method OpenBrowser | Exception desc : " + e.getMessage());
		}
		return driver;
	}

	/**
	 * @Description : This method waits for the element to be visible on page for specified time
	 * @param element
	 * @return element
	 */
	public static WebElement waitForElement(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * @Description : This method highlights the button element to be visible on page for specified time
	 * @param element
	 * @return element
	 */
	public static void highlightElement_btn(WebElement element) throws Exception
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", element);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black; border: 3px solid yellow");
		Thread.sleep(2000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	}

	/**
	 * @Description : This method highlights the link element to be visible on page for specified time
	 * @param element
	 * @return element
	 */
	public static void highlightElement_link(WebElement element) throws Exception
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", element);
		js.executeScript("arguments[0].setAttribute('style', arguments[1], arguments[2]);", element, "color: black; border: 3px solid yellow; background-color: yellow");
		Thread.sleep(2000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1], arguments[2]);", element, "");
	}

	/**
	 * @Description : This method is used to compare the expected and actual value
	 * @param expectedTitle
	 * @throws Exception
	 */
	public static void compare_Page_Title(String expectedTitle) throws Exception
	{
		try
		{
			Thread.sleep(5000);
			String pageTitle = driver.getTitle();
			if (pageTitle.contains(expectedTitle))
			{
				Log.info("PASS -- Verified Page Title: ==> " + pageTitle);
			}
			else
			{
				Log.error("FAIL -- Page Title not verified - Expected Title: ==> " + expectedTitle + " : Actual Title: ==>  " + pageTitle);
				Genericmethod.takeScreenshot(driver, ObjectRepository.TVE);
			}
			Thread.sleep(2000);
		}
		catch (Exception e)
		{
			Log.error("Unable to verify page title.");
			throw (e);
		}
	}

	/**
	 * @Description : This method is used get the location of a file
	 * @return path
	 */
	public static String getCleanPath()
	{
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 1);
		return path;
	}
	
	/**
	 * @Description : This method is used add jira ticket ID of issue in report
	 * @return path
	 */
	public static void jiraIssueID(String ID)
	{
		Log.test.log(LogStatus.ERROR, "Issue reported in JIRA ticket ID: <a href=\"https://straighterline.atlassian.net/browse/" + ID + "\">" + ID + "</a>");
		Log.error1("Issue reported in JIRA ticket ID: " + ID);
	}
}