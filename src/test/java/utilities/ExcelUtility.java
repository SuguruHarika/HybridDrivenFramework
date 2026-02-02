package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
    public  FileInputStream fi;
    public  FileOutputStream fo;
    public  XSSFWorkbook wb;
    public  XSSFSheet sheet;
    public  XSSFRow row;
    public  XSSFCell cell;
    public  CellStyle style;
    String path;
    public ExcelUtility(String path){
        this.path= path;
    }

    public int getRowCount(String XlSheet) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(XlSheet);
        int rowCount = sheet.getLastRowNum();
        fi.close();
        wb.close();
        return rowCount;
    }

    public int getCellCount(String XlSheet, int rowCount) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(XlSheet);
        row = sheet.getRow(rowCount);
        int cellCount = row.getLastCellNum();
        fi.close();
        wb.close();
        return cellCount;
    }

    public String getCellData(String XlSheet, int rowNum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(XlSheet);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colnum);

        String data;
        try {
            //data = cell.toString(); //we can use this line or below 2 lines both usage same
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        fi.close();
        wb.close();
        return data;
    }
    public void setCellData(String XlSheet, int rowNum, int colnum,String data) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(XlSheet);
        row = sheet.getRow(rowNum);

        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(path);
        wb.write(fo);
        fi.close();
        wb.close();
        fo.close();
    }
    public  void fillGreenColor(String XlSheet, int rowNum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(XlSheet);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colnum);
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo = new FileOutputStream(path);
        wb.write(fo);
        fi.close();
        wb.close();
        fo.close();
    }
    public  void fillRedColor(String XlSheet, int rowNum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(XlSheet);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colnum);
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo = new FileOutputStream(path);
        wb.write(fo);
        fi.close();
        wb.close();
        fo.close();
    }

    }
