package com.paf.scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.paf.wrappermethods.Wrappermethods;

public class Demo {
	
	@Test()
	public void demo_toolsqa() throws InterruptedException, IOException
	{
		//Setspeed(6000);
		WebDriver driver;
		driver=new FirefoxDriver();
		
		//Loading Webpage
		driver.get("http://demoqa.com/");
		
		driver.findElement(By.linkText("Registration")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("name_3_firstname")).sendKeys("test");
		Thread.sleep(2000);
		driver.findElement(By.id("name_3_lastname")).sendKeys("test");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[2]/div/div/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[3]/div/div/input")).click();
		Thread.sleep(2000);
		Select day = new Select(driver.findElement(By.id("mm_date_8")));
		day.selectByVisibleText("1");
		Thread.sleep(2000);
		Select month = new Select(driver.findElement(By.id("dd_date_8")));
		month.selectByVisibleText("1");
		Thread.sleep(2000);
		Select year = new Select(driver.findElement(By.id("yy_date_8")));
		year.selectByVisibleText("2014");
		
		
		//File Uploading
		
		driver.findElement(By.id("profile_pic_10")).click();
		Thread.sleep(4000);
		Runtime.getRuntime().exec("F:\\PEGA\\PAF_QA\\Automation\\PAF\\PAF_Workspace\\PAF_Library\\fileupload.exe");
		Thread.sleep(6000);
		
		driver.findElement(By.id("phone_9")).sendKeys("2018877036");
		Thread.sleep(2000);
		driver.findElement(By.id("username")).sendKeys("pathu");
		Thread.sleep(2000);
		driver.findElement(By.id("email_1")).sendKeys("pathmanaban.govindhan@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("password_2")).sendKeys("Kishan@2");
		Thread.sleep(2000);
		driver.findElement(By.id("confirm_password_password_2")).sendKeys("Kishan@2");
		Thread.sleep(2000);
		driver.findElement(By.name("pie_submit")).click();
		
	
		driver.close();
	
		
	}



}
