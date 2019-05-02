package com.OCS.objectRepository;

import org.openqa.selenium.By;

public class D365PurchaseOrderPage {

	
	public static By ModuleOpener =By.xpath("//div[@class='modulesPane-opener']");
	public static By ModuleExpandNo =By.xpath("//div[@title='Modules' and @aria-expanded='false']");
	public static By PurchaseOrdersExpandNo =By.xpath("//a[@aria-expanded='false' and contains(text(),'Purchase orders')]");
	public static By SelectPurchaseOrdersForReview =By.xpath("//a[contains(text(),'Purchase orders for review')]");
	
	public static By SelectModules =By.xpath("//div[@title='Modules']");
	public static By SelectOrders =By.xpath("(//a[@title='Customers']//following-sibling::a[contains(text(),'Orders') and @aria-expanded='false'])[1]");
	public static By SelectAccountsPayable =By.xpath("//div[@title='Modules']//following-sibling::a[contains(text(),'Accounts payable')]");
	
	
	//Upgrade
	public static By ModuleExpandNoUpgrade =By.xpath("//div[@aria-expanded='false' and contains(text(),'Modules')]");
	public static By SelectModulesUpgrade =By.xpath("//div[contains(text(),'Modules')]");
	public static By SelectAccountsPayableUpgrade =By.xpath("//a[contains(text(),'Accounts payable')]");
	public static By SelectAllPurchaseOrders =By.xpath("//a[contains(text(),'All purchase orders')]");
	public static By ClickNew =By.xpath("//span[contains(text(),'New')]");
	public static By EnterVendorAccount =By.xpath("//input[@name='PurchTable_OrderAccount']");
	public static By ClickOK =By.xpath("//button[@type='button' and @name='OK']");
	
	public static By ClickClose =By.xpath("//span[@class='button-commandRing Cancel-symbol']");
	public static By ClickResponseOnLatestOrder =By.xpath("//span[contains(text(),'View response on latest sent order')]");
	public static By GetResponseStatus =By.xpath("//label[contains(text(),'Response status')]//following-sibling::div/div/input");
	//public static By CloseForm =By.xpath("(//span[@class='button-commandRing Cancel-symbol'])[2]");
	public static By CloseForm =By.xpath("//div[@class='appBar layout-reflow-scope fill-width']//descendant::span[@class='button-commandRing Cancel-symbol']");
	public static By ClickConfirmation =By.xpath("//span[contains(text(),'Confirmation')]");
	
	
	
	
	public static By SelectItem =By.xpath("//label[contains(text(),'Item number')]//following-sibling::input");
	public static By SelectSize =By.xpath("//label[contains(text(),'Size')]//following-sibling::input");
	public static By ClickPurchase =By.xpath("//span[text()='Purchase']");
	public static By ClickSendForConfirmation =By.xpath("//span[contains(text(),'Send for confirmation')]");
	
	public static By SwitchToPrint =By.xpath("//label[contains(text(),'Print purchase order')]//following-sibling::div/span[@role='switch']");
	public static By SwitchToPrint01 =By.xpath("//label[contains(text(),'Print purchase order')]//following-sibling::div/span[@role='checkbox']");
	public static By CheckPrintPONo =By.xpath("//label[contains(text(),'Print purchase order')]//following-sibling::div/span[@title='No']");
	public static By CheckPrintMngDestNo =By.xpath("//label[contains(text(),'Use print management destination')]//following-sibling::div/span[@title='No']");
	public static By ConfirmingPO =By.xpath("(//span[@class='button-label' and contains(text(),'OK')])[2]");
	//public static By VerifyConfirmingPO =By.xpath("(//input[@title='Confirmed'])[1]");
	public static By VerifyConfirmingPO =By.xpath("//div[@class='group_content layout-container layout-horizontal layout-right layout-horizontal-bottomalign']//following-sibling::input[@title='Confirmed']");
	
	public static By SwitchToPrintMngDest =By.xpath("//label[contains(text(),'Use print management destination')]//following-sibling::div/span[@role='checkbox']");
	
	public static By PrintNo =By.xpath("//label[contains(text(),'Print purchase order')]//following-sibling::div/span[@title='No']");
	
	public static By PrintNo01 =By.xpath("//label[contains(text(),'Print purchase order')]//following-sibling::div/span[@role='checkbox' and @aria-checked='true']");
	//public static By PrintNo =By.xpath("//label[contains(text(),'Print purchase order')]//following-sibling::div/span[@title='No']");
	
	public static By VerifyInExtReview =By.xpath("//input[@title='In external review']");
	
	public static By SelectVendorCollaboration =By.xpath("//a[contains(text(),'Vendor collaboration')]");
	public static By ClickHeader =By.xpath("//span[contains(text(),'Header')]");
	public static By getPONumber =By.xpath("//label[contains(text(),'Purchase order')]//following-sibling::input");
	public static By SearchPO =By.xpath("//input[@name='NavListFilter_Input']");
	public static By SearchAllPO =By.xpath("//input[@name='GridFilter_Input']");
	public static By SelectPreviewPrint =By.xpath("//span[contains(text(),'Preview/Print')]");
	public static By SelectOriginalPreview =By.xpath("//span[contains(text(),'Original preview')]");
	public static By ViewVendorInformation =By.xpath("//div[contains(text(),'Vendor information')]");
	public static By SelectExportPO =By.xpath("//span[contains(text(),'Export')]");
	public static By ClosePreview =By.xpath("(//div[@class='button-container']/span[@class='button-commandRing Cancel-symbol'])[2]");
	public static By ClickAccept =By.xpath("//span[contains(text(),'Accept')]");
	public static By EnterNotes =By.xpath("//label[contains(text(),'Notes')]/following-sibling::textarea");
	public static By SubmitOK =By.xpath("//span[contains(text(),'OK')]");
	
	public static By SelectProfile =By.xpath("//span[@class='navigation-bar-userInitials']");
	public static By ClickSignout =By.xpath("//a[@name='SignOut']");
	
}
