package it.sasabz.sasabus.ui.info;

import java.util.ArrayList;

import it.sasabz.android.sasabus.R;
import it.sasabz.sasabus.data.hafas.XMLConnection;
import it.sasabz.sasabus.data.models.DBObject;
import it.sasabz.sasabus.data.models.Information;
import it.sasabz.sasabus.logic.DownloadInfos;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

import android.R.bool;
import android.os.Bundle;
import android.util.Log;

/**
 * Display general information about changes of routes ecc.
 */
public class InfoActivity extends SherlockActivity {

	private DownloadInfos downloadInfos;
	private MenuItem optionsMenuitemRefresh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_info);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		downloadInfos = new DownloadInfos();
		
		getInfosFromCache();
		
	}
	
	private void getInfosFromCache() {
		// TODO get the infos from cache and display them 
		// meanwhile new infos are being downloaded and 
		// eventually the view will get refreshed
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_info, menu);
		optionsMenuitemRefresh = menu.findItem(R.id.menu_refresh);
		
		loadInfos();
		
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.menu_refresh:
			loadInfos();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void loadInfos() {
		setRefreshActionButtonState(true);
		downloadInfos.downloadInfos(this, new InfosCallback() {
			@Override
			public void infosDownloaded(ArrayList<DBObject> infos) {
				setRefreshActionButtonState(false);
			}
		});
	}
	
	public interface InfosCallback {
		void infosDownloaded(ArrayList<DBObject> infos);
	}
	
	private void setRefreshActionButtonState(boolean refreshing) {
		if(refreshing) {
			optionsMenuitemRefresh.setActionView(R.layout.actionbar_indeterminate_progress);
		} else {
			optionsMenuitemRefresh.setActionView(null);
		}
	}
	
	private void addAdapterToListViews() {
		
	}
	
}