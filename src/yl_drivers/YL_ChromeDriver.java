package yl_drivers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import yl_features.YL_ContactUs;
import yl_features.YL_CreateAccount;

import org.openqa.selenium.WebDriver;

/*
 * This class is to create a driver, in this case from Chrome
 * An automation practice page was chosen
 */

public class YL_ChromeDriver {

	protected static WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
