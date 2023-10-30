package com.tests.testNg;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class SimpleTest extends SalesForceBase {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTest.class);
	
	
	@Test
	public void test2()  {
		System.out.println("inside test2");
	}
	
	@Test 
	public void loginTest() throws InterruptedException {
		
		LOGGER.info("loginTest method called");
		goToUrl("https://www.salesforce.com/");
		
		Thread.sleep(5000);
		maximizeBrowser();
		//getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(getDriver());
		WebElement element = getDriver()
				.findElement(By.xpath("/html/body/main/div[13]/section/div/article/div[2]/div/div/pbc-button/a"));
		
		assertEquals(element.getText(), "Start free trial");
		System.out.println("Element identified " + element.getText());
		action.scrollToElement(element);
		getTextFromElement(element, "Start Trial");
		System.out.println("Element scrolled");
		Thread.sleep(5000);
		action.moveToElement(element).build().perform();
		System.out.println("Element performed");
		clickElement(element, "Start Trial");
		Thread.sleep(3000);
		getDriver().switchTo().newWindow(WindowType.WINDOW);
		getDriver().get("https://www.google.com/");
		Thread.sleep(3000);
		WebElement searchbox = getDriver().findElement(By.id("APjFqb"));
		action.keyDown(Keys.SHIFT).sendKeys(searchbox, "tekarch").keyUp(Keys.SHIFT)
		.build().perform();
		
		Thread.sleep(3000);
	}
	
	

}
