package com.byui.cs246.group12.lcautoservice.model;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class ExcelManager {
    String filePath;

    public ExcelManager(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getProcedures(Car car) {
        return new ArrayList<>();
    }

    // reading excel file
    public void readExcelFile() throws IOException, BiffException {
        Workbook excelSheet = Workbook.getWorkbook(new File(filePath));
        Sheet sheet = excelSheet.getSheet(0);
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
    }
}
