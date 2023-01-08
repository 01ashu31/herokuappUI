package com.herokuappUI;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.herokuappUI.datahelpers.PropertiesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class AppTest {
	
	WebDriver driver;
	Properties properties;
	@BeforeMethod
	public void homePage() throws Exception {
		properties= new PropertiesReader().readPropertyFile();
		driver= WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.get(properties.getProperty("url"));
		driver.findElement(By.cssSelector("#btn-make-appointment")).click();
		Thread.sleep(2000);
	}
	@Test (priority = 0)
	public void loginPage() throws InterruptedException {
		driver.findElement(By.cssSelector("#txt-username")).sendKeys(properties.getProperty("user-name"));
		driver.findElement(By.cssSelector("#txt-password")).sendKeys(properties.getProperty("user-pass"));
		driver.findElement(By.cssSelector("#btn-login")).click();
		Thread.sleep(3000);
		Select dropdown= new Select(driver.findElement(By.id("combo_facility")));
		dropdown.selectByValue("Hongkong CURA Healthcare Center");
		driver.findElement(By.cssSelector("#chk_hospotal_readmission")).click();
		driver.findElement(By.id("radio_program_medicaid")).click();
		String visitdate= new java.util.Date().toString();
		driver.findElement(By.id("txt_visit_date")).sendKeys(visitdate);
		driver.findElement(By.id("txt_comment")).sendKeys("This is emergency and need to book appointment");
		Thread.sleep(2000);
		driver.findElement(By.id("btn-book-appointment")).click();
		Thread.sleep(2000);
//		String appnt=driver.findElement(By.linkText("Appointment Confirmation")).toString();
//		Assert.assertEquals(appnt, "Appointment Confirmation");
		
	}
	
	public void quitDriver() {
		driver.quit();
	}
    
}
