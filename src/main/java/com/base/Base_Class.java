package com.base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.testng.Assert;
import org.apache.commons.io.FileUtils;
// import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public abstract class Base_Class {
	
	public static WebDriver driver;
	
	public static ExtentReports extentReports;
	
	public static File file;
	
	protected static void browserLaunch(String browser) {
		try {
			if (browser.equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();
			}
			else if (browser.equalsIgnoreCase("Edge")) {
				driver = new EdgeDriver();
			}
			else if (browser.equalsIgnoreCase("FireFox")) {
				driver = new FirefoxDriver();
			}
			
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING BROWSER LAUNCH");
		}
		driver.manage().window().maximize();
	}

	protected static void urlLaunch(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING URL LAUNCH");	
		}
	}
	protected static void passInput(WebElement element, String value) {
		try {
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING PASSING INPUT");
		}
	}
	protected static void clickElement(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING CLICKING ELEMENT");
		}
	}
	
	protected static void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING CLOSING THE BROWSER");
		}
	}
	protected static void closeAllWindow() {
		try {
			driver.quit();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING CLOSING ALL WINDOW");
		}
	}
	protected static void moveToNewUrl(String url) {
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING NAVIGATING TO NEW URL");
		}
	}
	protected static void moveToPreviousPage() {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING MOVING BACK TO PREVIOUS PAGE");
		}
	}
	protected static void moveForward() {
		try {
			driver.navigate().forward();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING MOVING FORWARD");
		}
	}
	protected static void refreshPage() {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING REFRESH PAGE");
		}
	}
	protected static void acceptAlert() {
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING ACCEPTING ALERT");
		}
	}
	protected static void dismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING DISMISSING ALERT");
		}
	}
	protected static void passInputToAlert(String alertInput) {
		try {
			driver.switchTo().alert().sendKeys(alertInput);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING PASSING INPUT TO THE ALERT");
		}
	}
	protected static String getAlertText() {
		String alertText = ""; 
		try {
		 alertText = driver.switchTo().alert().getText();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING GETING THE ALERT TEXT");
		}
		return alertText;
	}
	protected static Select dropDownObject(WebElement element) {
		Select dropDown = new Select(element);
		return dropDown;
	}
	protected static void selectByDropDownValue(WebElement element, String dropDownValue) {
		try {
			dropDownObject(element).deselectByValue(dropDownValue);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING HANDLING DROPDOWN USING VALUE");
		}
	}
	protected static void selectByText(WebElement element, String text) {
		try {
			dropDownObject(element).selectByVisibleText(text);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING HANDLING DROPDOWN USING TEXT");
		}
	}
	protected static void selectByIndex(WebElement element, int index) {
		try {
			dropDownObject(element).selectByIndex(index);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING HANDLING DROPDOWN USING INDEX");
		}
	}
	protected static void implicitWait(Duration seconds) {
		try {
			driver.manage().timeouts().implicitlyWait(seconds);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING IMPLICIT WAIT");
		}
	}
	protected static void explicitWait(WebElement element, Duration seconds) {
		try {
			WebDriverWait explicitWait = new WebDriverWait(driver, seconds);
			explicitWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING EXPLICIT WAIT");
		}
	}
	public static String captureScreen() throws IOException {
		
			TakesScreenshot screenShot = (TakesScreenshot)driver;
			String timeStamp = new SimpleDateFormat("yyyyMMDD_HHMMSS").format(new Date());
			File sourceImage = screenShot.getScreenshotAs(OutputType.FILE);
			File destinationFile = new File("ScreenShot\\.png"+ "_" + timeStamp + ".png");
			FileUtils.copyFile(sourceImage, destinationFile);
			return destinationFile.getAbsolutePath();
	}
	protected static void validation(String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected);
			
		} catch (Exception e) {
			Assert.fail("ERROR : OCCURS DURING VALIDATION");
		}
	}
	public static void extentReportStart(String location) {
		extentReports = new ExtentReports();
		file = new File(location);
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}
	public static void extentReportTearDown(String location) throws IOException {
		extentReports.flush();
		file = new File(location);
		Desktop.getDesktop().browse((file).toURI());

	}
	
}

