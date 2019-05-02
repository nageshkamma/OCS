
package com.OCSShopify.tests;

import org.testng.annotations.Test;

import com.OCS.businessFunctions.BusinessFunctions;
import com.OCS.utilities.Data_Provider;

public class OCSD365_SalesOrder extends BusinessFunctions{
	@Test
	public void OCSShopify_SalesOrder() throws Throwable{
	try{
		//Read the excel data
				tstData = Data_Provider.getTestData("OCSShopifyD365_SalesOrder", "OCSShopify_SalesOrder");
			
				String username = tstData.get("UserName");	
				String password = tstData.get("Password");
				String CustomerAccountNumber = tstData.get("CustomerAccountNumber");	
				String EnterSalesOrderItemNumber = tstData.get("EnterSalesOrderItemNumber");
				String EnterSalesOrderSize= tstData.get("EnterSalesOrderSize");
				String SalesOrderValidationMessage= tstData.get("SalesOrderValidationMessage");
				String D365INT02Upgrade_URL = credentialManager.get("INT01_URL");	
			
				//This Method is Used to Launch the Application
				OCSShopifylaunchURL(D365INT02Upgrade_URL);
				//This Method is used to Login into the application
				implicityWait();
			
				SalesOrder_Login(username,password);
				//This Method is used to  create New Sales Order		
				NewSaleOrderCreation(CustomerAccountNumber,EnterSalesOrderItemNumber,EnterSalesOrderSize,SalesOrderValidationMessage);
				logOut();
	}
	catch(Exception e){
		failureReport("Submit OCS Shopify Form", "Failed to Submit OCS Shopify Form");
	}
 }
}