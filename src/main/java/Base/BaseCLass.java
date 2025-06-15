package Base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.Parameter;

import Utils.Constants;

public class BaseCLass {
    public static WebDriver d;
    public ExtentReports reporter;
    public ExtentSparkReporter sparkReporter;
    public ExtentTest extentTest;
	
	@BeforeTest
	public void beforeTest() {
		reporter = new ExtentReports();
		sparkReporter =  new ExtentSparkReporter(new File(System.getProperty("user.dir")+"\\reports\\index.html"));
		reporter.attachReporter(sparkReporter);
		sparkReporter.config().setDocumentTitle("Regression Automation Reports");
		sparkReporter.config().setReportName("Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		reporter.setSystemInfo("Tester", "Jagapathi");
		reporter.setSystemInfo("Os", System.getProperty("os.version"));
		
	}
	@Parameters("broswer")
	@BeforeMethod
	public void beforeMethod(ITestContext context, @Optional("chrome") String browser)
	{
		getBrowser(browser);
		d.manage().window().maximize();
		d.get(Constants.url);
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		extentTest= reporter.createTest("Test");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException, InterruptedException

	{ 
		Thread.sleep(2000);
		if(result.getStatus()==ITestResult.FAILURE)
		{   String path =captureScreenShots();
			extentTest.addScreenCaptureFromPath(path)
			.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		
		else if (result.getStatus()==ITestResult.SUCCESS)
		{
			String path =captureScreenShots();
			extentTest.addScreenCaptureFromPath(path)
			.pass("Passes test case", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		
		
		d.quit();
		
		
	}
	
	@AfterTest
	public void afterTest()
	{
		
		reporter.flush();
		try {
			Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"\\reports\\index.html").toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public String captureScreenShots() throws IOException
	
	{
		TakesScreenshot screenshot= (TakesScreenshot)d;
		File f =screenshot.getScreenshotAs(OutputType.FILE);
		String path =System.getProperty("user.dir")+"\\screenshots\\screenshot.png";
		FileUtils.copyFile(f, new File(path));
		return path;
	}
	
	
	
	
	
	
	
	
	
	public void getBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			d = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			d = new EdgeDriver();
		}
	}
}
