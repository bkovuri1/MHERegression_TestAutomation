package com.qa.MHE.stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.qa.MHE.Pages.BasePage;

import org.apache.log4j.Logger;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks extends BasePage{

	private static Logger LOGGER = Logger.getLogger(ServiceHooks.class);
	public ServiceHooks() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void startTest(Scenario scenario){
		BasePage.setUpDriver();
		LOGGER.info(scenario.getName() + " Started");
	}

	@After
	public void endTest(Scenario scenario){
		if(scenario.isFailed()){
			try{
				LOGGER.info(scenario.getName() + "is Failed");
				final byte[] screenshot = ((TakesScreenshot) BasePage.getWebDriver()).getScreenshotAs((OutputType.BYTES));
				scenario.embed(screenshot, "image/png");
				tearDownDriver();
			}catch(WebDriverException e){
				e.printStackTrace();
			}
		}else{
			try{
				LOGGER.info(scenario.getName() + " is Pass");
				final byte[] screenshot = ((TakesScreenshot) BasePage.getWebDriver()).getScreenshotAs((OutputType.BYTES));
				scenario.embed(screenshot, "image/png");
				tearDownDriver();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
