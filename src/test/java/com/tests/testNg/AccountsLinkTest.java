package com.tests.testNg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tests.base.automation.BaseTestAutomation;

public class AccountsLinkTest extends BaseTestAutomation {
	static Logger logger = LogManager.getLogger(AccountsLinkTest.class.getName());

	public AccountsLinkTest() {
		System.out.println("****inside constructor");
	}

	@BeforeClass
	public void setUp() throws Exception {
		launchBrowserAndLogin();
	}

	@AfterClass
	public void closeBrowser() throws Exception {
		quitBrowser();
	}

	@Test
	public void testAccountsTab() throws InterruptedException {
		goToUrl("https://tekarch36-dev-ed.develop.my.salesforce.com/001/o");
		Thread.sleep(3000);
		WebElement usernameEle = getDriver().findElement(
				By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[1]/div/div[2]/div[2]/div[2]/div[1]/div/a/span"));
		String username = usernameEle.getText();
		System.out.println(username);
		Assert.assertEquals(username, "Anuradha Jackson");

	}

	@Test(dependsOnMethods = "testAccountsTab")
	public void testCreateAccount() throws InterruptedException {
		findElementByAndClick(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		Thread.sleep(2000);
		WebElement textboxEle = getDriver().findElement(By.id("acc2"));
		enterText(textboxEle, "TestNG99", "AccountName");
		Thread.sleep(2000);
		Select availableType = new Select(getDriver().findElement(By.id("acc6")));
		boolean isOptionFound = selectAndVerifyOptions(availableType, "Technology Partner");
		Select customerPriority = new Select(getDriver().findElement(By.id("00NHs00000S6giG")));
		customerPriority.selectByValue("High");
		findElementByAndClick(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		Thread.sleep(2000);
		Assert.assertEquals(isOptionFound, true);
		logger.info("Test Case Passed");

	}
	
	@Test(dependsOnMethods = "testAccountsTab")
	public void createNewView() throws InterruptedException {
		findElementByAndClick(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		Thread.sleep(2000);
		enterText(By.id("fname"), "Test99View", "create new view");
		enterText(By.id("devname"), "Test99UniqueView","create new unique view");
		findElementByAndClick(By.name("save"));
		Thread.sleep(3000);	
	}
	
	
}
