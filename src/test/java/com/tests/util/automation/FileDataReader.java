package com.tests.util.automation;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileDataReader {
	
	private  XSSFWorkbook excelWorkBook;
	private  XSSFSheet excelWorkSheet;
	private  XSSFRow excelRow;
	private  XSSFCell excelCell;

	public Object[][] getLoginData(String filePath, String sheetName) throws Exception {

		String logindata[][] = null;

		FileInputStream excelFile = new FileInputStream(filePath);
		
		excelWorkBook = new XSSFWorkbook(excelFile);
		excelWorkSheet = excelWorkBook.getSheet(sheetName);
		System.out.println(excelWorkSheet.getSheetName());
		int rowsInExcel = excelWorkSheet.getPhysicalNumberOfRows();
		int columnsInExcel = excelWorkSheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(columnsInExcel);
		System.out.println(rowsInExcel);
		logindata = new String[rowsInExcel][columnsInExcel];

		for (int row = 0; row < rowsInExcel; row++) {
			System.out.println(row);
			for (int column = 0; column < columnsInExcel; column++) {
				logindata[row][column] = getCellValue(row, column);
			}
		}
		excelWorkBook.close();
		return logindata;
	}

	public String getCellValue(int rowNum, int colNum) throws Exception {
		excelRow = excelWorkSheet.getRow(rowNum);
		excelCell = excelRow.getCell(colNum);
		System.out.println("cell info " + excelCell);
		String cellValue = excelCell.getStringCellValue();
		return cellValue;

	}
}
