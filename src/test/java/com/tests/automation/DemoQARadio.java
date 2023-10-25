package com.tests.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoQARadio {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/radio-button");
		Thread.sleep(3000);

		WebElement radioY = driver.findElement(By.className("custom-control-label"));		
		 if (radioY.isEnabled() && !radioY.isSelected()) {
			 radioY.click();
		 }
		 Thread.sleep(3000);
		WebElement radioI = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/label"));
		
		 if (radioI.isEnabled() && !radioI.isSelected()) {
			 radioI.click();				 
		 }
		// JavascriptExecutor executor = (JavascriptExecutor) driver;
		// executor.executeScript("arguments[0].click();", radioI);
		// }
		Thread.sleep(3000);
		driver.quit();
		System.out.println("***Closing all browser windows***");
	}

}
