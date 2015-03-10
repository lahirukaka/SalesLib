/**
 * Copyright (c) 2014 Lahiru <lahirukaka@gmail.com>
 * 
 * This Code Section AppPagerAdapter.java in Package madfox.colhh.saleslib.pager for Project SalesLib was 
 * developed by Lahiru under MadFox Brand Name at Nov 21, 2014.
 * 
 * All rights reserved by MadFox Developer Lahiru
 */
package madfox.colhh.saleslib.adsdisplayer;

import madfox.colhh.saleslib.pages.AppPageIconName;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public final class AppPagerAdapter extends FragmentPagerAdapter {

	private AppPageIconName[] pages;
	
	public AppPagerAdapter(FragmentManager fm,AppPageIconName[] pages) {
		super(fm);
		this.pages=pages;
	}

	@Override
	public Fragment getItem(int position) {
		return pages[position];
	}

	@Override
	public int getCount() {
		return pages.length;
	}

}
