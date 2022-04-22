package yl_features;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import yl_base.YL_Login;
import yl_drivers.YL_ChromeDriver;
import yl_framework.YL_Framework;
import yl_support.YL_ReadUtility;
import yl_features.YL_CheckOut;

/*
* This class is to add items to the cart and proceed to do the checkout of these
* in this case there are only 2 items
* Some relative xpath were used because some elements weren't clickable
* An extension is used to be able to access the ChromeDriver (YL_ChromeDriver)
* That same class leads to the Website
*/

public class YL_AddtoCart extends YL_Framework {

	private String sTestCaseName;
	private int iTestCaseRow;

	@Test(dataProvider = "DataTest")
	public void addToCart(String usrEmail, String usrPassword) throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// This is for "Sign in"
		YL_Login login = new YL_Login();
		login.run(usrEmail, usrPassword);

		// This is to find the main menu and select the "Women" section and be able to
		// add an item to the cart
		YL_Login.clickOption("//a[contains(text(),'Women')]");
		YL_Login.clickOption("//img[@src=\"http://automationpractice.com/img/c/4-medium_default.jpg\"]");
		YL_Login.clickOption("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/h5/a");
		YL_Login.clickOption("//p[@id=\"add_to_cart\"]");

		// This is to find the main menu and select the "Dresses" section and be able to
		// add a second item to the cart
		YL_Login.clickOption("//*[@id=\"block_top_menu\"]/ul/li[2]/a");
		YL_Login.clickOption("//*[@id=\"center_column\"]/ul/li[2]/div/div[2]/h5/a");
		YL_Login.clickOption("//p[@id=\"add_to_cart\"]");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//a[@title='Proceed to checkout']"))));
		YL_Login.clickOption("//a[@title=\"Proceed to checkout\"]");

		// This part will proceed to do the check out, to complete the purchase.
		YL_CheckOut.purchase();

	}

	// This method here is to reach all the data from an excel file provided
	@DataProvider
	public Object[][] DataTest() throws Exception {
		YL_ReadUtility.setExcelFile(
				"C:\\Users\\christopher.carlos\\eclipse-workspace\\YourLogo\\resources\\data\\datausers.xlsx",
				"Sheet1");
		sTestCaseName = this.toString();
		iTestCaseRow = YL_ReadUtility.getRowContains(sTestCaseName, 0);
		Object[][] testObjArray = YL_ReadUtility.getTableArray(
				"C:\\Users\\christopher.carlos\\eclipse-workspace\\YourLogo\\resources\\data\\datausers.xlsx", "Sheet1",
				iTestCaseRow, 2);
		return (testObjArray);
	}
}