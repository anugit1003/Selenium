package com.tests.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SalesForceLoginPage {

	private WebDriver driver;

	private String userId;

	private String password;

	private String url;

	private String passFail;

	private String errorMsgActual;

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

	public String getPassFail() {
		return passFail;
	}

	public void setPassFail(String passFail) {
		this.passFail = passFail;
	}

	public String getErrorMsgActual() {
		return errorMsgActual;
	}

	public void setErrorMsgActual(String errorMsgActual) {
		this.errorMsgActual = errorMsgActual;
	}

	public void testLoginPage() throws InterruptedException {

		getDriver().get(getUrl());
		WebElement usernameEle = driver.findElement(By.id("username"));
		if (usernameEle.isDisplayed() && usernameEle.isEnabled()) {
			usernameEle.clear();
			usernameEle.sendKeys(getUserId());
		} else {
			System.err.println("user name is either not displayed or not enabled");
		}
		Thread.sleep(3000);
		WebElement passwordEle = driver.findElement(By.id("password"));
		if (passwordEle.isDisplayed() && passwordEle.isEnabled()) {
			passwordEle.clear();
			passwordEle.sendKeys(getPassword());
		} else {
			System.err.println("password is not displayed");
		}
		Thread.sleep(3000);
		
		
		WebElement buttonEle = driver.findElement(By.id("Login"));
		if (buttonEle.isEnabled()) {
			buttonEle.click();
		} else {
			System.err.println("Login button is not enabled");
		}
		Thread.sleep(3000);

		try {
			WebElement errorMsgElement = driver.findElement(By.id("error"));
			if (errorMsgElement != null && errorMsgElement.isDisplayed()) {
				// String errorMsgActual = "Please check your username and password. If you
				// still can't log in, contact your Salesforce administrator.";
				System.err.println("****Error Message is displayed****");
				if (errorMsgElement.getText().equals(getErrorMsgActual())) {
					System.out.println(getPassFail());
				}
			}

		} catch (NoSuchElementException nse) {
			System.err.println("****No Error Message Displayed in RED****");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testLandingPage() {
		WebElement landpage = getDriver().findElement(By.id("userNavLabel"));
		if (landpage.getText().equals("Anuradha Gorpalli")) {
			System.out.println("Test case passed");
		} else {
			System.err.println("Test case failed");
		}
	}

	public void testLogOut() throws InterruptedException {
		WebElement userele = getDriver().findElement(By.id("userNavLabel"));
		if (userele.getText().equals("Anuradha Gorpalli")) {
			userele.click();
			Thread.sleep(3000);
		} else {
			System.out.println("cannot find the link");
		}
		WebElement logout = getDriver().findElement(By.cssSelector("#userNav-menuItems > a:nth-child(5)"));
		logout.click();
	}

	public static void main(String[] args) throws InterruptedException {
		SalesForceLoginPage sflp = new SalesForceLoginPage();

		/* valid userid and blank password */
		sflp.setDriver(new ChromeDriver());
		sflp.setUrl("https://login.salesforce.com/");
		sflp.setUserId("agorpalli@agorpalli.com");
		sflp.setPassword("");
		sflp.setErrorMsgActual("Please enter your password.");
		sflp.setPassFail("Test Case passed");
		sflp.testLoginPage();
		sflp.getDriver().quit();
		System.out.println("Browser is closed");
		Thread.sleep(3000);

		/* valid userid and valid password */
		sflp.setDriver(new ChromeDriver());
		sflp.setUrl("https://login.salesforce.com/");
		sflp.setUserId("agorpalli@agorpalli.com");
		sflp.setPassword("Tekarch23");
		sflp.setPassFail("Test Case Failed");
		sflp.testLoginPage();
		Thread.sleep(3000);
		sflp.testLandingPage();
		Thread.sleep(3000);
		sflp.testLogOut();
		Thread.sleep(7000);
		sflp.getDriver().quit();
		System.out.println("Browser is closed");

		Thread.sleep(3000);

		/* invalid userid and invalid password */

		sflp.setDriver(new ChromeDriver());
		sflp.setUrl("https://login.salesforce.com/");
		sflp.setUserId("123");
		sflp.setPassword("22131");
		sflp.setErrorMsgActual(
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
		sflp.setPassFail("Test Case Passed");
		sflp.testLoginPage();
		
		sflp.getDriver().quit();
		System.out.println("Browser is closed");

		Thread.sleep(3000);

	}

}
