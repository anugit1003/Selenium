package com.tests.testNg;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tests.base.automation.BaseTestAutomation;
import com.tests.util.automation.Constants;

public class TestFilePhotoUpload extends BaseTestAutomation {

	@BeforeClass
	public void setUp() {
		initializeProperties();
		launchBrowser(getProps().getProperty(Constants.BROWSER));
		goToUrl(getProps().getProperty(Constants.SALESFORCE_URL));
		maximiseBrowser();
	}

	@AfterClass
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
	public void testFileUploadPage() throws InterruptedException {
		System.out.println("entered landing page");
		findElementByAndClick(By.id("userNavLabel"));	
		findElementByAndClick(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));	
		Thread.sleep(3000);
		findElementByAndClick(By.id("publisherAttachContentPost"));		
		System.out.println("File tab clicked");
		Thread.sleep(3000);
		findElementByAndClick(By.id("chatterUploadFileAction"));
		Thread.sleep(3000);
		Path path = Paths.get("src/test/resources/testupload.txt");
		File filePath = new File(path.toUri());
		getDriver().findElement(By.id("chatterFile")).sendKeys(filePath.toString());
		Thread.sleep(3000);		
		findElementByAndClick(By.id("publishersharebutton"));

	}
	
	@Test(dependsOnMethods = "testLoginPage")
	public void testFileUpload() throws InterruptedException {
		findElementByAndClick(By.id("userNavLabel"));
		
		findElementByAndClick(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));	
		Thread.sleep(3000);
		WebElement photoElement = getDriver().findElement(By.xpath("//*[@id=\"photoSection\"]/span[2]/img[1]"));		
		Actions action = new Actions(getDriver());
		action.moveToElement(photoElement).build().perform();
		Thread.sleep(2000);
		findElementByAndClick(By.id("uploadLink"));
		
		boolean didItCompare = compareTextForElement(By.id("uploadPhotoTitle"),"Upload Profile Photo" );
		Assert.assertEquals(didItCompare, true);
		findIframeAndSwitchTo(By.id("uploadPhotoContentId"));
		Path path = Paths.get("src/test/resources/mickeyicon.png");
		File filePath = new File(path.toUri());
		getDriver().findElement(By.id("j_id0:uploadFileForm:uploadInputFile")).sendKeys(filePath.toString());
		Thread.sleep(5000);
		WebElement element = getDriver().findElement(By.id("j_id0:uploadFileForm:uploadBtn"));
		element.sendKeys(Keys.ENTER);
		//findElementByAndClick(By.xpath("//*[@id=\"j_id0:uploadFileForm:save\"]"));
		Thread.sleep(10000);
		//getDriver().switchTo().defaultContent();

		
		
	}

}
