package UtilsPageFactory;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import BasePageFactory.BaseClassPageFactory;

public class ListenersPageFactory extends BaseClassPageFactory implements ITestListener {
	
	
	
	
	

	@Override
		public void onStart(ITestContext context) {
		
		}
		@Override
		public void onFinish(ITestContext context) {
			
		}
	@Override
	public void onTestFailure(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String path =null;
			try {
				path = captureScreenShot(result.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getTest().addScreenCaptureFromPath(path)
			.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
		}
	}
	
	

}
