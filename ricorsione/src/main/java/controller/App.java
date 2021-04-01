package controller;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;

import create.excel.NodeProcessing;
import create.excel.RowData;
import create.excel.WriteExcel;
import parser.json.MenuContent;
import parser.json.MenuNode;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {

//      read file properties
		Properties prop = new Properties();
		try (InputStream fis = new FileInputStream("./config.properties")) {
			prop.load(fis);
			System.out.println("PATH - IN  : " + prop.getProperty("file.path.in"));
			System.out.println("PATH - OUT : " + prop.getProperty("file.path.out"));
			
		} catch (Exception e) {
			System.out.println("file properties not found");
			e.printStackTrace();
			return;
		}

//      read file JSON
		try {
			Gson gson = new Gson();
			MenuContent menuContent = gson.fromJson(new FileReader(prop.getProperty("file.path.in")), MenuContent.class);

// preparo ed eseguo ricorsione
			NodeProcessing node = new NodeProcessing();
			int treeNode = 0;
			List<MenuNode> nodeList = menuContent.getNodes();
			List<RowData> rowForExcel = new ArrayList<RowData>();
			node.loadRow(nodeList, treeNode, rowForExcel);

// Creo la lista dei titoli delle colonne

			ArrayList<String> titleData = new ArrayList<String>();
			titleData.add("ServiceId");
			titleData.add("NodeName");
			titleData.add("NodeType");
			titleData.add("GroupType");
			titleData.add("FlowType");
			titleData.add("ResourceId");

// Creo la lista di dettaglio

			List<List<String>> rowsData = new ArrayList<List<String>>();
			for(RowData rowData : rowForExcel) {

				List<String> rowValues = new ArrayList<String>();

				rowValues.add(Integer.toString(rowData.getProfondita()));

				if (rowData.getServiceId() == 0) {
					rowValues.add("");
				} else {
					rowValues.add(Integer.toString(rowData.getServiceId()));
				}

				rowValues.add(rowData.getNodeName());
				rowValues.add(rowData.getNodeType());
				rowValues.add(rowData.getGroupType());
				rowValues.add(rowData.getFlowType());

				if (rowData.getResourceId() == 0) {
					rowValues.add("");
				} else {
					rowValues.add(Integer.toString(rowData.getResourceId()));
				}
				rowsData.add(rowValues);
			}

// preparazione scrittura su excel
			WriteExcel wexcel = new WriteExcel();
			String sheetName = "Menu " + menuContent.getVersion();
			wexcel.writeExcel(titleData, rowsData, prop.getProperty("file.path.out"), sheetName);

		} catch (Exception e) {
			System.out.println("stop program. Exception: " + e.getMessage());
			e.printStackTrace();
			return;

		} finally {
			System.out.println("ESECUZIONE TERMINATA!!!");
		}

	}
}
