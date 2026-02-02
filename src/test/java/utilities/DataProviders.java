package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;


public class DataProviders {
  @DataProvider(name="loginData")
    public String[][] getData() throws IOException {

     // String path = "C:\\dhaya-github\\HybridDrivenFramework\\src\\testdata\\validAndInvalidLoginData.xlsx";
      String path = ".\\testdata\\validAndInvalidLoginData.xlsx";
      //taking xl file from testdata

      ExcelUtility xlUtil = new ExcelUtility(path);//creating object for xlutility

      int totalRows =  xlUtil.getRowCount("Sheet1"); // getting row count

      int totalCells = xlUtil.getCellCount("Sheet1",1);// getting cell count

      //int[][] a = new int[][]; below line is like this
      String loginData[][] = new String[totalRows][totalCells];//created for 2 dimentional Array which can store xl data

      for(int i=1; i<=totalRows; i++){ //1 read the data from xcel and storing  into 2 deminsional array

          for (int j=0; j<totalCells;j++){ //0 i is rows j is cells

              loginData[i-1][j] = xlUtil.getCellData("Sheet1",i,j); //1,0
          }

      }
      return loginData;

  }
    }

    //for understanding above code
//1️⃣ How Excel data is passed to this test method
//        Your DataProvider (earlier)
//@DataProvider(name="LoginData")
//public String[][] getData()
//
//
//        This returns a 2D array like this 👇
//
//        Example Excel (Opencart_LoginData.xlsx)
//        Email	Password	Expected
//        valid@mail.com
//	pass123	Valid
//            wrong@mail.com
//	pass123	Invalid
//            valid@mail.com
//	wrong	Invalid
//            Converted to 2D Array
//            {
//            {"valid@mail.com", "pass123", "Valid"},
//            {"wrong@mail.com", "pass123", "Invalid"},
//            {"valid@mail.com", "wrong", "Invalid"}
//            }
//
//
//            👉 Each row = one test execution
//            👉 Each column = one parameter
//
//            2️⃣ How this data reaches the test method
//            Your Test Annotation
//@Test(
//        dataProvider="LoginData",
//        dataProviderClass=DataProviders.class
//)
//public void verify_loginDDT(String email, String pwd, String exp)
//
//        🔁 What TestNG does internally
//
//        TestNG does this automatically:
//
//        verify_loginDDT("valid@mail.com", "pass123", "Valid");
//        verify_loginDDT("wrong@mail.com", "pass123", "Invalid");
//        verify_loginDDT("valid@mail.com", "wrong", "Invalid");
//
//
//        📌 No manual calling needed


