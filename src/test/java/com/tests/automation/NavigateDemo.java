package com.tests.automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class NavigateDemo {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		driver.navigate().to("https://qa-tekarch.firebaseapp.com");
		Thread.sleep(3000);
		WebElement userName = driver.findElement(By.id("email_field"));
		userName.clear();
		userName.sendKeys("admin123@gmail.com");

		WebElement password = driver.findElement(By.id("password_field"));
		password.clear();
		password.sendKeys("admin123");
		Thread.sleep(3000);
		driver.findElement(By.tagName("button")).click();
		Thread.sleep(3000);
		WebElement switchto = driver.findElement(By.xpath("//*[@id=\"user_div\"]/div[1]/div[1]/button"));
		Actions action = new Actions(driver);
		action.moveToElement(switchto).build().perform();
		Thread.sleep(2000);
		String handler = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://www.google.com/");
		driver.switchTo().window(handler);
		Thread.sleep(3000);
		
		driver.close();
		/*driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().forward();
		driver.manage().window().maximize();*/
		/*
		 * Dimension newdimension = new Dimension(600,800);
		 * driver.manage().window().setSize(newdimension);
		 */

	}

}
