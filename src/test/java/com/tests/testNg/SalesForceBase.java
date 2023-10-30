package com.tests.testNg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tests.base.automation.BaseTestAutomation;
import com.tests.util.automation.Constants;

public class SalesForceBase extends BaseTestAutomation {
	
	@BeforeSuite
	public void setUp() {
		initializeProperties();
		launchBrowser(getProps().getProperty(Constants.BROWSER));
		goToUrl(getProps().getProperty(Constants.SALESFORCE_URL));
		maximizeBrowser();
	}
	
	//@AfterTest
	//public void closeBrowser() {
	//	quitBrowser();
	//}
	
	@Test
	public void testLoginPage() throws Exception {
		System.out.println("Login page Displayed");
		WebElement usernameEle = getDriver().findElement(By.id("username"));
		waitForVisibility(usernameEle, 5);
		enterText(usernameEle, getProps().getProperty(Constants.SF_USER_NAME), "user name");
		WebElement passwordEle = getDriver().findElement(By.id("password"));
		enterText(passwordEle, getProps().getProperty(Constants.SF_PASSWORD), "Password");
		//WebElement buttonEle = getDriver().findElement(By.id("Login"));
		//clickElement(buttonEle, "login button");
		findElementByAndClick(By.id("Login"));
		Thread.sleep(3000);
		System.out.println("Login page Clicked");

	}

	
	

}
