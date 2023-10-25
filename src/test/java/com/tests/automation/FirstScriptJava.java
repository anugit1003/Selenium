package com.tests.automation;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class FirstScriptJava {
	
	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriver driver = new ChromeDriver();
	    driver.get("http://www.google.com");
	    
	    WebElement searchBoxEle = driver.findElement(By.name("q"));
	    searchBoxEle.sendKeys("Taylor Swift");	 
	    searchBoxEle.click();
	    WebElement button1 = driver.findElement(By.className("gNO89b"));
	    button1.click();
	    System.out.println("My first selenium test");
	    System.out.println(driver.getCurrentUrl());
	    
	}

}
