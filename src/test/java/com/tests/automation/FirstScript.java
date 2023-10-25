package com.tests.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;


public class FirstScript {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver=new FirefoxDriver();
		driver.get("https://www.google.com/");
		Thread.sleep(5000);
		 WebElement searchBoxEle=driver.findElement(By.name("q"));
		
		 searchBoxEle.sendKeys("selenium");
		 Thread.sleep(5000);
		 WebElement button =driver.findElement(By.name("btnK"));
		 button.click();
		
		 System.out.println("completed");
		 driver.close();
		 
		 
		 
	}

}
