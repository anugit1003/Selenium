package com.tests.testNg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tests.base.automation.BaseTestAutomation;
import com.tests.util.automation.Constants;

public class AccountsLinkTest extends BaseTestAutomation {
	

	public AccountsLinkTest() {
		System.out.println("****inside constructor");
	}
	
	@BeforeClass
	public void setUp() {
		initializeProperties();
		launchBrowser(getProps().getProperty(Constants.BROWSER));
		goToUrl(getProps().getProperty(Constants.SALESFORCE_URL));
		maximizeBrowser();
	}

	@AfterClass
	public void closeBrowser() throws Exception {		
		quitBrowser();
	}
	@Test
	public void testLoginPage() throws Exception {
		System.out.println("Login page Displayed");
		WebElement usernameEle = getDriver().findElement(By.id("username"));
		waitForVisibility(usernameEle, 5);
		enterText(usernameEle, getProps().getProperty(Constants.SF_USER_NAME), "user name");
		WebElement passwordEle = getDriver().findElement(By.id("password"));
		enterText(passwordEle, getProps().getProperty(Constants.SF_PASSWORD), "Password");
		findElementByAndClick(By.id("Login"));
		Thread.sleep(3000);
		System.out.println("Login page Clicked");

	}
	public void accountsTab() {
		findElementByAndClick(By.id("AllTab_Tab"));
		findElementByAndClick(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[2]/table/tbody/tr[1]/td[1]/a"));
		WebElement usernameEle = getDriver().findElement(By.id("username"));
		String username = usernameEle.getText();
		System.out.println(username);
		Assert.assertEquals(username, "Anuradha Jackson");
		
	}
	public void createAccount() {
		findElementByAndClick(By.name("new"));
		WebElement textboxEle = getDriver().findElement(By.id("acc2"));
		enterText(textboxEle,  "TestNG",  "AccountName");
		Select availableType = new Select( getDriver().findElement(By.id("acc6")));
		boolean isOptionFound = false;		
		/*
		 * for (WebElement option : availableType) { if
		 * (option.getText().equals("Reports")) { isOptionFound = true;
		 * availableTabs.selectByValue("report");
		 * System.out.println("Reports is selected"); Thread.sleep(2000);
		 * type.selectByValue("Technology Partner");
		 */
	}


}
