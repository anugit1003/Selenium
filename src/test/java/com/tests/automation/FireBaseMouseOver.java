package com.tests.automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FireBaseMouseOver {

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
		System.out.println("user name is entered");

		WebElement password = getDriver().findElement(By.id("password_field"));
		password.clear();
		password.sendKeys(getPassword());
		System.out.println("password is entered");
		Thread.sleep(3000);
		getDriver().findElement(By.tagName("button")).click();
		Thread.sleep(3000);
		System.out.println("login successful");
		WebElement homeMenu = getDriver().findElement(By.linkText("Home"));
		homeMenu.click();
	}
	
	
	public  void mouseOver() throws InterruptedException {
		WebElement intractions = getDriver().findElement(By.xpath("//*[@id=\"user_div\"]/div[1]/div[2]/button"));
		Actions action = new Actions(getDriver());
		action.moveToElement(intractions).build().perform();
		System.out.println("Mouse is on Intractions tab");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement doubleclickMenu = getDriver().findElement(By.linkText("Double Click"));
		//System.out.println(doubleclickMenu.getAttribute("href"));
		//System.out.println(doubleclickMenu.getText());		
		doubleclickMenu.click();
		Thread.sleep(5000);
	}
	
	public void singleclick() throws InterruptedException {
		WebElement singleclick = getDriver().findElement(By.xpath("//*[@id=\"user_div\"]/div[2]/div[1]/button"));
		singleclick.click();		
		Thread.sleep(2000);
	}
	public void singleclickcount() {
		WebElement scc = getDriver().findElement(By.id("demo1"));
		System.out.println(scc.getText());
		if(scc.getText().equals("Count = 10")) {
			System.out.println("Test case passed");
			
		}else {
			System.out.println("Test case failed");
		}		
	}
	public void doubleclick() throws InterruptedException {
		
		WebElement doubleclick = getDriver().findElement(By.xpath("//*[@id=\"user_div\"]/div[2]/div[2]/button"));
		Actions action = new Actions(getDriver());
		action.doubleClick(doubleclick).perform();
		Thread.sleep(2000);
	}
	public void doubleclickcount() {
		WebElement dcc = getDriver().findElement(By.id("Selenium"));
		if(dcc.getText().equals("Count = 10")) {
			System.out.println("Test case passed");
		}else {
			System.out.println("Test case failed");
		}
	}
	
	public void logoutApp() throws InterruptedException {
		WebElement logout = getDriver().findElement(By.xpath("//*[@id=\"user_div\"]/div[1]/a[4]"));
		logout.click();
		System.out.println("logged out of application");
		Thread.sleep(2000);
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		FireBaseMouseOver fbmo = new FireBaseMouseOver();
		fbmo.setDriver(new ChromeDriver());
		fbmo.getDriver().get("https://qa-tekarch.firebaseapp.com/");
		Thread.sleep(3000);
		fbmo.setUserid("admin123@gmail.com");
		fbmo.setPassword("admin123");
		fbmo.loginPage();
		Thread.sleep(3000);
		fbmo.mouseOver();
		
	//	Single click loop
		for(int i=1;i<=10;i++) {
			fbmo.singleclick();	
		}	
		System.out.println("mouse single clicked 10 times");
	// Single click count method	
       fbmo.singleclickcount();
       
       //Double click loop
		for(int i=1;i<=10;i++) {
			fbmo.doubleclick();
		}
		System.out.println("mouse double clicked 10 times");
		//Double click count method		
		fbmo.doubleclickcount();
		fbmo.logoutApp();
		fbmo.getDriver().quit();
	}
}
