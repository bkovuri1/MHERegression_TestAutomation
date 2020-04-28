package com.qa.MHE.Pages;

import org.openqa.selenium.By;

import com.qa.MHE.Utility.WebElementActions;

import org.apache.log4j.Logger;

public class Cochrane_HomePage extends BasePage{

	public Cochrane_HomePage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Logger LOGGER = Logger.getLogger(Cochrane_HomePage.class);
	
	By img_cochraneLogo = By.xpath("//img[@class='brand-logo']");
	By lnk_cochrane_Reviews = By.xpath("(//nav[@class='main container']//a[contains(text(),'Cochrane Reviews')])[1]");
	
	
	
	public void fnc_ClickCochraneLogo() throws Exception{
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			WebElementActions.getElement(img_cochraneLogo).click();
			}catch(Exception e){
				LOGGER.error("**Unable to click on the Cochrane Logo**");
				throw new RuntimeException(e.fillInStackTrace());
			}		
	}
	
	public boolean fnc_VerifyifHomepageLoaded() throws Exception{
		boolean isHomePageLoaded = false;
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			WebElementActions.getElement(lnk_cochrane_Reviews);
			if(WebElementActions.isElementPresent(lnk_cochrane_Reviews)){
				isHomePageLoaded = true;
			}
			WebElementActions.logInfoStepToReport("End.!");
		}catch(Exception e){
			LOGGER.error("**Unable to click on the Cochrane Logo**");
			throw new RuntimeException(e.fillInStackTrace());
		}
		return isHomePageLoaded;
	}
}
