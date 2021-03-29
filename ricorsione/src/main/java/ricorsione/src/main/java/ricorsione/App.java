package ricorsione;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;

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
			ElaboraNodi nodi = new ElaboraNodi();
			int profondita = -1;
			List<MenuNode> nodeList = menuContent.getNodes();
			List<Excel> excel = new ArrayList<>();
			nodi.caricaListaExcel(nodeList, profondita, excel);

// preparazione scrittura su excel				
			WriteExcel wexcel = new WriteExcel();
			String sheetName = "Menu " + menuContent.getVersion();
			wexcel.writeExcel(excel, prop.getProperty("file.path.out"), sheetName);

		} catch (Exception e) {
			System.out.println("stop program. Exception: " + e.getMessage());
			e.printStackTrace();
			return;
			
		} finally {
			System.out.println("ESECUZIONE TERMINATA!!!");
		}

	}
}
