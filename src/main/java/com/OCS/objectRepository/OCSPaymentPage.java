package com.OCS.objectRepository;
import org.openqa.selenium.By;

public class OCSPaymentPage {

	
	
	public static By EnterCardNumber =By.xpath("//label[text()='Card number']//following-sibling::input[1]");
	public static By EnterCardName =By.xpath("//input[@placeholder='Name on card']");
	public static By EnterCardExpiry =By.xpath("//label[text()='Expiration date (MM / YY)']//following-sibling::input[1]");
	public static By EnterCodeSecurityCode =By.xpath("//input[@placeholder='Security code']");
	public static By ClickCompleteOrder =By.xpath("//div[@class='shown-if-js']");
	public static By GetOrderNumber =By.xpath("//span[@class='os-order-number']");
	public static By SelectCancel =By.xpath("//button[contains(text(),'Cancel')]");
	
}
