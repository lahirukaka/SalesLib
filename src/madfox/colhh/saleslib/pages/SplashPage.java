package madfox.colhh.saleslib.pages;

import madfox.colhh.madfoxcustom.dialogs.FlexibleDialogBox;
import madfox.colhh.saleslib.R;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;


public class SplashPage extends FlexibleDialogBox {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		setLayout(R.layout.slash_screen);
		return super.onCreateDialog(savedInstanceState);
	}
	
	@Override
	public void show(FragmentManager manager, String tag) {
		super.show(manager, tag);
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {}
				return null;
			}
			protected void onPostExecute(Void result) {
				dismiss();
			};
		}.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}
}
