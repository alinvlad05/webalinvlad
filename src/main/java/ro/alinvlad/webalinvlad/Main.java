package ro.alinvlad.webalinvlad;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("Id comanda");
        firstRow.createCell(1).setCellValue("Pret total");

        FileOutputStream fileOutputStream = new FileOutputStream("raport.xls");
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
    }
}
