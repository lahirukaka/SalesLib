/**
 * Copyright (c) 2014 Lahiru <lahirukaka@gmail.com>
 * 
 * This Code Section SalesKit.java in Package madfox.colhh.saleslib for Project SalesLib was 
 * developed by Lahiru under MadFox Brand Name at Nov 21, 2014.
 * 
 * All rights reserved by MadFox Developer Lahiru
 */
package madfox.colhh.saleslib.install;

import madfox.colhh.saleslib.Packages;
import madfox.colhh.saleslib.R;
import madfox.colhh.saleslib.UserNoticeDialogs;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

public final class SalesKit {

	public static final String MARKET_URL = "market://details?id=";
	public static final String WEB_URL = "http://play.google.com/store/apps/details?id=";

	/**
	 * <b>Check whether App is installed...</b>
	 * 
	 * @param URI
	 * @param activity
	 * @return ifExists
	 */
	public static final boolean isInstalled(Packages pkges, Activity activity) {
		PackageManager pkg = activity.getPackageManager();

		try {
			pkg.getPackageInfo(pkges.getPackage(),
					PackageManager.GET_ACTIVITIES);
			return true;
		} catch (NameNotFoundException er) {
			return false;
		}
	}

	/**
	 * <b>Ask User To Install</b>
	 * 
	 * @param pkg
	 * @param activity
	 */
	public static final void askUserToInstall(Packages pkg, final Activity activity) {
		final Uri uri_market = Uri.parse(MARKET_URL + "" + pkg.getPackage());
		final Uri uri_web= Uri.parse(WEB_URL + "" + pkg.getPackage());

		UserNoticeDialogs und = new UserNoticeDialogs(pkg.getName(),
				R.drawable.dialog_question, "Install " + pkg.getName() + " ?");
		und.setButtons(true, true, new String[] { "Yes", "Not Now" },
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface view, int which) {
						if (which == DialogInterface.BUTTON_POSITIVE) {
							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setData(uri_market);
							try
							{
								activity.startActivity(intent);
							}catch(ActivityNotFoundException er)
							{
								intent.setData(uri_web);
								activity.startActivity(intent);
							}
						}
					}
				});
		und.show(activity.getFragmentManager(), "asktoinstall");
	}
}
