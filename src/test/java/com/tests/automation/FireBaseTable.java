package com.tests.automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FireBaseTable {
	private String userid;
	private String password;
	private WebDriver driver;

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void loginPage() throws InterruptedException {
		WebElement userid= getDriver().findElement(By.id("email_field"));
		userid.clear();
		userid.sendKeys(getUserid());
		Thread.sleep(3000);
		System.out.println("user name is entered");

		WebElement password = getDriver().findElement(By.id("password_field"));
		password.clear();
		password.sendKeys(getPassword());
		System.out.println("password is entered");
		Thread.sleep(3000);
		getDriver().findElement(By.tagName("button")).click();
		Thread.sleep(3000);
		System.out.println("login successful");
		//WebElement homeMenu = getDriver().findElement(By.linkText("Home"));
		//homeMenu.click();
	}
	public void testTable() throws InterruptedException {
		WebElement widget = driver.findElement(By.xpath("//*[@id=\"user_div\"]/div[1]/div[3]/button"));
		Actions action = new Actions(getDriver());
		action.moveToElement(widget).build().perform();
		System.out.println("Mouse is on widget tab");
		WebElement table = driver.findElement(By.xpath("//*[@id=\"user_div\"]/div[1]/div[3]/div/a[1]"));
		table.click();
		Thread.sleep(3000);
	}
	
	


	public static void main(String[] args) throws InterruptedException {
		FireBaseTable fbt = new FireBaseTable();
		fbt.setDriver(new ChromeDriver());
		//fbt.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		fbt.getDriver().get("https://qa-tekarch.firebaseapp.com/");
		fbt.setUserid("admin123@gmail.com");
		fbt.setPassword("admin123");
		fbt.loginPage();
		fbt.testTable();
		

	}

}
