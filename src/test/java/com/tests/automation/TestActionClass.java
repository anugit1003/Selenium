package com.tests.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;

import com.tests.base.automation.BaseTestAutomation;

public class TestActionClass extends BaseTestAutomation {

	public void scrollToElement() throws InterruptedException {

		launchBrowser("chrome");
		goToUrl("https://www.salesforce.com/");
		Thread.sleep(5000);
		maximizeBrowser();
		//getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(getDriver());
		WebElement element = getDriver()
				.findElement(By.xpath("/html/body/main/div[13]/section/div/article/div[2]/div/div/pbc-button/a"));
		System.out.println("Element identified");
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
		
		quitBrowser();

	}

	
}
