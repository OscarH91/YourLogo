package yl_features;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import yl_base.YL_Login;
import yl_drivers.YL_ChromeDriver;
import yl_framework.YL_Framework;
import yl_support.YL_ReadUtility;

/*
* This class is to send a contact us form
* All information is grabbed from an excel file
* Some relative xpath were used because some elements weren't clickable
* An extension is used to be able to access the ChromeDriver (YL_ChromeDriver)
* That same class leads to the Website
*/

public class YL_ContactUs extends YL_Framework {

	private String sTestCaseName;
	private int iTestCaseRow;

	@Test(dataProvider = "DataTest")
	public void ContactUs(String address, String idNumber, String message) throws InterruptedException {

		// Implicit wait was used so that all the elements had time to show up
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Here we fill the contact us form with the required information only
		YL_Framework.clickOption("//div[@id=\"contact-link\"]");
		YL_Framework.clickOption("//option[@value='2']");
		YL_Framework.writeInput("//input[@id=\"email\"]", address);
		YL_Framework.writeInput("//input[@id=\"id_order\"]", idNumber);
		YL_Framework.writeInput("//textarea[@id=\"message\"]", message);

		YL_Framework.clickOption("//button[@id=\"submitMessage\"]");
	}

	// This method here is to reach all the data from an excel file provided
	@DataProvider
	public Object[][] DataTest() throws Exception {
		YL_ReadUtility.setExcelFile(
				"C:\\Users\\christopher.carlos\\eclipse-workspace\\YourLogo\\resources\\data\\user_contactus.xlsx",
				"Sheet1");
		sTestCaseName = this.toString();
		iTestCaseRow = YL_ReadUtility.getRowContains(sTestCaseName, 0);
		Object[][] testObjArray = YL_ReadUtility.getTableArray(
				"C:\\Users\\christopher.carlos\\eclipse-workspace\\YourLogo\\resources\\data\\user_contactus.xlsx",
				"Sheet1", iTestCaseRow, 3);
		return (testObjArray);
	}
}
