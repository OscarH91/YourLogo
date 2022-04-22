package yl_base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import yl_drivers.YL_ChromeDriver;
import yl_framework.YL_Framework;
import yl_support.YL_ReadUtility;

/*
* This class was created to do a login and not have repetitive code on each class
* Some relative xpath were used because some elements weren't clickable
* An extension is used to be able to access the ChromeDriver (YL_ChromeDriver)
* That same class leads to the Website
*/

public class YL_Login extends YL_Framework {

	@Test
	public void run(String usrEmail, String usrPassword) {
		YL_Framework.clickOption("//a[@title=\"Log in to your customer account\"]");
		YL_Framework.writeInput("//input[@id=\"email\"]", usrEmail);
		YL_Framework.writeInput("//input[@id=\"passwd\"]", usrPassword);

		YL_Framework.clickOption("//button[@id=\"SubmitLogin\"]");

	}
}
