package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter reporter;
	public ExtentReports erepoter;
	public ExtentTest etest;
	
	String repName;

	public void onTestStart(ITestResult result) {

		
	}

	public void onTestSuccess(ITestResult result) {
		etest=erepoter.createTest(result.getName());
		etest.assignCategory(result.getMethod().getGroups());
		etest.createNode(result.getName());
		etest.log(Status.PASS, "Test Passed");
		
	}

	public void onTestFailure(ITestResult result) {
		etest=erepoter.createTest(result.getName());
		etest.assignCategory(result.getMethod().getGroups());
		etest.createNode(result.getName());
		etest.log(Status.FAIL, "Test failed");
		etest.log(Status.FAIL, result.getThrowable().getMessage());
		
		
	}

	public void onTestSkipped(ITestResult result) {
		etest=erepoter.createTest(result.getName());
		etest.assignCategory(result.getMethod().getGroups());
		etest.createNode(result.getName());
		etest.log(Status.SKIP, "Test skipped");
		etest.log(Status.SKIP, result.getThrowable().getMessage());
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext testContext) {
		String timestamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		 repName="Test-report-"+timestamp+".html";
		 reporter=new ExtentSparkReporter("./reports/"+repName);
		 reporter.config().setDocumentTitle("RestAssuredProject");
		 reporter.config().setReportName(" pet store API");
		 erepoter=new ExtentReports();
		 erepoter.attachReporter(reporter);
		 erepoter.setSystemInfo("Application","Pet Store API");
		 erepoter.setSystemInfo("operating System", System.getProperty("os.name"));
		 erepoter.setSystemInfo("User Name", System.getProperty("user.name"));
		 erepoter.setSystemInfo("Environment", "QA");
		 erepoter.setSystemInfo("user", "harika");
		 


		 
		 reporter.config().setTheme(Theme.DARK);
		 
		 
		 
		 
		
	}

	public void onFinish(ITestContext context) {

		erepoter.flush();
	}

}
