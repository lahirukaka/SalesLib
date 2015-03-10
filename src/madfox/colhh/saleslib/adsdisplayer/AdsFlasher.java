package madfox.colhh.saleslib.adsdisplayer;

import madfox.colhh.saleslib.Packages;
import madfox.colhh.saleslib.R;
import madfox.colhh.saleslib.pages.AdPages;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class AdsFlasher extends AdPages {

	private int timer=0;
	private int term=1;
	private Packages[] pkgs;
	private AsyncTask<Integer, Void, Void> task;
	private View view;
	private int color;
	
	public AdsFlasher() {
	}
	
	public AdsFlasher(int timer, int color, Packages... pkgs)
	{
		this.pkgs=pkgs;
		this.timer=timer;
		this.color=color;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.ad_page_name_icon_1, container, false);
		this.view=view;
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		/*Restoring*/
		if(savedInstanceState!=null)
		{
			timer=savedInstanceState.getInt("timer");
			color=savedInstanceState.getInt("color");
			String pkgnames[]=savedInstanceState.getStringArray("pkgs");
			pkgs=new Packages[pkgnames.length];
			for(int r=0;r<pkgnames.length;r++)
			{
				pkgs[r]=Packages.valueOf(pkgnames[r]);
			}
		}
		
		if(pkgs.length>0) startTimer(); else changeAd();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
				
		String[] pkgsname=new String[pkgs.length];
		for(int r=0;r<pkgs.length;r++)
		{
			pkgsname[r]=pkgs[r].toString();
		}
		outState.putStringArray("pkgs", pkgsname);
		outState.putInt("timer", timer);
		outState.putInt("color", color);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		task.cancel(true);
	}
	
	private void startTimer() {
		task=new AsyncTask<Integer, Void, Void>() {
			@Override
			protected Void doInBackground(Integer... params) {
				publishProgress();
				while(true)
				{
					if(isCancelled()) break;
					try {
						Thread.sleep(params[0]);
					} catch (InterruptedException e) {
					}finally
					{
						publishProgress();
					}
				}
				return null;
			}
			@Override
			protected void onProgressUpdate(Void... values) 
			{
				changeAd();
			};
		}.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,timer);
	}

	private void changeAd()
	{
		if(term<=pkgs.length)
		{
			pkg=pkgs[term-1];
			initViewsForIconNameTemplate(view,color);
			if(term==pkgs.length) term = 1;
			else term++;
		}
	}
	
}
