package com.tests.automation;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SeleniumExcel {
	

	
	public void createTable(List<String> headerList, List<List<String>> tableDataList) {
		Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Table Data");
        
        Row headerRow = sheet.createRow(0);
        //use this haeder list to loop
        int cellCount = 0;
        for(String header : headerList) {
        	Cell cell = headerRow.createCell(cellCount);
        	cell.setCellValue(header);
        	cellCount++;
        }
        
         int rows = 1;
        // 2. Create Rows - Body
        for(List<String> tableRowData : tableDataList) {
        	cellCount = 0;
        	
        	Row dataRow = sheet.createRow(rows);
        	for(String rowCellData : tableRowData) {
        		Cell cell = dataRow.createCell(cellCount);
        		cell.setCellValue(rowCellData);
        		cellCount++;
        	}
        	
        	rows++;
        }
        
     // 3. Write the workbook to a file
        try {
        	FileOutputStream fileOut = new FileOutputStream("firebase_tabledata.xlsx");
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	

    public static void main(String[] args) {
        // 1. Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Table Data");

        // 2. Create a row and cell
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Google HyperLink");

        // 3. Use CreationHelper to create the hyperlink
        CreationHelper createHelper = workbook.getCreationHelper();
        Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.URL);
        hyperlink.setAddress("https://www.google.com");

        // 4. Assign the hyperlink to the cell
        cell.setHyperlink(hyperlink);

        // Apply a blue color style to the hyperlink cell to make it more recognizable as a link
        CellStyle hlinkStyle = workbook.createCellStyle();
        Font hlinkFont = workbook.createFont();
        hlinkFont.setUnderline(Font.U_SINGLE);
        hlinkFont.setColor(IndexedColors.BLUE.getIndex());
        hlinkStyle.setFont(hlinkFont);
        cell.setCellStyle(hlinkStyle);

        // 5. Write the workbook to a file
        try {
        	FileOutputStream fileOut = new FileOutputStream("firebase_tabledata.xlsx");
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

