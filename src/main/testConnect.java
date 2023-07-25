
package main;


import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class testConnect {
    public static void main(String[] args)
    {
        //System.out.print("Nam");
        getDataFromExcel();
    }
    
//    public static void readXLS(String tenFile)
//    {
//        try {
//            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(tenFile));
//            XSSFSheet sheet = wb.getSheetAt(0);
//            XSSFRow row = sheet.getRow(0);
//            System.out.print(row.getCell(2));
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void readExcel(){
        try {
            
            FileInputStream file = new FileInputStream("D:\\file1.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFFormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
            for (Row row : sheet){
                for(Cell cell:row){
                    System.out.println(cell.getNumericCellValue());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
//     public static void getDataFromExcel(){
//        FileInputStream excelFIS = null;
//        
//        XSSFWorkbook excelImportWorkBook = null;
//        
//        String currentDirectoryPath = "D:\\file1.xlsx";
//        
//       
//        
//
//                    try {
//                        
//                        excelFIS = new FileInputStream(currentDirectoryPath);
//                        
//                        excelImportWorkBook = new XSSFWorkbook(excelFIS);
//                        XSSFSheet excelSheet = excelImportWorkBook.getSheetAt(0);
//                        for(int i = 0;i<excelSheet.getLastRowNum();i++){
//                            XSSFRow excelRow = excelSheet.getRow(i);
//
//                            for(int j=0;j<excelRow.getLastCellNum();j++){
//                                XSSFCell cell = excelRow.getCell(j);
//                                System.out.println(cell.getNumericCellValue());
//                            }
//                        }
//                    } catch (Exception e) {
//                        System.out.println("khong tim thay");
//                    }
//      
//    }
    
    
    public static void getDataFromExcel(){
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportWorkBook = null;
        
        JFileChooser excelFileChooserImport = new JFileChooser();
        excelFileChooserImport.setMultiSelectionEnabled(false);
        // Tạo font cho tiếng Việt
        
        int excelChooser = excelFileChooserImport.showOpenDialog(null);
        if(excelChooser == JFileChooser.APPROVE_OPTION){
            System.out.println("chon file roi ma");
            try {
                File excelFile = excelFileChooserImport.getSelectedFile();
                
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS); 
                excelImportWorkBook = new XSSFWorkbook(excelBIS); 
                XSSFSheet excelSheet = excelImportWorkBook.getSheetAt(0);
                System.out.println(excelSheet.getLastRowNum());
                for(int i = 0;i<=excelSheet.getLastRowNum();i++){
                    XSSFRow excelRow = excelSheet.getRow(i);
                    for(int j = 0;j<=excelRow.getLastCellNum();j++){
                        XSSFCell cell = excelRow.getCell(j);
                        System.out.println(cell);
                    }
                }
            } catch (Exception e) {
                System.out.println("khong tim thay");
            }
        }
        
    }
    
    
}