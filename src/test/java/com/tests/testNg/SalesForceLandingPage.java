package com.tests.testNg;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tests.base.automation.BaseTestAutomation;
import com.tests.util.automation.Constants;

public class SalesForceLandingPage extends BaseTestAutomation {

	
	@BeforeTest
	public void setUp() {
		initializeProperties();
		launchBrowser(getProps().getProperty(Constants.BROWSER));
		goToUrl(getProps().getProperty(Constants.SALESFORCE_URL));
		maximiseBrowser();
	}
	
	@AfterTest
	public void closeBrowser() {
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
		//WebElement buttonEle = getDriver().findElement(By.id("Login"));
		//clickElement(buttonEle, "login button");
		findElementByAndClick(By.id("Login"));
		Thread.sleep(3000);
		System.out.println("Login page Clicked");
	}
	
	
	@Test(dependsOnMethods = "testLoginPage")
	public void testLandingPage() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("testlanding method");		
		WebElement userNavElement = getDriver().findElement(By.id("userNavLabel"));
		waitForVisibility(userNavElement, 5);
		String fullName = getProps().getProperty(Constants.SF_FIRST_NAME) + " "
				+ getProps().getProperty(Constants.SF_LAST_NAME);
		System.out.println(fullName + "  -- " + userNavElement.getText());
		if (userNavElement.getText().equals(fullName)) {
			userNavElement.click();
			System.out.println("Test case passed");
		} else {
			System.err.println("Test case failed");
		}

	}
	
	
	@Test(dependsOnMethods = "testLandingPage")
	public void testMenuUnderUser() throws InterruptedException {

		WebElement myprofile = getDriver().findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
		Thread.sleep(2000);
		// waitForVisibility(myprofile, 5);
		if (myprofile.getText().equals("My Profile")) {
			System.out.println("Test Case Passed");
		} else {
			System.out.println("Test Case Failed");
		}
		WebElement mysettings = getDriver().findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[2]"));
		Thread.sleep(2000);
		// waitForVisibility(mysettings, 5);
		if (mysettings.getText().equals("My Settings")) {
			System.out.println("Test Case Passed");
		} else {
			System.out.println("Test Case Failed");
		}
		WebElement devconsole = getDriver().findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]"));
		// waitForVisibility(devconsole, 5);
		if (devconsole.getText().equals("Developer Console")) {
			System.out.println("Test Case Passed");
		} else {
			System.out.println("Test Case Failed");
		}
		WebElement switchtoligex = getDriver().findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[4]"));
		// waitForVisibility(switchtoligex, 5);
		if (switchtoligex.getText().equals("Switch to Lightning Experience")) {
			System.out.println("Test Case Passed");
		} else {
			System.out.println("Test Case Failed");
		}
	}

	@Test(dependsOnMethods = "testLandingPage")
	public void testProfilePage() throws InterruptedException {
		System.out.println("testing the My profile page");
		findElementByAndClick(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
		Thread.sleep(2000);
		findElementByAndClick(By.id("moderatorMutton"));		
		Thread.sleep(2000);
		findElementByAndClick(By.xpath("//*[@id=\"chatterTab\"]/div[1]/div/div[1]/div[1]/div/ul/li[2]/a"));		
		Thread.sleep(5000);
		WebElement editpage = getDriver().findElement(By.id("aboutMeTitle"));
		if (editpage.getText().equals("Edit Profile")) {
			System.out.println("Test case passed. Edit page is displayed.");
		} else {
			System.out.println("Test case failed");
		}
		Thread.sleep(2000);

		WebElement iframe = getDriver().findElement(By.xpath("//*[@id=\"aboutMeContentId\"]"));

		// Switch to the frame
		getDriver().switchTo().frame(iframe);
		System.out.println("switched to iframe");
		WebElement lastname = getDriver().findElement(By.xpath("//*[@id=\"lastName\"]"));
		String str = "textbox";
		String ulastName = getProps().getProperty(Constants.SF_U_LAST_NAME);
		enterText(lastname, ulastName, str);
		WebElement save = getDriver().findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
		save.click();
		getDriver().switchTo().defaultContent();
		Thread.sleep(1000);
		WebElement changedprofile = getDriver().findElement(By.id("userNavLabel"));
		String fullName = getProps().getProperty(Constants.SF_FIRST_NAME) + " "
				+ getProps().getProperty(Constants.SF_U_LAST_NAME);
		if (changedprofile.getText().equals(fullName)) {
			changedprofile.click();
			System.out.println("Test case passed");
		} else {
			System.err.println("Test case failed");
		}
	}
	
	@Test(dependsOnMethods = "testLoginPage")
	public void testPostLink() throws InterruptedException {
		
		findElementByAndClick(By.id("userNavLabel"));
		Thread.sleep(2000);
		findElementByAndClick(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));	
		Thread.sleep(2000);
		findElementByAndClick(By.xpath("//*[@id=\"publisherAttachTextPost\"]/span[1]"));	
		Thread.sleep(2000);
		findIframeAndSwitchTo(By.xpath("//*[@id=\"cke_43_contents\"]/iframe"));
		findElementByAndClick(By.xpath("/html/body"));
		String data = "I am learning TestNG";
		enterText(By.xpath("/html/body"), data, "text area"); 
		Thread.sleep(3000);
		getDriver().switchTo().defaultContent();
		findElementByAndClick(By.id("publishersharebutton"));		
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = { "testLandingPage", "testProfilePage" })
	public void logout() {
		System.out.println("logout called ***");
		WebElement logout = getDriver().findElement(By.cssSelector("#userNav-menuItems > a:nth-child(5)"));
		waitForVisibility(logout, 5);
		logout.click();

	}
	
	

	

}
