package com.byui.cs246.group12.lcautoservice.model;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

public class PdfManager extends AppCompatActivity {
	Spinner T_spinner, M_spinner, Y_spinner, K_spinner;
	Button createButton;
	Bitmap bmp, scaledBmp;
	int pageWidth=1200;
	private ExcelManager excelManager;
	Date dateObj;
	DateFormat dateFormat;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		T_spinner = findViewById(R.id.tradeMarkSpinner);
		M_spinner = findViewById(R.id.modelSpinner);
		Y_spinner = findViewById(R.id.yearSpinner);
		K_spinner = findViewById(R.id.kilometersSpinner);
		createButton = findViewById(R.id.lookProceduresBtn);
		bmp= BitmapFactory.decodeResource(getResources(), R.drawable.logo_lc);
		scaledBmp= Bitmap.createScaledBitmap(bmp,415,86,false);


		ActivityCompat.requestPermissions(this, new
				String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
				PackageManager.PERMISSION_GRANTED);

		generatePdf();
	}
	public void generatePdf() {
		createButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dateObj= new Date();
				PdfDocument myPdf= new PdfDocument();
				Paint myPaint = new Paint();
				Paint titlePaint = new Paint();

				PdfDocument.PageInfo myPageInfo= new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
				PdfDocument.Page myPage= myPdf.startPage(myPageInfo);
				Canvas canvas=myPage.getCanvas();
				canvas.drawBitmap(scaledbmp, 0, 0, myPaint);
				titlePaint.setTextAlign(Paint.Align.CENTER);
				titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
				titlePaint.setTextSize(70);
				canvas.drawText("Summary Of Procedures", pageWidth/2, 500, titlePaint);

				dateFormat= new SimpleDateFormat("dd/mm/yy");
				canvas.drawText("Date:"+dateFormat.format(dateObj), pageWidth-20, 640, myPaint);

				dateFormat= new SimpleDateFormat("HH:mm:ss");
				canvas.drawText("Time:"+dateFormat.format(dateObj), pageWidth-20, 690, myPaint);

				canvas.drawText("Trademark:"+T_spinner.getSelectedItem().toString(), 20, 590, myPaint);
				canvas.drawText("Model:"+M_spinner.getSelectedItem().toString(),20, 640, myPaint);
				canvas.drawText("Year:"+ Y_spinner.getSelectedItem().toString(), 20, 690, myPaint);
				canvas.drawText("Kilometers:"+K_spinner.getSelectedItem().toString(), 20, 730, myPaint);
				myPaint.setStyle(Paint.Style.STROKE);
				myPaint.setStrokeWidth(3);


				myPdf.finishPage(myPage);

				File file = new File(Environment.getExternalStorageDirectory().getPath(),"/Test.pdf");

				try{
					myPdf.writeTo(new FileOutputStream(file));
				}catch(IOException e){
					e.printStackTrace();
				}
				myPdf.close();
			}
		});
	}

}
