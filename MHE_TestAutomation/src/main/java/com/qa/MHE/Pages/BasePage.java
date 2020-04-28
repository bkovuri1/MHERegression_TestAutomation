package com.qa.MHE.Pages;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.log4j.Logger;

public class BasePage {
	
	private static Logger LOGGER = Logger.getLogger(BasePage.class);
	
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	public static Properties prop;
	public static String environment = null;

	public BasePage() throws Exception{
		try{
			prop = new Properties();
			FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/MHE/config/config.properties");
			prop.load(fip);
		}
		catch(Exception e){
			throw new Exception("Property file not opening for some reason.");
		}
	}
	
	public static WebDriver getWebDriver(){
		try{
			return tdriver.get();
		}catch(Exception e){
			throw new RuntimeException("webdriver not passed");
		}
	}
	
	public static void setUpDriver(){
		try{
			LOGGER.info("Initialization of WebDriver started");
			environment = prop.getProperty("env");
			String browserName = prop.getProperty("browser");
			if(browserName.equalsIgnoreCase("chrome")){
				ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				options.addArguments("--profile-directory=Default");
				options.addArguments("--disable-infobars");
				options.addArguments("start-maximized");
				options.addArguments("--disable-notifications");
				/*DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.setBrowserName("chrome");*/
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver.exe");
				tdriver.set(new ChromeDriver(options));
				//tdriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubAddress")), capabilities));
			}
			else if(browserName.equalsIgnoreCase("IE")){
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/IEDriverServer.exe");
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.setCapability("requiredWindowFocus", false);
				options.setCapability("nativeEvents", false);
				options.setCapability("ignoreProtectedModeSettings", true);
				options.setCapability("ignorezoomsetting", true);
				tdriver.set(new InternetExplorerDriver(options));
			}
			getWebDriver().manage().deleteAllCookies();
			getWebDriver().manage().window().maximize();
			getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if(environment.equalsIgnoreCase("MHE_URL")){
				getWebDriver().get(prop.getProperty("MHE_URL"));
			}
			if(environment.equalsIgnoreCase("JCore")){
				getWebDriver().get(prop.getProperty("JCore_URL"));
			}
		}catch(Exception e){
			LOGGER.error("Error in Initializing the WebDriver");
			e.printStackTrace();
			throw new RuntimeException("No Browse Driver found to start Test Exection");
		}
	}
	
	public static void tearDownDriver(){
		try{
			getWebDriver().quit();
		}catch(Exception e){
			LOGGER.error("Failed to close the WebDriver");
			throw new RuntimeException("Failed to close the webDriver");
		}
	}
}