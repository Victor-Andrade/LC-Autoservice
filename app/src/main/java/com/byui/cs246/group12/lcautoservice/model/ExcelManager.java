package com.byui.cs246.group12.lcautoservice.model;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelManager {
    private static final String TAG = "ExcelManager";

    public ExcelManager() {
    }

    public List<String> getProcedures(Car car) {
        return new ArrayList<>();
    }

    // reading excel file
    public void readExcelFile(Context context) throws IOException, BiffException {
        AssetManager assetManager = context.getAssets();
        Log.d(TAG, "readExcelFile: " + Arrays.toString(assetManager.list("")));
        InputStream f = assetManager.open("DB-MAINTENANCE-PROGRAMS-TLC.xlsx");
        InputStreamReader reader = new InputStreamReader(f);
        BufferedReader bufferedReader = new BufferedReader(reader);
        Log.d(TAG, "readExcelFile: " + bufferedReader.readLine());
        Workbook excelSheet = Workbook.getWorkbook(new File("DB-MAINTENANCE-PROGRAMS-TLC.xlsx"));
        Sheet sheet = excelSheet.getSheet(0);
        for (int j = 0; j < sheet.getColumns(); j++) {
            for (int i = 0; i < sheet.getRows(); i++) {
                Cell cell = sheet.getCell(j, i);
                CellType type = cell.getType();
                if (type == CellType.LABEL) {
                    Log.d(TAG, "I got a label "
                            + cell.getContents());
                }

                if (type == CellType.NUMBER) {
                    Log.d(TAG, "I got a number "
                            + cell.getContents());
                }

            }
        }
        Log.d(TAG, "readExcelFile: " + sheet.toString());
    }
}
