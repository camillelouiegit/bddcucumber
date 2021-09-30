package com.framework.core;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtility {

	// make it local variable
	private static XSSFWorkbook excelWBook;
	private static XSSFSheet excelWSheet;
	private static XSSFCell excelCell;
	private static XSSFRow excelRow;
	private static String tcID;
	private static int tcIDIndex;

	// make it local variable
	public static List<String> headerList;
	public static List<String> tcDataList;

	@SuppressWarnings("rawtypes")
	public static Map getTCData(String testCaseID) throws Exception {

		// Get Headers
		headerList = new ArrayList<String>();
		excelRow = excelWSheet.getRow(0);
		int counter = 0;
		String header;
		do {
			excelCell = excelRow.getCell(counter);
			header = excelCell.getStringCellValue();
			headerList.add(header);
			counter++;
		} while (header != "");

		for (String temp : headerList) {
			Log.testStep("INFO", "temp value = " + temp, "temp value = " + temp);
		}

		// set TCID and get Row index
		int counterRow = 0;
		String testCaseIDValue;
		do {
			excelCell = excelWSheet.getRow(counterRow).getCell(0);
			testCaseIDValue = excelCell.getStringCellValue().toString();
			Log.testStep("INFO", "Row Value = " + testCaseIDValue, "Row Value = " + testCaseIDValue);
			if (testCaseIDValue.equalsIgnoreCase(testCaseID)) {
				tcID = testCaseIDValue;
				tcIDIndex = counterRow;
				Log.testStep("INFO", "Test Case ID = " + testCaseIDValue, "Test Case ID = " + testCaseIDValue);
				break;
			} else {
				Log.testStep("INFO", "Test Case ID " + testCaseIDValue + " is not equal to " + testCaseID,
						"Test Case ID = " + testCaseIDValue + " not equal to " + testCaseID);
			}
			counterRow++;
		} while (testCaseIDValue != "");

		Log.testStep("INFO", "Test Case ID = " + tcID, "Test Case ID = " + tcID);
		Log.testStep("INFO", "Test Case ID row index = " + tcIDIndex, "Test Case ID row index = " + tcIDIndex);

		// get TCData
		tcDataList = new ArrayList<String>();
		int dataCounter = 1;
		String tcDataRowValue;
		do {
			excelCell = excelWSheet.getRow(tcIDIndex + dataCounter).getCell(0);
			tcDataRowValue = excelCell.getStringCellValue();
			tcDataList.add(tcDataRowValue);
			counter++;
		} while (tcDataRowValue != "");

		for (String temp : tcDataList) {
			Log.testStep("INFO", "temp value = " + temp, "temp value = " + temp);
		}

		return null;
	}

	public static String getTestData(String TCID, String headerName) throws Exception {

		excelRow = excelWSheet.getRow(0);
		int counter = 0;
		int headerIndex = 0;
		String header;
		do {
			excelCell = excelRow.getCell(counter);
			header = excelCell.getStringCellValue();
			if (header.equalsIgnoreCase(headerName)) {
				headerIndex = counter;
				break;
			} else {

			}
			counter++;
		} while (header != "");

		int counterRow = 0;
		int rowIndex = 0;
		String testCaseIDValue;
		do {
			excelCell = excelWSheet.getRow(counterRow).getCell(0);
			testCaseIDValue = excelCell.getStringCellValue().toString();
			if (testCaseIDValue.equalsIgnoreCase(TCID)) {
				rowIndex = counterRow;
				break;
			} else {

			}
			counterRow++;
		} while (testCaseIDValue != "");

		excelCell = excelWSheet.getRow(rowIndex).getCell(headerIndex);
		String testData = excelCell.getStringCellValue();
		return testData;
	}

	public static void setSheetName(String sheetname) throws Exception {
		// Open the Excel file
		String finalPath = System.getProperty("user.dir") + "/src/main/resources/TestData/TestData.xlsx";
		FileInputStream ExcelFile = new FileInputStream(finalPath);
		excelWBook = new XSSFWorkbook(ExcelFile);
		excelWSheet = excelWBook.getSheet(sheetname);
	}
}
