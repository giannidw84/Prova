package create.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	private int maxTreeNode = 0;

	public void writeExcel(ArrayList<String> titleData, List<List<String>> rowsData, String excelFilePath,
			String sheetName) throws IOException {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Prepara titoli delle colonne
		ArrayList<String> header = excelHeader(titleData, rowsData);

		// Scrive i titoli delle colonne
		for (int i = 0; i < header.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(header.get(i));
			cell.setCellStyle(headerCellStyle);
		}

		// Scrive le righe di dettaglio
		int rowCount = 0;

		for (List<String> rowData : rowsData) {
			Row row = sheet.createRow(++rowCount);
			writeRow(rowData, row, this.maxTreeNode);
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < header.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}
	}

	private ArrayList<String> excelHeader(ArrayList<String> titleData, List<List<String>> rowsData) {

		// determina il numero di celle profondità, prendendo il valore massimo di
		// PROFONDITA

		for (List<String> row : rowsData) {
			if (maxTreeNode < Integer.parseInt(row.get(0))) {
				maxTreeNode = Integer.parseInt(row.get(0));
			}
		}

		// crea intestazione colonne
		ArrayList<String> title = new ArrayList<String>();

		// ciclo per scrivere i numeri 0, 1, 2, 3 .... nel titolo
		for (int i = 0; i <= maxTreeNode; i++) {
			title.add(Integer.toString(i));
		}

		// ciclo per scrivere i titoli
		for (int i = 0; i < titleData.size(); i++) {
			title.add(titleData.get(i));
		}

		return title;
	}

	private void writeRow(List<String> rowData, Row row, int maxTreeNode) {

		int x = 0;

		for (int i = 0; i <= maxTreeNode; i++) {

			Cell cell = row.createCell(i);

			if (i == Integer.parseInt(rowData.get(0))) {

				cell.setCellValue("X");
			} else {
				cell.setCellValue(" ");
			}
			x = i;
		}

		for (int i = 1; i < rowData.size(); i++) {
			x++;
			Cell cell = row.createCell(x);
			cell.setCellValue(rowData.get(i));
		}

	}

}
