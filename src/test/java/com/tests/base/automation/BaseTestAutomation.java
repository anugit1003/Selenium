package com.tests.base.automation;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tests.util.automation.PropertiesUtility;

public class BaseTestAutomation {

	private String userId;

	private WebDriver driver;

	private WebDriverWait wait;
	
	private Properties props;

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
	
	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
	
	public void initializeProperties() {
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties props = propUtility.loadFile("applicationDataProperties");
		System.out.println(props);
		setProps(props);
		
	}

	public void launchBrowser(String browserName) {

		switch (browserName) {
		case "firefox":
			setDriver(new FirefoxDriver());
			System.out.println("firefox driver initialized");
			break;
		case "chrome":
			setDriver(new ChromeDriver());
			System.out.println("chrome driver initialized");
			break;
		default:
			System.out.println("you have not entrered the correct browser");
		}
	}

	public void goToUrl(String url) {
		getDriver().get(url);
		System.out.println(url + "is entered");
	}

	public void maximiseBrowser() {
		getDriver().manage().window().maximize();
		System.out.println("browser window has maximized");
	}

	public String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		System.out.println(data + " - extracted the text from " + objectName);
		return data;
	}
	
	public String getTextFromElement(By by, String objectName) {
		WebElement element = getDriver().findElement(by);
		String data = element.getText();
		System.out.println(data + " - extracted the text from " + objectName);
		return data;
	}
	
	public boolean compareTextForElement(By by, String compareString) {
		boolean compareText = false;
		WebElement element = getDriver().findElement(by);
		if(compareString.equals(element.getText())){
			System.out.println("compare string successful for "+ compareString);
			compareText = true;
		}else {
			System.out.println("compare string  not successful for "+ compareString);
			
		}
		
		return compareText;
	}
	
	public void quitBrowser() {
		System.out.println("Quitting the browser");
		getDriver().quit();
	}

	public void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			System.out.println("data is entered in the " + objectName);
		} else {
			System.out.println(objectName + " element is not displayed");
		}
	}
	
	public void enterText(By by, String data, String objectName) {
		WebElement element = getDriver().findElement(by);
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			System.out.println("data is entered in the " + objectName);
		} else {
			System.out.println(objectName + " element is not displayed");
		}
	}

	public static void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			System.out.println(objectName + "button is clicked");
		} else {
			System.out.println("button element is not enabled");
		}
	}

	public void waitForVisibility(WebElement ele, int time, int pollingtime, String objectName) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getDriver());
		wait.withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(ElementNotInteractableException.class);

		wait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println(objectName + " is waited for visibility using fluent wait");
	}
	public void waitForVisibility(WebElement ele, int time) {
		wait = new WebDriverWait(getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void clearElement(WebElement ele,String objectNam) {
		if(ele.isDisplayed()) {
			ele.clear();
			System.out.println(objectNam + "is cleared");
		}else {
			System.out.println(objectNam +"is not displayed" );
		}
	}
	

	public void findElementByAndClick(By by) {
		WebElement element = getDriver().findElement(by);
		element.click();
		
	}
	
	public void findIframeAndSwitchTo(By by) {
		WebElement element = getDriver().findElement(by);
		getDriver().switchTo().frame(element);
		
	}
	/*public void selectByIndex(WebElement element,int index,String objectName) {
		waitForVisibility(element,5,objectName);
		
	}*/
}
