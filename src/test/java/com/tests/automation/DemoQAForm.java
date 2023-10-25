package com.tests.automation;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoQAForm {

	public static void demoForm(WebDriver driver) throws InterruptedException {
		

		WebElement firstName = driver.findElement(By.id("firstName"));
		firstName.clear();
		firstName.sendKeys("abc");
		Thread.sleep(3000);

		WebElement lastName = driver.findElement(By.id("lastName"));
		lastName.clear();
		lastName.sendKeys("xyz");
		Thread.sleep(3000);
		
		WebElement email = driver.findElement(By.id("userEmail"));
		email.sendKeys("dfgffl@gmail.com");	
		
		WebElement maleRadio = driver.findElement(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[3]/div[2]/div[1]/label"));

			maleRadio.click();
			Thread.sleep(3000);

		WebElement radio2 = driver.findElement(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[3]/div[2]/div[2]/label"));
		boolean selectButtonFemale = radio2.isSelected();
		if (selectButtonFemale == false) {

			radio2.click();
			Thread.sleep(3000);
		}
		WebElement radio3 = driver.findElement(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[3]/div[2]/div[3]/label"));
		boolean selectButtonOther = radio3.isSelected();
		if (selectButtonOther == false) {
			radio3.click();
			Thread.sleep(3000);
		}
		
		WebElement phnumber = driver.findElement(By.id("userNumber"));
		phnumber.sendKeys("5679082345");
		
		
		WebElement sportsbox = driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]/label"));
		
		boolean issportselected = sportsbox.isSelected();
		if(!issportselected) {
			sportsbox.click();
		}else {
			System.out.println("The checkbox is already selected");
		}
		Thread.sleep(2000);
		WebElement readingbox = driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]/label"));
		boolean isreadselected = sportsbox.isSelected();
		if(isreadselected == false) {
			readingbox.click();
		}else {
			System.out.println("The checkbox is already selected");
		}
		Thread.sleep(2000);
		WebElement musicbox = driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[3]/label"));
		boolean ismusicselected = musicbox.isSelected();
		if(ismusicselected==false) {
			musicbox.click();
		}else {
			System.out.println("The checkbox is already selected");
		}
		Thread.sleep(3000);
		WebElement buttonsub = driver.findElement(By.id("submit"));
		if(buttonsub.isEnabled()) {
			buttonsub.sendKeys(Keys.ENTER);
		}else {
			System.out.println("Submit button is not enabled");
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/automation-practice-form");
		demoForm(driver);
		Thread.sleep(5000);
		driver.quit();
		System.out.println("form is closed");
		

	}

}
