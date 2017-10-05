package com.test.automation.uIAutomation.testBase;

import java.io.IOException;
import org.apache.log4j.Logger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import TestScript.*;

/**
 * @Description In this class, we initialize log4j and the invoke a method from that class to get the instance of log4j in each of 
 * the other classes. It is a java based utility that has got all the generic methods already implemented so that we are enabled to use log4j.
 * Log levels are popularly known as printing methods. These are used for printing the log messages.
 * @author kaustubh.damle
 */
public class Log 
{
	/** Initialize Log4j logs **/
	private static Logger Log = Logger.getLogger(Log.class.getName());
	public static ExtentReports extent = ExtentManager.getInstance();
	public static ExtentTest test;

	/** This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite **/
	public static void startTestCase()
	{
		Log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Log.info("XXXXXXXXXXXXXXXXXXX      Execution of StraighterLine Test cases     XXXXXXXXXXXXXXXXXXXX");
		Log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	/** This is to print environment details and time of the test case execution **/
	public static void environmentdetails(String Environment) throws IOException
	{
		Log.info("========================================================================================");
		Log.info("XXXXXXXXXXXXXXXXXXXX " + Environment + " Site is used for performing testing" + " XXXXXXXXXXXXXXXXXXXX");
		Log.info("XXXXXXXXXXXXXXXX Execution started at :- " + Genericmethod.getTimeStampValue() + " XXXXXXXXXXXXXXXXXX");
		Log.info("========================================================================================");
	}

	/** This is to print log for the ending of the test case execution **/
	public static void endTestCase()
	{
		Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXX             E---N---D           XXXXXXXXXXXXXXXXXXXXXXXXXXX");
		Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

	/** This is to print log for the ending of each test script execution **/
	public static void endTestScript()
	{
		Log.info("<======================================================================================>");
		Log.info("<================================ End of test script ==================================>");
		Log.info("<======================================================================================>");
	}

	/** This is to print info log for each test script **/
	public static void info1(String message)
	{
		Log.info(message);
	}

	/** This is to print error log for each test script **/
	public static void error1(String message)
	{
		Log.error(message);
	}

	/** This is to print info log for each test script and also within extent report **/
	public static void info(String message)
	{
		Log.info(message);
		test.log(LogStatus.INFO, message);
	}

	/** This is to print warn log for each test script and also within extent report **/
	public static void warn(String message)
	{
		Log.warn(message);
		test.log(LogStatus.WARNING, message);
	}

	/** This is to print error log for each test script and also within extent report **/
	public static void error(String message)
	{
		Log.error(message);
		test.log(LogStatus.ERROR, message);
	}

	/** This is to print fatal log for each test script and also within extent report **/
	public static void fatal(String message)
	{
		Log.fatal(message);
		test.log(LogStatus.FATAL, message);
	}

	/** This is to print debug log for each test script and also within extent report **/
	public static void debug(String message)
	{
		Log.debug(message);
		test.log(LogStatus.UNKNOWN, message);
	}

	/** This is to print pass log for each test script and also within extent report **/
	public static void pass(String message)
	{
		Log.info(message);
		test.log(LogStatus.PASS, message);
	}

	/** This is to print fail log for each test script and also within extent report **/
	public static void fail(String message)
	{
		Log.error(message);
		test.log(LogStatus.FAIL, message);
	}
}