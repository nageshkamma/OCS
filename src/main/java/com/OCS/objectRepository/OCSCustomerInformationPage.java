package com.OCS.objectRepository;

import org.openqa.selenium.By;

public class OCSCustomerInformationPage {

	
	public static By CustEmailAddress =By.id("checkout_email");
	public static By CustFirstName =By.xpath("//label[text()='First name']//following-sibling::input");
	public static By CustLastName =By.xpath("//label[text()='Last name']//following-sibling::input");
	public static By CustAddress =By.xpath("//label[text()='Address']//following-sibling::input");
	public static By CustApartment =By.xpath("//label[contains(text(),'Apartment')]//following-sibling::input");
	public static By CustCity =By.xpath("//input[@id='checkout_shipping_address_city']");
	public static By CustPostalCode =By.xpath("//input[@id='checkout_shipping_address_zip']");
	public static By CustPincode =By.xpath("//label[text()='PIN code']//following-sibling::input");
	public static By CustPhoneNo =By.xpath("//label[text()='Phone']//following-sibling::input");
	public static By ClickContinueToShipping=By.xpath("//button[@type='submit']");
	public static By ClickContinue=By.id("continue_button");

	
	//input[@id='checkout_shipping_address_zip']
	
}
