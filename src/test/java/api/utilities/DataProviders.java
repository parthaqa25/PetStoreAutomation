package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="Data")
	public String [][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";  //taking excel file from testData
		
		XlUtility xl=new XlUtility(path);  //creating an object for utility
		
		int rownum=xl.getRowCount("Sheet1");
		int colcount=xl.getCellCount("Sheet1", 1);
		
		String apidata[][]=new String[rownum][colcount]; //created for two dimensional array which can store
		
		for(int i=1;i<=rownum;i++)  //1   //read the data from excel and storing it into two dimensional array
		{
			for(int j=0;j<colcount;j++) //0  //1 is rows and j is col
			{
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j); //1,0 
			}
		}
		
		return apidata; //returning two dimensional array
	}
	
	//DataProvider2
	
	@DataProvider(name="UserNames")
	public String [] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";  //taking excel file from testData
		
		XlUtility xl=new XlUtility(path);  //creating an object for utility
		
		int rownum=xl.getRowCount("Sheet1");
		
		String apidata[]=new String[rownum];
		
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]= xl.getCellData("Sheet1", i, 1);
		}
		
		return apidata; //returning two dimensional array
	}
	
	
	//DataProvider3
	
	
	//DataProvider4

}
