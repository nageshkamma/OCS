package com.OCS.objectRepository;

import org.openqa.selenium.By;

public class OCSShopifyHomePage {

	public static By SelectDay =By.id("dob__day");
	public static By SelectMonth =By.id("dob__month");
	public static By SelectYear =By.id("dob__year");
	public static By ClickVerify =By.xpath("//button[@class='btn btn--outline']");
	public static By SelectConfirmCheckbox =By.xpath("//label[@for='dob__confirm__checkbox' and //i[@class='icon icon--check']]");
	public static By ClickConfirm =By.xpath("//button[contains(text(),'CONFIRM')]");
	public static By ClickStartBrowsing =By.xpath("//button[contains(text(),'Start Browsing')]");
	public static By SelectAccessories =By.xpath("//h3[@class='menu__item--title' and contains(text(),'Accessories')]");
	public static By ClickShopAllAccessories =By.xpath("//a[@href='/collections/all-accessories' and @class='btn btn--primary btn--outline menu__tier-two__btn']");
	public static By SelectProduct =By.xpath("//h3[contains(text(),'Unbleached Tips')]");
	public static By SearchProduct =By.xpath("//form[@title='search-form']/input[@type='search']");
	//public static By ClickProduct =By.xpath("//p[contains(text(),'RAW')]//following-sibling::p[1]");
	//public static By ClickProduct =By.xpath("//p[contains(text(),'RAW')]//following-sibling::p[text()='Classic Papers']");
	public static By ClickProduct =By.xpath("//div[@class='aa-product-text']//*[(text()='Classic Papers')]");
	public static By ClickCart =By.xpath("//div[@class='gram-widget']//descendant::span[@class='cart-count js-cart-count']");
	
	
	public static By SelectOnlineStore =By.xpath("//span[text()='Online Store']/../following-sibling::div");
	public static By SelectCatalog =By.xpath("//ul[@id='SiteNav']//following-sibling::span[text()='Catalog']");
	//public static By SelectProduct =By.xpath("(//span[text()='1:1 Sativa Drops (10-15 mg/ml THC, 10-15 mg/ml CBD)']/parent::a)[1]");
	
	public static By AddToCart =By.xpath("//button[@name='add']");
	public static By AddToBag =By.xpath("//span[text()='Add to Bag']");
	public static By SelectConfirmAge =By.xpath("//label[@for='age_confirm--flyout']");
	public static By SelectAcceptingTerms =By.xpath("//input[@id='terms_confirm--flyout']");
	public static By ClickCheckout =By.xpath("//a[contains(text(),'Checkout')]");
	
	public static By Checkout =By.xpath("//input[@value='Check out']");
	
	
}
