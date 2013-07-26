package it.sasabz.sasabus.ui.info;

import java.util.ArrayList;

import it.sasabz.android.sasabus.R;
import it.sasabz.sasabus.data.hafas.XMLConnection;
import it.sasabz.sasabus.data.models.Information;
import it.sasabz.sasabus.logic.DownloadInfos;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

import android.os.Bundle;
import android.util.Log;

/**
 * Display general information about changes of routes ecc.
 */
public class InfoActivity extends SherlockActivity {

	private DownloadInfos downloadInfos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addIndeterminateProgressBar();
		
		setContentView(R.layout.fragment_info);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		downloadInfos = new DownloadInfos();
		
		loadInfos();
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Log.i("test", "onprepareoptionsmenu called");
		return super.onPrepareOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		} else if (item.getItemId() == 1) {
			loadInfos();
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void addIndeterminateProgressBar() {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		getSherlock().setProgressBarIndeterminateVisibility(true);
	}
	
	private void removeIndeterminateProgressBar() {
		getSherlock().setProgressBarIndeterminateVisibility(false);
	}
	
	private void loadInfos() {
		downloadInfos.downloadInfos(this, new InfosCallback() {
			@Override
			public void infosDownloaded(ArrayList<Information> infos) {
				removeIndeterminateProgressBar();
			}
		});
	}
	
	public interface InfosCallback {
		void infosDownloaded(ArrayList<Information> infos);
	}
	
}