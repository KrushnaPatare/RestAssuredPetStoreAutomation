package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getA11Data() throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownum = xl.getRowCount("Sheetl");
		int colcount = xl.getCellCount("Sheetl", 1);
		
		String apidata[][] = new String[rownum][colcount];
		for(int i=1; i<=rownum; i++) 
		{
			for(int j=0; j<colcount; j++) 
			{
				apidata[i-1][j]= xl.getCellData("Sheet1", i, j);
			}
		}
			
		return apidata;
	}
	
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//Pserdata.xlsx";
		XLUtility xl = new XLUtility(path);
	
		int rownum = xl.getRowCount("Sheet1");
		String apidata[] = new String[rownum];
	
		for(int i=1; i<=rownum; i++)
		{ 
			apidata[i-1]= xl.getCellData("Sheet1",i, 1);
		}
	
		return apidata;
	}
	

}
