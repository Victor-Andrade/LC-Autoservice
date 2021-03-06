package com.byui.cs246.group12.lcautoservice.model;
package com.excelcsv;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class ExcelManager {
    public List<String> getProcedures(Car car){
        return new ArrayList<>();
    }
}
public class ExcelReadWrite {
String filePath;
ExcelReadWrite(String filePath){
this.filePath = filePath;
}
// writing excel file
public void writeExcelFile(){
WritableWorkbook excelSheet = null;
try {
excelSheet = Workbook.createWorkbook(new File(filePath));
WritableSheet excelFile = myCar.createSheet("Sheet 1", 0);
Label label = new Label(0, 0, "Test Count");
excelFile.addCell(label);
Number number = new Number(0, 1, 1);
excelFile.addCell(number);
label = new Label(1, 0, "Result");
excelFile.addCell(label);
label = new Label(1, 1, "Passed");
excelFile.addCell(label);
number = new Number(0, 2, 2);
excelFile.addCell(number);
label = new Label(1, 2, "Passed 2");
excelFile.addCell(label);
excelSheet.write();
}catch (Exception e){
e.printStackTrace();
}
}
// reading excel file
public void readExcelFile(){
try {
excelSheet = Workbook.getWorkbook(new File(filePath));
Sheet sheet = workbook.getSheet(0);
Cell cell1 = sheet.getCell(0, 0);
System.out.print(cell1.getContents() + ":");
Cell cell2 = sheet.getCell(0, 1);
System.out.println(cell2.getContents());
Cell cell3 = sheet.getCell(1, 0);
System.out.print(cell3.getContents() + ":");
Cell cell4 = sheet.getCell(1, 1);
System.out.println(cell4.getContents());
System.out.print(cell1.getContents() + ":");
cell2 = sheet.getCell(0, 2);
System.out.println(cell2.getContents());
System.out.print(cell3.getContents() + ":");
cell4 = sheet.getCell(1, 2);
System.out.println(cell4.getContents());
} catch (IOException e) {
e.printStackTrace();
}
}
}
