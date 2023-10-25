package com.tests.automation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestPage {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://qa-tekarch.firebaseapp.com/");
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

		WebElement widget = driver.findElement(By.xpath("//*[@id=\"user_div\"]/div[1]/div[3]/button"));
		Actions action = new Actions(driver);
		action.moveToElement(widget).build().perform();
		System.out.println("Mouse is on widget tab");
		WebElement table = driver.findElement(By.xpath("//*[@id=\"user_div\"]/div[1]/div[3]/div/a[1]"));
		table.click();
		Thread.sleep(3000);
		List<String> headerData = new ArrayList<String>();
		List<String> rowData = new ArrayList<String>();
		List<List<String>> masterList = new ArrayList<List<String>>();

		List<WebElement> hdata = driver.findElements(By.tagName("th"));
		for (WebElement headerele : hdata) {
			System.out.println(headerele.getText());
			headerData.add(headerele.getText());
		}
		System.out.println(headerData);

		int listCount = 0;
		List<WebElement> tabledata = driver.findElements(By.tagName("td"));
		System.out.println(tabledata.size());

		for (WebElement celldata : tabledata) {
			rowData.add(celldata.getText());
			listCount++;
			if (listCount == headerData.size()) {
				masterList.add(rowData);
				System.out.println(rowData);
				rowData = new ArrayList<String>();
				listCount = 0;
			}

		}

		Thread.sleep(3000);

		driver.quit();
		
		SeleniumExcel sl = new SeleniumExcel();
		sl.createTable(headerData, masterList);

	}

}
