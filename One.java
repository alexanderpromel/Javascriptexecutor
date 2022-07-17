package org.digits;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Numbers{
	static WebDriver driver;
	@BeforeClass
	public static void launch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public static void quit() {
		System.out.println("quit");
		//driver.quit();
	}
	
	
	long startTime;
	@BeforeMethod
	public void beforeMethod() {
		startTime = System.currentTimeMillis();
	}
	
	@AfterMethod
	public void afterMethod() {
		long endTime = System.currentTimeMillis();
		System.out.println("Total time is: " +(endTime-startTime));
	}
	
	WebElement user;
	WebElement pass;
	WebElement login;
	
	
	@Test(priority=1)
	public void login() {
		user = driver.findElement(By.xpath("//input[@id='email']"));
		pass = driver.findElement(By.xpath("//input[@id='pass']"));
		login = driver.findElement(By.xpath("//button[@name='login']"));
	}
	
	JavascriptExecutor js;
	@Test(priority=2)
	public void javaScript() {
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('value','Alex')", user);
		js.executeScript("arguments[0].setAttribute('value', '123456789')", pass);
		js.executeScript("arguments[0].click()", login);
	}
	
	@Test(priority=3)
	public void navigateBack() {
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		driver.navigate().back();
	}
	
	@Test(priority=4, invocationCount=5)
	public void scrollInto() {
		WebElement down = driver.findElement(By.xpath("//a[contains(text(), 'Careers')]"));
		js.executeScript("arguments[0].scrollIntoView(true)", down);
		js.executeScript("arguments[0].scrollIntoView(false)", down);
	}
	
	@Test(priority=5)
	public void takeScreenshot() {
		TakesScreenshot tk = (TakesScreenshot)driver;
		File type = tk.getScreenshotAs(OutputType.FILE);
		File dest = new File("C://Users//Msi//eclipse-workspace//TestNG-June//src//test//resources/lookup");
		try {
			FileUtils.copyFile(type, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}



 


















