package com.byui.cs246.group12.lcautoservice.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.List;

/**
 * This class handles all the links for the sponsor's social media sites:
 * Main website
 * Facebook
 * Instagram
 */

public class SocialMediaHandler {

	/**
	 * This method setup all the links for the sponsor's social media.
	 * @param activity will take  the end user to the sponsor's website, Facebook page or Instagram
	 */

	public static void goToFacebookProfile(Activity activity) {
		String yourpageid = "tallerluiscedeno";

		final String urlFb = "fb://facewebmodal/f?href=https://www.facebook.com/"+yourpageid;
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(urlFb));

		// If a Facebook app is installed, use it. Otherwise, launch
		// a browser
		PackageManager packageManager = activity.getPackageManager();
		@SuppressLint("QueryPermissionsNeeded") List<ResolveInfo> list =
				packageManager.queryIntentActivities(intent,
						PackageManager.MATCH_DEFAULT_ONLY);
		if (list.size() == 0) {
			final String urlBrowser = "https://www.facebook.com/"+yourpageid;
			intent.setData(Uri.parse(urlBrowser));
		}

		activity.startActivity(intent);
	}

	public static void goToInstagramProfile(Activity activity) {
		PackageManager packageManager = activity.getPackageManager();
		String url = "https://www.instagram.com/tallerluiscedeno/";
		final Intent intent = new Intent(Intent.ACTION_VIEW);
		try {
			if (packageManager.getPackageInfo("com.instagram.android", 0) != null) {
				if (url.endsWith("/")) {
					url = url.substring(0, url.length() - 1);
				}
				final String username = url.substring(url.lastIndexOf("/") + 1);

				intent.setData(Uri.parse("http://instagram.com/_u/" + username));
				intent.setPackage("com.instagram.android");
				activity.startActivity(intent);
			}
		} catch (PackageManager.NameNotFoundException ignored) {
		}
		intent.setData(Uri.parse(url));
		activity.startActivity(intent);
	}

	public static void goToTheWebsite(Activity activity) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("https://www.tallerluiscedeno.com/"));
		activity.startActivity(browserIntent);
	}

}
