package com.tests.base.automation;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.tests.testNg.AccountsLinkTest;
import com.tests.util.automation.Constants;
import com.tests.util.automation.PropertiesUtility;

public class BaseTestAutomation {
	
	static Logger logger = LogManager.getLogger(BaseTestAutomation.class.getName());
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
	
	/* This will launch browser and login to Salesforce app */
	public void launchBrowserAndLogin() throws Exception {
		initializeProperties();
		launchBrowser(getProps().getProperty(Constants.BROWSER));
		goToUrl(getProps().getProperty(Constants.SALESFORCE_URL));
		maximizeBrowser();		
		loginToSalesforce();
	}
	
	public void loginToSalesforce() throws InterruptedException {
		logger.info("Login to salesforce");
		WebElement usernameEle = getDriver().findElement(By.id("username"));
		waitForVisibility(usernameEle, 5);
		enterText(usernameEle, getProps().getProperty(Constants.SF_USER_NAME), "user name");
		WebElement passwordEle = getDriver().findElement(By.id("password"));
		enterText(passwordEle, getProps().getProperty(Constants.SF_PASSWORD), "Password");
		findElementByAndClick(By.id("Login"));
		Thread.sleep(3000);
		logger.info("Login Button clicked - successful");
	}
	
	public void initializeProperties() {
		PropertiesUtility propUtility = new PropertiesUtility();
		Properties props = propUtility.loadFile("applicationDataProperties");
		logger.info(props);
		setProps(props);
		
	}

	public void launchBrowser(String browserName) {

		switch (browserName) {
		case "firefox":
			setDriver(new FirefoxDriver());
			logger.info("firefox driver initialized");
			break;
		case "chrome":
			setDriver(new ChromeDriver());
			logger.info("chrome driver initialized");
			break;
		default:
			logger.error("you have not entrered the correct browser");
		}
	}

	public void goToUrl(String url) {
		getDriver().get(url);
		logger.info(url + "is entered");
	}

	public void maximizeBrowser() {
		getDriver().manage().window().maximize();
		logger.info("browser window has maximized");
	}

	public String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		logger.info(data + " - extracted the text from " + objectName);
		return data;
	}
	
	public String getTextFromElement(By by, String objectName) {
		WebElement element = getDriver().findElement(by);
		String data = element.getText();
		logger.info(data + " - extracted the text from " + objectName);
		return data;
	}
	
	public boolean compareTextForElement(By by, String compareString) {
		boolean compareText = false;
		WebElement element = getDriver().findElement(by);
		if(compareString.equals(element.getText())){
			logger.info("compare string successful for "+ compareString);
			compareText = true;
		}else {
			logger.error("compare string  not successful for "+ compareString);
			
		}
		
		return compareText;
	}
	
	public void quitBrowser() {
		logger.info("Quitting the browser");
		getDriver().quit();
	}

	public void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			logger.info("data is entered in the " + objectName);
		} else {
			logger.error(objectName + " element is not displayed");
		}
	}
	
	public void enterText(By by, String data, String objectName) {
		WebElement element = getDriver().findElement(by);
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			logger.info("data is entered in the " + objectName);
		} else {
			logger.error(objectName + " element is not displayed");
		}
	}

	public static void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			logger.info(objectName + "button is clicked");
		} else {
			logger.error("button element is not enabled");
		}
	}

	public void waitForVisibility(WebElement ele, int time, int pollingtime, String objectName) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getDriver());
		wait.withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(ElementNotInteractableException.class);

		wait.until(ExpectedConditions.visibilityOf(ele));
		logger.info(objectName + " is waited for visibility using fluent wait");
	}
	public void waitForVisibility(WebElement ele, int time) {
		wait = new WebDriverWait(getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void clearElement(WebElement ele,String objectNam) {
		if(ele.isDisplayed()) {
			ele.clear();
			logger.info(objectNam + "is cleared");
		}else {
			logger.error(objectNam +"is not displayed" );
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
	
	public boolean selectAndVerifyOptions(Select selectElement, String toVerify) {
		List<WebElement> type = selectElement.getOptions();
		boolean isOptionFound = false;
		for (WebElement option : type) {
			if (option.getText().equals(toVerify)) {
				isOptionFound = true;
				selectElement.selectByValue(toVerify);
				logger.info(toVerify + " is selected.");

			}
		}
		
		return isOptionFound;
	}
	
	
	/*public void selectByIndex(WebElement element,int index,String objectName) {
		waitForVisibility(element,5,objectName);
		
	}*/
}
