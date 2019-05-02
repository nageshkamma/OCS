package com.OCS.utilities;

import java.io.File;
import java.util.HashMap;

import com.OCS.accelerators.Configuration;
import com.OCS.accelerators.TestEngine;

import jxl.Sheet;
import jxl.Workbook;

public class Data_Provider {
	
	public static String[][] getTableArray(String sheetName) throws Exception{
		try{

			Workbook workbook = Workbook.getWorkbook(new File("Data"+File.separator+Configuration.GetProperty("CurrentApplication")+File.separator+"TestData.xls"));
 
			Sheet sheet = workbook.getSheet(sheetName);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			String[][] tabArray=new String[rows-1][cols];
			for (int i=1;i<rows;i++){
				for (int j=0;j<cols;j++){
					tabArray[i-1][j]=sheet.getCell(j,i).getContents();
				}
			}
			System.out.println("");

			workbook.close();
			return(tabArray);
		}
		catch (Exception e) {
			System.out.println(e+Thread.currentThread().getStackTrace()[1].getClassName()+" dataprovider");
			return null;
		}

	}
	public static HashMap<String,String> getTestData(String sheetName, String executeTCName) throws Exception{//, String executeTCName, String executeStatus
		try{
			System.out.println(TestEngine.application);
			HashMap<String,String> data=new HashMap<String,String>();
			String filePath=System.getProperty("user.dir")+File.separator+"Data"+File.separator+TestEngine.application+File.separator+"TestData.xls";
			Workbook workbook = Workbook.getWorkbook(new File(filePath));
			String[]  tdata=null;
			Sheet sheet = workbook.getSheet(sheetName);
			int rows = sheet.getRows();
			int cols = sheet.getColumns(); 
			String[]  tabHeader=new String[cols];
			for (int i=0;i<cols;i++){
				  tabHeader[i]=sheet.getCell(i,0).getContents();
			}
			
			for (int i=1;i<rows;i++){ 
				if(sheet.getCell(0,i).getContents().equalsIgnoreCase("Yes") && sheet.getCell(1,i).getContents().equalsIgnoreCase(executeTCName)){ //matching key;
					tdata=new String[cols];
					
				     for (int j=0;j<cols;j++){
				    	 
				    	 	if(sheet.getCell(j,i).getContents().equalsIgnoreCase("n/a")){
				    	 		tdata[j]="";
				    	 	}else{
				    	 		tdata[j]=sheet.getCell(j,i).getContents();
				    	 	}
				    	
					}
				     
				    break; 
					}
				}
			 for(int i=0;i<tabHeader.length;i++){
				 data.put(tabHeader[i], tdata[i]);
			  }

			workbook.close();
			return data;
			
		}
		catch (Exception e) {
			System.out.println(e+Thread.currentThread().getStackTrace()[1].getClassName()+" dataprovider");
			return null;
		}

	}
	


}

