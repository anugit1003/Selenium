package com.tests.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DemoQATextBox {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/text-box");
		System.out.println("launched the website");
		Thread.sleep(5000);
		
		WebElement fullname = driver.findElement(By.id("userName"));
		fullname.sendKeys("Anu vada");
		//Thread.sleep(3000);
		
		WebElement email = driver.findElement(By.id("userEmail"));
		email.sendKeys("asd@gmail.com");
		//Thread.sleep(3000);
		
		WebElement caddress = driver.findElement(By.id("currentAddress"));
		caddress.sendKeys("11345,selenium court");
		//Thread.sleep(3000);
		
		WebElement paddress = driver.findElement(By.id("permanentAddress"));
		paddress.sendKeys("11345,selenium st");
		Thread.sleep(3000);
		
		WebElement button = driver.findElement(By.id("submit"));
		button.sendKeys(Keys.ENTER);
		System.out.println("***Submitted the form");
		Thread.sleep(5000);
		
		//close all browser windows
		driver.quit();
		System.out.println("***Closing all browser windows***");
		
	}

}
