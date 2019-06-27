package udemy.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelDataReadTest {

	public ArrayList<String> getExcelData() throws IOException {

		ArrayList<String> alRes = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//LokeshExcel.xls");  
		//XSSFWorkbook wb = new XSSFWorkbook(fis);
		HSSFWorkbook wb = new HSSFWorkbook(fis);

		HSSFSheet sheet = wb.getSheetAt(0);
		//XSSFSheet sheet = wb.getSheetAt(0);

		Iterator<Row> rows = sheet.iterator();

		Row firstRow = rows.next();

		Iterator<Cell> cell = firstRow.cellIterator();

		int i=0, column =0;

		while(cell.hasNext()) {
			Cell cv = cell.next();
			if(cv.getStringCellValue().equalsIgnoreCase("TestCase")) {
				column =i;
			}
			i++;
		}

		while(rows.hasNext()) {
			Row row = rows.next();
			if(row.getCell(column).getStringCellValue().equalsIgnoreCase("Login")) {




				Iterator<Cell>  cells=row.cellIterator();
				while(cells.hasNext())
				{
					Cell cellValue=	cells.next();
					if(cellValue.getCellTypeEnum()==CellType.STRING)
					{

						alRes.add(cellValue.getStringCellValue());
					}
					else{

						alRes.add(NumberToTextConverter.toText(cellValue.getNumericCellValue()));

					}
				}
			}
		}
		return alRes;
	}

	@Test
	public void testOne() throws IOException {
		System.err.println(getExcelData());
	}
}
