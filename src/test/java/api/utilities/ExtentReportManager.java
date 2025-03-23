package api.utilities;

//Extent report 5.x

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


public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter; //UI of the Report
	public ExtentReports extent;   //populate common information on the Report
	public ExtentTest test;   // creating test case entries in the report and update status of the test methods
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		 /*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		   Date dt=new Date();
		   String Currentdatetimestamp=df.format(dt);
		 */
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		repName="Test-Report-" + timeStamp + ".html";
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\" + repName); //specific project location of the report
		
		sparkReporter.config().setDocumentTitle("RestAssured Automation Project"); //Title of the report
		sparkReporter.config().setReportName("Pet Store Users API");   //name of the report
		sparkReporter.config().setTheme(Theme.STANDARD);                       //theme of the report
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Partha");
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());      //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());         //to display groups in the report
		test.createNode(result.getName());
		test.log(Status.PASS, result.getName()+"got successfully executed");
		
	}
	
   public void onTestFailure(ITestResult result) {
		
	   test=extent.createTest(result.getTestClass().getName());
	   test.createNode(result.getName());
	   test.assignCategory(result.getMethod().getGroups());
	   
	   test.log(Status.FAIL, result.getName()+"got failed");
	   test.log(Status.FAIL, result.getThrowable().getMessage());
	}
   
   public void onTestSkipped(ITestResult result) {
		
	   test=extent.createTest(result.getTestClass().getName());
	   test.createNode(result.getName());
	   test.assignCategory(result.getMethod().getGroups());
	   test.log(Status.SKIP, result.getName()+"got skipped");
	   test.log(Status.INFO, result.getThrowable().getMessage());
	}
   
   public void onFinish(ITestContext context) {
	   
	   extent.flush();
	   
	 }
	   
  }
