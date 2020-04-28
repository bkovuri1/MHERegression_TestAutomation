package com.qa.MHE.stepDefinitions;

import org.testng.log4testng.Logger;

import com.qa.MHE.Pages.BasePage;
import com.qa.MHE.Pages.MHE_HeaderPage;
import com.qa.MHE.Utility.CommonUtility;
import com.qa.MHE.Utility.WebElementActions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepsDef extends BasePage{
	
	private static Logger LOGGER = Logger.getLogger(StepsDef.class);
	MHE_HeaderPage mheHeaderPage = new MHE_HeaderPage();
	CommonUtility utility = new CommonUtility();
	public StepsDef() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Given("^Navigate to MHE Home Page$")
	public void User_Opens_Application_URL(){
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			mheHeaderPage.fnc_ClickMHELogo();
			WebElementActions.logInfoStepToReport("End..!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@When("^MHE site loaded$")
	public void VerifyMHESiteLoad(){
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			mheHeaderPage.fnc_VerifyifHomepageLoaded();
			WebElementActions.logInfoStepToReport("End..!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Then("^Verify if introductory message is displayed$")
	public void verifyIntroductoryMessage(){
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			mheHeaderPage.fnc_VerifyIntroductoryMessage();
			WebElementActions.logInfoStepToReport("End..!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Given("^Navigate to About Page$")
	/*@And("^Navigate to About Page$")*/
	public void navigateToAboutPage(){
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			mheHeaderPage.fnc_NavigateToAboutPage();
			mheHeaderPage.fnc_VerifyIntroMessageNotPresent();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@When("^page loaded$")
	public void pageLoad(){
		try{
			utility.waitForPageLoad();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Then("^Verify intro message is not displaying$")
	public void verifyIntroductoryMessageNotPresent(){
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			mheHeaderPage.fnc_VerifyIntroMessageNotPresent();
			WebElementActions.logInfoStepToReport("End..!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Then("^Verify Publisher logo is displayed$")
	public void verifyPublisherLogo(){
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			mheHeaderPage.fnc_ClickMHELogo();
			WebElementActions.logInfoStepToReport("End..!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Then("^Verify mheeducation.com opened in new window$")
	public void verifyMHEEducationSiteOpened(){
		try{
			WebElementActions.logInfoStepToReport("Start..!");
			mheHeaderPage.fnc_VerifyMHEEducationSiteOpened();
			WebElementActions.logInfoStepToReport("End..!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@When("^Verify Sitelogo is displayed and Click$")
	public void verifySiteLogoDisplay(){
		try{
			WebElementActions.logInfoStepToReport("Start..!!");
			mheHeaderPage.fnc_VerifyifSiteLogoPresent();
			mheHeaderPage.fnc_ClickSiteLogo();
			utility.waitForPageLoad();
			WebElementActions.logInfoStepToReport("End..!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@When("^Verify HomePage is Loaded$")
	public void verifyHomePageLoad(){
		try{
			WebElementActions.logInfoStepToReport("Start..!!");
			utility.waitForPageLoad();
			String currURL = getWebDriver().getCurrentUrl();
			if(currURL.equals(prop.getProperty("MHE_URL"))){
				LOGGER.info("Home page is getting displayed when clicked on Site Logo");
			}
			else{
				LOGGER.error("Home page is not getting displayed when clicked on Site Logo");
				throw new Exception("Unable to navigate to the Homepage url after clicking on the Sitelogo");
			}
			WebElementActions.logInfoStepToReport("End..!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
