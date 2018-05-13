package com.paf.scripts;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
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
@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
    MethodListener.class })
public class TC1_Gmail_login {
	
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
	String Testcasesheet1="Globalpath";
	String Testcasesheetname1=ReadpropertyFile(Testcasesheet1);
	temp.globalpath=Testcasesheetname1;
	System.out.println("File path:"+Testcasesheetname1);
	System.out.println("Gloabal path:"+	temp.globalpath);
	//Configuration ATU Reports
	System.setProperty("atu.reporter.config",temp.globalpath+"\\atu.properties");
	

	String Testcasesheet="Gmail_Testing";
	String Testcasesheetname=ReadpropertyFile(Testcasesheet);
	System.out.println("File path:"+Testcasesheetname);
	//Checking Execute test case Yes/No
	Workbook w10=Workbook.getWorkbook(new File(Testcasesheetname));
	Sheet s10=w10.getSheet(0);
	Cell cell=s10.getCell(12,2);
	String execution_status=cell.getContents();
	System.out.println("Flag status"+execution_status);

	if(execution_status.contentEquals("false"))
	{
		   throw new SkipException("Skipping this test case as it is selected as FALSE");
	}
	
	String Datasheetpath="Datasheetpath";
	String datasheetname=ReadpropertyFile(Datasheetpath);
	System.out.println("File path:"+datasheetname);
	//Checking Execute test case Yes/No
	Workbook w=Workbook.getWorkbook(new File(datasheetname));
	/*Sheet s=w.getSheet(2);
	Cell cell=s.getCell(3,1);
	String execution_status=cell.getContents();
	System.out.println("Flag status"+execution_status);

	if(execution_status.contentEquals("false"))
	{
		   throw new SkipException("Skipping this test case as it is selected as FALSE");
	}*/

	 //Getting Environement and Browser details
	String Datasheetpath1="Datasheetpath";
	String datasheetname1=ReadpropertyFile(Datasheetpath1);
	System.out.println("Datasheetname : "+datasheetname1);
	
	Workbook w1=Workbook.getWorkbook(new File(datasheetname));
	Sheet s1=w1.getSheet(0);
	Cell cell1=s1.getCell(1,3);
	String env=cell1.getContents();
	Cell cell21=s1.getCell(1,4);
	String browsername=cell21.getContents();
	
	System.out.println("Envrionement: "+env);
	String environment=env.substring(0, 1);
	System.out.println("Environement:"+environment);
	int env1=Integer.parseInt(environment);
	
	String browserindex=browsername.substring(0, 1);
	int bindex=Integer.parseInt(browserindex);
	System.out.println("Environement:"+browserindex);
	
	WebDriver driver = null;
	String financeurl = null;
	String url = null;
	
	switch(bindex)
	{
	case 1:
		//Firefox
		driver= new FirefoxDriver();
		System.out.println("User Seelcted browser is : Firefox");
		break;
	case 2:
		try {
			String chromedriver="Chromedriver";
			String chromedriverpath=ReadpropertyFile(chromedriver);
			System.out.println("Chrome driver path : "+chromedriverpath);
			System.setProperty("webdriver.chrome.driver",chromedriverpath);
			driver=new ChromeDriver();
			System.out.println("User Seelcted browser is : Chrome");
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem in Chrome browser launch");
		}
		break;
		
	case 3:
		try {
			String IEDriver="IEdriver";
			String IEdriverpath=ReadpropertyFile(IEDriver);
			System.out.println("IE driver path : "+IEdriverpath);
			System.setProperty("webdriver.ie.driver", IEdriverpath);
			driver=new InternetExplorerDriver();
			System.out.println("User Seelcted browser is : Internet Explorer");
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem in IE browser launch");
		}
		
		
		break;
	default:
		break;
	}
	
	//temp.env=env;
	
	
	switch(env1)
	{
	case 1:
		try {
			System.out.println("Selected Environment is QA ");
			Sheet s2=w.getSheet(1);
			Cell cell11=s2.getCell(1,2);
			url=cell11.getContents();
			Cell cell2=s2.getCell(2,2);
			financeurl=cell2.getContents();
			Cell cell3=s2.getCell(5,2);
			temp.cpr_dsn=cell3.getContents();
			System.out.println("QADSN"+temp.cpr_dsn);
			
			
			System.out.println("QA Environment configured");
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem in QA Environment intiation");
		}
		
		
		break;
	
	case 2:
		try {
			System.out.println("Selected Environment is UAT ");
			Sheet s2=w.getSheet(1);
			Cell cell11=s2.getCell(1,3);
			url=cell11.getContents();
			Cell cell2=s2.getCell(2,3);
			financeurl=cell2.getContents();
			Cell cell3=s2.getCell(5,2);
			temp.cpr_dsn=cell3.getContents();
			System.out.println("UATDSN"+temp.cpr_dsn);
			
			System.out.println("QA Environment configured");
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem in UAT Environment intiation");
		}
		
		
		break;
	case 3:
		try {
			System.out.println("Looop3 ");
			Sheet s2=w.getSheet(1);
			Cell cell11=s2.getCell(1,1);
			url=cell11.getContents();
			Cell cell2=s2.getCell(2,1);
			financeurl=cell2.getContents();
			
			System.out.println("POC environment is intiated");
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem in POC Environment intiation");
		}
		
		
		break;
	default:
		break;
	}
	
   		 test_one_loginlls(driver,url,financeurl);
   		 //sendsms_cpr(driver,"Servername");
   		 sendemail();
		 //patientfind_start(driver);
		 //searchbyid(driver);
	     close_driver(driver);

}

public void test_one_loginlls(WebDriver driver, String url, String financeurl) throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException
{
	String Testcasesheet="Gmail_Testing";
	String Testcasesheetname=ReadpropertyFile(Testcasesheet);
	System.out.println("File path:"+Testcasesheetname);
try {
	
	 //Getting Object properties
	File src=new File(temp.globalpath+"/Object_CPR.properties");
	FileInputStream fis=new FileInputStream(src);
	Properties pro=new Properties();
	pro.load(fis);
			
	//Getting username and password for Login
	//Checking Execute test case Yes/No
	Workbook w10=Workbook.getWorkbook(new File(Testcasesheetname));
	Sheet s10=w10.getSheet(0);
	Cell cell=s10.getCell(7,2);
	String username=cell.getContents();
	Cell cell1=s10.getCell(7,3);
	String password=cell1.getContents();
	System.out.println("Username"+username);
	System.out.println("passwd"+password);
	
	
	ATUReports.currentRunDescription = "Patient 360 - Testing Search by Patient Idt";
	ATUReports.setWebDriver(driver);
	ATUReports.indexPageDescription = "PAF Project";
	System.out.println("URL : "+url);
	Wrappermethods wM = new Wrappermethods(driver);
	System.out.println("URL: "+url);
	wM.launchApp(url);

	ATUReports.currentRunDescription = "URL Launched successfully";

	//driver.findElement(By.id(pro.getProperty("Emailid"))).sendKeys(username);
	driver.findElement(By.id("email")).sendKeys("pathmanaban.govindhan1984@gmail.com");
	Thread.sleep(2000);
	//driver.findElement(By.id(pro.getProperty("next"))).click();
	//Thread.sleep(2000);
	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(30, TimeUnit.SECONDS)
            .pollingEvery(5, TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class);
	wait.until(ExpectedConditions.elementToBeClickable(By.name("j_password")));
	driver.findElement(By.name("j_password")).sendKeys(password);
	Thread.sleep(2000);
	//signIn
	driver.findElement(By.xpath(pro.getProperty("signIn"))).click();
	Thread.sleep(6000);
	//driver.findElement(By.xpath("//md-menu-item[8]/button")).click();
	//Thread.sleep(4000);
	//driver.findElement(By.xpath("//button[@type='button'])[7]")).click();
	//Thread.sleep(4000);
	String dbresult=VerifyuserinDB();
	System.out.println("DB Result:"+dbresult);
	
	ATUReports.add("Login to Gmail Portal", username+" "+password, "Login with given credentials", "Logged in successfully",true);
	Thread.sleep(4000);
	
	 WritableWorkbook rwb1;
	 Workbook wb1;
	 // Sheet s;
	 WritableSheet rws1;
	 wb1=Workbook.getWorkbook(new File(Testcasesheetname));
	 rwb1=Workbook.createWorkbook(new File(Testcasesheetname), wb1);
	 rws1=rwb1.getSheet(0);
	 
	 WritableFont writablelabel = new WritableFont(WritableFont.TAHOMA, 10,WritableFont.BOLD);
	 WritableCellFormat labelfont = new WritableCellFormat(writablelabel);
	 labelfont.setBackground(Colour.GREEN);
	 Label L11=new Label(11,2,"PASS",labelfont);
	 Label L12=new Label(10,2,"Logged in successfully");
	 
	 rws1.addCell(L11);
	 rws1.addCell(L12);

	 rwb1.write();
	 rwb1.close();
	 ATUReports.add("Uploaded document verified successfully","","",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	 
} catch (Exception e) {
    e.printStackTrace();
	System.out.println("Problem in Gmail login");
	 //Taking screenshot
	 String no=new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 temp.image1="TC_1_Gmail_"+no;
	 FileUtils.copyFile(scrFile, new File(temp.globalpath+"\\Screenshots\\TC_1_Gmail_"+no+".jpg"));
	 System.out.println("Global path: "+temp.globalpath); 
	
	 WritableWorkbook rwb1;
	 Workbook wb1;
	// Sheet s;
	 WritableSheet rws1;
	 wb1=Workbook.getWorkbook(new File(Testcasesheetname));
	 rwb1=Workbook.createWorkbook(new File(Testcasesheetname), wb1);
	 rws1=rwb1.getSheet(0);
	 
	 WritableFont writablelabel = new WritableFont(WritableFont.TAHOMA, 10,WritableFont.BOLD);
	 WritableCellFormat labelfont = new WritableCellFormat(writablelabel);
	 labelfont.setBackground(Colour.RED);
	 Label L11=new Label(11,2,"FAIL",labelfont);
	 Label L12=new Label(10,2,"Problem in Login");

	 
	 rws1.addCell(L11);
	 rws1.addCell(L12);

	 rwb1.write();
	 rwb1.close();
	
	 int rowno =2;
	 int colnno =13;
	 write_screenshot_jxl(Testcasesheetname,rowno,colnno,temp.image1);
	 driver.close();
	 ATUReports.add("Problemn in verifying uploaded document","","",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	ATUReports.add("Problem in Gmail Login","","", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	
}


}


public String VerifyuserinDB()
{
	 String l1="";
	 //String l2="";
	try {
		
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/test","root","root");  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from employee");  
		while(rs.next())  
		{
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		l1=rs.getString(2);
		}
		con.close();  

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Problem in Database Handling");
	}
	return l1;	
}

public void close_driver(WebDriver driver) throws InterruptedException, RowsExceededException, WriteException, IOException
{	try {
	Wrappermethods wM = new Wrappermethods(driver);
	wM.closeApp();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	System.out.println("Problem in LLS Logout");
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
		if(datasheetname.contentEquals("Gmail_Testing"))
		{
		 filepath=prop.getProperty("Gmail_Testing");
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




public void write_screenshot(String Testcasesheetname, int rowno, int colno, String image) throws IOException
{
	try {
		
		String Testcasesheet1="Screenshotpath";
		String screenshotpath=ReadpropertyFile(Testcasesheet1);
		System.out.println("image name : "+image);
		FileInputStream fsIP= new FileInputStream(new File(Testcasesheetname)); //Read the spreadsheet that needs to be updated
		
		HSSFWorkbook wb = new HSSFWorkbook(fsIP); //Access the workbook
		  
		HSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
		  
		HSSFCell cell = null; // declare a Cell object
		
		//cell = (HSSFCell) worksheet.getRow(2).getCell(2);   // Access the second cell in second row to update the value
		  
		//((HSSFCell) cell).setCellValue("View Screenshot name "+image+".jpg");  // Get current cell value value and overwrite the value
		
		HSSFHyperlink file_link=new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
		//file_link.setAddress("file:///J://Screenshots//"+image+".png");
		   file_link.setAddress(screenshotpath+"//Screenshots/");
		cell = (HSSFCell) worksheet.getRow(rowno).getCell(colno);   // Access the second cell in second row to update the value
		((HSSFCell) cell).setCellValue("View Screenshot name "+image+".jpg");  // Get current cell value value and overwrite the value
		((HSSFCell) cell).setHyperlink(file_link);
		
		fsIP.close(); //Close the InputStream
		 
		FileOutputStream output_file =new FileOutputStream(new File(Testcasesheetname));  //Open FileOutputStream to write updates
		  
		wb.write(output_file); //write changes
		  
		output_file.close();  //close the stream    
		
		System.out.println("Screenshot added successfully............");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Problem in handling appending heyperlink Excel");
		
	}
    
}

public void write_screenshot_jxl(String Testcasesheetname, int rowno, int colno, String image) throws IOException
{
	try {
		
		String Testcasesheet1="Screenshotpath";
		String screenshotpath=ReadpropertyFile(Testcasesheet1);
		System.out.println("screenshot payth : "+screenshotpath);
		System.out.println("image name : "+image);
		
		FileInputStream fsIP= new FileInputStream(new File(Testcasesheetname)); //Read the spreadsheet that needs to be updated
		 WritableWorkbook rwb1;
		 Workbook wb1;
		// Sheet s;
		 WritableSheet rws1;
		 wb1=Workbook.getWorkbook(new File(Testcasesheetname));
		 rwb1=Workbook.createWorkbook(new File(Testcasesheetname), wb1);
		 rws1=rwb1.getSheet(0);
	
		  URL  url = new URL(screenshotpath+"//Screenshots/");
		 
	      WritableHyperlink wh = new WritableHyperlink(colno,rowno,url);
	      rws1.addHyperlink(wh);
	      WritableFont redFont = new WritableFont(WritableFont.ARIAL);
	      redFont.setColour(Colour.RED);
	      WritableCellFormat cellFormat = new WritableCellFormat(redFont);
	      Label label = new Label(colno, rowno, "Click to view screenshot: "+image, cellFormat);
	      rws1.addCell(label);
		
		 rwb1.write();
		 rwb1.close();
		
		System.out.println("Screenshot added successfully............");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Problem in handling appending heyperlink Excel");
		
	}
    
}



public void SendMail(String FileName, String email_client)
{
	 System.out.println("Email:"+email_client);
	 String mindtree_ID = "pathmanaban.govindhan@gmail.com";
           //String mindtree_ID = "ProdQA-MT@tmp.com";
	// String Tmp_ID = "Yankanna_Machakanur@mindtree.com";
	 String from = "pathmanaban.govindhan@gmail.com";
	 String host = "smtp.gmail.com";
	 Properties properties = System.getProperties();
	 properties.setProperty("mail.smtp.host", host);
	 
	 Session session = Session.getDefaultInstance(properties);
	 
	try{
		 // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

    
        // Set Subject: header field
        message.setSubject("Patient360 Testing Report in Production");
     // Set To: header field of the header.
		   message.addRecipient(Message.RecipientType.TO,
									new InternetAddress(mindtree_ID));
		  message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("pathmanaban.govindhan@gmail.com"));
		  //message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email_client));
		  
        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Please find attachment for Patient360 testing report in Production");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = FileName;
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName("Environment Test Report.xls");
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);

        // Send message
        Transport.send(message);

		  System.out.println("Sent message successfully....");
		}
	catch (MessagingException mex) {
		   mex.printStackTrace();
		}
}



public void sendsms_cpr(WebDriver driver, String servername) throws InterruptedException
{
	try {
		
	    String mobile_nw="T-Mobile";
		driver.get("http://www.mobile-sender.com/");
		Thread.sleep(3000);
		driver.findElement(By.id("number")).sendKeys("2018877036");
		driver.findElement(By.name("subject_txt")).sendKeys(servername+" Down");
		
		Thread.sleep(4000);
		WebElement utype3= driver.findElement(By.id("provider"));
		Select d4=new Select(utype3);
		d4.selectByVisibleText(mobile_nw);
		Thread.sleep(4000);
		driver.findElement(By.name("textmessage")).sendKeys(servername+" down...");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//tr[5]/td[2]/input")).click();
		Thread.sleep(10000);
		driver.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		driver.close();
		e.printStackTrace();
		System.out.println("Failing sms send to CPR");
	}
}


public  void sendemail() {
	  try{

          Properties props = new Properties();
          props.put("mail.smtp.host", "smtp-mail.outlook.com"); // for gmail use smtp.gmail.com
          props.put("mail.smtp.ssl.trust", "smtp-mail.outlook.com");
          props.put("mail.smtp.auth", "true");
          props.put("mail.debug", "true"); 
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.port", "587");
      

          Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication("pathmanaban.govindhan@outlook.com", "Kishan@2");
              }
          });

          mailSession.setDebug(true); // Enable the debug mode

          Message msg = new MimeMessage( mailSession );

          //--[ Set the FROM, TO, DATE and SUBJECT fields
          msg.setFrom( new InternetAddress( "pathmanaban.govindhan@outlook.com" ) );
          msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse("pathmanaban.govindhan@outlook.com") );
          msg.setSentDate( new Date());
          msg.setSubject( "Hello World!" );

          //--[ Create the body of the mail
          msg.setText( "Hello!!!" );
          
          BodyPart messageBodyPart = new MimeBodyPart();
          // Now set the actual message
          messageBodyPart.setText("Please find attachment for Application process test results");

          //Create a multipar message
          Multipart multipart = new MimeMultipart();

          // Set text message part
          multipart.addBodyPart(messageBodyPart);

          // Part two is attachment
          messageBodyPart = new MimeBodyPart();
          String filename = "F:\\PEGA\\PAF_QA\\Automation\\PAF\\PAF_Workspace\\Parameters\\Gmail_Testing.xls";
          
          DataSource source = new FileDataSource(filename);
          messageBodyPart.setDataHandler(new DataHandler(source));
          messageBodyPart.setFileName("Gmailtesting.xls");
          multipart.addBodyPart(messageBodyPart);

          // Send the complete message parts
          msg.setContent(multipart);
          //--[ Ask the Transport class to send our mail message
          Transport.send( msg );

      }catch(Exception E){
          System.out.println( "Oops something has gone pearshaped!");
          System.out.println( E );
      }
}}

