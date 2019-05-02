package com.OCS.accelerators;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.OCS.support.HtmlReportSupport;
import com.OCS.support.MyListener;
import com.OCS.support.ReportStampSupport;
import com.OCS.utilities.SendMail;
public class TestEngine extends SendMail {
	public static Logger logger = Logger.getLogger(TestEngine.class.getName());
	// public static ConfiguratorSupport configProps = new
	// ConfiguratorSupport("config.properties");
	// public static ConfiguratorSupport counterProp = new
	// ConfiguratorSupport(configProps.getProperty("counterPath"));
	public static HashMap<String, String> tstData = null;
	public static HashMap<String, String> credentialManager = null;
	public static HashMap<String, String> propertyManager = null;
	public static HashMap<String, String> executionData = null;
	public String currentSuite = "";
	public String method = "";
	public boolean flag = false;
	public static WebDriver webDriver = null;
	public static WebDriver d = null;
	public static EventFiringWebDriver driver = null;
	public static int stepHeaderNum = 0;
	public static int stepNum = 0;
	public static int PassNum = 0;
	public static int FailNum = 0;
	public static int passCounter = 0;
	public static int failCounter = 0;
	public String testName = "";
	public String testCaseExecutionTime = "";
	public StringBuffer x = new StringBuffer();
	public String finalTime = "";
	public boolean isSuiteRunning = false;
	public static String testCaseDescription = "";
	public static Map<String, String> testDescription = new LinkedHashMap<String, String>();
	public static Map<String, String> testResults = new LinkedHashMap<String, String>();
	public String url = null;
	public int countcompare = 0;
	public static String projectName = "d2l";
	public static String browserName;
	static int len = 0;
	static int i = 0;
	public static ITestContext itc;
	public static String groupNames = null;
	public static String rpSumDt = null;
	public static File file = null;
	public static String application = null;
	public static String rStartDate = null;
	public static String suiteName = "";
	public static String folderName = null;
	public static String chartName = null;
	public static String buildNumer_Jenkins;
	public static String strJenkinsExecution;

	/**
	 * Initializing browser requirements, Test Results file path and Database
	 * requirements from the configuration file
	 * 
	 * @Revision History
	 * 
	 */
	// Before Suite
	@BeforeSuite(alwaysRun = true)
	public void first(ITestContext context) throws Throwable {
		try {
			// Reads all the values from the Credential Manager Properties Sheet
			propertyManager = CredentialManager.getProperty("Properties");
			// Reads all the values from Given Environment Sheet in Credential Manager
			credentialManager = CredentialManager.getProperty(propertyManager.get("Environment"));
			SimpleDateFormat rpSumDtSDT = new SimpleDateFormat("MMM_dd_yyyy_z_HH_mm_ss");
			rpSumDtSDT.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			rpSumDt = rpSumDtSDT.format(new Date());
			rStartDate = rpSumDtSDT.format(new Date());
			ReportStampSupport.calculateSuiteStartTime();
			Map<String, String> suiteParameters = context.getCurrentXmlTest().getSuite().getParameters();
			String testParameters = context.getCurrentXmlTest().getSuite().getTests().toString();
			System.out.println(testParameters);
			System.out.println("~~~~~~~~Before Suite~~~~~~~~");
			Configuration.PutProperty("CurrentApplication", suiteParameters.get("CurrentApplication"));
			Configuration.PutProperty("browser", suiteParameters.get("browser"));
			browserName = suiteParameters.get("browser");
			System.out.println(browserName);
			suiteName = context.getCurrentXmlTest().getSuite().getName().replace(" ", "_").trim();

			String log4jConfigFile = System.getProperty("user.dir") + File.separator + "Properties" + File.separator
					+ "log4j.properties";
			PropertyConfigurator.configure(log4jConfigFile);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			logger.info(
					"################################################################################################");
			// logger.info("***Release Version***"+propertyManager.get("ReleaseVersion") );
			// logger.info("PSFT URL:"+credentialManager.get("PSFT_URL"));
			// logger.info("D2L URL:"+credentialManager.get("D2L_URL"));
			logger.info("Execution Date: " + dateFormat.format(date));
			logger.info(
					"################################################################################################");

		} catch (Exception ex) {

		}
	}

	// Before Class
	@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
	public void first(ITestContext ctx, String browser) throws Throwable {
		System.out.println("~~~~~~~~Before Class~~~~~~~~");
	}

	/**
	 * De-Initializing and closing all the connections
	 * 
	 * @throws Throwable
	 */
	// After Suite
	@Parameters({ "browser" })
	@AfterSuite(alwaysRun = true)
	public void tearDown(ITestContext ctx, String browser) throws Throwable {
		System.out.println("~~~~~~~~After Suite~~~~~~~~");
		ReportStampSupport.calculateSuiteExecutionTime();
		if (propertyManager.get("MailTransfer").equalsIgnoreCase("True"))
			SendMail.attachReportsToEmail();

	}

	/**
	 * Write results to Browser specific path
	 * 
	 * @Revision History
	 * 
	 */
	// @Parameters({"browserType"})
		public static String filePath() {
			String strDirectoy = "";
			String strDirectoy_local="";
			String result="";
			strDirectoy_local = TestEngine.folderName.trim()+"_"+rStartDate;
			strDirectoy = TestEngine.folderName.trim();
			buildNumer_Jenkins=System.getProperty("BuildNumber");
			//buildNumer_Jenkins="2";
			String buildNumer="Arbella_Regression_"+buildNumer_Jenkins;
			if(buildNumer_Jenkins !=null  ){
				new File(configProps.getProperty("screenShotPath") + buildNumer).mkdirs();
				File strDirectoy1=new File(configProps.getProperty("screenShotPath") + buildNumer+ "/" +strDirectoy);
				//strDirectoy = TestEngine.folderName.trim() + "_" + rStartDate;
				if (!strDirectoy1.exists()) {
					strDirectoy1.mkdirs();
				}
				File resultSet=new File(configProps.getProperty("screenShotPath") + buildNumer+ "/" +strDirectoy + "/" + "Screenshots");
				if (!resultSet.exists()) {
					resultSet.mkdir();
					HtmlReportSupport.copyLogos();
				}
				result=configProps.getProperty("screenShotPath")+ buildNumer+"/" + strDirectoy;
			}
			else{
				if (strDirectoy_local != "") {
					new File(configProps.getProperty("screenShotPath") + strDirectoy_local).mkdirs();
				}

				File results = new File(configProps.getProperty("screenShotPath") + strDirectoy_local + "/" + "Screenshots");
				if (!results.exists()) {
					results.mkdir();
					HtmlReportSupport.copyLogos();
				}
				result=configProps.getProperty("screenShotPath")+strDirectoy_local;
			}
			
				
		
			
			return result;

		}

	// Before Method
	@SuppressWarnings({ "static-access", "deprecation" })
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void reportHeader(Method method, ITestContext ctx, String browser) throws Throwable {
		System.out.println("~~~~~~~~Before Method~~~~~~~~");
		strJenkinsExecution=System.getProperty("JenkinsExecution");
		System.out.println("Execute from Jenkins :: "+strJenkinsExecution);
		itc = ctx;
		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("getting in");
			System.setProperty("webdriver.gecko.driver", "Drivers" + File.separator + "geckodriver");
			webDriver = new FirefoxDriver();
			System.out.println("Entered");
			i = i + 1;

		} else if (browser.equalsIgnoreCase("ie")) {
			//BusinessFunctions.CloseIEDriverServerinstances();
			//ClosBrowserHistory();
			//killBrowserInstance_ie();
			File file = new File("Drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
			caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			caps.setCapability("IE.binary", "C:/Program Files/Internet Explorer/iexplore.exe");
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			caps.setCapability("ignoreProtectedModeSettings", true);
			caps.setJavascriptEnabled(true);
			webDriver = new InternetExplorerDriver(caps);
			i = i + 1;

		} else if (browser.equalsIgnoreCase("chrome")) {
			Thread.sleep(2000);
			System.setProperty("webdriver.chrome.driver", "Drivers"+File.separator+"chromedriver");
			Thread.sleep(2000);
			if(strJenkinsExecution==null)
			{
				System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
				webDriver = new ChromeDriver();
			}
			else
			{
				DesiredCapabilities caps=DesiredCapabilities.chrome();
				System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
				webDriver = new RemoteWebDriver(new URL("http://usv-bmsapp-01.us.umuc.edu:4444/wd/hub"),caps);
			}
		}
		else if (browser.equalsIgnoreCase("safari")) {
			SafariOptions cap = new SafariOptions();
			System.setProperty("webdriver.safari.noinstall", "true");
			//cap.useCleanSession(true); // if you wish safari to forget session everytime
			webDriver = new SafariDriver(cap);
		}
		driver = new EventFiringWebDriver(webDriver);
		// d=driver;
		Thread.sleep(2000);
		MyListener myListener = new MyListener();
		driver.register(myListener);

		try {
			// driver.manage().deleteAllCookies();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();

			// FolderCreation
			reportCreater();
			currentSuit = ctx.getCurrentXmlTest().getSuite().getName();
		} catch (Exception e1) {
			System.out.println(e1);
		}
		// ===============================

		itc = ctx;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
		sdf.format(date);
		calculateTestCaseStartTime();

		flag = false;

		tc_name = method.getName().toString() + "-" + browser;
		String[] ts_Name = this.getClass().getName().toString().split("\\.");
		packageName = ts_Name[0] + "." + ts_Name[1] + "." + ts_Name[2];

		testHeader(tc_name.replace("-" + browser, ""));

		stepNum = 0;
		PassNum = 0;
		FailNum = 0;
		testName = method.getName();

		String[] tmp = testName.split("_");
		String desc = "";
		if ((this.testCaseDescription != null) && (!this.testCaseDescription.isEmpty())) {
			desc = this.testCaseDescription;
		} else {
			for (int i = 0; i < tmp.length; i++) {
				desc = desc + " " + tmp[i];
			}
		}
		testDescription.put(testName + "-" + browser, desc);

	}

	// /~~~~~~~~After Method~~~~~~~~
	@Parameters({ "browser" })
	@AfterMethod(alwaysRun = true)
	public void tearDown(String browser) throws Exception {
		System.out.println("~~~~~~~~After Method~~~~~~~~");
		calculateTestCaseExecutionTime();
		closeDetailedReport(browser);

		try {

			if (FailNum != 0) {

				testResults.put(tc_name, "FAIL");
				failCounter = failCounter + 1;
			} else if (FailNum == 0) {
				testResults.put(tc_name, "PASS");
				passCounter = passCounter + 1;
			} else if (tstData.get("ExecuteStatus").trim().equalsIgnoreCase("No")) {
				testResults.put(tc_name, "SKIP");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(tc_name);
		HtmlReportSupport.createHtmlSummaryReport(browser);
		driver.quit();
		Thread.sleep(5000);

		// Writing Execution Data to Log
		// createLogFileWithExecutionData();

	}

	// ~~~~~~~~After Class~~~~~~~~
	@Parameters({ "browser" })
	@AfterClass(alwaysRun = true)
	public void close(String browser) {
		System.out.println("~~~~~~~~After Class~~~~~~~~");
	}

	public void reportCreater() throws Throwable {
		int intReporterType = Integer.parseInt(configProps.getProperty("reportsType"));

		switch (intReporterType) {
		case 1:

			break;
		case 2:
			break;
		default:

			htmlCreateReport();
			break;
		}
	}

	public void calculateTestCaseStartTime() {
		iStartTime = System.currentTimeMillis();
	}

	/***
	 * This method is supposed to be used in the @AfterMethod to calculate the total
	 * test case execution time to show in Reports by taking the start time from the
	 * calculateTestCaseStartTime method.
	 */
	public void calculateTestCaseExecutionTime() {
		iEndTime = System.currentTimeMillis();
		iExecutionTime = (iEndTime - iStartTime);
		TimeUnit.MILLISECONDS.toSeconds(iExecutionTime);
		HtmlReportSupport.executionTime.put(tc_name, String.valueOf(TimeUnit.MILLISECONDS.toSeconds(iExecutionTime)));
		// System.out.println(tc_name+";Time
		// :"+String.valueOf(TimeUnit.MILLISECONDS.toSeconds(iExecutionTime)));
	}

	public void onSuccess(String strStepName, String strStepDes) {
		onSuccess(strStepName, strStepDes, "");
	}

	public void onSuccess(String strStepName, String strStepDes, String stepTime) {

		file = new File(TestEngine.filePath() + "/" + strTestName.split("-")[0] + "_" + rpTime + ".html");// "SummaryReport.html"
		Writer writer = null;
		stepNum = stepNum + 1;

		try {
			// testdescrption.put(TestTitleDetails.x.toString(),
			// TestEngine.testDescription.get(TestTitleDetails.x));
			if (!map.get(packageName + ":" + tc_name).equals("FAIL")) {
				map.put(packageName + ":" + tc_name, "PASS");
				// map.put(TestTitleDetails.x.toString(),
				// TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
			writer = new FileWriter(file, true);
			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes.replace("[", "<b>").replace("]", "</b>") + "</td> ");
			writer.write(
					"<td class='Pass' align='center'><font size='2' color='green'><B>Pass</B></font><img  src='./Screenshots/passed.ico' width='18' height='18'/></td> ");
			PassNum = PassNum + 1;
			String strPassTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strPassTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeStep() {

		if (stepHeaderNum > 0) {

			try {
				File file = new File(TestEngine.filePath() + "/" + strTestName.split("-")[0] + "_" + rpTime + ".html");// "SummaryReport.html"
				Writer writer = null;
				writer = new FileWriter(file, true);
				writer.write("</tbody>");
				writer.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// public void addStep(String strStepName, String strStepDes) {
	public void addStep(String strStepDes) {
		closeStep();
		File file = new File(TestEngine.filePath() + "/" + strTestName.split("-")[0] + "_" + rpTime + ".html");// "SummaryReport.html"
		Writer writer = null;

		stepHeaderNum = stepNum + 1;
		String sheaderId = "Header_" + stepHeaderNum;
		try {

			writer = new FileWriter(file, true);
			writer.write("<tbody><tr class='section'>");
			writer.write("<td colspan='5' onclick=toggleMenu('" + sheaderId + "')>+ ");
			writer.write(strStepDes.replace("[", "<b>").replace("]", "</b>") + "</td></tr></tbody>");
			writer.write("<tbody id='" + sheaderId + "' style='display:table-row-group'>");

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onWarning(String strStepName, String strStepDes) {
		onWarning(strStepName, strStepDes, "");
	}

	public void onWarning(String strStepName, String strStepDes, String stepTime) {

		Writer writer = null;
		try {
			File file = new File(TestEngine.filePath() + "/" + strTestName.split("-")[0] + "_" + rpTime + ".html");// "SummaryReport.html"

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			FailNum = FailNum + 1;

			writer.write("<td class='Fail'  align='center'><a  href='" + "./Screenshots" + "/"
					+ strStepDes.replace(" ", "_").replace("[", "").replace("]", "") + stepTime + ".jpeg'"
					+ " alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img src='./Screenshots/warning.ico' width='18' height='18'/></a></td>");

			String strFailTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strFailTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();

		} catch (Exception e) {

		}

	}

	public void onFailure(String strStepName, String strStepDes, String stepTime) {
		Writer writer = null;
		try {
			File file = new File(TestEngine.filePath() + "/" + strTestName.split("-")[0] + "_" + rpTime + ".html");// "SummaryReport.html"

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");

			writer.write("<td class='justified'>" + strStepDes.replace("[", "<b>").replace("]", "</b>") + "</td> ");

			FailNum = FailNum + 1;

			writer.write("<td class='Fail' align='center'><a  href='" + "./Screenshots" + "/"
					+ strStepDes.replace(" ", "_").replace("[", "").replace("]", "") + stepTime + ".jpeg'"
					+ " alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><font size='2' color='red'><B>Fail</B></font><img  src='./Screenshots/failed.ico' width='18' height='18'/></a></td>");

			String strFailTime = ReportStampSupport.getTime();
			writer.write("<td><small>" + strFailTime + "</small></td> ");
			writer.write("</tr> ");
			writer.close();
			if (map.get(packageName + ":" + tc_name).equals("PASS")) {
				//map.put(packageName + ":" + tc_name + ":", "FAIL");
				Assert.assertTrue(false);
				// map.put(TestTitleDetails.x.toString(),
				// TestEngine.testDescription.get(TestTitleDetails.x.toString()));
			}
		} catch (Exception e) {

		}

	}

	public void closeDetailedReport(String browser) {

		try {
			closeStep();

			File file = new File(TestEngine.filePath() + "/" + strTestName.split("-")[0] + "_" + rpTime + ".html");// "SummaryReport.html"
			Writer writer = null;
			writer = new FileWriter(file, true);
			writer.write("</table>");
			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("</colgroup>");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'> ");
			writer.write("<th colspan='4'>Execution Time In Seconds (Includes Report Creation Time) : "
					+ executionTime.get(tc_name) + "&nbsp;</th> ");
			writer.write("</tr> ");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Steps Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + PassNum + "</td>");
			writer.write("<td class='fail'>&nbsp;Steps Failed&nbsp;: </td>");
			writer.write("<td class='fail'>" + FailNum + "</td>");
			writer.write("</tr>");
			writer.close();
		} catch (Exception e) {

		}
	}

	public void closeSummaryReport(String browser) {
		if(TestEngine.buildNumer_Jenkins !=null  ){
			file = new File(TestEngine.filePath() + "/" + "Summary_Results_"
					 +TestEngine.buildNumer_Jenkins+ ".html");// "SummaryReport.html"
			}
			else{
				file = new File(TestEngine.filePath() + "/" + "Summary_Results_"
						 +TestEngine.rpSumDt+ ".html");// "SummaryReport.html"	
			}
		//file = new File(TestEngine.filePath() + "/" + "Summary_Results_" + TestEngine.rpSumDt + ".html");// "SummaryReport.html"
		Writer writer = null;
		try {
			writer = new FileWriter(file, true);

			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' /> ");
			writer.write("</colgroup> ");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'>");
			writer.write("<th colspan='4'>Total Duration  In Seconds (Including Report Creation) : "
					+ ((int) iTestExecutionTime) + "</th>");
			writer.write("</tr>");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Tests Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + passCounter + "</td> ");
			writer.write("<td class='fail'>&nbsp;Tests Failed&nbsp;:</td>");
			writer.write("<td class='fail'> " + failCounter + "</td> ");
			writer.write("</tr>");
			writer.write("</tfoot>");
			writer.write("</table> ");

			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reportStep(String StepDesc) {
		StepDesc = StepDesc.replaceAll(" ", "_");
		File file = new File(TestEngine.filePath() + "/" + strTestName.split("-")[0] + "_" + rpTime + ".html");// "SummaryReport.html"
		Writer writer = null;

		try {
			writer = new FileWriter(file, true);
			if (BFunctionNo > 0) {
				writer.write("</tbody>");
			}
			writer.write("<tbody>");
			writer.write("<tr class='section'> ");
			writer.write("<td colspan='5' onclick=toggleMenu('" + StepDesc + stepNum + "')>+ " + StepDesc + "</td>");
			writer.write("</tr> ");
			writer.write("</tbody>");
			writer.write("<tbody id='" + StepDesc + stepNum + "' style='display:table-row-group'>");
			writer.close();
			BFunctionNo = BFunctionNo + 1;
		} catch (Exception e) {

		}
	}

	/**
	 * killBrowserInstance :: It kills the browser processes running if any.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void killBrowserInstance() throws InterruptedException {
		try {
			System.out.println("..");
			if (System.getProperty("os.name").contains("Windows"))
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			else {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver");
			}
			// Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Thread.sleep(10000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void killBrowserInstance_ie() throws InterruptedException {
		try {
			System.out.println("..");
			if (System.getProperty("os.name").contains("Windows"))
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			//SuccessReportWithScreenshot("Close All IEDriverServer instances", "Sucessfully  Closed all existing IEDriverServer instances");
			else {
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer");
			}
			// Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Thread.sleep(10000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void ClosBrowserHistory() throws Throwable {
		
		 String IEDriverServer="taskkill /f /im IEDriverServer.exe"; 

		 try {

            Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");

            Thread.sleep(40);
           // SuccessReportWithScreenshot("Clear all IE browser History", "Sucessfully Cleared all IE browser History");
          

        } catch (IOException e) {
       	 //failureReport("Clear all IE browser History", "Failed to Clear all IE browser History");
            e.printStackTrace();

        }
	

}

	
	
	
	
	/*public void CloseIEDriverServerinstances() throws Throwable {
		
		 String IEDriverServer="taskkill /f /im IEDriverServer.exe"; 

        try

        {

           System.out.println("Close all existing IEDriverServer instances");
           SuccessReportWithScreenshot("Close All IEDriverServer instances", "Sucessfully  Closed all existing IEDriverServer instances");
            Process process = Runtime.getRuntime().exec(IEDriverServer);


        } catch (IOException e)

        {

            e.printStackTrace();
            failureReport("Close All IEDriverServer instances", "Failed to Close all existing IEDriverServer instances");
        }*/
	

//}
	/**
	 * Used for Results Folder creation with TestName froM XML
	 * 
	 * @param testContext
	 */

	@BeforeTest(alwaysRun = true)
	public void startTest(final ITestContext testContext) {

		iTestStartTime = System.currentTimeMillis();
		application = testContext.getCurrentXmlTest().getParameter("CurrentApplication").trim();
		chartName = propertyManager.get("ReleaseVersion") + "ExecutionStatus";
		failCounter = 0;
		passCounter = 0;
		serialNo = 0;
		folderName = testContext.getName().trim();
		System.out.println(folderName); // it prints "Check name test"
	}

	/**
	 * Closing the SummaryReport
	 * 
	 * @throws IOException
	 */

	@AfterTest
	public void closeTest() throws IOException {

		ReportStampSupport.calculateTestExecutionTime();

		if (configProps.getProperty("ChartType").trim().equalsIgnoreCase("Pie")
				|| configProps.getProperty("ChartType").trim().contains("Pie")
				|| configProps.getProperty("ChartType").trim().contains("pie"))
			createPieChartForReport(chartName, passCounter, failCounter, 0);
		else
			System.out.println("Currently Supports only Pie Graph");
		// createBarChartForReport(chartName, passCounter, failCounter, 0);

		closeSummaryReport(browser);
		writer.close();

	}

	/**
	 * Used to Create Pie Chart for Report
	 * 
	 * @param suiteName
	 * @param passCount
	 * @param failCount
	 * @param skipCount
	 */
	public void createPieChartForReport(String chartName, int passCounter, int failCounter, int skipCounter)
			throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();

		// Data Set Values
		dataset.setValue("Pass " + passCounter, new Double(passCounter));
		dataset.setValue("Fail " + failCounter, new Double(failCounter));
		dataset.setValue("Skip " + skipCounter, new Double(skipCounter));

		JFreeChart chart = ChartFactory.createPieChart(chartName, dataset, true, true, true);

		// LegendTitle legend = chart.getLegend();
		// legend.setFrame(new BlockBorder(Color.white));

		PiePlot ColorConfigurator = (PiePlot) chart.getPlot();
		ColorConfigurator.setSimpleLabels(true);
		ColorConfigurator.setLabelLinksVisible(true);
		ColorConfigurator.setSectionOutlinesVisible(false);
		ColorConfigurator.setShadowPaint(null);
		ColorConfigurator.setLabelGenerator(null);

		// Percentage Values
		ColorConfigurator.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));

		// Customized Colors
		Color pass_color = new Color(200, 57, 35);
		Color fail_color = new Color(231, 222, 21);
		Color skip_color = new Color(83, 180, 29);

		ColorConfigurator.setSectionPaint("Skip " + skipCounter, fail_color);
		ColorConfigurator.setSectionPaint("Fail " + failCounter, pass_color);
		ColorConfigurator.setSectionPaint("Pass " + passCounter, skip_color);

		int width = 400;
		int height = 400;
		try {
			ChartUtilities.saveChartAsJPEG(new File(
					TestEngine.filePath() + File.separator + "Screenshots" + File.separator + chartName + ".jpg"),
					chart, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Used to Create Log File with Execution Data
	 */
	public void createLogFileWithExecutionData() {
		try {

			String[] className = this.getClass().getName().toString().split("\\.");
			logger.info("================================================");
			logger.info("Class Name" + " : " + className[(className.length) - 1]);
			logger.info("================================================");
			if (executionData != null) {
				Iterator<Entry<String, String>> entries = executionData.entrySet().iterator();
				while (entries.hasNext()) {
					Entry<String, String> entry = entries.next();
					if (entry.getKey().trim().equalsIgnoreCase("ClassName"))
						continue;
					logger.info(entry.getKey() + " : " + entry.getValue());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
