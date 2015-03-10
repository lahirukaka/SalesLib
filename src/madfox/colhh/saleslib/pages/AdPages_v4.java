package madfox.colhh.saleslib.pages;

import madfox.colhh.saleslib.Packages;
import madfox.colhh.saleslib.R;
import madfox.colhh.saleslib.install.SalesKit;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public abstract class AdPages_v4 extends Fragment implements OnClickListener {

	protected Packages pkg;
	
	public AdPages_v4() {
	}
	
	public AdPages_v4(Packages pkg) {
		this.pkg=pkg;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(savedInstanceState!=null)
		{
			pkg=(Packages)Packages.valueOf(savedInstanceState.getString("pkg"));
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		try
		{
			outState.putString("pkg", pkg.toString());
		}catch (NullPointerException e) {
		}
	}
	
	protected void initViewsForIconNameTemplate(View view,int color)
	{
		TextView text=((TextView)view.findViewById(R.id.ad_page_title));
		text.setText(pkg.getName());
		if(color>0) text.setTextColor(color);
		text.setOnClickListener(this);
		ImageView img=((ImageView)view.findViewById(R.id.ad_page_img));
		img.setImageResource(pkg.getLogo());
		img.setOnClickListener(this);
	}
	
	/*Listener*/
	@Override
	public void onClick(View view) {
		
		if(!SalesKit.isInstalled(pkg, getActivity()))
		{
			SalesKit.askUserToInstall(pkg, getActivity());
		}else
		{
			Toast.makeText(getActivity(), pkg.getName() + " is already installed...", 
					Toast.LENGTH_LONG).show();
		}
	}
}
