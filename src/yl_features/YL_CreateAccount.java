package yl_features;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import yl_drivers.YL_ChromeDriver;
import yl_framework.YL_Framework;
import yl_support.YL_ReadUtility;

/*
* This class is to create a new user 
* All information is grabbed from an excel file
* Some relative xpath were used because some elements weren't clickable
* An extension is used to be able to access the ChromeDriver (YL_ChromeDriver)
* That same class leads to the Website
*/

public class YL_CreateAccount extends YL_Framework {

	private String sTestCaseName;
	private int iTestCaseRow;
	
	
	@Test(dataProvider = "DataTest")
	public static void createAccount(String firstName, String lastName, String email, String password, String address, String city, String zipCode, String mobilePhone) throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//Code to access the full create account form
		YL_Framework.clickOption("//a[@class=\"login\"]");
		YL_Framework.writeInput("//input[@id=\"email_create\"]", email);
		YL_Framework.clickOption("//button[@id=\"SubmitCreate\"]");
				
		//This code fills in all the required fields on the create account page
		driver.findElement(By.xpath("//input[@id=\"id_gender1\"]")).click();
		YL_Framework.writeInput("//input[@id=\"customer_firstname\"]", firstName);
		YL_Framework.writeInput("//input[@id=\"customer_lastname\"]", lastName);
		YL_Framework.writeInput("//input[@id=\"email\"]", email);
		YL_Framework.writeInput("//input[@id='passwd']", password);
		
		YL_Framework.dropdownSelection("//select[@id=\"days\"]", 21);
		YL_Framework.dropdownSelection("//select[@id=\"months\"]", 7);
		YL_Framework.dropdownSelection("//select[@id=\"years\"]", 25);
		
		YL_Framework.writeInput("//input[@id=\"firstname\"]", firstName);
		YL_Framework.writeInput("//input[@id=\"lastname\"]", lastName);
		YL_Framework.writeInput("//input[@id=\"address1\"]", address);
		YL_Framework.writeInput("//input[@id=\"city\"]", city);
		
		YL_Framework.dropdownSelection("//select[@id=\"id_state\"]", 1);
		
		YL_Framework.writeInput("//input[@id=\"postcode\"]", zipCode);
		YL_Framework.writeInput("//input[@id=\"phone_mobile\"]", mobilePhone);
		
		YL_Framework.clickOption("//button[@id=\"submitAccount\"]");
	}
	
	//This method here is to reach all the data from an excel file provided
	@DataProvider
	public Object[][] DataTest() throws Exception {
		YL_ReadUtility.setExcelFile("C:\\Users\\christopher.carlos\\eclipse-workspace\\YourLogo\\resources\\data\\user_createaccount.xlsx", "Sheet1");
		sTestCaseName = this.toString();
		iTestCaseRow = YL_ReadUtility.getRowContains(sTestCaseName, 0);
		Object[][] testObjArray = YL_ReadUtility.getTableArray("C:\\Users\\christopher.carlos\\eclipse-workspace\\YourLogo\\resources\\data\\user_createaccount.xlsx", "Sheet1", iTestCaseRow, 8);
		return (testObjArray);
	}
}
