package com.OCS.businessFunctions;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

import org.apache.poi.ss.formula.functions.Now;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;

import com.OCS.accelerators.ActionEngine;
import com.OCS.accelerators.CredentialManager;
import com.OCS.objectRepository.CreationofSalesOrder;
import com.OCS.objectRepository.D365PurchaseOrderPage;
import com.OCS.objectRepository.D365SalesOrderPage;
import com.OCS.objectRepository.OCSCustomerInformationPage;
import com.OCS.objectRepository.OCSLoginPage;
import com.OCS.objectRepository.OCSPaymentPage;
import com.OCS.objectRepository.OCSShippingMethodPage;
import com.OCS.objectRepository.OCSShopifyHomePage;
import com.OCS.objectRepository.SalesOrderLoginInformationPage;
import com.OCS.utilities.FileReader;
import com.mysql.jdbc.util.VersionFSHierarchyMaker;
//import com.thoughtworks.selenium.webdriven.commands.Click;


public class BusinessFunctions extends ActionEngine {
	//static String orderNo="22890";  
	static String orderNo;  
	static String PONumber;  
	public void selectActionByVisibleText(By locator,String dropdown) throws Throwable
	{

		try
		{
		Select s=new Select(driver.findElement(locator));
		s.selectByVisibleText(dropdown);
		SuccessReportWithScreenshot("Select", "Successfully selected value " + dropdown);
		Thread.sleep(500);
		}
		catch(Exception e)
		{
			failureReport("Select", "Unable to select Value " + dropdown);
		}
	}

	public void OCSShopifylaunchURL() throws Throwable {
		try {
			
			propertyManager = CredentialManager.getProperty("Properties");
			credentialManager = CredentialManager.getProperty(propertyManager.get("Environment"));
			launchUrl(credentialManager.get("D365INT02Upgrade_URL"));
		
			SuccessReportWithScreenshot("Launch URL", "Sucessfully  launched  OCS Shopify URL");
		} catch (Exception e) {
			failureReport("Launch URL", "Failed to launch the OCS Shopify URL");
		}
	}
	
	public void SalesOrder_Login(String username, String password) throws Throwable {

		try {
			
		
			
			type(SalesOrderLoginInformationPage.SelectUseAnotherAccountFirstName, username, "Usrername");
		
			SuccessReportWithScreenshot("Enter UserName", "Sucessfully Entered UserName");
			click(SalesOrderLoginInformationPage.SelectUseAnotherAccountNextButton, "Click on Next button");
			type(SalesOrderLoginInformationPage.SelectUseAnotherAccountPassword, password, "Password");
			SuccessReportWithScreenshot("Enter Password", "Sucessfully Entered Password");
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			
			click(SalesOrderLoginInformationPage.ClickonSignIn, "Click on login button");
			SuccessReportWithScreenshot("SignInButton", "SignIn button cliked Sucessfully");
			click(SalesOrderLoginInformationPage.ClickonYes, "Click on YES button");
			SuccessReportWithScreenshot("YES Button", "YES button cliked Sucessfully");
					} catch (Exception e) {
			e.printStackTrace();
			failureReport("Login validation", "Failed to Login into SaleOrder Application");
		}
	}
	
	
	public void NewSaleOrderCreation(String CustomerAccountNumber,String EnterSalesOrderItemNumber,String EnterSalesOrderSize,String SalesOrderValidationMessage) throws Throwable {

		try {
			
			
			
			click(CreationofSalesOrder.ClickmodulesPaneOpener,"Click on ClickSalesOrder");
			SuccessReportWithScreenshot("Click modules PaneOpener Button", "Sucessfully Clicked modulesPaneOpener");
			
			click(CreationofSalesOrder.AccountsReceivable,"Click on Accounts Receivable");
			SuccessReportWithScreenshot("Click Accounts Receivable Button", "Sucessfully Clicked AccountsReceivable");
			
			
			
			click(CreationofSalesOrder.ClickAllSalesOrders,"Click on ClickSalesOrder");
			SuccessReportWithScreenshot("Click All Sales order Button", "Sucessfully Clicked All sales Order button");
			
			
			
			//click(CreationofSalesOrder.ClickSalesOrder,"Click on ClickSalesOrder");
			//SuccessReportWithScreenshot("Click ClickSalesOrder Button", "Sucessfully Clicked ClickSalesOrder");
			
			
			
			// Navigating to New Sales Order page
			click(CreationofSalesOrder.ClickNewSalesOrderButton,"Click on New Sales Order Button");
			SuccessReportWithScreenshot("Sales Order Page", "Sucessfully Navigate to Sales Order page");
			// Enter Customer Account Number
			
			type(CreationofSalesOrder.EnterCustomerAccountNumber, CustomerAccountNumber, "CustomerAccountNumber");
            SuccessReportWithScreenshot("Enter CustomerAccountNumber", "Sucessfully Entered CustomerAccountNumber");

			//click(CreationofSalesOrder.CustAccountArrowButton,"Click on CustAccountArrowButton");
			Thread.sleep(300);
			click(CreationofSalesOrder.CustAccntSelection,"Click on CustAccntSelection");
			Thread.sleep(200);

			click(CreationofSalesOrder.ClickOkButton,"Click on ok Button");
			//Thread.sleep(1000);
			//waitForElementPresent(CreationofSalesOrder.ClickSalesOrder5,"Click on ClickSalesOrder");
			//click(CreationofSalesOrder.ClickSalesOrder5,"Click on ClickSalesOrder");
			//SuccessReportWithScreenshot("Click ClickSalesOrder Button", "Sucessfully Clicked ClickSalesOrder");
			

            SuccessReportWithScreenshot("Click Ok Button", "Sucessfully Navigate to Sals Order Info page");
           // verifyTextPresent("Confirmation");
            //assertText(CreationofSalesOrder.VerifySalesOrder,"Validate that Sales Order Created is displayed");
        	isTextPresent("Product tester");
			type(CreationofSalesOrder.EnterSalesOrderItemNumber, EnterSalesOrderItemNumber, "EnterSalesOrderItemNumber");
            SuccessReportWithScreenshot("Enter SalesOrderItemNumber", "Sucessfully Entered SalesOrderItemNumber");
			Thread.sleep(500);
            JSScrollTo(CreationofSalesOrder.EnterSalesOrderSize, " EnterSalesOrderSize");
			Thread.sleep(500);
			//driver.findElement(CreationofSalesOrder.EnterSalesOrderSize).sendKeys("30 caps");
			click(CreationofSalesOrder.EnterSalesOrderSize, " EnterSalesOrderSize");
			click(CreationofSalesOrder.SizeArrowButton,"Click on SELL");
			Thread.sleep(100);
			click(CreationofSalesOrder.SizeSelection,"Click on SELL");
			Thread.sleep(200);
            //type(CreationofSalesOrder.EnterSalesOrderSize, EnterSalesOrderSize, "EnterSalesOrderSize");
            SuccessReportWithScreenshot("Enter SalesOrder Size", "Sucessfully Entered SalesOrder Size");
           click(CreationofSalesOrder.ClickOnSaveButton,"Click on SaveButton");
			Thread.sleep(1000);
           //SuccessReportWithScreenshot("Click Save Button", "Sucessfully Clicked save button");
			
           
			 click(CreationofSalesOrder.ClickOnSELLTab,"Click on SELL");
			 SuccessReportWithScreenshot("Click SELL tab", "Sucessfully Clicked SELL tab");

			click(CreationofSalesOrder.ClickOnConfirmSalesOrderTab,"Click on ConfirmSalesOrder Button");
			SuccessReportWithScreenshot("Click ConfirmSalesOrder Button", "Sucessfully Clicked ConfirmSalesOrder button");
			
			
			click(CreationofSalesOrder.ClickOnConfirmSalesOrderOkButton,"Click on ConfirmSalesOrder  ok Button");
			SuccessReportWithScreenshot("Click OnConfirmSales Order Ok Button", "Sucessfully Clicked ConfirmSalesOrder ok button");
			
			//click(CreationofSalesOrder.ClickOnConfirmSalesOrderOkButton,"Click on ConfirmSalesOrder  ok Button");
			//SuccessReportWithScreenshot("Click OnConfirmSales Order Ok Button", "Sucessfully Clicked ConfirmSalesOrder ok button");
		    
			
			click(CreationofSalesOrder.ClickOnConfirmSalesOrderPostOkButton,"Click on ConfirmSalesOrder Post ok Button");
			SuccessReportWithScreenshot("Click On Confirm  Post Sales Order Ok Button", "Sucessfully Clicked ConfirmSalesOrder Post ok button");
			Thread.sleep(500);
			String s = getText(CreationofSalesOrder.SalesOrderValidationMessage,SalesOrderValidationMessage);
			
			
			if(s.equalsIgnoreCase(SalesOrderValidationMessage)){
				//failureReport("Sales Order Validation Message", "Failed to  handle Sales Order Validation Message");
				SuccessReportWithScreenshot("Sales Order Validation Message", "Sucessfully Sales Order Validation message");
			}else{
			failureReport("Sales Order Validation Message", "Failed to  handle Sales Order Validation Message");
				//SuccessReportWithScreenshot("Sales Order Validation Message", "Sucessfully Sales Order Validation message");
				
			}
			Thread.sleep(50000);
			click(CreationofSalesOrder.ClickOnHeaderButton,"Click on Header Button");
			SuccessReportWithScreenshot("Click On Header Button", "Sucessfully Clicked Header  button");
			
			//String s1 = getText(CreationofSalesOrder.Documentstatus,"Confirmation");
	     /*if(s1.equalsIgnoreCase(SalesOrderValidationMessage)){
				
				SuccessReportWithScreenshot("Document status Validation Message", "Sucessfully Document status Validation message");
			}else{
				failureReport("Document status Validation Message", "Failed to  Document status Validation Message");
			
				
			}*/
			//assertText(CreationofSalesOrder.Documentstatus,"Validate that Sales Order Created is displayed");
			//verifyText("Confirmation", CreationofSalesOrder.Documentstatus);
			isTextPresent("Confirmation");
			
		} catch (Exception e) {
			e.printStackTrace();
			failureReport("Submit Sales Order", "Failed to Create Sales Order");
		}
	}
	

	
	public void EnterCustomerInformation(String email,String fname,String lname,String address,String city,String postalCode,String phno) throws Throwable
	{
		try
		{
		EnterText(OCSCustomerInformationPage.CustEmailAddress,email);
		EnterText(OCSCustomerInformationPage.CustFirstName,fname);
		EnterText(OCSCustomerInformationPage.CustLastName,lname);
		EnterText(OCSCustomerInformationPage.CustAddress,address);
		EnterText(OCSCustomerInformationPage.CustCity,city);
		EnterText(OCSCustomerInformationPage.CustPostalCode,postalCode);
		EnterText(OCSCustomerInformationPage.CustPhoneNo,phno);
		SuccessReportWithScreenshot("Entering details", "Successfully entered values ");
		}
		catch(Exception e)
		{
			failureReport("Entering details", "Unable to enter Value ");
		}
		
	}
	
	public void EnterPaymentInformation(String cardName,String Name,String expiry,String securityCode) throws Throwable
	{
		try{
			
		
	driver.switchTo().frame(0);
	EnterText(OCSPaymentPage.EnterCardNumber,cardName);
	driver.switchTo().defaultContent();
	driver.switchTo().frame(1);
	EnterText(OCSPaymentPage.EnterCardName,Name);
	driver.switchTo().defaultContent();
	driver.switchTo().frame(2);
	Thread.sleep(2000);
	EnterText(OCSPaymentPage.EnterCardExpiry,expiry);
	driver.switchTo().defaultContent();
	driver.switchTo().frame(3);
	EnterText(OCSPaymentPage.EnterCodeSecurityCode,securityCode);
	driver.switchTo().defaultContent();
	Thread.sleep(1000);
	SuccessReportWithScreenshot("Entering Card details", "Successfully entered details ");
		}
		catch(Exception e)
		{
			
			failureReport("Entering card details", "Unable to enter details ");
		}
	}
	
	
	public void EnterText(By locator,String data) throws InterruptedException
	{
		Thread.sleep(500);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(data);
		
	}
	
	public void CaptureOrderNumber(By locator) throws Throwable
	{
		if(driver.findElement(locator).getText() != null)
		{
			String[] CRNnumber=driver.findElement(locator).getText().split("#");
			orderNo=CRNnumber[1];
			System.out.println(orderNo);
			SuccessReportWithScreenshot("Generating Order Number", "Successfully Generated");
		}
		else
		{
			failureReport("Generating Order Number", "Order Number not generated ");
		}
		
	}
	
	
//	public String holdName;
	/**
	 * To Get the Current Date in Number Format
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String getDateInFormat(String actualDate, String format) throws Throwable {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateInString = actualDate;
		String convertedDate = null;
		try {

			Date date = formatter.parse(dateInString);
			convertedDate = formatter.format(date);
			System.out.println(formatter.format(date));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

	/**
	 * @Description: Launching OCSShopifylaunch URL
	 * @throws Throwable
	 */
	public void OCSShopifylaunchURL(String URL) throws Throwable {
		try {
			
			propertyManager = CredentialManager.getProperty("Properties");
			credentialManager = CredentialManager.getProperty(propertyManager.get("Environment"));
			launchUrl(URL);
		
			SuccessReportWithScreenshot("Launch URL", "Sucessfully  launched  OCS Shopify URL");
		} catch (Exception e) {
			failureReport("Launch URL", "Failed to launch the OCS Shopify URL");
		}
	}

	public static void  titleValidation(WebDriver driver, String exp_title)
	{
		String act_title=driver.getTitle();
		Assert.assertEquals(exp_title, act_title);
	}

	
	public void OCS_Login(String username, String password) throws Throwable {

		try {
			type(OCSLoginPage.EmailAddress, username, "UserName");
			click(OCSLoginPage.SelectNext, "Click on Next");
			type(OCSLoginPage.Password, password, "Password");
			
			
			click(OCSLoginPage.ClickSubmit, "Click on Submit");
			click(OCSLoginPage.ClickSubmit, "Click on Yes to Confirm");
			
			SuccessReportWithScreenshot("LoginButton", "login button cliked Sucessfully");
					} catch (Exception e) {
			e.printStackTrace();
			failureReport("Login validation", "Failed to Login into Application");
		}
	}
	
	
	public void goToModules(String module) throws Throwable
	{
		try
		{
		click(D365SalesOrderPage.ModuleOpener, "Click on Menu");
		
		if(isElementPresent(D365PurchaseOrderPage.ModuleExpandNoUpgrade))
		{
			click(D365PurchaseOrderPage.SelectModulesUpgrade, "Click on Modules");	
		}
		
		if(module.contains("Vendor Collabaration"))
		{
			int i=10;
			
			while(i<=32)
			{
				//For Upgrade
				//if(!getTextForModules(By.xpath("//a[@role='menuitem']["+i+"]")).contains("Vendor collaboration") )
				
				if(!getTextForModules(By.xpath("//a[@role='menu']["+i+"]")).contains("Vendor collaboration") )
				{
					click(D365PurchaseOrderPage.SelectVendorCollaboration, "Select Vendor Collaboration");	
					
					
				break;	
				}
			++i;	
			}	
			
			if(isElementPresent(D365PurchaseOrderPage.PurchaseOrdersExpandNo))
			{
				click(D365PurchaseOrderPage.PurchaseOrdersExpandNo, "Go to Purchase Orders");	
			}
			click(D365PurchaseOrderPage.SelectPurchaseOrdersForReview, "Select Purchase Orders For Review");
			
		}
		else{
		click(D365PurchaseOrderPage.SelectAccountsPayableUpgrade, "Select on Accounts Payable");
		click(D365PurchaseOrderPage.SelectAllPurchaseOrders, "Select on All Purchase Orders");
		SuccessReportWithScreenshot("Navigating To Purchase Order", "Successfully Entered To Purchase Order Page");
		Thread.sleep(4000);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			failureReport("Navigating To "+module, "Failed");
		}
	}	
	
	public String getTextForModules(By locator)
	{
		return driver.findElement(locator).getText();
	}
	
	
	public void verifyConfirmStatus() throws Throwable
	{
		try{
		type(D365PurchaseOrderPage.SearchAllPO,PONumber, "Search PO");
		Thread.sleep(3000);
		driver.findElement(D365PurchaseOrderPage.SearchAllPO).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[contains(@title,'"+PONumber+"')]")).click();
		Thread.sleep(5000);
		if(driver.findElement(D365PurchaseOrderPage.VerifyConfirmingPO).isDisplayed())
			
			SuccessReportWithScreenshot("Validating the PO status as confirmed", "Successfully Validated");
		else
			failureReport("Validating the PO status as confirmed", "Failed");
		}
		catch(Exception e)
		{
			failureReport("Verifying Confirm Status", "Failed");
		}
	}
	
	public void selectPOAccount(String vendorAccount) throws Throwable
	{
		try{
		click(D365PurchaseOrderPage.ClickNew, "Click on New");
		type(D365PurchaseOrderPage.EnterVendorAccount,vendorAccount, "Enter Vendor Account Number");
		driver.findElement(D365PurchaseOrderPage.EnterVendorAccount).sendKeys(Keys.TAB);
		Thread.sleep(6000);
		click(D365PurchaseOrderPage.ClickOK, "Click Submit");
		SuccessReportWithScreenshot("Selecting PO", "Successfully Selected");
		}
		catch(Exception e)
		{
			failureReport("Selecting PO", "Failed");
		}
	}
	
	
	public void enterPOitemDetails(String itemNumber,String itemSize) throws Throwable
	{
		try{
		int i=20;
		type(D365PurchaseOrderPage.SelectItem,itemNumber, "Select PO Item");
		driver.findElement(D365PurchaseOrderPage.SelectItem).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		while(i<=25)
		{
		driver.findElement(By.xpath("(//input[@tabindex='0'])["+i+"]")).click();
		try
		{
			
			if(driver.findElement(D365PurchaseOrderPage.SelectSize).isDisplayed())
			{
				type(D365PurchaseOrderPage.SelectSize,itemSize, "Enter Item Size");
				driver.findElement(D365PurchaseOrderPage.SelectSize).sendKeys(Keys.TAB);
				Thread.sleep(5000);
				break;
			}
		}
		catch(Exception e)
		{
			;
			
		}
		++i; 
		}
		Thread.sleep(3000);
		click(D365PurchaseOrderPage.ClickHeader, "Go to Header");
		getPONumber();
		Thread.sleep(3000);
		click(D365PurchaseOrderPage.ClickPurchase, "Go To Purchase");
		click(D365PurchaseOrderPage.ClickSendForConfirmation, "Click on Send For Confirmation");
		
		try{
		if(driver.findElement(D365PurchaseOrderPage.CheckPrintPONo).isDisplayed())
		{
			click(D365PurchaseOrderPage.SwitchToPrint01, "Select YES for Print Purchase Order");
		}
			
		}
		catch(Exception e)
		{
			;
		}			
		click(D365PurchaseOrderPage.ClickOK, "Click Ok for PO Confirmation");
		Thread.sleep(6000);
		if(isElementPresent(D365PurchaseOrderPage.SelectExportPO))
			SuccessReportWithScreenshot("Opening Print Preview", "Successfully opened");
		else
			failureReport("Opening Print Preview", "Failed");
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(D365PurchaseOrderPage.VerifyInExtReview));				
		click(D365PurchaseOrderPage.VerifyInExtReview, "Verify In External Review");
		}
		catch(Exception e)
		{
			failureReport("Entering PO Item details", "Failed");
		}
	}
	
	public void getPONumber()
	{
		String Ordernum=driver.findElement(D365PurchaseOrderPage.getPONumber).getAttribute("title");
		PONumber=Ordernum.split("C")[0];
		System.out.println(PONumber);
	}
	
	public void acceptPO() throws IOException, Throwable
	{
		try{
		WebDriverWait wait;
		type(D365PurchaseOrderPage.SearchPO,PONumber, "Search PO");
		Thread.sleep(3000);
		driver.findElement(D365PurchaseOrderPage.SearchPO).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		click(D365PurchaseOrderPage.SelectPreviewPrint, "Select Preview/Print");
		wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(D365PurchaseOrderPage.SelectOriginalPreview));	
		click(D365PurchaseOrderPage.SelectOriginalPreview, "Select Original Preview");
		Thread.sleep(4000);
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(D365PurchaseOrderPage.SelectExportPO)).click();	
		//Select s=new Select(driver.findElement(D365PurchaseOrderPage.SelectExportPO));
		//wait.until(ExpectedConditions.visibilityOfElementLocated((By) s.getFirstSelectedOption()));
		Thread.sleep(10000);
		SuccessReportWithScreenshot("Opening Original Preview", "Successfully opened");
		click(D365PurchaseOrderPage.ClosePreview, "Close Preview");
		click(D365PurchaseOrderPage.ClickAccept, "Click Accept");
		wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(D365PurchaseOrderPage.SubmitOK));
		type(D365PurchaseOrderPage.EnterNotes,"Accepting the order for 24 quantity of 100012 15g", "Entering Accepting Notes");
		Thread.sleep(2000);
		click(D365PurchaseOrderPage.SubmitOK, "Confirming the Acceptance");
		Thread.sleep(4000);
		click(D365PurchaseOrderPage.ClickClose, "Close the form");
		Thread.sleep(4000);
		}
		catch(Exception e )
		{
			failureReport("Accepting PO", "Failed");
		}
	}
	
	
	public void logOut() throws Throwable
	{
		click(D365PurchaseOrderPage.SelectProfile, "Go to Profile");
		click(D365PurchaseOrderPage.ClickSignout, "Select SignOut");
		Thread.sleep(5000);
	}
	
	
	public void validateResponseOrderPage() throws Throwable
	{
		try{
		click(D365PurchaseOrderPage.ClickPurchase, "Go To Purchase");
		click(D365PurchaseOrderPage.ClickResponseOnLatestOrder, "Click View Response on Latest Sent Order");
		String responseStatus=getTextForTitleWithoutReport(D365PurchaseOrderPage.GetResponseStatus);
		if(responseStatus.equalsIgnoreCase("Accepted"))
		{
			SuccessReportWithScreenshot("Validating Response Status as Success", "Response Status is Success");
		}
		else
		{
			failureReport("Validating Response Status as Success", "Failed");
		}
		click(D365PurchaseOrderPage.CloseForm, "Close Form");
	}
		catch(Exception e)
		{
			failureReport("Validating Response Order Page", "Failed");
		}
	}

	
	public void confirmOrder() throws Throwable
	{
		try{
		click(D365PurchaseOrderPage.ClickPurchase, "Go To Purchase");
		click(D365PurchaseOrderPage.ClickConfirmation, "Click on Confirmation");
		if(isElementPresent(D365PurchaseOrderPage.CheckPrintPONo))
		click(D365PurchaseOrderPage.SwitchToPrint01, "Enable Print Purchase Order");
		if(isElementPresent(D365PurchaseOrderPage.CheckPrintMngDestNo))
		click(D365PurchaseOrderPage.SwitchToPrintMngDest, "Enable Use Print Management Destination");
		click(D365PurchaseOrderPage.SubmitOK, "Confirming the Order");
		if(isElementPresent(D365PurchaseOrderPage.ConfirmingPO))
			click(D365PurchaseOrderPage.ConfirmingPO, "Confirming the Order");
	Thread.sleep(4000);
	if(isElementPresent(D365PurchaseOrderPage.SelectExportPO))
		SuccessReportWithScreenshot("Opening Print Preview", "Successfully opened");
	else
		failureReport("Opening Print Preview", "Failed");
	
		SuccessReportWithScreenshot("Clicked on Confirming the Order", "Successfully Clicked on Confirming the Order");
	}
		catch(Exception e)
		{
			failureReport("Confirming the PO", "Failed");
		}
	}
	
	
public void goToAllSalesOrders() throws Throwable
{
	try
	{
	click(D365SalesOrderPage.ModuleOpener, "Click on Menu");
	
	if(isElementPresent(D365SalesOrderPage.ModuleExpandNo))
	{
		click(D365SalesOrderPage.SelectModules, "Click on Modules");	
	}
	click(D365SalesOrderPage.SelectAccountsReceivable, "Select on Accounts Receivable");
	click(D365SalesOrderPage.SelectAllSalesOrders, "Select on All Sales Orders");
	SuccessReportWithScreenshot("Navigating To Sales Order", "Successfully Entered To Sales Order Page");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		failureReport("Navigating To Sales Order", "Failed");
	}
}
	

public boolean isElementPresent(By locator)
{
	try
	{
	driver.findElement(locator);
	return true;
	}
	catch(org.openqa.selenium.NoSuchElementException e)
	{
		return false;
	}
	
}
	
public void searchCRN() throws Throwable
{
	try
	{
	int i=3;
	
	while(i<=13)
	{
		
		if(i>12)
		{
			click(D365SalesOrderPage.SelectMore, "Select More");
			click(D365SalesOrderPage.ClickOptions, "Select More");
			click(D365SalesOrderPage.SelectPersonalizeTheForm, "Select on Personalize the Form");
			click(D365SalesOrderPage.SelectInsert, "Click on Insert");
			click(D365SalesOrderPage.SelectField, "Select Field");
			click(D365SalesOrderPage.SelectColumn, "Select Field");
			searchCustomerRequisition();
			click(D365SalesOrderPage.SelectCRNcheckbox, "Select CRN checkbox");
			click(D365SalesOrderPage.ClickInsert, "Select Insert");
			click(D365SalesOrderPage.CloseOptions, "Close Options");
			driver.navigate().refresh();
			searchCRN();
		}
		
		try{
		if(driver.findElement(By.xpath("(//div[@aria-colindex='"+i+"']//input)[1]")).isDisplayed())
				driver.findElement(By.xpath("(//div[@aria-colindex='"+i+"']//input)[1]")).click();
		}
		catch(Exception e)
		{
			;
		}
		//driver.findElement(By.xpath("(//div[@aria-colindex='"+i+"']//input)[1]")).sendKeys(Keys.TAB);
		try{
		if(driver.findElement(D365SalesOrderPage.ClickCRN).isDisplayed())	
			break;
		}
		catch(Exception e)
		{
			;
		}
		++i;
		
	}
	    
	click(D365SalesOrderPage.ClickCRN, "Click on Customer Requisition");	    
	type(D365SalesOrderPage.SearchCRN,orderNo, "Enter CRN");
	click(D365SalesOrderPage.ClickApply, "Click on Search");
	click(By.xpath("(//input[@title='"+orderNo+"'])[2]/../preceding-sibling::div[9]"), "Click on SO");
	SuccessReportWithScreenshot("Searching CRN", "Successfully Searched CRN");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		failureReport("Searching CRN", "Failed");
	}
	
}


public void selectProduct(String product) throws Throwable
{
	try{
	By selectProduct=By.xpath("//h3[text()='"+product+"']");
	By clickNext=By.xpath("//div[@class='collection__products']//a[@title='Next']");
	if(!isElementPresent(selectProduct))
	while(isElementPresent(clickNext))
	{
	if(isElementPresent(selectProduct))
		break;
	else
		click(clickNext, "Search Product");
	
	}
	click(By.xpath("//h3[text()='"+product+"']"), "Select Product");
	}
	catch(Exception e)
	{
		failureReport("No Product Found with "+product, "Failed");
	}
}


public void cancelSurvey() throws Throwable
{
	try{
	if(driver.findElement(OCSPaymentPage.SelectCancel).isDisplayed())
	{
		click(OCSPaymentPage.SelectCancel, "Click on Cancel survey");
	}
	}
	catch(Exception e)
	{
		;
	}
}

public void searchCustomerRequisition() throws Throwable
{
	int i=1;
	while(i<=30)
	{
		if(!getTextForTitleWithoutReport(By.xpath("(//input[@title='Sales orders'])["+i+"]/../preceding-sibling::div[2]/input")).contains("Customer requisition") )
		driver.findElement(By.xpath("(//input[@title='Sales orders'])["+i+"]/../preceding-sibling::div[2]/input")).click();
		else
		break;	
	++i;	
	}
	
}


public String getTextForTitleWithoutReport(By locator) throws Throwable
{
	
	String title=driver.findElement(locator).getAttribute("title");
	//SuccessReportWithScreenshot("Getting Status", "Successfully fetched");
return	title;
}


public String getTextForTitle(By locator,String fields) throws Throwable
{
	try{
	String title=driver.findElement(locator).getAttribute("title");
	SuccessReportWithScreenshot("Getting Status for "+fields, "Successfully fetched "+fields);
return	title;

	}
	catch(Exception e)
	{
		e.printStackTrace();
		failureReport("Getting Status for "+fields, "Failed");
	}
	return currentSuite;

}

	public static void selectEffectiveDate(WebDriver driver) throws InterruptedException {

		Now n = new Now();

		Date d = new Date();

		String today = LocalTime.now().toString().replace(":", "_").replace(".", "_");

		SimpleDateFormat sd = new SimpleDateFormat("dd");

		today = sd.format(d);

		//driver.findElement(By.xpath("//*[@id='policyTerm']/div[1]/div/span/div/span")).click();
		//driver.findElement(By.xpath("//span[@class='input-group-addon']")).click();
		driver.findElement(By.xpath("//*[@id='policyTerm']/div[1]/div/span/div/span/i")).click();
        Thread.sleep(5000);
		// find the calendar
		List<WebElement> columns = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='today day']"));

		// comparing the text of cell with today's date and clicking it.
		for (WebElement cell : columns) {
			if (cell.getText().equals(today)) {
				cell.click();
				break;
			}
		}

	}
	

	public static void selectEffectiveDateApplicationinformationDOB(WebDriver driver) throws InterruptedException {

		Now n = new Now();

		Date d = new Date();

		String today = LocalTime.now().toString().replace(":", "_").replace(".", "_");

		SimpleDateFormat sd = new SimpleDateFormat("dd");

		today = sd.format(d);

		//driver.findElement(By.xpath("//*[@id='policyTerm']/div[1]/div/span/div/span")).click();
		driver.findElement(By.xpath("//*[@id='AppInfoSection']/div/div/span/div/span/i")).click();
		//driver.findElement(By.xpath("//*[@id='EffectiveDate']//parent::div/span/i[@class='fa fa-2']")).click();
		
		Thread.sleep(5000);
		// find the calendar
		List<WebElement> columns = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='today day']"));
		//List<WebElement> columns = driver.findElements(By.xpath("//td[@class='today day']"));
		// comparing the text of cell with today's date and clicking it.
		for (WebElement cell : columns) {
			if (cell.getText().equals(today)) {
				cell.click();
				break;
			}
		}

	}

	
	
	public static void selectEffectiveDateDriverDetailsinformationDOB(WebDriver driver) throws InterruptedException {

		Now n = new Now();

		Date d = new Date();

		String today = LocalTime.now().toString().replace(":", "_").replace(".", "_");

		SimpleDateFormat sd = new SimpleDateFormat("dd");

		today = sd.format(d);

		//driver.findElement(By.xpath("//*[@id='policyTerm']/div[1]/div/span/div/span")).click();
		driver.findElement(By.xpath("//*[@id='DriverDetailSection']/div[4]/div/span/div/span/i")).click();
        Thread.sleep(5000);
		// find the calendar
		List<WebElement> columns = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='today day']"));

		// comparing the text of cell with today's date and clicking it.
		for (WebElement cell : columns) {
			if (cell.getText().equals(today)) {
				cell.click();
				break;
			}
		}

	}

	public static boolean verifyUnderWriterRuleMessage(WebDriver driver, String ExpectedAlertMessage)

	{

		boolean TrueFalse = false;

		WebElement alert_getAlertId = driver.findElement(By.id("alert-danger"));

		List<WebElement> alert_getAllTheAlerts = alert_getAlertId.findElements(By.xpath("//*[@id='alert-danger']/p"));

		System.out.println(alert_getAllTheAlerts.size());

		ExpectedAlertMessage = ExpectedAlertMessage.replace(" ", "");

		System.out.println(ExpectedAlertMessage);

		String ActualAlertMessage;

		for (int i = 0; i <= alert_getAllTheAlerts.size() - 1; i++)

		{

			ActualAlertMessage = alert_getAllTheAlerts.get(i).getText().replace(" ", "");

			// System.out.println(sActualAlertMessage);

			if (ActualAlertMessage.contains(ExpectedAlertMessage))

			{

				// System.out.println("Displayed");

				TrueFalse = true;

				break;

			}

		}

		return TrueFalse;

	}
	

	
	
	}





































