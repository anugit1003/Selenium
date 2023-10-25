package com.tests.automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoQATable {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/webtables");
		System.out.println("found the table");
		Thread.sleep(2000);
		WebElement table = driver.findElement(By.className("rt-table"));
		List<WebElement> headerlist = table.findElements(By.className("rt-resizable-header-content"));
		for(WebElement ele : headerlist) {
			System.out.print(ele.getText()+" ");
			
		}
		System.out.println();
		Thread.sleep(2000);
		
		//List<WebElement> tabledata = table.findElements(By.className("rt-tbody"));
		List<WebElement> tabledata = table.findElements(By.className("rt-td"));
		int count = 0;
		for(WebElement tableele : tabledata) {
			System.out.print(tableele.getText() +" ");
			count++;
			if(count > 6) {
				System.out.println();
				count = 0;
			}
			
		}
		Thread.sleep(2000);
		
		driver.quit();

	}

}
