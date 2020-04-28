package com.qa.MHE.Utility;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.log4testng.Logger;

import com.qa.MHE.Pages.BasePage;

public class WebElementActions extends BasePage{
	
	public WebElementActions() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Logger LOGGER = Logger.getLogger(WebElementActions.class);
	static JavascriptExecutor js;
	
	public static void successStepToReport(String methodName,String customMessage){
		LOGGER.info("-[**" + methodName +"**] - " +customMessage);
	}
	
	public static void failureStepToReport(String methodName,String customMessage, Exception e){
		LOGGER.error("-[**" + methodName +"**] - " +customMessage);
	}
	
	public static void warnStepToReport(String customMessage){
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		LOGGER.warn("-[**" + methodName +"**] - " +customMessage);
		
	}
	
	public static void logInfoStepToReport(String customMessage){
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		LOGGER.info("-[**" + methodName +"**] - " +customMessage);
	}
	
	public static WebElement getElement(By webLocator){
		WebElement element = null;
		try{
			logInfoStepToReport("Start..!");
			element = fluentWaitForElement(webLocator);
			logInfoStepToReport("End..!");			
		}catch(Exception e){
			throw new RuntimeException("Exception:"+e.getStackTrace());
		}
		return element;
	}

	public static WebElement fluentWaitForElement(final By locator) {
		// TODO Auto-generated method stub
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getWebDriver())
		     .ignoring(NoSuchElementException.class)
		     .pollingEvery(Duration.ofSeconds(5))
		     .withTimeout(Duration.ofSeconds(30));
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
				
		return element;
	}
	
	public static List<WebElement> getAllElements(By webLocator){
		List<WebElement> allElements;
		try{
			logInfoStepToReport("Started..!");
			allElements = getWebDriver().findElements(webLocator);	
			logInfoStepToReport("End..!");
		}catch(Exception e){
			throw new RuntimeException("Exception:"+e.getStackTrace());
		}
		return allElements;
	}
	
	public static boolean isElementPresent(final By locator) {
		boolean isElementPresent = false;
		try {
			logInfoStepToReport("Start..!");
			WebElement ele = fluentWaitForElement(locator);
			isElementPresent = ele.isDisplayed();
			logInfoStepToReport("End..!");
		} catch (Exception e) {
			isElementPresent = false;
			throw new RuntimeException("Exception:"+e.getStackTrace());
		}
		return isElementPresent;
	}
}
