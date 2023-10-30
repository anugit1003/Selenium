package com.tests.testNg;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tests.base.automation.BaseTestAutomation;
import com.tests.util.automation.Constants;

public class DeveloperConsoleTest extends BaseTestAutomation {
	
	
	
	@BeforeClass
	public void setUp() {
		initializeProperties();
		launchBrowser(getProps().getProperty(Constants.BROWSER));
		goToUrl(getProps().getProperty(Constants.SALESFORCE_URL));
		maximizeBrowser();
		System.out.println("inside setup");
	}

	@AfterClass
	public void closeBrowser() throws Exception {
		System.out.println("inside close browser");
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

	@Test(dependsOnMethods = "testLoginPage")
	public void testDeveloperConsolePage() throws InterruptedException {
		findElementByAndClick(By.id("userNavLabel"));
		Thread.sleep(2000);
		findElementByAndClick(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]"));
		Thread.sleep(2000);
		System.out.println("Developer console is clicked");
		String mainWindow = getDriver().getWindowHandle();
		Set<String> windowSet = getDriver().getWindowHandles();
		String childWindowTitle = null;
		for (String window : windowSet) {
			if (!mainWindow.equals(window)) {
				 childWindowTitle = getDriver().switchTo().window(window).getTitle();
				System.out.println(childWindowTitle);				
				Thread.sleep(3000);
				getDriver().switchTo().window(window).close();				
			}
		}
		Assert.assertEquals(childWindowTitle, "Developer Console");
		Thread.sleep(3000);

	}
}
