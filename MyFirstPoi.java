package com.example.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MyFirstPoi {

	public static void main(String[] args) {
		
		//Excel Workbook
		XSSFWorkbook workbook = null;

		//Excel Sheet
		XSSFSheet sheet = null;
						
		try {
			
			// Input file name
			String excelFileName = "D:\\EclipseLatest\\excelOutput\\MyFirstExcel.xlsx";
			File excelFile = new File(excelFileName);
			
			// Output File name
			FileOutputStream out = new FileOutputStream(excelFile);
			
			workbook = new XSSFWorkbook();
			sheet = workbook
					.createSheet("FirstSheet");
			
			int rowNum = 0; //row number
			int cellNum = 0; //cell number
			
			Row rowOne = sheet.createRow(rowNum);
			Cell cellOneRowOne = rowOne.createCell(cellNum);
			cellOneRowOne.setCellValue("My First Row, First Cell Value");
			
			cellNum++;
			Cell cellTwoRowOne = rowOne.createCell(cellNum);
			cellTwoRowOne.setCellValue("My First Row, Second Cell Value");
		
			
			//reset cell no.
			cellNum = 0;
			rowNum++;//Second row
			
			Row rowTwo = sheet.createRow(rowNum);
			Cell cellOneRowTwo = rowTwo.createCell(cellNum);
			cellOneRowTwo.setCellValue("My Second Row, First Cell Value");
			
			cellNum++;
			Cell cellTwoRowTwo = rowTwo.createCell(cellNum);
			cellTwoRowTwo.setCellValue("My Second Row, Second Cell Value");		
			
			workbook.write(out);
			out.close();
			workbook.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
