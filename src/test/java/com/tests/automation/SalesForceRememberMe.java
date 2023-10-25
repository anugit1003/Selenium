package com.tests.automation;

import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SalesForceRememberMe {
	private WebDriver driver;

	private String userId;

	private String password;

	private String url;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void testRememberMe() throws InterruptedException {
		getDriver().get(getUrl());
		WebElement usernameEle = getDriver().findElement(By.id("username"));
		if (usernameEle.isDisplayed() && usernameEle.isEnabled()) {
			usernameEle.clear();
			usernameEle.sendKeys(getUserId());
		} else {
			System.err.println("user name is either not displayed or not enabled");
		}
		Thread.sleep(3000);
		WebElement passwordEle = getDriver().findElement(By.id("password"));
		if (passwordEle.isDisplayed() && usernameEle.isEnabled()) {
			passwordEle.clear();
			passwordEle.sendKeys(getPassword());
		} else {
			System.err.println("password is not displayed");
		}
		Thread.sleep(3000);

		WebElement rememberEle = getDriver().findElement(By.id("rememberUn"));
		boolean isselected = rememberEle.isSelected();
		if (isselected == false) {
			rememberEle.click();
		} else {
			System.out.println("check box is already selected");
		}
		Thread.sleep(3000);
		
		WebElement loginEle = driver.findElement(By.id("Login"));
		if (loginEle.isEnabled()) {
			loginEle.click();
		} else {
			System.err.println("Login button is not enabled");
		}
		Thread.sleep(3000);
		

		WebElement nameEle = driver.findElement(By.id("userNavLabel"));
		if (nameEle.getText().equals("Anuradha Gorpalli")) {
			nameEle.click();
			Thread.sleep(3000);
		} else {
			System.out.println("cannot find the link");
		}

		WebElement logout = getDriver().findElement(By.cssSelector("#userNav-menuItems > a:nth-child(5)"));
		logout.click();
		Thread.sleep(5000);
		
		WebElement textboxEle = getDriver().findElement(By.id("idcard-identity"));
		if(textboxEle.getText().equals(getUserId())) {
			System.out.println("Test case passed");
		}else {
			System.out.println("test case failed");
		}
		WebElement rememberEle1 = getDriver().findElement(By.id("rememberUn"));
		if(rememberEle1.isSelected()) {
			System.out.println("Test case passed");
		}else {
			System.out.println("test case failed");
		}
		
	}
	
	

	public static void main(String[] args) throws InterruptedException {
		SalesForceRememberMe sfrm = new SalesForceRememberMe();

		sfrm.setDriver(new ChromeDriver());
		sfrm.setUrl("https://login.salesforce.com/");
		sfrm.setUserId("agorpalli@agorpalli.com");
		sfrm.setPassword("Tekarch23");
		sfrm.testRememberMe();
		sfrm.getDriver().quit();
		System.out.println("browser is closed");

	}

}
