package com.demo.assignment.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.demo.assignment.model.Activity;

@Component("ExcelFileGenerator")
public class ExcelFileGenerator extends FileGenerator {
	
	@Override
	public ByteArrayInputStream generateOutputFile(List<Activity> activities) throws IOException {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("master-plan");
		
		assignFileHeaders(getFileHeaders(), sheet);
		generateOutputFile(activities, sheet);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workBook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());
	}
	
	private void assignFileHeaders(Map<Integer, String> headers, XSSFSheet sheet ) {
		XSSFRow headerRow = sheet.createRow(0);
		
		for(int position=0 ; position < headers.size(); ++position) {
			headerRow.createCell(position, Cell.CELL_TYPE_STRING).setCellValue(headers.get(position));;
			sheet.autoSizeColumn(position);
		}
	}
	
	private void generateOutputFile(List<Activity> activities, XSSFSheet sheet) {
		int rowNumber = 1;
		for(Activity activity: activities) {
			XSSFRow row = sheet.createRow(rowNumber++);
			row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(activity.getSerialNo());
			row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(activity.getName());
			row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(processDate(activity.getStartDate()));
			row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(processDate(activity.getEndDate()));
		}
	}

}
