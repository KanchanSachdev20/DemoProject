package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.frameworkexception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	OptionsManager optionsManager;
	public static String highlightElement;
	public Properties prop;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {

		System.out.println("browser name is" + prop.getProperty("browser"));
		optionsManager = new OptionsManager(prop);
		highlightElement = prop.getProperty("highlight");

		switch (prop.getProperty("browser").toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on grid/remote:
				init_remoteDriver("chrome");
			} else {
				// run it on local
				System.out.println("running tests on local");
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "edge":
			//driver = new EdgeDriver();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on grid/remote:
				init_remoteDriver("edge");
			} else {
				// run it on local
				System.out.println("running tests on local");
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run on grid/remote:
				init_remoteDriver("firefox");
			} else {
				// run it on local
				System.out.println("running tests on local");
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		default:
			System.out.println("Plesae pass the right browser");
			throw new FrameworkException("NoBrowserFoundException");
		}	

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	
	private void init_remoteDriver(String browserName) {
		System.out.println("Running tests on grid with browser: " + browserName);

		try {
			switch (browserName.toLowerCase()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;

			default:
				break;
			}
		}

		catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		
		//mvn clean install -Denv="qa"
		//mvn clean install
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName = System.getProperty("env");
		System.out.println("Running test cases on env:"+ envName);
		
		try {
		if(envName == null) {
			  ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
		}
		else {
			switch(envName.toLowerCase().trim()) {
			case "qa":
			 ip  = new FileInputStream("./src/main/resources/config/qa.config.properties");
			 break;
			 
			case "dev":
				 ip  = new FileInputStream("./src/main/resources/config/dev.config.properties");
				 break;
				 
			case "stage":
				 ip  = new FileInputStream("./src/main/resources/config/stage.config.properties");
				 break;
				 
			case "uat":
				 ip  = new FileInputStream("./src/main/resources/config/uat.config.properties");
				 break;
				 
			case "prod":
				 ip  = new FileInputStream("./src/main/resources/config/config.properties");
				 break;
				 
			default:
				System.out.println("please pass right env name");
				throw new FrameworkException("NoValidEnvGiven");
					
			}
		}
	}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return prop;

	}
	

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
