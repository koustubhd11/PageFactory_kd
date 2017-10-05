package com.test.automation.uIAutomation.testBase;

import java.util.Properties;
import org.openqa.selenium.WebDriver;




/**
 * @author kaustubh.damle
 * Common functionality required by methods are written here
 */
 

public class TestBase
{
	//public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public static  WebDriver driver;
	
	//String url ="http://automationpractice.com/index.php";
	/*String url ="http://demo.guru99.com/V4/";
	String browser = "Chrome";*/
	//String browser = "Firefox";
	//Excel_Reader excelReader;
	public static Properties CONFIG;
	
	/*public void init() throws IOException
	{
		FileInputStream fs = new FileInputStream("src/config/config.properties");
		CONFIG = new Properties();
		CONFIG.load(fs);
		String Environment = CONFIG.getProperty("Environment");
		Log.environmentdetails(Environment);
		Log.startTestCase();
		Log.info1("Retriving the values from config.properties file.");
		selectBrowser(browser);
		getUrl(url);
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		System.setProperty("webdriver.gecko.driver", "drivers\\chromedriver.exe");
	}
	
	public void selectBrowser(String browser)
	{
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		Log.info1("Createing Object of " +browser);
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
	}
	 
	public void getUrl(String url)
	{
		Log.info1("Navigating to "+url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}*/

	/*@BeforeSuite
	*//**
	 * @Created By : Vaibhav.Shrivastava
	 * @Description : This method will be execute before executing the TCScript class
	 * @Return : Test case ID for execution
	 * @throws Exception
	 **//*
	public static void initializeSuite() throws Exception
	{
		*//** This method will read the config.properties file and load all the values over the object fs. *//*
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automation\\uIAutomation\\config\\config.properties");
		CONFIG = new Properties();
		CONFIG.load(fs);
		String Environment = CONFIG.getProperty("Environment");
		Log.environmentdetails(Environment);
		Log.startTestCase();
		Log.info1("Retriving the values from config.properties file.");
//		SetupEnvironment.setdataSheet();
	}
	
	
	@BeforeMethod
	*//**
	 * @Created By : Vaibhav.Shrivastava
	 * @Description : This method will execute before executing each Test method
	 * @Return : Browser for performing execution
	 * @Return : PASS / FAIL
	 **//*
	public void setUp() throws Exception
	{
		*//** This step will invoke the driver whose value has been passed as browser name in config.properties file*//*
		driver = Genericmethod.OpenBrowser(CONFIG);
		String sBrowserName = CONFIG.getProperty("BrowserType");
		Log.info1(sBrowserName + " Browser has been initiated for testing.");
		new Baseclass(driver);
	}*/
	
}
