package com.nareshIt.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nareshIt.entity.BooksExcelFileEntity;

public class Helper {

	public static boolean checkExcelfile(MultipartFile file) {
		if (file == null || file.isEmpty())
			return false;

//		String contentType = file.getContentType();
//		String fileName = file.getOriginalFilename();
//		if (fileName == null)
//			return false;
//
//		return (contentType != null
//				&& (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") || // .xlsx
//						contentType.equals("application/vnd.ms-excel") || // .xls
//						contentType.equals("application/vnd.oasis.opendocument.spreadsheet") // .ods
//				)) || (fileName.endsWith(".xlsx") || fileName.endsWith(".xls") || fileName.endsWith(".ods"));

		return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}

	public static List<BooksExcelFileEntity> excelFilesInsertDatabase(InputStream io) throws IOException {

		// Create empty list to store final book data
		List<BooksExcelFileEntity> list = new ArrayList<>();

		// Use Set to store unique product names (avoid duplicates)
		Set<String> uniqeProducts = new HashSet<>();

		// Create workbook object from input stream
		XSSFWorkbook work = new XSSFWorkbook(io);

		// Get the first sheet from workbook (Sheet 0)
		XSSFSheet sheetAt = work.getSheetAt(0);

		// Create row iterator to loop through Excel rows
		Iterator<Row> iteratorRows = sheetAt.iterator();

		// Skip the header row (usually first row)
		if (iteratorRows.hasNext()) {
			iteratorRows.next();
		}

		// Loop through remaining rows (actual data)
		while (iteratorRows.hasNext()) {

			// Get the current row
			Row iteratorRow = iteratorRows.next();

			// Get all cells in this row
			Iterator<Cell> cells = iteratorRow.iterator();

			// Create a new Book object to hold current row data
			BooksExcelFileEntity exceFile = new BooksExcelFileEntity();

			// Loop through each cell in the current row
			while (cells.hasNext()) {

				// Get current cell
				Cell cell = cells.next();

				// Decide based on column index
				switch (cell.getColumnIndex()) {

				// Column 0: Product Name
				case 0:
					exceFile.setProductName(cell.getStringCellValue());
					break;

				// Column 1: Description
				case 1:
					exceFile.setDescription(cell.getStringCellValue());
					break;

				// Column 2: Price (numeric value)
				case 2:
					exceFile.setPrice(cell.getNumericCellValue());
					break;

				// For any other column: Do nothing
				default:
					break;
				}

				// Only add the book if the product name is unique
				if (uniqeProducts.add(exceFile.getProductName())) {
					list.add(exceFile);
				}
			}
		}

		// Return the final list of book objects
		return list;
	}

}
