package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileExporter {
	public static ByteArrayInputStream generteExcelFile(List<Employee> employees) {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Employees");

			Row row = sheet.createRow(0);
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			// Creating header
			Cell cell = row.createCell(0);
			cell.setCellValue("First Name");
			cell.setCellStyle(headerStyle);

			cell = row.createCell(1);
			cell.setCellValue("Last Name");
			cell.setCellStyle(headerStyle);

			// Creating rows for each Employee
			int i = 0;
			for (Employee emp : employees) {
				Row dataRow = sheet.createRow(i + 1);
				dataRow.createCell(0).setCellValue(emp.getFirstName());
				dataRow.createCell(1).setCellValue(emp.getLastName());
				i++;
			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}