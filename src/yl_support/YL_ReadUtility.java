package yl_support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * This class was create to get all excel data
 * Used for the classes CreateAccount, ContactUs and AddtoCart
 */

public class YL_ReadUtility {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	public static void setExcelFile(String Path, String SheetName) throws Exception {

		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet and save to variables
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow, int _totalCols)
			throws Exception {

		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int startCol = 1;
			int columni = 0, columnj = 0;
			int totalRows = 1;
			int totalColumns = _totalCols;
			tabArray = new String[totalRows][totalColumns];
			
			// Here we access the file, cell by cell and save to the array created
			for (int j = startCol; j <= totalColumns; j++, columnj++) {
				if (columnj == 6 || columnj == 7) {
					tabArray[columni][columnj] = getCellData(iTestCaseRow, j, "int");
				} else {
					tabArray[columni][columnj] = getCellData(iTestCaseRow, j, "string");
				}
				System.out.println(tabArray[columni][columnj]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public static String getCellData(int RowNum, int ColNum, String type) throws Exception {

		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = "";
			if (type.equals("int")) {
				// Cell value converted to long as the number is a long number
				CellData = String.valueOf((long) Cell.getNumericCellValue());
			} else {
				CellData = Cell.getStringCellValue();
			}
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	public static String getTestCaseName(String sTestCase) throws Exception {

		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			throw (e);
		}
	}

	public static int getRowContains(String sTestCaseName, int colNum) throws Exception {

		int i;
		try {
			int rowCount = YL_ReadUtility.getRowUsed();
			for (i = 0; i < rowCount; i++) {
				if (YL_ReadUtility.getCellData(i, colNum, "string").equalsIgnoreCase(sTestCaseName)) {
					break;
				}
			}
			return i;
		} catch (Exception e) {
			throw (e);
		}
	}

	public static int getRowUsed() throws Exception {
		try {
			int RowCount = ExcelWSheet.getLastRowNum();
			return RowCount;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}
}
