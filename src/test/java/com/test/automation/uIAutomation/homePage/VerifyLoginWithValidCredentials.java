package com.test.automation.uIAutomation.homePage;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.test.automation.uIAutomation.data.Baseclass;
import com.test.automation.uIAutomation.data.ObjectRepository;
import com.test.automation.uIAutomation.testBase.Genericmethod;
import com.test.automation.uIAutomation.testBase.Log;
import com.test.automation.uIAutomation.testBase.TestBase;
import com.test.automation.uIAutomation.uiActions.HomePage;



public class VerifyLoginWithValidCredentials extends TestBase 
{
	
	//public static final Logger log = Logger.getLogger(TC001_VerifyLoginWithValidCredentials_Testing.class.getName());
	//String sheetName = "TestLoginData";
	
	HomePage homePage; // obj of HomePage class

	@BeforeSuite
	/**
	 * @Created By : Kaustubh.Damle
	 * @Description : This method will be execute before executing the TCScript class
	 * @Return : Test case ID for execution
	 * @throws Exception
	 **/
	public static void initializeSuite() throws Exception
	{
		/** This method will read the config.properties file and load all the values over the object fs. */
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automation\\uIAutomation\\config\\config.properties");
		CONFIG = new Properties();
		CONFIG.load(fs);
		String Environment = CONFIG.getProperty("Environment");
		System.out.println(Environment);
		Log.environmentdetails(Environment);
		Log.startTestCase();
		Log.info1("Retriving the values from config.properties file.");
//		SetupEnvironment.setdataSheet();
	}
	
	
	@BeforeMethod
	/**
	 * @Created By : Kaustubh.Damle
	 * @Description : This method will execute before executing each Test method
	 * @Return : Browser for performing execution
	 * @Return : PASS / FAIL
	 **/
	public void setUp() throws Exception
	{
		/** This step will invoke the driver whose value has been passed as browser name in config.properties file*/
		driver = Genericmethod.OpenBrowser(CONFIG);
		//System.out.println("");
		String sBrowserName = CONFIG.getProperty("BrowserType");
		System.out.println(sBrowserName);
		Log.info1(sBrowserName + " Browser has been initiated for testing.");
		new Baseclass(driver);
	}
	
	@Test (priority=0) //(testName = "Guru_99_01", enabled = false)
	public void verifyLoginWithValidCredentials() throws Exception
	{
		Log.info1("============ Starting verifyLoginWithInvalidCredentials Test ===================");
		homePage = new HomePage(driver);
		homePage.loginToapplication(ObjectRepository.Scenario_Guru_99_01);
		Log.info1("=========== Finished verifyLoginWithInvalidCredentials Test ====================");
	}
	
	@Test (priority=1)//(testName = "Guru_99_02", enabled = false)
	public void CreateNewUser()
		{
		Log.info1("============ Starting CreateNewUSerTest Test ===================");
		//homePage.loginToapplication(ObjectRepository.Scenario_Guru_99_02);
		CreateNewCustomer newCustomer = new CreateNewCustomer(driver);	
		newCustomer.CreateNewUser(ObjectRepository.Scenario_Guru_99_02);
		Log.info1("============ Finishing CreateNewUSerTest Test ===================");
		}
	
	@AfterTest
	public void CloseBrowser() throws Exception
	{
		//Thread.sleep(6000);
		//driver.close();
		driver.quit();
		//log.info("Successfully Closed the Browser Instance");
	}
}

