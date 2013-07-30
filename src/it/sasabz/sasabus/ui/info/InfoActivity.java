package it.sasabz.sasabus.ui.info;

import java.util.ArrayList;
import java.util.List;

import it.sasabz.android.sasabus.R;
import it.sasabz.sasabus.data.hafas.XMLConnection;
import it.sasabz.sasabus.data.models.DBObject;
import it.sasabz.sasabus.data.models.Information;
import it.sasabz.sasabus.logic.DownloadInfos;
import it.sasabz.sasabus.ui.Utility;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

import android.R.bool;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Display general information about changes of routes ecc.
 */
public class InfoActivity extends SherlockActivity {

	private DownloadInfos downloadInfos;
	private MenuItem optionsMenuitemRefresh;
	private LinearLayout linearlayoutInfos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_info);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		initializeViews();
		
		downloadInfos = new DownloadInfos();
		
		getInfosFromCache();
		
	}
	
	private void initializeViews() {
		linearlayoutInfos = (LinearLayout) findViewById(R.id.linearlayout_infos);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_info, menu);
		optionsMenuitemRefresh = menu.findItem(R.id.menu_refresh);
		
//		loadInfos();
		
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
	
	
	public interface InfosCallback {
		void infosDownloaded(ArrayList<Information> infos);
	}
	
	
	private void getInfosFromCache() {
		// TODO get the infos from cache and display them 
		// meanwhile new infos are being downloaded and 
		// eventually the view will get refreshed
		
		addAdapterToListViews(downloadInfos.getInfosFromCache());
	}
	
	private void loadInfos() {
		setRefreshActionButtonState(true);
		downloadInfos.downloadInfos(this, new InfosCallback() {
			@Override
			public void infosDownloaded(ArrayList<Information> infos) {
				addAdapterToListViews(infos);
				setRefreshActionButtonState(false);
			}
		});
	}
	
	
	private void setRefreshActionButtonState(boolean refreshing) {
		if(refreshing) {
			optionsMenuitemRefresh.setActionView(R.layout.actionbar_indeterminate_progress);
		} else {
			optionsMenuitemRefresh.setActionView(null);
		}
	}

	private ListView listviewCurrentlyOpen;
	private Button buttonCurrentlyOpen;
	
	private void addAdapterToListViews(List<Information> infos) {
		linearlayoutInfos.removeAllViews();
		
		String[] areas = downloadInfos.getAreas(infos);
		
		for (String area : areas) {
			View viewInfos = getLayoutInflater().inflate(R.layout.list_info, null);
			
			//Add area as title
			TextView textviewArea = (TextView) viewInfos.findViewById(R.id.textview_area);
			textviewArea.setText(area);
			
			//Add list of Infos for specific area
			List<Information> filteredInfos = DownloadInfos.getInfosForArea(infos, area);
			final ListView list = (ListView) viewInfos.findViewById(R.id.listview_infos);
			ArrayAdapter<Information> listadapter = new InfosAdapter(this, 
					R.layout.listview_item_info, R.id.textview_busline, 
					filteredInfos);
			list.setAdapter(listadapter);
			
			
			
			//Add button for expanding/collapsing infos for a specific area
			final Button buttonExpandCollapse = (Button) viewInfos.findViewById(R.id.button_expand);
			buttonExpandCollapse.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (list.getVisibility() == View.GONE) {
						expand(buttonExpandCollapse, list);
						
						if (listviewCurrentlyOpen != null && listviewCurrentlyOpen != list) {
							collapse(buttonCurrentlyOpen, listviewCurrentlyOpen);
						}
						listviewCurrentlyOpen = list;
						buttonCurrentlyOpen = buttonExpandCollapse;
					} else {
						collapse(buttonExpandCollapse, list);
					}
				}
			});
			
			linearlayoutInfos.addView(viewInfos);
			
		}
	}
	
	private void expand(Button button, ListView list) {
		button.setCompoundDrawablesWithIntrinsicBounds(
				0, 0, R.drawable.ic_navigation_collapse, 0);
		list.setVisibility(View.VISIBLE);
	}
	
	private void collapse(Button button, ListView list) {
		button.setCompoundDrawablesWithIntrinsicBounds(
				0, 0, R.drawable.ic_navigation_expand, 0);
		list.setVisibility(View.GONE);
	}
	
}