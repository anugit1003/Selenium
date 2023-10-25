package com.tests.testNg;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tests.base.automation.BaseTestAutomation;
import com.tests.util.automation.FileDataReader;

public class LoginDataProviderTest extends BaseTestAutomation{
	
	private String passFail;

	private String errorMsgActual;

	
	public void setPassFail(String passFail) {
		this.passFail = passFail;
	}

	public void setErrorMsgActual(String errorMsgActual) {
		this.errorMsgActual = errorMsgActual;
	}

	public String getPassFail() {
		return passFail;
	}

	public String getErrorMsgActual() {
		return errorMsgActual;
	}

	@BeforeMethod
	public void setUp() {
		
		launchBrowser("chrome");
		setErrorMsgActual(
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
		setPassFail("Test Case Passed");
		
	}
	
	@Test(dataProvider = "logindata")
	public void testLogin(String username, String password) throws InterruptedException {
		
		getDriver().get("https://login.salesforce.com/");
		Thread.sleep(3000);
		getDriver().manage().window().maximize();
		WebElement usernameEle = getDriver().findElement(By.id("username"));
		enterText(usernameEle, username, "user name");
		WebElement passwordEle = getDriver().findElement(By.id("password"));
		enterText(passwordEle, password, "Password");
		WebElement buttonEle = getDriver().findElement(By.id("Login"));
		Thread.sleep(3000);
		clickElement(buttonEle, "login button");
		
		try {
			/** when valid userid and password is entered, this element is not displayed */
			WebElement errorMsgElement = getDriver().findElement(By.id("error"));
			if (errorMsgElement != null && errorMsgElement.isDisplayed()) {
								
				System.err.println("****Error Message is displayed in red on login page****");
				
				if (errorMsgElement.getText().equals(getErrorMsgActual())) {
					System.out.println(getPassFail());
				}
			}

		} catch (NoSuchElementException nse) {
			System.err.println("****No Error Message Displayed in RED****");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("test login completed");
	}
	
	
	

	@DataProvider(name="logindata")
	public Object[][] logindataFeed() throws Exception{
		String filePath = "C:\\Users\\gorti\\anuprojects\\eclipse-workspace\\SeleniumProject\\src\\test\\resources\\LoginData.xlsx";
		String sheetName = "logindatasheet";
		Object [][] salesforcedata= new FileDataReader().getLoginData(filePath, sheetName);		
		return salesforcedata;

	}
		
	@AfterMethod
	public void closeBrowser() {		
		quitBrowser();
	}
	

}
