package BasePageFactory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import UtilsPageFactory.AbstractMethods;

public class BaseClassPageFactory {
	
	public static WebDriver driver;
	public ExtentReports extentReport;
	public ExtentSparkReporter sparkReporter;
	public static  ExtentTest extenTest;
      public static ThreadLocal<WebDriver> tdriver= new ThreadLocal<WebDriver>();
    
      public String datePath;
	 
	 public static WebDriver getDriver()
	 {
		return tdriver.get();
	 }

	 public static void setDriver(WebDriver driver)
	 {
		 tdriver.set(driver);
	 }
	public static void closethread()
	{
		tdriver.remove();
	}
	
	  public static ThreadLocal<ExtentTest> tTest= new ThreadLocal<ExtentTest>();
			public static ExtentTest getTest()
			{
				return tTest.get();
			}
			public void setTest(ExtentTest test)
			{
				tTest.set(test);
			}
			public static void closeTestthread()
			{
				tTest.remove();
			}
		
	
    
	
	@BeforeTest
	public void beforeTest()
	{
		extentReport = new ExtentReports();
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports.pagefactory.html");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Test Automation Report");
		extentReport.attachReporter(sparkReporter);
	}

	
	@AfterTest
	public void afterTest()
	{
		
		
		extentReport.flush();
		try {
			Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"\\reports.pagefactory.html").toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Parameters("Browser")
	@BeforeMethod
	public void beforeMethod(String browser,Method m)
	{
		//driver = setUpBrowser(browser);
		setUpBrowser(browser);
		getDriver().manage().window().maximize();

		getDriver().get(getUrl());
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// test report
		extenTest = extentReport.createTest(m.getName());
		setTest(extenTest);
		getTest().assignAuthor("Jagapathi");
		getTest().assignCategory("Regression");
		getTest().assignDevice("Chrome");
	}
	
	public String getUrl()
	{
		String url ="https://rahulshettyacademy.com/client";
		return url;
	}
	
	

	public void setUpBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
			setDriver(driver);
			
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
			setDriver(driver);
			
		}
		//return driver;
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			getTest().info("Test passed");
		}
		else if (result.getStatus()==ITestResult.FAILURE)
		{
			getTest().fail("Test Failed");
		}
		else if (result.getStatus()==ITestResult.SKIP)
		{
			getTest().skip("Test skipped");
		}
		
		Thread.sleep(2000);
		getDriver().quit();
		closethread();
		
	}
	
	public String captureScreenShot(String name)
	{
		
		if(datePath == null)
		{
			LocalDateTime myDateObj = LocalDateTime.now();
		    //System.out.println("Before formatting: " + myDateObj);
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");

		    datePath = myDateObj.format(myFormatObj);
		}
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\screenshot1\\"+datePath+name+"\\screenshot.png";
		try {
			FileUtils.copyFile(src,new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
}
