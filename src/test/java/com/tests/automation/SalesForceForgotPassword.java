package com.tests.automation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SalesForceForgotPassword {
	
	private String userId;
	
	private WebDriver driver;

	
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

	public void testClickForgotPassword() throws InterruptedException {
		WebElement usernameEle = getDriver().findElement(By.id("username"));
		System.out.println("User name is enetered");
		if (usernameEle.isDisplayed() && usernameEle.isEnabled()) {
			usernameEle.clear();
			usernameEle.sendKeys(getUserId());
		} else {
			System.err.println("user name is either not displayed or not enabled");
		}
		Thread.sleep(5000);

		WebElement forgotpassword = getDriver().findElement(By.id("forgot_password_link"));
		forgotpassword.click();
		Thread.sleep(5000);
	}

	public void testForgotPasswordPage() throws InterruptedException {

		WebElement landpage = getDriver().findElement(By.id("header"));
		if (landpage.getText().equals("Forgot Your Password")) {
			System.out.println("testLandingPage - Test case passed");
		} else {
			System.err.println("testLandingPage - Test case failed");
		}
		Thread.sleep(5000);
		WebElement usernameEle = getDriver().findElement(By.id("un"));
		System.out.println("User name is entered again");
		if (usernameEle.isDisplayed() && usernameEle.isEnabled()) {
			usernameEle.clear();
			usernameEle.sendKeys(getUserId());
		} else {
			System.err.println("user name is either not displayed or not enabled");
		}
		Thread.sleep(5000);
		WebElement buttonContinue = getDriver().findElement(By.id("continue"));
		buttonContinue.click();
		Thread.sleep(5000);
		System.out.println("click on 'continue' button completed");

	}
	public void testSendEmailPage() throws InterruptedException {
		WebElement landpage1 = getDriver().findElement(By.xpath("/html/body/div[1]/div/div/h1"));
		if(landpage1.getText().equals("Check Your Email")) {
			System.out.println("Test Case passed");
		}else {
			System.out.println("Test case failed");
		}
		Thread.sleep(3000);
		WebElement loginReturn = getDriver().findElement(By.linkText("Return to Login"));
		loginReturn.click();	
		Thread.sleep(3000);
	}

	public static void main(String[] args) throws InterruptedException {
		SalesForceForgotPassword sffp = new SalesForceForgotPassword();
		sffp.setDriver(new ChromeDriver());
		sffp.getDriver().get("https://login.salesforce.com/");
		
		sffp.setUserId("agorpalli@agorpalli.com");
		sffp.testClickForgotPassword();
		sffp.testForgotPasswordPage();
		sffp.testSendEmailPage();
	    sffp.getDriver().quit();
	}

}
