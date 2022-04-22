package yl_features;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import yl_base.YL_Login;
import yl_drivers.YL_ChromeDriver;
import yl_framework.YL_Framework;
import yl_support.YL_ReadUtility;
import yl_features.YL_CheckOut;

/*
* This class was created exclusively to separate the check out from the add to cart 
* Some relative xpath were used because some elements weren't clickable
* An extension is used to be able to access the ChromeDriver (YL_ChromeDriver)
* That same class leads to the Website
*/

public class YL_CheckOut extends YL_ChromeDriver {

	@Test
	public static void purchase() throws InterruptedException {

		// Implicit wait was used so that all elements had time to show up
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		YL_Framework.clickOption("//*[@id=\"center_column\"]/p[2]/a[1]");
		YL_Framework.clickOption("//*[@id=\"center_column\"]/form/p/button");
		YL_Framework.clickOption("//input[@type=\"checkbox\"]");
		YL_Framework.clickOption("//*[@id=\"form\"]/p/button");
		YL_Framework.clickOption("//a[@class=\"bankwire\"]");
		YL_Framework.clickOption("//*[@id=\"cart_navigation\"]/button");

		System.out.println("Confirm Purhase: "
				+ driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p/strong")).getText());

		Thread.sleep(5000);
	}
}
