package com.OCSShopify.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.OCS.businessFunctions.BusinessFunctions;
import com.OCS.objectRepository.D365SalesOrderPage;
import com.OCS.objectRepository.OCSCustomerInformationPage;
import com.OCS.objectRepository.OCSLoginPage;
import com.OCS.objectRepository.OCSPaymentPage;
import com.OCS.objectRepository.OCSShippingMethodPage;
import com.OCS.objectRepository.OCSShopifyHomePage;
import com.OCS.utilities.Data_Provider;

public class OCSD365_PurchaseOrder extends BusinessFunctions{
	
	
	@Test
	public void OCSShopify_SalesOrder() throws Throwable{
	try{
		//Read the excel data
		tstData = Data_Provider.getTestData("OCSShopify_PurchaseOrder", "OCSShopify_PurchaseOrder");
		String username = tstData.get("UserName");	
		String password = tstData.get("Password");
		String vendorAccount = tstData.get("VendorAccount");
		String itemNumber = tstData.get("ItemNumber");
		String itemSize = tstData.get("ItemSize");
		String D365INT01_URL = credentialManager.get("INT01_URL");
		
		//Launching D365
		OCSShopifylaunchURL(D365INT01_URL);
		OCS_Login(username,password);
		
		goToModules("Purchase Order");
		selectPOAccount(vendorAccount);
		enterPOitemDetails(itemNumber,itemSize);
		goToModules("Vendor Collabaration");
		acceptPO();
		goToModules("Purchase Order");
		validateResponseOrderPage();
		confirmOrder();
		//goToModules("Purchase Order");
		verifyConfirmStatus();
		logOut();
	
	}
	catch(Exception e){
		e.printStackTrace();
		failureReport("Submit OCS Shopify Form", "Failed to Submit OCS Shopify Form");
	}
 }
}