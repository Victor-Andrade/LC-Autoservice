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

/**
 * This class handles all the PDF related functions.
 * Create PDF
 * Send PDF through WhatsApp
 */

public class PdfManager {
    private static final String TAG = "PdfManager";
    private final String stringfile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/Test.pdf";
    Bitmap bmp, scaledBmp;
    int pageWidth = 1240;
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
    public void generatePdf(Car car, Context context) {
        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_lc);
        scaledBmp = Bitmap.createScaledBitmap(bmp, 400, 100, false);
        dateObj = new Date();
        PdfDocument myPdf = new PdfDocument();
        Paint myPaint = new Paint();
        Paint titlePaint = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1240, 1754, 1).create();
        PdfDocument.Page myPage = myPdf.startPage(myPageInfo);
        Canvas canvas = myPage.getCanvas();
        canvas.drawBitmap(scaledBmp, 20, 70, myPaint);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(40);
        canvas.drawText("Resumen de Procedimientos", pageWidth / 2, 300, titlePaint);

        myPaint.setTextSize(20);

        dateFormat = new SimpleDateFormat("dd/MM/yy");
        canvas.drawText("Fecha: " + dateFormat.format(dateObj), pageWidth - 150, 100, myPaint);
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        canvas.drawText("Hora: " + dateFormat.format(dateObj), pageWidth - 150, 150, myPaint);
        canvas.drawText("Marca: " + car.getBrand(), 20, 400, myPaint);
        canvas.drawText("Modelo: " + car.getModel(), 20, 450, myPaint);
        canvas.drawText("Año: " + car.getYear(), 20, 500, myPaint);
        canvas.drawText("Kilometros: " + car.getKilometers() + " km", 20, 550, myPaint);
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
