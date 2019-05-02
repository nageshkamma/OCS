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

public class OCSShopify_SalesOrder extends BusinessFunctions{
	
	
	@Test
	public void OCSShopify_SalesOrder() throws Throwable{
	try{
		//Read the excel data
		tstData = Data_Provider.getTestData("OCSShopify_SalesOrder", "OCSShopify_SalesOrder");
	
		String username = tstData.get("UserName");	
		String password = tstData.get("Password");
		String day = tstData.get("Day");	
		String month = tstData.get("Month");	
		String year = tstData.get("Year");	
		String email = tstData.get("CustEmail");
		String firstName = tstData.get("CustFname");
		String lastName = tstData.get("CustLname");
		String address = tstData.get("CustAddress");
		String city = tstData.get("CustCity");
		String postalCode = tstData.get("CustPostalCode");
		String phNo = tstData.get("CustPhno");
		
		String cardNumber = tstData.get("CardNumber");
		String cardHolderName = tstData.get("CardHolderName");
		String cardExpiry = tstData.get("CardExpiry");
		String cardSecurityCode = tstData.get("CardSecurityCode");
		String selectProduct = tstData.get("SelectProduct");
		String ShopifyINT01_URL = credentialManager.get("ShopifyINT01_URL");
		String D365INT01_URL = credentialManager.get("INT01_URL");
		
		
		//Launching Shopify
		OCSShopifylaunchURL(ShopifyINT01_URL);
		selectActionByVisibleText(OCSShopifyHomePage.SelectDay,day);
		selectActionByVisibleText(OCSShopifyHomePage.SelectMonth,month);
		selectActionByVisibleText(OCSShopifyHomePage.SelectYear,year);
		click(OCSShopifyHomePage.ClickVerify, "Click on Verify");
		click(OCSShopifyHomePage.SelectConfirmCheckbox, "Select Confirm and Acknowledge");
		click(OCSShopifyHomePage.ClickConfirm, "Select Confirm");
		click(OCSShopifyHomePage.ClickStartBrowsing, "Click on Start Browsing");
		click(OCSShopifyHomePage.SelectAccessories, "Select Accessories");
		click(OCSShopifyHomePage.ClickShopAllAccessories, "Click on Shop All Accessories");
		selectProduct(selectProduct);
				
		/*type(OCSShopifyHomePage.SearchProduct,"classic papers", "Enter product");
		Thread.sleep(8000);
		click(OCSShopifyHomePage.ClickProduct, "Select Product");*/
		click(OCSShopifyHomePage.AddToBag, "Add items to bag");
		click(OCSShopifyHomePage.ClickCart, "Go to Cart");
		click(OCSShopifyHomePage.SelectConfirmAge, "Confirming Age greater than 19");
		click(OCSShopifyHomePage.SelectAcceptingTerms, "Accepting Terms");
		Thread.sleep(3000);
		click(OCSShopifyHomePage.ClickCheckout, "Select Checkout");
		EnterCustomerInformation(email,firstName,lastName,address,city,postalCode,phNo);
		click(OCSCustomerInformationPage.ClickContinueToShipping, "Click Continue To Shipping");
		click(OCSShippingMethodPage.SelectContinueToPayment, "Select Continue To Payment");
		EnterPaymentInformation(cardNumber,cardHolderName,cardExpiry,cardSecurityCode);
		click(OCSPaymentPage.ClickCompleteOrder, "Select Continue To Payment");
		Thread.sleep(3000);
		cancelSurvey();
		CaptureOrderNumber(OCSPaymentPage.GetOrderNumber);

		
		//Launching D365
		OCSShopifylaunchURL(D365INT01_URL);
		OCS_Login(username,password);
		goToAllSalesOrders();
		searchCRN();
		click(D365SalesOrderPage.ClickPickAndPack, "Select Pick And Pack");
		click(D365SalesOrderPage.ClickOnPickingList, "Click on Picking List");
		getTextForTitle(D365SalesOrderPage.getPickingRoute,"Picking Route");
		getTextForTitle(D365SalesOrderPage.getSentTo3PL,"SentTo3PL");
		click(D365SalesOrderPage.ClickClose, "Close the form");
		click(D365SalesOrderPage.ClickHeader, "Go to Header");
		getTextForTitle(D365SalesOrderPage.getDocumentStatus,"Document Status");
		logOut();
		
	}
	catch(Exception e){
		e.printStackTrace();
		failureReport("Submit OCS Shopify Form", "Failed to Submit OCS Shopify Form");
	}
 }
}