package com.qa.MHE.Pages;

import org.openqa.selenium.By;
import com.qa.MHE.Utility.CommonUtility;
import com.qa.MHE.Utility.WebElementActions;

import org.apache.log4j.Logger;

public class MHE_HeaderPage extends BasePage{

	public MHE_HeaderPage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	CommonUtility utility = new CommonUtility();
	private static Logger LOGGER = Logger.getLogger(MHE_HeaderPage.class);
	
	By img_MHELogo = By.xpath("//img[@alt='McGrawHill Education.']");
	By lbl_IntroductoryMsg = By.xpath("(//div[@class='inner']/descendant::div[@class='block-content'])[1]/div/p");
	By lnk_About = By.xpath("//div[@class='header-top']//a[contains(text(),'About')]");
	By lnk_Administration = By.xpath("//div[@class='header-top']//a[contains(text(),'Administration')]");
	By lbl_SiteLogo = By.xpath("//div[@class='region region-header-branding']//a/img[@alt='Access Engineering']");
	
	public void fnc_ClickMHELogo() throws Exception{
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			WebElementActions.getElement(img_MHELogo).click();
			WebElementActions.logInfoStepToReport("End..!");
			}catch(Exception e){
				LOGGER.error("**Unable to click on the MHE Logo**");
				throw new RuntimeException(e.fillInStackTrace());
			}		
	}
	
	public void fnc_VerifyMHEEducationSiteOpened(){
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			utility.switchTo("BY_WINURL", "https://www.mheducation.com/");
			if(getWebDriver().getCurrentUrl().contains("https://www.mheducation.com/")){
				LOGGER.info("Navigated to https://www.mheducation.com/ in a new tab/window");
			}
			else{
				LOGGER.error("Unable to navigate to https://www.mheducation.com/ in a new tab/window");
				throw new Exception("Unable to navigate to https://www.mheducation.com/ in a new tab/window");
			}
			WebElementActions.logInfoStepToReport("End..!");
		}
		catch(Exception e){
			LOGGER.error("**Unable to click on the MHE Logo**");
			throw new RuntimeException(e.fillInStackTrace());
		}
	}
	
	public boolean fnc_VerifyifHomepageLoaded() throws Exception{
		boolean isHomePageLoaded = false;
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			utility.waitForPageLoad();
			if(WebElementActions.isElementPresent(lbl_IntroductoryMsg)){
				isHomePageLoaded = true;
			}
			WebElementActions.logInfoStepToReport("End.!");
		}catch(Exception e){
			LOGGER.error("**MHE Page didn't loaded successfully after clicking on LOGO Image**");
			throw new RuntimeException(e.fillInStackTrace());
		}
		return isHomePageLoaded;
	}
	
	public boolean fnc_VerifyIntroductoryMessage() throws Exception{
		boolean verifyIntroductoryMsg = false;
		String introductoryMessage = "The award-winning engineering reference platform for academics, students, and professionals.";
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			if(WebElementActions.isElementPresent(lbl_IntroductoryMsg)){
				verifyIntroductoryMsg = utility.verifyText(lbl_IntroductoryMsg, introductoryMessage);
				if(verifyIntroductoryMsg){
					LOGGER.info("The Introductory message in the home page is displayed as expected: "+introductoryMessage);
				}
				else{
					LOGGER.error("The Introductory message in the home page is not displayed as expected. The displayed value is: "+utility.getElementText(lbl_IntroductoryMsg)+", but the expected value is: "+introductoryMessage);
				}
			}
			WebElementActions.logInfoStepToReport("End.!");
		}catch(Exception e){
			LOGGER.error("**MHE Page didn't loaded successfully after clicking on LOGO Image**");
			throw new RuntimeException(e.fillInStackTrace());
		}
		return verifyIntroductoryMsg ;
	}
	
	public void fnc_NavigateToAboutPage() throws Exception{
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			WebElementActions.getElement(lnk_About).click();
			}catch(Exception e){
				LOGGER.error("**Unable to click on the About Link in the Home Page**");
				throw new RuntimeException(e.fillInStackTrace());
			}		
	}
	
	public void fnc_NavigateToAdministrationPage() throws Exception{
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			WebElementActions.getElement(lnk_Administration).click();
			}catch(Exception e){
				LOGGER.error("**Unable to click on the Administration Link in the Home Page**");
				throw new RuntimeException(e.fillInStackTrace());
			}		
	}
	
	public void fnc_VerifyIntroMessageNotPresent(){
		try{
			if(WebElementActions.isElementPresent(lbl_IntroductoryMsg)){
				throw new Exception("Introductory message is present in other pages too");
			}
		}
		catch(Exception e){
			LOGGER.error("**Intro message is not present**");
		}
	}
	
	public boolean fnc_VerifyifSiteLogoPresent() throws Exception{
		boolean isSiteLogoPresent = false;
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			utility.waitForPageLoad();
			if(WebElementActions.isElementPresent(lbl_SiteLogo)){
				isSiteLogoPresent = true;
			}
			WebElementActions.logInfoStepToReport("End.!");
		}catch(Exception e){
			LOGGER.error("**Site logo is not displaying in the header section**");
			throw new RuntimeException(e.fillInStackTrace());
		}
		return isSiteLogoPresent;
	}
	
	public void fnc_ClickSiteLogo() throws Exception{
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			utility.waitForPageLoad();
			if(WebElementActions.isElementPresent(lbl_SiteLogo)){
				WebElementActions.getElement(lbl_SiteLogo).click();
			}
			WebElementActions.logInfoStepToReport("End.!");
		}catch(Exception e){
			LOGGER.error("**Site logo is not displaying in the header section**");
			throw new RuntimeException(e.fillInStackTrace());
		}
	}
}