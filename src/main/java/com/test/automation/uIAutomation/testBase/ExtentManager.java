package com.test.automation.uIAutomation.testBase;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.TimeZone;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.uIAutomation.data.ObjectRepository;
import com.test.automation.uIAutomation.excelReader.Xls_Reader;
import com.test.automation.uIAutomation.homePage.VerifyLoginWithValidCredentials;


/**
 * @Description Extent Manager class/Phase contains method which creates the Test execution Extent HTML report at runtime.
 * @author Kaustubh.Damle
 */
public class ExtentManager
{
	static Properties config;

	/**
	 * This method gets the instance for report and also sets relevant data regarding execution
	 */
	//@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	public static ExtentReports getInstance()
	{
		if (Log.extent == null)
		{
			try
			{
//				String systime = Genericmethod.getTimeStampValue().replace(":", "-");
				Log.extent = new ExtentReports("Automation_Report//SLAUTOMATE_Report_Transcripts.html", false, DisplayOrder.OLDEST_FIRST);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			try
			{
				FileInputStream fs;
				fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automation\\uIAutomation\\config\\config.properties");
				config = new Properties();
				config.load(fs);
				String envirment = config.getProperty("Environment");
				String release = config.getProperty("Release");
				String browserType = config.getProperty("BrowserType");
				TimeZone timeZone = TimeZone.getDefault();
				String TimeZonename = timeZone.getDisplayName();
				String gitBranch = config.getProperty("GitBranch");
				String url = config.getProperty("TestURL");
				/*String style = ".test { border: 2px solid #00BFFF; }"
						+ "body, .test .right span, .collapsible-header { background: #cce5ff; }"
						+ ".cat-other { border-color: white !important; color: white; }"
						+ ".panel-lead { font-size:14px }" + "nav .right { color: #324259; }"
						+ "span.label.info { font-size: 16px; font-weight: 400; padding: 3px 5px; }";
				
				Log.extent.config().documentTitle("Straighterline Automation Report").reportName("Straighterline")
						.insertCustomStyles(style).reportHeadline("Automation Report");*/
				
				Log.extent.addSystemInfo("Selenium Version", "2.53.1").addSystemInfo("Testing Environment", envirment)
						.addSystemInfo("Release", release).addSystemInfo("Test URL", url)
						.addSystemInfo("TimeZone", TimeZonename).addSystemInfo("Git branch", gitBranch)
						.addSystemInfo("Browser type", browserType)
						.addSystemInfo("User Name", "delaPlex").addSystemInfo("Host Name", "QMC185-delaPlex");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return Log.extent;
	}

	/**
	 * This method adds information regarding started test in report
	 * @param testCase
	 * @throws Exception
	 */
	public static void startTestCase(String testCase) throws Exception
	{
		int currentSuiteID;
		int currentTestCaseID;
		String currentTestSuite;
		String currentTestCaseName;
		Xls_Reader currentTestSuiteXLS;
		Xls_Reader Wb = new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automation\\uIAutomation\\data\\Controller.xlsx");
		String TestURL = VerifyLoginWithValidCredentials.CONFIG.getProperty("TestURL");
		for (currentSuiteID = 2; currentSuiteID <= Wb.getRowCount(ObjectRepository.TEST_SUITE_SHEET); currentSuiteID++)
		{
			currentTestSuite = Wb.getCellData(ObjectRepository.TEST_SUITE_SHEET, ObjectRepository.Test_Suite_ID, currentSuiteID);
	        if(Wb.getCellData(ObjectRepository.TEST_SUITE_SHEET, ObjectRepository.RUNMODE, currentSuiteID).equals(ObjectRepository.RUNMODE_YES))
	 		{
				currentTestSuiteXLS = new Xls_Reader("src/config/" + currentTestSuite + ".xlsx");
				for (currentTestCaseID = 2; currentTestCaseID <= currentTestSuiteXLS.getRowCount(ObjectRepository.TEST_CASES_SHEET); currentTestCaseID++) 
				{
					currentTestCaseName = currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.Test_Case_ID, currentTestCaseID);
					if (currentTestCaseName.equals(testCase))
	                {
						String desc = currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, "Description", currentTestCaseID);
						String subModule = currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, "Sub Module", currentTestCaseID);
						Log.test = Log.extent.startTest(currentTestCaseName, desc).assignCategory(currentTestSuite + " Module", subModule + " Sub-Module");
						Log.test.log(LogStatus.INFO, "Testing on URL : " + TestURL);
					}
				}
			}
		}
	}

	/**
	 * This method adds information regarding skipped test in report
	 * @throws Exception
	 */
	public static void skippedTestCase() throws Exception
	{
		int currentSuiteID;
		int currentTestCaseID;
		String currentTestSuite;
		Xls_Reader currentTestSuiteXLS;
		Xls_Reader Wb = new Xls_Reader("src/config/Controller.xlsx");
		for (currentSuiteID = 2; currentSuiteID <= Wb.getRowCount(ObjectRepository.TEST_SUITE_SHEET); currentSuiteID++)
	 	{
			currentTestSuite = Wb.getCellData(ObjectRepository.TEST_SUITE_SHEET, ObjectRepository.Test_Suite_ID, currentSuiteID);
			if (Wb.getCellData(ObjectRepository.TEST_SUITE_SHEET, ObjectRepository.RUNMODE, currentSuiteID).equals(ObjectRepository.RUNMODE_YES))
			{
				currentTestSuiteXLS = new Xls_Reader("src/config/" + currentTestSuite + ".xlsx");
				for (currentTestCaseID = 2; currentTestCaseID <= currentTestSuiteXLS.getRowCount(ObjectRepository.TEST_CASES_SHEET); currentTestCaseID++) 
				{
					String currentTestCaseName = currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.Test_Case_ID, currentTestCaseID);
					String desc = currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, "Description",	currentTestCaseID);
					String subModule = currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, "Sub Module", currentTestCaseID);
					if (currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.RUNMODE, currentTestCaseID).equals(ObjectRepository.RUNMODE_NO)) 
					{
						Log.test = Log.extent.startTest(currentTestCaseName, desc).assignCategory(currentTestSuite + " Module", subModule + " Sub-Module");
						Log.test.log(LogStatus.SKIP, "Test script execution skipped.");
						if (config.getProperty("Environment").equals("Production"))
						{
							if (subModule.equals("Checkout"))
							{
								Log.test.log(LogStatus.SKIP, "This scenario cannot be executed on Production as it contains checkout process.");
							}
						}
						if (currentTestSuite.equals("CMS")|| currentTestSuite.equals("Bottom_Navigation"))
						{
							if (config.getProperty("Environment").equals("Staging"))
							{
								if (subModule.contains("- Links"))
								{
									Log.test.log(LogStatus.SKIP, "This scenario cannot be executed on staging as blog articles are not identical as in Production.");
								}
							}
						}
						Log.extent.endTest(Log.test);
					}
					else if (currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.RUNMODE, currentTestCaseID).equals(ObjectRepository.RUNMODE_YES))
					{
						for (int num = 0; num < SetupEnvironment.resultSetcheckout.size(); num++)
						{
							if (SetupEnvironment.resultSetcheckout.get(num).contains(currentTestCaseName))
							{
								Log.test = Log.extent.startTest(currentTestCaseName, desc).assignCategory(currentTestSuite + " Module", subModule + " Sub-Module");
								Log.test.log(LogStatus.SKIP, "Test script execution skipped.");
								Log.test.log(LogStatus.SKIP, "This scenario cannot be executed on Production as it contains checkout process.");
								Log.extent.endTest(Log.test);
								break;
							}
						}
						for (int num = 0; num < SetupEnvironment.resultSetBlog.size(); num++)
						{
							if (SetupEnvironment.resultSetBlog.get(num).contains(currentTestCaseName))
							{
								Log.test = Log.extent.startTest(currentTestCaseName, desc).assignCategory(currentTestSuite + " Module", subModule + " Sub-Module");
								Log.test.log(LogStatus.SKIP, "Test script execution skipped.");
								Log.test.log(LogStatus.SKIP, "This scenario cannot be executed on staging as blog articles are not identical as in Production.");
								Log.extent.endTest(Log.test);
							}
						}
					}
				}
			}
		}
	}
}