package com.test.automation.uIAutomation.testBase;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import com.test.automation.uIAutomation.data.ObjectRepository;
import com.test.automation.uIAutomation.excelReader.Xls_Reader;


/**
 * @Description This class contains method which fetch all the test case ID from
 * excel test data sheet whose RunMode is set to 'Y' for execution process
 **/
public class SetupEnvironment
{
	//Constant to read excel suite.xlsx file during execution process 
	public static int currentSuiteID;
	public static int currentTestCaseID;
	public static String RunModeM;
	public static String RunModeTC;
	public static String currentTestSuite;
	public static String currentTestCaseName;
	public static Xls_Reader currentTestSuiteXLS;
	public static ArrayList<String> resultSetYes;
	public static ArrayList<String> resultSetNo;
	public static ArrayList<String> resultSetcheckout;
	public static ArrayList<String> resultSetBlog;
	public static Properties config;
	public static String subModule;

	/**
	 * @Description : Returns the test case ID to be executed.
	 * @return resultSetYes
	 * @throws Exception
	 */
	public ArrayList<String> start() throws Exception
	{
		Xls_Reader Wb = new Xls_Reader("src/config/Controller.xlsx");
		resultSetYes = new ArrayList<String>();
		resultSetNo = new ArrayList<String>();
		resultSetcheckout = new ArrayList<String>();
		resultSetBlog = new ArrayList<String>();
		FileInputStream fs = new FileInputStream("src/config/config.properties");
		config = new Properties();
		config.load(fs);
		String environment = config.getProperty("Environment");
		for (currentSuiteID = 2; currentSuiteID <= Wb.getRowCount(ObjectRepository.TEST_SUITE_SHEET); currentSuiteID++)
		{
			// test suite name = test suite .xls file having test cases
			currentTestSuite = Wb.getCellData(ObjectRepository.TEST_SUITE_SHEET, ObjectRepository.Test_Suite_ID, currentSuiteID);
			RunModeM = Wb.getCellData(ObjectRepository.TEST_SUITE_SHEET, ObjectRepository.RUNMODE, currentSuiteID);
			if (Wb.getCellData(ObjectRepository.TEST_SUITE_SHEET, ObjectRepository.RUNMODE, currentSuiteID).equals(ObjectRepository.RUNMODE_YES))
			{
				currentTestSuiteXLS = new Xls_Reader("src/config/" + currentTestSuite + ".xlsx");
				for (currentTestCaseID = 2; currentTestCaseID <= currentTestSuiteXLS.getRowCount("Test Case"); currentTestCaseID++)
				{
					currentTestCaseName = currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.Test_Case_ID, currentTestCaseID);
					RunModeTC = currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.RUNMODE, currentTestCaseID);
					subModule = currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, "Sub Module", currentTestCaseID);
					if (currentTestSuiteXLS.getCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.RUNMODE, currentTestCaseID).equals(ObjectRepository.RUNMODE_YES))
					{
						if (currentTestSuite.equals("CMS") || currentTestSuite.equals("Bottom_Navigation"))
						{
							if (environment.equals("Staging"))
							{
								if (subModule.contains("- Links"))
								{
									resultSetNo.add(currentTestCaseName);
									resultSetBlog.add(currentTestCaseName);
								}
								else
								{
									resultSetYes.add(currentTestCaseName);
								}
							}
							else if (subModule.equals("Checkout"))
							{
								resultSetNo.add(currentTestCaseName);
								resultSetcheckout.add(currentTestCaseName);
							}
							else
							{
								resultSetYes.add(currentTestCaseName);
							}
						}
						else
						{
							if (environment.equals("Production"))
							{
								if (subModule.equals("Checkout"))
								{
									resultSetNo.add(currentTestCaseName);
									resultSetcheckout.add(currentTestCaseName);
								}
								else
								{
									resultSetYes.add(currentTestCaseName);
								}
							}
							else
							{
								resultSetYes.add(currentTestCaseName);
							}
						}
					}
					else 
					{
						// iterating through all keywords
						resultSetNo.add(currentTestCaseName); // no data with the test
					}
				}
			}
		}
		return resultSetYes; // test to be executed
	}

	/**
	 * @Description : Sets the result of current suite test scripts to SKIP
	 * @throws Exception
	 */
	public static void setdataSheet() throws Exception
	{
		Xls_Reader Wb_SET_SKIP = new Xls_Reader("src/config/Controller.xlsx"); 
		for (currentSuiteID = 2; currentSuiteID <= Wb_SET_SKIP.getRowCount(ObjectRepository.TEST_SUITE_SHEET); currentSuiteID++)
		{
			currentTestSuite = Wb_SET_SKIP.getCellData(ObjectRepository.TEST_SUITE_SHEET, ObjectRepository.Test_Suite_ID, currentSuiteID);
			SetupEnvironment.setValue_Excel_SKIP(currentTestSuite);
		}
	}

	/**
	 * @Description : Gets the row number of the test case sheet
	 * @param testCaseName
	 * @return Rownum
	 * @throws Exception
	 */
	public static int getRowNum(String testCaseName) throws Exception
	{
		int RowNum = currentTestSuiteXLS.getCellRowNum(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.Test_Case_ID, testCaseName);
		return RowNum;
	}

	/**
	 * @Description : Sets the result of test script to SKIP
	 * @param sheetName
	 */
	public static void setValue_Excel_SKIP(String sheetName)
	{
		if (!(sheetName.equals(null)))
		{
			Xls_Reader currentXLS = new Xls_Reader("src/config/" + sheetName + ".xlsx");
			for (int RN = 2; RN <= currentXLS.getRowCount(ObjectRepository.TEST_CASES_SHEET); RN++)
			{
				currentXLS.setCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.RESULT, RN, ObjectRepository.KEYWORD_SKIP);
			}
		}
	}

	/**
	 * @Description : Sets the result of test script to PASS or FAIL
	 * @param RESULT
	 * @param testCaseName
	 * @param sheetName
	 * @throws Exception
	 */
	public  static void createXLSReport(String RESULT, String testCaseName, String sheetName) throws Exception
	{
		Xls_Reader currentTestSuiteXLS = new Xls_Reader("src/config/" + sheetName + ".xlsx");
		int Cell_value = SetupEnvironment.getRowNum(testCaseName);
		if (RESULT.equals(ObjectRepository.KEYWORD_PASS))
		{
			currentTestSuiteXLS.setCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.RESULT, Cell_value, ObjectRepository.KEYWORD_PASS);
		}
		else
		{
			currentTestSuiteXLS.setCellData(ObjectRepository.TEST_CASES_SHEET, ObjectRepository.RESULT, Cell_value, ObjectRepository.KEYWORD_FAIL);
		}
	}
}