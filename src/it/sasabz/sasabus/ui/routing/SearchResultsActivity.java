package it.sasabz.sasabus.ui.routing;

import java.util.ArrayList;

import it.sasabz.android.sasabus.R;
import it.sasabz.sasabus.data.hafas.XMLConnection;
import it.sasabz.sasabus.logic.BusSchedulesDatabase;
import it.sasabz.sasabus.logic.SearchConnection;
import it.sasabz.sasabus.ui.Utility;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

public class SearchResultsActivity extends SherlockActivity{
	
	private TextView textviewDeparture;
	private TextView textviewArrival;
	private SearchConnection connection;
	private ExpandableListView expandablelistviewResults;
	
	private String departure = "";
	private String arrival = "";
	private String date = "";
	private String time = "";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addIndeterminateProgressBar();
		
		setContentView(R.layout.fragment_search_results);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		connection = new SearchConnection();
		
		Bundle extras = getIntent().getExtras();
		
		getParametersForSearch(extras);
		
		
		initializeViews();
		
		addBusstopsToTextview();
		
		searchForConnection(departure, arrival, date, time);
		
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
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
	
	
	private void getParametersForSearch(Bundle extras) {
		try {
			departure = extras.getString("departure");
			arrival = extras.getString("arrival");
			date = extras.getString("date");
			time = extras.getString("time");
		} catch (Exception e) {
			Log.e("NULL", "Parameters for connection search are null");
		}
	}
	
	
	private void initializeViews() {
		textviewDeparture = (TextView) findViewById(R.id.textview_departure);
		textviewArrival = (TextView) findViewById(R.id.textview_arrival);
		expandablelistviewResults = (ExpandableListView) findViewById
				(R.id.expandablelistview_results);
	}
	
	private void addBusstopsToTextview() {
		textviewDeparture.setText(departure);
		textviewArrival.setText(arrival);
	}
	
	
	private void searchForConnection(String departure, String arrival, String date, String time) {
		connection.searchForConnection(departure, arrival, date, time, this, new SearchCallback() {
			@Override
			public void searchIsFinished(ArrayList<XMLConnection> connections) {
				addAdapterToExpandableListView();
				setGroupIndicatorToRight();
				removeIndeterminateProgressBar();
				expandablelistviewResults.setVisibility(View.VISIBLE);
			}
		});
	}
	
	public interface SearchCallback {
		void searchIsFinished(ArrayList<XMLConnection> connections);
	}
	
	
	private void addAdapterToExpandableListView() {
		SearchResultsAdapter adapter = new SearchResultsAdapter(this,
				connection.getConncections(), connection.getConnectionDetails());
		expandablelistviewResults.setAdapter(adapter);
	}

	private void setGroupIndicatorToRight() {
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
 
        expandablelistviewResults.setIndicatorBounds(width - Utility.getDipsFromPixel(this, 100), width
                - Utility.getDipsFromPixel(this, 10));
	}
	
	
}