package Utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviderUtility {

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        String path = ".\\testData\\Data.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);
        int totalRows = excelUtility.getRowCount("Sheet1");
        int totalCols =excelUtility.getCellCount("Sheet1",1);

        String loginData[][]= new String[totalRows][totalCols];
        for(int r =1;r<=totalRows;r++){
            for (int c=0;c<totalCols;c++){
                loginData[r-1][c]= excelUtility.getCellData("Sheet1",r,c);
            }
        }
        return loginData;
    }
}
