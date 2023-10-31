package com.tests.testNg;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tests.base.automation.BaseTestAutomation;
import com.tests.util.automation.Constants;

public class MySettingsTest extends BaseTestAutomation {

	/* Apache log4j Logger - version 2 */
	static Logger logger = LogManager.getLogger(MySettingsTest.class.getName());
			
			
	public MySettingsTest() {
		logger.info("****inside constructor");
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
		reSetSelectedTab();
		quitBrowser();
	}

	@Test
	public void testLoginPage() throws Exception {
		logger.info("********Login page Displayed");
		WebElement usernameEle = getDriver().findElement(By.id("username"));
		waitForVisibility(usernameEle, 5);
		enterText(usernameEle, getProps().getProperty(Constants.SF_USER_NAME), "user name");
		WebElement passwordEle = getDriver().findElement(By.id("password"));
		enterText(passwordEle, getProps().getProperty(Constants.SF_PASSWORD), "Password");
		findElementByAndClick(By.id("Login"));
		Thread.sleep(3000);
		logger.info("Login button Clicked");

	}

	@Test(dependsOnMethods = "testLoginPage")
	public void testSettingPage() throws InterruptedException {
		findElementByAndClick(By.id("userNavLabel"));
		Thread.sleep(2000);
		findElementByAndClick(By.xpath("//*[@id=\"userNav-menuItems\"]/a[2]"));
		Thread.sleep(2000);
		logger.info("My setting is clicked");

	}
	@Test(dependsOnMethods = "testSettingPage" , priority = 1)
	public void testPersonalPage() throws Exception {
		findElementByAndClick(By.id("PersonalInfo"));
		Thread.sleep(2000);
		WebElement loginHistory = getDriver().findElement(By.id("LoginHistory_font"));
		Actions action = new Actions(getDriver());
		// action.scrollToElement(loginHistory).build().perform();
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(5000);
		// action.moveToElement(loginHistory).build().perform();
		loginHistory.click();
		logger.info("Login history page is clicked");
		Thread.sleep(2000);
		findElementByAndClick(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a"));
		Thread.sleep(5000);
	}

	@Test(dependsOnMethods = "testSettingPage",priority = 2)
	public void testDisplayPage() throws Exception {

		Thread.sleep(3000);
		WebElement display = getDriver().findElement(By.xpath("//*[@id=\"DisplayAndLayout_font\"]"));
		Thread.sleep(2000);
		Actions action = new Actions(getDriver());
		action.moveToElement(display);
		display.click();
		Thread.sleep(2000);
		findElementByAndClick(By.id("CustomizeTabs_font"));
		Thread.sleep(3000);
		logger.info("Customize tab is clicked");
		Select customApp = new Select(getDriver().findElement(By.id("p4")));
		customApp.selectByVisibleText("Salesforce Chatter");
		Thread.sleep(2000);
		logger.info("SalesForce Chatter Option is selected");
		Select availableTabs = new Select(getDriver().findElement(By.id("duel_select_0")));
		List<WebElement> availableOptions = availableTabs.getOptions();
		boolean isOptionFound = false;		
		for (WebElement option : availableOptions) {
			if (option.getText().equals("Reports")) {
				isOptionFound = true;				
				availableTabs.selectByValue("report");
				logger.info("Reports is selected");
				Thread.sleep(2000);				
				findElementByAndClick(By.id("duel_select_0_right"));
				Thread.sleep(2000);
				findElementByAndClick(By.name("save"));
				logger.info("Reports is added to the selected tab");
				break;
			} 
		}
		if(!isOptionFound) {
			throw new NoSuchElementException("Report option was not found. It is already selected");
		}
		
		//https://tekarch36-dev-ed.develop.my.salesforce.com/p/setup/layout/ConfigureMyTabs?setupid=CustomizeTabs
		Assert.assertEquals(isOptionFound, true);
		logger.info("Test case passed");
	}
	
	public void reSetSelectedTab() throws Exception{
		logger.info("Inside restSelectedTab()");
		goToUrl("https://tekarch36-dev-ed.develop.my.salesforce.com/p/setup/layout/ConfigureMyTabs?setupid=CustomizeTabs");
		Select customApp = new Select(getDriver().findElement(By.id("p4")));
		customApp.selectByVisibleText("Salesforce Chatter");
		Thread.sleep(2000);
		logger.info("SalesForce Chatter Option is selected");
		Select selectedTabs = new Select(getDriver().findElement(By.id("duel_select_1")));
		List<WebElement> selectedOptions = selectedTabs.getOptions();
		for(WebElement option : selectedOptions) {
			if(option.getText().equals("Reports")) {
				selectedTabs.selectByValue("report");
				logger.info("Reports is selected");
				Thread.sleep(2000);	
				findElementByAndClick(By.id("duel_select_0_left"));
				Thread.sleep(2000);
				findElementByAndClick(By.name("save"));
				logger.info("Reports is added to the available tab");								
			}
		}
		logger.info(" restSelectedTab() completed");
	}

}
