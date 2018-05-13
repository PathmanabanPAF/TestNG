package com.paf.utils;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class HTMLREport {
	public static int failStep;
	public static String excelLog, htmlLog;//, indexReport="";
	public static String clientHTMLFileLocation;
	public static String testFailed;
	
	public static boolean isValidEmailAddress(final String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static void logFailure(WebDriver driver, String stepDescription){
		testFailed = "Failed";
		excelLog=excelLog + (++failStep) + " . " + stepDescription+"\n";
		//HTMLREport.appendFail(stepDescription + "<br>Landed Page Title is : " + driver.getTitle() ,takeScreenshot(driver));
	}
	

	
	/*public static String takeScreenshot(WebDriver driver) {
		String screenShotName = new SimpleDateFormat("_yy-MMM-dd-HH-mm-ss").format(Calendar.getInstance().getTime());
				
		File screenShotFolder = driver.getScreenShotFolder();
		if (driver instanceof TakesScreenshot) {
			File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try{
				FileUtils.copyFile(tempFile, new File(screenShotFolder+"/" + screenShotName + ".jpg"));
			}catch (IOException e) {
				// TODO handle exception
			}
		}
		return screenShotFolder + "/" + screenShotName + ".jpg";
	}
	
	public static void writeToLog(String clientName){
		htmlLog=htmlLog+"</ul></font>";
		htmlLog = htmlLog +"</body></html>";
		File clientLogHTMLFolder = driver.getClientLogHTMLFolder();
		
		try{
	        clientHTMLFileLocation = clientLogHTMLFolder.getName();
	        String clientHTMLFile = clientLogHTMLFolder + "//"+clientName.replaceAll("/", ".")+".html";
	        File file = new File(clientHTMLFile);
	        FileUtils.writeStringToFile(file, htmlLog, true);
	        //indexReport=indexReport+"<a href=\"file:///" + clientHTMLFile + "\">"+clientName+"</a><br>";
	     }catch(IOException e){
	    	System.out.println("File creation error : " + e.getMessage());
		}finally{
			if(HTMLREport.testFailed.contains("Fail")){
				Reporter.log("Failed for client : " + clientName);
				Assert.fail("Some of test steps are failed - please look into html log");
			}
		}
		
	}*/
	
	public static String parseString(String string){
		System.out.println("Location String received: " + string);
		if(string.contains(",")){
			String[] stringArray = string.split(",");
			string = stringArray[0].trim();
		}
		
		if(string.contains(" "))
			string = string.replaceAll(" ", "-");
		
		if(string.contains("\\"))
			string = string.replaceAll("\\", "_");
		
		return string.trim();
	}

	
	public static void appendClientHeader(String clientName){
		htmlLog = "<html><body><font color=gold><h1>"+clientName + "</font></h1>";//<br>";
	}
	
	public static void appendTestCaseHeader(String testCaseHeader){
		htmlLog = htmlLog + "<font color=blue><H3>"+testCaseHeader+"</H3></font><font color=maroon><ul>";
	}
	public static void appendTestCaseSubTitle(String testCaseHeaderSubTitle){
		htmlLog = htmlLog + "<font color=maroon><H3>"+testCaseHeaderSubTitle+"</H3></font>";
	}
	public static void appendPass(String stepDescription){
		//testFailed = "Passed";
		failStep++;
		htmlLog = htmlLog + "<br><font color=Green>" + failStep + ". " + stepDescription+ "<font color=red>" + "</font>";
	}
	
	public static void appendFail(String stepDescription, String screenShot){
		htmlLog = htmlLog + "<br><font color=red>" + failStep + ". " +stepDescription + "- <a href=" + screenShot + "> Click Here For Screen Shot </a></font>";
	}
	
}

