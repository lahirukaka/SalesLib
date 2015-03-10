/**
 * Copyright (c) 2014 Lahiru <lahirukaka@gmail.com>
 * 
 * This Code Section AppPager.java in Package madfox.colhh.saleslib.pager for Project SalesLib was 
 * developed by Lahiru under MadFox Brand Name at Nov 21, 2014.
 * 
 * All rights reserved by MadFox Developer Lahiru
 */
package madfox.colhh.saleslib.adsdisplayer;

import madfox.colhh.saleslib.Packages;
import madfox.colhh.saleslib.R;
import madfox.colhh.saleslib.pages.AppPageIconName;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class AppPager extends Fragment {

	private ViewPager pager;
	private AppPagerAdapter adapter;
	private Packages[] packages;
	private AppPageIconName[] pages;
	private int rotate=0;
	private AsyncTask<Integer, Void, Void> task;
	private int color;
	
	public AppPager() {
	}
	
	public AppPager(int color,Packages... packages) {
		this.packages=packages;
		this.color=color;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.app_pager, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		pager=(ViewPager)getView().findViewById(R.id.sales_pager);
		if(savedInstanceState!=null)
		{
			String[] pkgs=savedInstanceState.getStringArray("pkgs");
			packages=new Packages[pkgs.length];
			for(int r=0;r<pkgs.length;r++)
			{
				packages[r]=Packages.valueOf(pkgs[r]);
			}
			rotate=savedInstanceState.getInt("rotate");
			color=savedInstanceState.getInt("color");
		}
		try
		{
			buildAppPages();
		}catch (NullPointerException e) {
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		startRotator();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		try{
			task.cancel(true);
		}catch (Exception e) {
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		String[] pkgs=new String[packages.length];
		for(int r=0;r<packages.length;r++)
		{
			pkgs[r]=packages[r].toString();
		}
		outState.putStringArray("pkgs", pkgs);
		outState.putInt("rotate", rotate);
		outState.putInt("color", color);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		pager=null;
		adapter=null;
		packages=null;
		pages=null;;
		task=null;
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		onDestroy();
	}
	
	private void buildAppPages() throws NullPointerException
	{
		pages=new AppPageIconName[packages.length];
		for(int r=0;r<packages.length;r++)
		{
			pages[r]=new AppPageIconName(packages[r],color);
		}
		/*call adapter*/
		adapter=new AppPagerAdapter(getActivity().getSupportFragmentManager(),pages);
		pager.setAdapter(adapter);
		
	}
	
	private void startRotator() {
		if(rotate>0 && packages.length>1)
		{
			task=new AsyncTask<Integer, Void, Void>() {
				@Override
				protected Void doInBackground(Integer... params) {
					while(true)
					{
						if(this.isCancelled())
						{
							break;
						}else
						{
							try {
								synchronized (this) {
									this.wait(params[0]);
								}
								//Thread.sleep(params[0]);
								publishProgress();
							} catch (InterruptedException e) {}
						}
					}
					return null;
				}
				
				@Override
				protected void onProgressUpdate(Void... values) 
				{
					if(packages.length==(pager.getCurrentItem()+1))
					{
						pager.setCurrentItem(0,true);
					}else
					{
						pager.setCurrentItem(pager.getCurrentItem()+1,true);
					}
					
				};
			}.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, this.rotate);
		}
	}
	
	/**
	 * <b>Set rotate timer</b>
	 * @param rotate
	 * @return
	 */
	public AppPager setRotateTime(int rotate)
	{
		this.rotate=rotate;
		return this;
	}

}
