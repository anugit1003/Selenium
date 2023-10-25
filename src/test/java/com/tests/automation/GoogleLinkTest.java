package com.tests.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;


public class GoogleLinkTest {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new FirefoxDriver();
		driver.get("https://www.google.com/");
		
		/*String expectedTitle="Google - About Google, Our Culture & Company News";
		
		driver.findElement(By.linkText("About")).click();//By.linkText("About")
		 String actualTitle =driver.getTitle();
		 if(expectedTitle.equals(actualTitle)) {
			 System.out.println("testcase passed");
		 }
		 else {
			 System.out.println("testcase failed");
		 }*/
		 
		/* String expectedTitle="Google Search - What is Google Search and How Does it Work";
		 driver.findElement(By.partialLinkText("Search")).click();
		 String actualTitle =driver.getTitle();
		 if(expectedTitle.equals(actualTitle)) {
			 System.out.println("testcase passed");
		 }
		 else {
			 System.out.println("testcase failed");
		 }*/
		
		String expectedTitle="Google for Small Business - Resources to get your small business online";
		 driver.findElement(By.partialLinkText("es")).click();
		 String actualTitle =driver.getTitle();
		 if(expectedTitle.equals(actualTitle)) {
			 System.out.println("testcase passed");
		 }
		 else {
			 System.out.println("testcase failed");
		
		 }
		 driver.close();
		 
	}	 

}
