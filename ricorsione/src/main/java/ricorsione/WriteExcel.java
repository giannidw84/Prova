package ricorsione;

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

	private int maxProfondita;
	
	public void writeExcel(List<Excel> listExcel, String excelFilePath, String sheetName) throws IOException {

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
		
		// Creo la lista dei titoli delle colonne
		ArrayList<String> listaTitoliColonne = new ArrayList<String>();
		listaTitoliColonne.add("ServiceId");
		listaTitoliColonne.add("NodeName");
		listaTitoliColonne.add("NodeType");
		listaTitoliColonne.add("GroupType");
		listaTitoliColonne.add("FlowType");
		listaTitoliColonne.add("ResourceId");

		// Prepara titoli delle colonne
		ArrayList<String> rigaTitoli = excelHeader(listExcel, listaTitoliColonne);

		// Scrive i titoli delle colonne
		for (int i = 0; i < rigaTitoli.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(rigaTitoli.get(i));
			cell.setCellStyle(headerCellStyle);
		}

		// Scrive le righe di dettaglio
		int rowCount = 0;

		for (Excel lExcel : listExcel) {
			Row row = sheet.createRow(++rowCount);
			writeDetail(lExcel, row, this.maxProfondita);
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < rigaTitoli.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}
	}

	private ArrayList<String> excelHeader(List<Excel> listExcel,ArrayList<String> listaTitoliColonne) {

		// determina il numero di celle profondità, prendendo il valore massimo di
		// PROFONDITA

		for (int i = 0; i < listExcel.size(); i++) {

			if (maxProfondita < listExcel.get(i).getProfondita()) {
				maxProfondita = listExcel.get(i).getProfondita();
			}
		}

		//System.out.println("Numero di celle profondita: " + maxProfondita);

		// crea intestazione colonne
		ArrayList<String> title = new ArrayList<String>();

		int x = 0;
		
		// ciclo per scrivere i numeri 0, 1, 2, 3 .... nel titolo
		for (int i = 0; i <= maxProfondita; i++) {
			title.add(Integer.toString(i));
		}

		// ciclo per scrivere i titoli
		for (int i = 0; i < listaTitoliColonne.size(); i++) {
			title.add(listaTitoliColonne.get(i));
		}
		
		return title;
	}

	private void writeDetail(Excel aExcel, Row row, int maxColonne) {

		int x = 0;

		for (int i = 0; i <= maxColonne; i++) {

			Cell cell = row.createCell(i);

			if (i == aExcel.getProfondita()) {

				cell.setCellValue("X");
			} else {
				cell.setCellValue(" ");
			}
			x = i;
		}

		x++;
		Cell cell = row.createCell(x);
		if (aExcel.getServiceId() == 0) {
			cell.setCellValue(" ");
		} else {
			cell.setCellValue(aExcel.getServiceId());
		}
		
		x++;
		cell = row.createCell(x);
		cell.setCellValue(aExcel.getNodeName());
		
		x++;
		cell = row.createCell(x);
		cell.setCellValue(aExcel.getNodeType());
		
		x++;
		cell = row.createCell(x);
		cell.setCellValue(aExcel.getGroupType());
		
		x++;
		cell = row.createCell(x);
		cell.setCellValue(aExcel.getFlowType());
		
		x++;
		cell = row.createCell(x);

		if (aExcel.getResourceId() == 0) {
			cell.setCellValue(" ");
		} else {
			cell.setCellValue(aExcel.getResourceId());
		}

	}

}
