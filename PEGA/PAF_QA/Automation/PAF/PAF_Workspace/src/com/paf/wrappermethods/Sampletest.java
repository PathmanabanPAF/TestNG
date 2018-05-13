package com.paf.wrappermethods;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.paf.wrappermethods.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.paf.wrappermethods.*;

public class Sampletest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */

@Test()
public void test() throws IOException, RowsExceededException, WriteException
{
		// TODO Auto-generated method stub

		WebDriver driver = new FirefoxDriver();
	  //  driver.manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		Wrappermethods wM = new Wrappermethods(driver);

		wM.launchApp("http://api.checklist.com/register");
		
		wM.setvalueById("email", "pathmanaban.g@rediffmail.com");
		wM.setvalueById("password", "test1234");
		wM.setvalueById("name", "Pathmanaban");
		wM.selectvaluebytext("country", "India");
		wM.clickByXpath("/html/body/div/div[2]/div[1]/form/div[5]/button");
		
		
		wM.closeApp();
		
		
	
		
}

}

