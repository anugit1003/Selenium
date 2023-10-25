package com.tests.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoQAWindowHandle {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/browser-windows");
        Thread.sleep(2000);
  WebElement childWindow = driver.findElement(By.id("windowButton"));
  childWindow.click();
  String str = childWindow.getText();
  System.out.println(str);
  Thread.sleep(2000);
        String mainWindowHandle = driver.getWindowHandle();
        if (!mainWindowHandle.equalsIgnoreCase(str)) {
            driver.switchTo().window(str);
            WebElement text = driver.findElement(By.id("sampleHeading"));
            System.out.println("Heading of child window is " + text.getText());
        }
	}

}
