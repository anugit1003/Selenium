package com.tests.automation;

import static java.lang.Thread.sleep;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class FireBaseLoginTest {

	/**
	 * creates a Chrome Driver to launch an URL
	 * 
	 * @param url - the url of the application to be launched to login
	 * @throws Exception
	 */
	public static WebDriver launchBrowser(String url) throws Exception {

		WebDriver driver = new ChromeDriver();
		driver.get(url);
		Thread.sleep(5000);
		System.out.println("Launched the url " + url);
		return driver;
	}

	public static void studentRegistrationForm(WebDriver driver) throws InterruptedException {

		WebElement name = driver.findElement(By.id("name"));
		name.clear();
		name.sendKeys("abc");
		Thread.sleep(3000);

		WebElement fathername = driver.findElement(By.id("lname"));
		fathername.clear();
		fathername.sendKeys("xyz");
		Thread.sleep(3000);

		WebElement address = driver.findElement(By.id("postaladdress"));
		address.clear();
		address.sendKeys("25816 flyawayct");
		Thread.sleep(3000);

		WebElement paddress = driver.findElement(By.id("personaladdress"));
		paddress.clear();
		paddress.sendKeys("25816");
		Thread.sleep(3000);

		WebElement radioM =  driver.findElement(By.xpath("/html/body/div[2]/div[2]/form/div[5]/span[1]/input[@value='male']"));
		
		boolean selectButtonMale = radioM.isSelected();
		if(selectButtonMale == false) {
			radioM.click();
		}
		 Thread.sleep(3000);
				
				
		 WebElement radioF = driver.findElement(By.xpath("/html/body/div[2]/div[2]/form/div[5]/span[2]/input[@value='female']"));
		 boolean selectButtonFemale = radioF.isSelected();
		 if(selectButtonFemale == false) {
			 
			 radioF.click();
		 }
		 Thread.sleep(4000);
		 
		 
		 Select city = new Select(driver.findElement(By.id("city")));
		 
		 city.selectByValue("newdelhi");
		 Thread.sleep(2000);
		 List<WebElement> cityoptions = city.getOptions();
		 for(WebElement option : cityoptions) {
			 System.out.println(option.getText());
		 }
		 Select course = new Select(driver.findElement(By.id("course")));
		 course.selectByValue("mca");
		 Thread.sleep(3000);
		 List<WebElement> courseoptions = course.getOptions();
		 for(WebElement option :courseoptions) {
			 System.out.println(option.getText());
		 }
			 
			 Select district = new Select(driver.findElement(By.id("district")));
			 district.selectByValue("goa");
			 Thread.sleep(3000);
			 List<WebElement> districtoptions = district.getOptions();
			 for(WebElement option :districtoptions ) {
				 System.out.println(option.getText());
				 
			 }
			 Select state = new Select(driver.findElement(By.id("state")));
			 state.selectByValue("mba");
			 Thread.sleep(3000);
			 List<WebElement> stateoptions = state.getOptions();
			 for(WebElement option:stateoptions) {
				 System.out.println(option.getText());
			 }
			 
			 WebElement pincode = driver.findElement(By.id("pincode"));
			 pincode.sendKeys("123654");
			 Thread.sleep(2000);
			 
			 WebElement email = driver.findElement(By.id("emailid"));
			 email.sendKeys("agorti@gmail.com");
			 Thread.sleep(2000);
			 
			 WebElement button = driver.findElement(By.className("bootbutton"));
			 button.sendKeys(Keys.ENTER);
			 Thread.sleep(3000);
		 }
		 
	
	public static void testLoginForm(WebDriver driver) throws InterruptedException {

		WebElement userName = driver.findElement(By.id("email_field"));
		userName.clear();
		userName.sendKeys("admin123@gmail.com");

		WebElement password = driver.findElement(By.id("password_field"));
		password.clear();
		password.sendKeys("admin123");
		Thread.sleep(3000);
		driver.findElement(By.tagName("button")).click();
		Thread.sleep(3000);

		// String h1String = "Student Registration Form";
		// System.out.println(driver.getTitle());

	}

	public static void logoutApp(WebDriver driver) throws Exception {

		List<WebElement> tags = driver.findElements(By.tagName("a"));
		for (WebElement tag : tags) {

			//String text = tag.getText();
			// System.out.println(text);
			if (tag.getText().equals("Logout")) {
				tag.click();
			}
		}
		// clear login fields
		WebElement userName = driver.findElement(By.id("email_field"));
		userName.clear();
		WebElement password = driver.findElement(By.id("password_field"));
		password.clear();
		// Thread.sleep(5000);

		System.out.println("logged out of the application");

	}

	public static void main(String[] args) throws Exception {

		String theUrl = "https://qa-tekarch.firebaseapp.com/";
		WebDriver driver = launchBrowser(theUrl);
		testLoginForm(driver);
		studentRegistrationForm(driver);
		logoutApp(driver);

		Thread.sleep(5000);
		
		//close all browser windows
		driver.close();

	}

}
