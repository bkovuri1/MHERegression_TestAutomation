package com.qa.MHE.Utility;

import java.util.Set;
import java.util.function.Function;

import javax.print.DocFlavor.BYTE_ARRAY;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.MHE.Pages.BasePage;

public class CommonUtility extends BasePage {

	public CommonUtility() throws Exception {
		// TODO Auto-generated constructor stub
	}

	/*public void waitForPageLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(getWebDriver(), 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}*/
	
	public String getCurrentWindowID(){
		try{
			WebDriver driver = getWebDriver();
			String winId = driver.getWindowHandle();
			return winId;			
		}catch(Exception e){
			return null;
		}
		
	}
	
	public Set<String> getAllWindowIDs(){
		try{
			WebDriver driver = getWebDriver();
			return driver.getWindowHandles();
		}catch(Exception e){
			return null;
		}		
	}
	
	public void switchToNewWindow(Set<String> alreadyOpenedWinIDs){
		try{
			WebDriver webDriver = getWebDriver();
			Set<String> availableWindows = webDriver.getWindowHandles();
			
			for(String winId: availableWindows){
				if(!alreadyOpenedWinIDs.contains(winId)){
					webDriver.switchTo().window(winId);
				}
			}
			waitForPageLoad();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private enum WindowSwitchType {
		
		/** By WinTitle	 **/
		 BY_WINTITLE,
		/** By Winurl	 **/
		 BY_WINURL,
		 /** By Frame	 **/
		 BY_FRAME,
		 /** By ParentFrame	 **/
		 BY_PARENTFRAME,
		 /** By Default	 **/
		 BY_DEFAULT,
		 /** By the winclose	 **/
		 BY_WINCLOSE,
		 /** By Alert	 **/
		 BY_ALERT,
		 /** By windowdialog	 **/
		 BY_WINDOWDIALOG_TITLE,
		 /** By Frame Index	 **/
		 BY_FRAME_INDEX,
		 /** By winID	 **/
		 BY_WINID,
		 /** By window alert **/
		 BY_WINALERT}
	
	public boolean switchTo(String switchType, String switchExpValue) {
		try{
			switch(WindowSwitchType.valueOf(switchType)) {
			case BY_WINTITLE:
				return switchWindow(switchType, switchExpValue); 
			case BY_WINURL:
				return switchWindow(switchType, switchExpValue);
			/*case BY_WINDOWDIALOG_TITLE:
				return switchWindowDialog(switchType, switchExpValue);*/
			case BY_FRAME:
				getWebDriver().switchTo().defaultContent();
				getWebDriver().switchTo().frame(switchExpValue);					
				break;
			case BY_FRAME_INDEX:
				getWebDriver().switchTo().defaultContent();
				getWebDriver().switchTo().frame(Integer.parseInt(switchExpValue));
			case BY_PARENTFRAME:
				getWebDriver().switchTo().parentFrame();
			case BY_DEFAULT:
				getWebDriver().switchTo().defaultContent();
			case BY_WINCLOSE:
				getWebDriver().close();
				break;
			case BY_WINALERT:
				WebDriverWait alertWait = new WebDriverWait(getWebDriver(), 5);
				alertWait.until(ExpectedConditions.alertIsPresent());
				Alert alert = getWebDriver().switchTo().alert();
				alert.accept();
				break;
			case BY_WINID:
				getWebDriver().switchTo().window(switchExpValue);
				break;
			default:
				throw new IllegalArgumentException("Parameter switchType should be  BY_WINTITLE, BY_WINURL, BY_FRAME, BY_PARENTFRAME, BY_DEFAULT, BY_WINCLOSE, BY_ALERT,BY_WINDOWDIALOG_TITLE, BY_FRAME_INDEX,BY_WINID, BY_WINALERT");
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean switchWindow(String switchType, String winExpValue) throws Exception {
		WebDriver webDriver = getWebDriver();
		boolean bSwitchWindow = false;
		String winActValue = "";
		Set<String> availableWindows = webDriver.getWindowHandles();
		if(!availableWindows.isEmpty()){
			for(String windowId: availableWindows){
				if(switchType.equalsIgnoreCase("BY_WINTITILE")){
					winActValue = webDriver.switchTo().window(windowId).getTitle().trim().toLowerCase();
				}else{
					winActValue = webDriver.switchTo().window(windowId).getCurrentUrl().trim().toLowerCase();
				}
				winExpValue = winExpValue.trim().toLowerCase();
				if(winActValue.contains(winExpValue)){
					bSwitchWindow = true;
					break;
				}
			}
		}
		return bSwitchWindow;
	}
	/**
	 * 
	 * @param lbl_IntroductoryMsg, expectedText
	 * @return
	 * @description - Verifies the text and returns the boolean
	 * @throws Exception
	 */
	public boolean verifyText(By lbl_IntroductoryMsg, String expectedText) throws Exception {
		boolean isTextDisplayedasExpected = false;
		try{
			WebDriver webDriver = getWebDriver();
			if(webDriver.findElement(lbl_IntroductoryMsg).getText().equals(expectedText)){
				isTextDisplayedasExpected = true;
			}
		}
			catch(Exception e){
				e.printStackTrace();
			}
		return isTextDisplayedasExpected;
	}
	
	/**
	 * 
	 * @param lbl_IntroductoryMsg
	 * @return
	 * @description - get the text of webElement
	 * @throws Exception
	 */
	public String getElementText(By lbl_IntroductoryMsg) throws Exception {
		String elementText = "";
		try{
			WebDriver webDriver = getWebDriver();
			elementText = webDriver.findElement(lbl_IntroductoryMsg).getText();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return elementText;
	}
	
	public void waitForPageLoad() {
	    Wait<WebDriver> wait = new WebDriverWait(getWebDriver(), 30);
	    wait.until(new Function<WebDriver, Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            System.out.println("Current Window State       : "
	                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
	            return String
	                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
	                .equals("complete");
	        }
	    });
	}
}