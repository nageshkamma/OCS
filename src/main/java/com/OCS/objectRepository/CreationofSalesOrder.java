package com.OCS.objectRepository;

import org.openqa.selenium.By;

public class CreationofSalesOrder {
	
	
	public static By ClickmodulesPaneOpener =By.xpath("//div[@class='modulesPane-opener']");
	public static By AccountsReceivable =By.xpath("//div[@class='modulesPane-flyout slideInL']//a[@title='Accounts receivable']");
	public static By ClickAllSalesOrders =By.xpath("//div[@class='modulesFlyout dashboardTiles_wide']//a[@title='All sales orders']");
	
	
	
	public static By ClickNewSalesOrderButton =By.xpath("//span[@class='button-commandRing New-symbol']");
	public static By EnterCustomerAccountNumber =By.xpath("//input[@type='text'][@name='SalesTable_CustAccount']");
	public static By ClickOkButton =By.xpath("//text()[contains(.,'OK')]/ancestor::div[1]");
	public static By EnterSalesOrderItemNumber =By.xpath(" //*[contains(@id,'SalesLine_ItemId_input')]");
	public static By EnterSalesOrderSize =By.xpath("//*[contains(@id,'InventoryDimensionsGrid_InventSizeId_input')]");
	public static By ClickOnSaveButton =By.xpath("//div[@class='primaryConductor']//span[@class='button-commandRing Save-symbol']");
	public static By ClickSalesOrder =By.xpath("//*[@id='salestablelistpage_1_SalesOrder_button']/span[normalize-space(.)='Sales order']");
    public static By ClickSalesOrder5 =By.xpath("//*[contains(@id,'salestablelistpage') and not(contains(@aria-expanded,'false'))]/button//span[normalize-space(.)='Sales order']");
	public static By ClickOnSELLTab =By.xpath("(//span[normalize-space(.)='Sell'])[2]");
	public static By ClickOnGenerateTab =By.xpath("//label[text()='Generate']");
	public static By ClickOnConfirmSalesOrderTab =By.xpath("//span[text()='Confirm sales order']");
	public static By ClickOnConfirmSalesOrderOkButton =By.xpath("//*[contains(@id,'OK_label')]");
	public static By ClickOnConfirmSalesOrderPostOkButton =By.xpath("//*[contains(@id,'Ok')and @class='button-label'] ");
	public static By SalesOrderValidationMessage =By.xpath("//div[@class='primaryConductor']//div[@class='messageBar-holder messageBar-message-truncate']//span[@class='messageBar-message']");
	public static By SizeArrowButton =By.xpath("//*[contains(@id,'InventSizeId')]//div[@class='lookupButton']");
	public static By CustAccountArrowButton =By.xpath("//*[contains(@id,'CustAccount')]//div[@class='lookupButton']");
	public static By SizeSelection =By.xpath("//*[contains(@id,'SysGen_InventSizeId_input')]");
	public static By CustAccntSelection =By.xpath("//*[contains(@id,'CustTable_AccountNum_input') and contains(@title,'C00000032')]");
	public static By VerifySalesOrder =By.xpath("//div[@class='primaryConductor']//div[@class='group_content fill-width layout-container layout-horizontal layout-horizontal-bottomalign']//span[@data-dyn-controlname='HeaderTitle']");
	public static By ClickOnHeaderButton =By.xpath("(//div[@class='pivotItem-content fill-width fill-height layout-container layout-vertical']//span[@class='label radioButton-label staticText'])[2]");
	public static By Documentstatus =By.xpath("//*[contains(@data-dyn-controlname,'status_DocumentStatus') and (contains(@aria-expanded,'false'))]");
	////text()[contains(.,'Sales order')]/ancestor::button[1]




}
