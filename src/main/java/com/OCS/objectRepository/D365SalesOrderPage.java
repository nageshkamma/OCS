package com.OCS.objectRepository;

import org.openqa.selenium.By;

public class D365SalesOrderPage {

	
	public static By ModuleOpener =By.xpath("//div[@class='modulesPane-opener']");
	public static By ModuleExpandNo =By.xpath("//div[@title='Modules' and @aria-expanded='false']");
	public static By SelectModules =By.xpath("//div[@title='Modules']");
	public static By SelectOrders =By.xpath("(//a[@title='Customers']//following-sibling::a[contains(text(),'Orders') and @aria-expanded='false'])[1]");
	public static By SelectAccountsPayable =By.xpath("//div[@title='Modules']//following-sibling::a[contains(text(),'Accounts payable')]");
	public static By SelectAccountsReceivable =By.xpath("//div[@title='Modules']//following-sibling::a[contains(text(),'Accounts receivable')]");
	public static By SelectAllSalesOrders =By.xpath("//a[@title='All sales orders']");

	
	
	
	
	public static By ClickCRN =By.xpath("//div[contains(text(),'Customer requisition')]");
	public static By SelectMore =By.xpath("//div[@title='More']");
	public static By ClickOptions =By.xpath("//span[contains(text(),'Options')]");
	public static By SelectPersonalizeTheForm =By.xpath("//span[contains(text(),'Personalize this form')]");
	public static By SelectInsert =By.xpath("//span[contains(text(),'Insert')]");
	public static By SelectField =By.xpath("//span[contains(text(),'Field')]");
	public static By SelectColumn =By.xpath("//div[contains(text(),'Status')]");
	public static By SelectCRNcheckbox =By.xpath("//input[@title='Customer requisition']/../preceding-sibling::label//span[@class='checkBox']");
	public static By ClickInsert =By.xpath("(//div[@class='button-container']//span[contains(text(),'Insert')])[2]");
	public static By CloseOptions =By.xpath("//span[contains(text(),'Close')]");
	
	
	public static By SearchCRN =By.xpath("//label[contains(text(),'Filter field: Customer requisition, operator')]//following-sibling::input");
	public static By ClickApply =By.xpath("//span[contains(text(),'Apply')]");
	public static By SelectSO =By.xpath("(//input[@title='22860'])[2]/../preceding-sibling::div[9]");
	
	public static By ClickPickAndPack =By.xpath("(//span[contains(text(),'Pick and pack')])[2]");
	public static By ClickOnPickingList =By.xpath("//span[text()='Picking list']");
	public static By getPickingRoute =By.xpath("//label[text()='Picking route']//following-sibling::span[2]");
	public static By getSentTo3PL =By.xpath("//label[text()='Sent to 3PL']//following-sibling::div//input");
	
	public static By ClickClose =By.xpath("(//span[@class='button-commandRing Cancel-symbol'])[3]");
	public static By ClickHeader =By.xpath("//span[contains(text(),'Header')]");
	public static By getDocumentStatus =By.xpath("//label[contains(text(),'Document status')]//following-sibling::div//input");
	public static By EmailAddress =By.xpath("//input[@type='email']");
	
}
