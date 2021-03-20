package com.byui.cs246.group12.lcautoservice.model;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.byui.cs246.group12.lcautoservice.R;
import java.io.File;

/**
 * This class handles all the PDF related functions.
 * <ul>
 *     <li>Creates PDF</li>
 *     <li>Asks permissions to find file and share it via WhatsApp</li>
 *     </ul>
 */

public class PdfManager {
	private final String stringfile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator +
			"Test.pdf";

	private ExcelManager excelManager;

//	public Pdf generatePdf() {
//		return null;
//	}

	public void buttonShareFile(Context context){
		File file = new File(stringfile);

		if(!file.exists()){
			Toast.makeText(context, "File doesn't exist.", Toast.LENGTH_LONG).show();
			return;
		}
		Intent intentShare = new Intent(Intent.ACTION_SEND);
		intentShare.setType("application/pdf");
		intentShare.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+file));

		context.startActivity(Intent.createChooser(intentShare,"Share file..."));
	}
}
