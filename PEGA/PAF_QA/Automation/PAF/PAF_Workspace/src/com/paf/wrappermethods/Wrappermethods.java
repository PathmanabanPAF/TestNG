package com.paf.wrappermethods;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Wrappermethods {
	
	WebDriver driver;
	Excelmethods em;

	int i=0;
	public Wrappermethods(WebDriver driver)
	{
		this.driver=driver;
		this.em=em;
	}
	
	public void launchApp(String url) throws RowsExceededException, WriteException, IOException{
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
			//takesnap();
			System.out.println("Browser launched successfully");
			//em.reportstep("Launch URL", "URL launched", "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Browser launch failed");
			//em.reportstep("Launch URL", "URL not launched", "Failed");
			takesnap();
		}
	}

	public void takesnap() throws IOException
	{
		try {
			File source= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("D:\\Testleaf\\Homework\\src\\Screenshot\\snap"+i+".jpg"));
			i++;
			System.out.println("Snapshot taken");
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to take snapshot");
		}

	}
	
	public void clicklink(String link) throws IOException, RowsExceededException, WriteException
	{
	 try {
		driver.findElement(By.linkText(link)).click();
		System.out.println("Link clicked");
		//em.reportstep("Click Link", "Link clicked", "Success");
		 //takesnap();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Link not clicked");
		//em.reportstep("Click Link", "Link not clicked", "Failed");
		takesnap();
	}
	}
	
	public void clickpartiallink(String link) throws RowsExceededException, WriteException, IOException
	{
     try {
		driver.findElement(By.partialLinkText(link));
		System.out.println("Partail Link clicked");
		//em.reportstep("Click Partial Link", "Partial Link clicked", "Success");
	} catch (NoSuchElementException e) {
		// TODO Auto-generated catch block
		System.out.println("Partial Link not clicked");
		//em.reportstep("Click Partial Link", "Partial Link not clicked", "Failed");
		takesnap();
	}
     catch(WebDriverException e)
     {
    	 e.printStackTrace();
     }
	}
	
	public void clickbyid(String id) throws IOException, RowsExceededException, WriteException
	{
	 try {
		//driver.findElement(By.id(id)).click();
		 WebElement element = driver.findElement(By.id(id));
		 JavascriptExecutor executor = (JavascriptExecutor)driver;
		 executor.executeScript("arguments[0].click();", element);
		 System.out.println("Id Element clicked");
		//em.reportstep("Click Link", "Link clicked", "Success");
		 //takesnap();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Id Element not clicked");
		//em.reportstep("Click Link", "Link not clicked", "Failed");
		takesnap();
	}
	}
	public void setvalueById(String id,String value) throws IOException, RowsExceededException, WriteException
	{
		try {
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(value);
			System.out.println("Value entered by id");
			///takesnap();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Value not entered by id");
			//em.reportstep("Enter value by id", "Value not enetered", "Failed");
			takesnap();
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Test finished");
		}
		
	}
	
	public void setvalueByname(String name,String value) throws IOException, RowsExceededException, WriteException
	{
		try {
			driver.findElement(By.name(name)).clear();
			driver.findElement(By.name(name)).sendKeys(value);
			System.out.println("Value entered by Name");
			//em.reportstep("Enter value by Name", "Value entered", "Success");
			
			//takesnap();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Value not entered by Name");
			//em.reportstep("Enter value by Name", "Value not enetered by name", "Failed");
			takesnap();
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
		}
	
	
	public void selectvaluebytext(String id, String text) throws IOException, RowsExceededException, WriteException
	{
		try {
			WebElement ele1=driver.findElement(By.id(id));
			Select d1=new Select(ele1);
			d1.selectByVisibleText(text);
			System.out.println("Select value by dropdown");
			//em.reportstep("Select value by dropdown", "Value Selected", "Success");
			//takesnap();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Value not selected by dropdown");
			//em.reportstep("Select value by dropdown", "Value not selected", "Failed");
			takesnap();
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
	}
	
	public void clickByXpath(String text) throws IOException, RowsExceededException, WriteException
	{
		try {
			//driver.findElement(By.xpath(text)).click();
			
			 WebElement element = driver.findElement(By.xpath(text));
			 JavascriptExecutor executor = (JavascriptExecutor)driver;
			 executor.executeScript("arguments[0].click();", element);
			System.out.println("Click by Xpath location");
			//em.reportstep("Click by Xpath location", "Xpath location clicked", "Success");
			//takesnap();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Click by Xpath location failed");
			//em.reportstep("Click by Xpath location", "Xpath location not clicked", "Failed");
			takesnap();
		}
	}
	
	//Method for accepting alert by ID
		public void AcceptAlert(String ID) throws IOException, RowsExceededException, WriteException{
		try {
			driver.findElement(By.id(ID)).click();
			takesnap();
			i++;
			Alert a=driver.switchTo().alert();
			System.out.println(a.getText());
			a.accept();
			System.out.println("Accepted Alert");
			em.reportstep("Accept Alert", "Alert accepted", "Success");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			System.out.println("Alert not accepted");
			em.reportstep("Accept alert", "Alert failed", "Failed");
			takesnap();
		}
		catch(WebDriverException e)
		{
		 e.printStackTrace();
		}
		}

		//Method for dismissing alert by ID
		public void DismissAlert(String ID) throws IOException, RowsExceededException, WriteException{
		try {
			driver.findElement(By.id(ID)).click();
			takesnap();
			i++;
			Alert a=driver.switchTo().alert();
			System.out.println(a.getText());
			a.dismiss();
			System.out.println("Accepted Dismiss");
			em.reportstep("Accept Dismiss", "Alert dismissed", "Success");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			System.out.println("Alert not dismissed");
			em.reportstep("Dismiss alert", "Alert dismissed", "Failed");
			takesnap();
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
		}

		//Method for verifying a text by Xpath
		public void VerifyText(String Xpath,String ExpectedText) throws IOException, RowsExceededException, WriteException{
		try {
			String ActualText=driver.findElement(By.xpath(Xpath)).getText();
			if(ExpectedText.equals(ActualText))
			{
			System.out.println("Expected text : "+ExpectedText+
			"\n matches with the \n Actual Text : "+ActualText);
			System.out.println("***********************");
			}
			else
			{
				System.out.println("Text not matches");
			}
			System.out.println("Verify text");
			em.reportstep("Verify text", "Verify text passed", "Success");
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			//System.out.println("Element not found");
			System.out.println("Verify text failed");
			em.reportstep("Verify text", "Verify text failed", "Failed");
			takesnap();
		}
		}
		
		//Method for verifying Title of the page 
		public void VerifyTitle(String ExpectedTitle) throws RowsExceededException, WriteException, IOException{
		try {
			String ActualTitle=driver.getTitle();
			if(ExpectedTitle.equals(ActualTitle))
			{
			System.out.println("Expected Title : " +ExpectedTitle+
					"\n matches with the \n Actual Title :  "+ActualTitle);
			
			System.out.println("******************");
			}
			else
			{
				System.out.println("Title not matches");
			}
			
			System.out.println("Verify title");
			em.reportstep("Verify title", "Verify title passed", "Success");
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			//System.out.println("Element not found");
			System.out.println("Verify title failed");
			em.reportstep("Verify title", "Verify title failed", "Failed");
			takesnap();
		}
		}

	
	public void closeApp(){
		driver.quit();
	}
	



}
