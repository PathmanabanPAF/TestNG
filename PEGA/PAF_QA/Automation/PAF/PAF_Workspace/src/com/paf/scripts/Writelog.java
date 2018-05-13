package com.paf.scripts;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.paf.wrappermethods.*;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;


import com.paf.wrappermethods.*;

public class Writelog {
	
	private static final String String = null;
	private Object subWindowHandler;


	/**
	 * @param args
	 * @throws Exception 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
@SuppressWarnings("null")



@Test()
public void test() throws IOException, RowsExceededException, WriteException, InterruptedException, BiffException
{
	String Testcasesheet1="configpath";
	String Testcasesheetname1=ReadpropertyFile(Testcasesheet1);
	
	
	String no=new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime());
	String no1=new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	   //get current date time with Date()
	   Date date = new Date();
	   System.out.println(dateFormat.format(date));
	
	File sourceFile = new File(
			Testcasesheetname1+"/Parameters/Gmail_Testing.xls");

	File destFile = new File(
			Testcasesheetname1+"/Test Reports/Gmail_Testing_"+no1+" "+no+".xls");

	/* verify whether file exist in source location */
	if (!sourceFile.exists()) {
		System.out.println("Source File Not Found!");
	}

	/* if file not exist then create one */
	if (!destFile.exists()) {
		try {
			destFile.createNewFile();
			
			System.out.println("Destination file doesn't exist. Creating one!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	FileChannel source = null;
	FileChannel destination = null;

	try {

		/**
		 * getChannel() returns unique FileChannel object associated a file
		 * output stream.
		 */
		source = new FileInputStream(sourceFile).getChannel();

		destination = new FileOutputStream(destFile).getChannel();

		if (destination != null && source != null) {
			destination.transferFrom(source, 0, source.size());
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}

	finally {
		if (source != null) {
			try {
				source.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (destination != null) {
			try {
				destination.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}


public static String ReadpropertyFile(String datasheetname)
{	
	  File file = new File("F:/PEGA/PAF_QA/Automation/PAF/PAF_Workspace/Config.properties");
	  System.out.println("Started");
	  
	  FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String filepath = null;
		//return prop.getProperty("URL");
		if(datasheetname.contentEquals("Datasheetpath"))
		{
		 filepath=prop.getProperty("Datasheetpath");
		}
		else
		if(datasheetname.contentEquals("Chromedriver"))
		{
			filepath=prop.getProperty("Chromedriver");
		}
		else
		if(datasheetname.contentEquals("IEdriver"))
		{
			filepath=prop.getProperty("IEdriver");
		}
		else
		if(datasheetname.contentEquals("Patient_Intents"))
		{
		 filepath=prop.getProperty("Patient_Intents");
		}
		else
		if(datasheetname.contentEquals("Globalpath"))
		{
		 filepath=prop.getProperty("Globalpath");
		}
		else
		if(datasheetname.contentEquals("Screenshotpath"))
		{
		 filepath=prop.getProperty("Screenshotpath");
		}
		else
		if(datasheetname.contentEquals("configpath"))
		{
		 filepath=prop.getProperty("configpath");
		}
		return filepath;		
		
}


}

