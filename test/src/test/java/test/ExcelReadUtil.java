package test;
import java.io.File;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReadUtil {
  public static String[][] readExcelInto2DArray(String excelFilePath, String sheetName, int totalCols) {

    File file = new File(excelFilePath);

    String[][] tabArray = null;

    	try {
            @SuppressWarnings("unused")
			OPCPackage opcPackage = OPCPackage.open(file.getAbsolutePath());

            Workbook wb = WorkbookFactory.create(file);

            Sheet sheet = wb.getSheet(sheetName);

            int totalRows = sheet.getLastRowNum();

            tabArray = new String[totalRows][totalCols];
        for (int i = 1; i <= totalRows; i++) {
          for (int j = 0; j < totalCols; j++) {
        	  
            Cell cell = sheet.getRow(i).getCell(j);
            System.out.println(cell + " " + i + " " + j);

            if (cell == null)
              continue;

            switch (cell.getCellType()) {
            case BOOLEAN:
              tabArray[i-1][j] = String.valueOf(cell.getBooleanCellValue());
              break;
            case NUMERIC:
              tabArray[i-1][j] = String.valueOf(cell.getNumericCellValue());
              break;
            case STRING:
              tabArray[i-1][j] = cell.getStringCellValue();
              break;
            default:
              tabArray[i-1][j] = "";
              break;
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }

      return tabArray;
  }

}