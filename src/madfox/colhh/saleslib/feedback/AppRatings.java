package madfox.colhh.saleslib.feedback;

import madfox.colhh.saleslib.Packages;
import madfox.colhh.saleslib.R;
import madfox.colhh.saleslib.UserNoticeDialogs;
import madfox.colhh.saleslib.install.SalesKit;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;


public final class AppRatings {

	private final String SHA_PREF_FILE="feedback_ratings";
	private final String TOTAL_VIEWS="totviews";
	private final String IS_RATED="israted";
	private final String POS_IMPR="positiveimpr";
	
	private final String MARKET_URL = SalesKit.MARKET_URL;
	private final String WEB_URL = SalesKit.WEB_URL;
	
	private Context context;
	
	public AppRatings(Context context) {
		this.context=context;
	}
	
	public void increaseViews()
	{
		SharedPreferences set= context.getSharedPreferences(SHA_PREF_FILE,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = set.edit();
		edit.putInt(TOTAL_VIEWS, (set.getInt(TOTAL_VIEWS, 0) + 1));
		edit.commit();
	}
	
	public void increasePositiveImpr()
	{
		SharedPreferences set= context.getSharedPreferences(SHA_PREF_FILE,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = set.edit();
		edit.putInt(POS_IMPR, (set.getInt(POS_IMPR, 0) + 1));
		edit.commit();
	}
	
	public void checkAndAsk(final Packages pkg,Activity activity)
	{
		if(!isRated())
		{
			SharedPreferences set= context.getSharedPreferences(SHA_PREF_FILE,
					Context.MODE_PRIVATE);
			int views=set.getInt(TOTAL_VIEWS, 0);
			int pos=set.getInt(POS_IMPR, 0);

			if(views>=5 && pos>=5 && ((views%5)==0))
			{
				UserNoticeDialogs und=new UserNoticeDialogs("Rate App", 
						R.drawable.dialog_question, "Rate " + pkg.getName() +
						" now ?");
				und.setButtons(true, true, new String[] {"Yes","Not Now"}, 
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int event) {
								if(DialogInterface.BUTTON_POSITIVE==event)
								{
									Intent intent = new Intent(Intent.ACTION_VIEW);
									intent.setData(Uri.parse(MARKET_URL + pkg.getPackage()));
									try{
										context.startActivity(intent);
									}catch (ActivityNotFoundException e) {
										intent.setData(Uri.parse(WEB_URL + pkg.getPackage()));
										context.startActivity(intent);
									}
								}
							}
				});
				und.show(activity.getFragmentManager(), "ratings");
			}
		}
	}
	
	public boolean isRated()
	{
		SharedPreferences set= context.getSharedPreferences(SHA_PREF_FILE,
				Context.MODE_PRIVATE);
		return set.getBoolean(IS_RATED, false);
	}
	
	public void setRated()
	{
		SharedPreferences set= context.getSharedPreferences(SHA_PREF_FILE,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = set.edit();
		edit.putBoolean(IS_RATED, true);
		edit.commit();
	}
	
	public void writeData(String file, String key, String value)
	{
		SharedPreferences set = context.getSharedPreferences(file, Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = set.edit();
		edit.putString(key, value).commit();
	}
	
	public Object readData(String file, String key)
	{
		SharedPreferences set = context.getSharedPreferences(file, Context.MODE_PRIVATE);
		return set.getString(key, "0");
	}
}
