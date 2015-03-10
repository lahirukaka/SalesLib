package madfox.colhh.saleslib.pages;

import madfox.colhh.saleslib.Packages;
import madfox.colhh.saleslib.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class AppPageIconName extends AdPages_v4 {
	
	private int color;
	
	public AppPageIconName() {
	}
	
	public AppPageIconName(Packages pkg,int color) {
		super(pkg);
		this.color=color;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view=inflater.inflate(R.layout.ad_page_name_icon_1, container,false);
		
		initViewsForIconNameTemplate(view,color);
		return view;
	}

}
