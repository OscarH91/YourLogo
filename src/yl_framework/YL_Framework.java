package yl_framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import yl_drivers.YL_ChromeDriver;

public class YL_Framework extends YL_ChromeDriver {

	// Method to write text on inputs
	public static void writeInput(String xPath, String dato) {
		WebElement element = driver.findElement(By.xpath(xPath));
		element.clear();
		element.sendKeys(dato);
	}

	// Method to select an option from a dropdown module
	public static void dropdownSelection(String xPath, int text) {
		Select element = new Select(driver.findElement(By.xpath(xPath)));
		element.selectByIndex(text);
	}

	// Method to create a click action
	public static void clickOption(String xPath) {
		WebElement element = driver.findElement(By.xpath(xPath));
		element.click();
	}

}
