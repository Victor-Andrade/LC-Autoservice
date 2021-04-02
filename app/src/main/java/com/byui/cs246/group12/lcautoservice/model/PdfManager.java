package com.byui.cs246.group12.lcautoservice.model;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.widget.Toast;

import com.byui.cs246.group12.lcautoservice.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This class handles all the PDF related functions.
 * <ul>
 *     <li>Generates PDF with Car information and procedures</li>
 *     <li>Send PDF through WhatsApp to specific contact</li>
 * </ul>
 */

public class PdfManager {
    private static final String TAG = "PdfManager";
    private final String stringfile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/LC_AutoService_Quote.pdf";
    Bitmap bmp, scaledBmp;
    int pageWidth = 1240;
    int pageHeight = 1754;
    Date dateObj;
    DateFormat dateFormat;

    public void buttonShareFile(Context context) {
        File file = new File(stringfile);

        if (!file.exists()) {
            Toast.makeText(context, "File doesn't exist.", Toast.LENGTH_LONG).show();
            return;
        }

        Intent sendIntent = new Intent("android.intent.action.SEND");
        Uri uri = Uri.fromFile(file);
        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.ContactPicker"));
        sendIntent.setType("application/pdf");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("50684863046") + "@s.whatsapp.net");
        context.startActivity(sendIntent);
    }

    @SuppressLint("SimpleDateFormat")
    public void generatePdf(Car car, List<String> procedures, Context context) {
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_lc);
        scaledBmp = Bitmap.createScaledBitmap(bmp, 400, 100, false);
        dateObj = new Date();
        PdfDocument myPdf = new PdfDocument();
        Paint myPaint = new Paint();
        Paint titlePaint = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        PdfDocument.Page myPage = myPdf.startPage(myPageInfo);
        Canvas canvas = myPage.getCanvas();
        canvas.drawBitmap(scaledBmp, 20, 70, myPaint);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(40);
        canvas.drawText("Resumen de Procedimientos", pageWidth/2, 300, titlePaint);

        myPaint.setTextSize(20);
        myPaint.setTypeface(Typeface.DEFAULT_BOLD);

        dateFormat = new SimpleDateFormat("dd/MM/yy");
        canvas.drawText("Fecha: " + dateFormat.format(dateObj), pageWidth - 200, 100, myPaint);
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        canvas.drawText("Hora: " + dateFormat.format(dateObj), pageWidth - 200, 150, myPaint);
        canvas.drawText("Marca: " + car.getBrand(), 20, 400, myPaint);
        canvas.drawText("Modelo: " + car.getModel(), 20, 450, myPaint);
        canvas.drawText("Año: " + car.getYear(), 300, 400, myPaint);
        canvas.drawText("Kilometros: " + car.getKilometers() + " km", 300, 450, myPaint);

        myPaint.setTextSize(16);
        myPaint.setTypeface(Typeface.DEFAULT);

        int height = 600;
        if(procedures.size()> 0){
            for (String procedure : procedures) {
                if(pageHeight - height < 50){
                    myPdf.finishPage(myPage);
                    myPageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
                    myPage = myPdf.startPage(myPageInfo);
                    canvas = myPage.getCanvas();
                    height = 50;
                }
                canvas.drawText(procedure, 20,height , myPaint);
                height +=30;
            }
        }
        myPdf.finishPage(myPage);
        File file = new File(stringfile);

        try {
            myPdf.writeTo(new FileOutputStream(file));
            Log.d(TAG, "PDF created");
            Toast.makeText(context, "PDF Created", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e(TAG, "Error creating PDF", e);
            Toast.makeText(context, "Error creating PDF", Toast.LENGTH_SHORT).show();
        }
        myPdf.close();
    }
}
