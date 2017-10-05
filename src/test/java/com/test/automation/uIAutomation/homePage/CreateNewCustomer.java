package com.test.automation.uIAutomation.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.uIAutomation.data.ObjectRepository;
import com.test.automation.uIAutomation.excelReader.Xls_Reader;
import com.test.automation.uIAutomation.testBase.Log;
import com.test.automation.uIAutomation.uiActions.HomePage;

public class CreateNewCustomer extends HomePage

{
	public CreateNewCustomer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	public static int testCaseRow = 2;
	public static Xls_Reader myline_excel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automation\\uIAutomation\\data\\TestData.xlsx");
	
	
	// For Creating New User
		@FindBy(xpath= ObjectRepository.NewUser)  WebElement NewUser;
		@FindBy(name = ObjectRepository.CustomerName)  WebElement CustomerName;
		@FindBy(xpath = ObjectRepository.Gender)  WebElement Gender;
		@FindBy(name = ObjectRepository.DateOfBirth)  WebElement DateOfBirth;
		@FindBy(name= ObjectRepository.CustomerAddress)  WebElement CustomerAddress;
		@FindBy(name = ObjectRepository.CustomerCity)  WebElement CustomerCity;
		@FindBy(name= ObjectRepository.CustomerState)  WebElement CustomerState;
		@FindBy(name= ObjectRepository.CustomerPin)  WebElement CustomerPin;
		@FindBy(name= ObjectRepository.CustomerMobNo)  WebElement CustomerMobileNo;
		@FindBy(name= ObjectRepository.CustomerEmailID)  WebElement CustomerEmailId;
		@FindBy(name= ObjectRepository.CustomerPassword)  WebElement CustomerPassword;
		@FindBy(name= ObjectRepository.SubmitBtn)  WebElement FormSubmitBtn;
		
		
		/*public CreateNewCustomer (WebDriver driver)
		{
			PageFactory.initElements(driver, this); // this refers to current class object
		}*/
		
		public void CreateNewUser(String sheetName)
		{
			loginToapplication(sheetName);
			
			NewUser.click();
			Log.info1("Successfully Clicked on New User Tab");
			
			String Customername = myline_excel.getCellData(sheetName, ObjectRepository.CustomerName_Excel, testCaseRow);
			CustomerName.sendKeys(Customername);
			Log.info1("Successfully Entered Customer Name " +Customername);
			
			Gender.click();
			Log.info1("Successfully clicked on Gender radio button");
			
			String dateofbirth = myline_excel.getCellData(sheetName, ObjectRepository.DOB_Excel, testCaseRow);
			DateOfBirth.sendKeys(dateofbirth);
			Log.info1("Successfully Entered Date Of Birth  " +dateofbirth);
			
			String Customeraddress = myline_excel.getCellData(sheetName, ObjectRepository.Customeraddress_Excel, testCaseRow);
			CustomerAddress.sendKeys(Customeraddress);
			Log.info1("Successfully Entered Customer Address " +Customeraddress);
			
			String Customercity = myline_excel.getCellData(sheetName, ObjectRepository.Customercity_Excel, testCaseRow);
			CustomerCity.sendKeys(Customercity);
			Log.info1("Successfully Entered Customer City " +Customercity);
			
			String Customerstate = myline_excel.getCellData(sheetName, ObjectRepository.Customerstate_Excel, testCaseRow);
			CustomerState.sendKeys(Customerstate);
			Log.info1("Successfully Entered Customer State " +Customerstate);
			
			String Customerpin = myline_excel.getCellData(sheetName, ObjectRepository.Customerpin_Excel, testCaseRow);
			CustomerPin.sendKeys(Customerpin);
			Log.info1("Successfully Entered Customer Pin " +Customerpin);
			
			String CustomerMob = myline_excel.getCellData(sheetName, ObjectRepository.CustomerMob_Excel, testCaseRow);
			CustomerMobileNo.sendKeys(CustomerMob);
			Log.info1("Successfully Entered Customer Mobile NO. " +CustomerMob);
			
			String CustomerEmail = myline_excel.getCellData(sheetName, ObjectRepository.CustomerEmail_Excel, testCaseRow);
			CustomerEmailId.sendKeys(CustomerEmail);
			Log.info1("Successfully Entered Email Address  " +CustomerEmail);
			
			String CustomerPwd = myline_excel.getCellData(sheetName, ObjectRepository.CustomerPwd_Excel, testCaseRow);
			CustomerPassword.sendKeys(CustomerPwd);
			Log.info1("Successfully Entered Password  " +CustomerPwd);
			
			FormSubmitBtn.click();
			Log.info1("Successfully clicked on the Form Submit button");
		}

}
