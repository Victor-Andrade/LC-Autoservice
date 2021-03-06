package com.byui.cs246.group12.lcautoservice.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

public class SocialMediaHandler {

	public final void launchFacebook() {
		String yourpageid = "tallerluiscedeno";

		final String urlFb = "fb://page/"+yourpageid;
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(urlFb));

		// If a Facebook app is installed, use it. Otherwise, launch
		// a browser
		final PackageManager packageManager = getPackageManager();
		@SuppressLint("QueryPermissionsNeeded") List<ResolveInfo> list =
				packageManager.queryIntentActivities(intent,
						PackageManager.MATCH_DEFAULT_ONLY);
		if (list.size() == 0) {
			final String urlBrowser = "https://www.facebook.com/"+yourpageid;
			intent.setData(Uri.parse(urlBrowser));
		}

		startActivity(intent);
	}

	public static Intent newInstagramProfileIntent(PackageManager pm, String url) {
		final Intent intent = new Intent(Intent.ACTION_VIEW);
		try {
			if (pm.getPackageInfo("com.instagram.android", 0) != null) {
				if (url.endsWith("/")) {
					url = url.substring(0, url.length() - 1);
				}
				final String username = url.substring(url.lastIndexOf("tallerluiscedeno/") + 1);

				intent.setData(Uri.parse("http://instagram.com/_u/" + username));
				intent.setPackage("com.instagram.android");
				return intent;
			}
		} catch (PackageManager.NameNotFoundException ignored) {
		}
		intent.setData(Uri.parse(url));
		return intent;
	}

	public void goToTheWebsite() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("https://www.tallerluiscedeno.com/"));
		startActivity(browserIntent);

	}

}
