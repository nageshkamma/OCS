package com.OCS.accelerators;

import java.io.File;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;

public class CredentialManager {

	public static HashMap<String,String> getProperty(String environmentName) throws Exception{
		HashMap<String,String> data=new HashMap<String,String>();
		try{
			
			String filePath=System.getProperty("user.dir")+File.separator+"CredentialManager.xls";
			Workbook workbook = Workbook.getWorkbook(new File(filePath));
			
			Sheet sheet = workbook.getSheet(environmentName);
			int rows = sheet.getRows();
			int cols = sheet.getColumns(); 
			
			if(cols>2)
				System.out.println("Number of Columns are more than 2");
			
			for(int i=1;i<rows;i++){
				
				try {
					
					String propertyName=sheet.getCell(0,i).getContents().trim();
					String propertyValue=sheet.getCell(1,i).getContents().trim();
					
					if(!propertyName.isEmpty() || !propertyValue.isEmpty())
						data.put(propertyName,propertyValue);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			workbook.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Environment Name not Matched");
			return null;
		}
		return data;

	}
	
}

	
