package it.sasabz.sasabus.ui.news;

import it.sasabz.android.sasabus.R;
import it.sasabz.sasabus.data.models.News;
import it.sasabz.sasabus.logic.DownloadNews;
import it.sasabz.sasabus.ui.CustomDialog;
import it.sasabz.sasabus.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;

/**
 * Display general information about changes of routes ecc.
 */
public class NewsFragment extends SherlockFragment {

	private ViewPager mViewPager;
	private NewsPagerAdapter mPagerAdapter;
	
	private MenuItem mOptionsMenuitemRefresh;
	private LinearLayout mLinearlayoutInfos;
	
	private String[] mCities;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_news, container, false);
		
		getSherlockActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		initializeViews(view);
		
		addTabs(view);
		
		getInfosFromCache();
		
		setHasOptionsMenu(true);
		
		return view;
	}
	
	
	private void initializeViews(View view) {
		mLinearlayoutInfos = (LinearLayout) view.findViewById(R.id.linearlayout_infos);
		
		mCities = getResources().getStringArray(R.array.cities);
	}
	
	private void addTabs(View view) {
		// Instantiate the ViewPager
		 mViewPager = (ViewPager) view.findViewById(R.id.pager);
		 
		 // Instantiate the PagerAdapter
		 mPagerAdapter = new NewsPagerAdapter(mViewPager, getChildFragmentManager(), getSherlockActivity());
		 
		 // Add Tabs to the Adapter
		 mPagerAdapter.addTab(null, mCities[0], DownloadNews.getInfosFromCache());
		 mPagerAdapter.addTab(null, mCities[1], DownloadNews.getInfosFromCache());
		 
		 mViewPager.setAdapter(mPagerAdapter);
		 
		 // Bind the widget to the adapter
		 PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		 tabs.setViewPager(mViewPager);
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		MainActivity parentActivity = (MainActivity) getSherlockActivity();
		boolean drawerIsOpen = parentActivity.isDrawerOpen();
		//If the drawer is closed, show the menu related to the content
		if (!drawerIsOpen) {
			menu.clear();
			parentActivity.getSupportMenuInflater().inflate(R.menu.info_fragment, menu);
		}
		super.onPrepareOptionsMenu(menu);
	}
	
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getSherlockActivity().getSupportMenuInflater().inflate(R.menu.info_fragment, menu);
		mOptionsMenuitemRefresh = menu.findItem(R.id.menu_refresh);
		
		loadInfos();
		super.onCreateOptionsMenu(menu, inflater);
	}
	
//	@Override
//	public void onPrepareOptionsMenu(Menu menu) {
//		getSherlockActivity().getSupportMenuInflater().inflate(R.menu.info_fragment, menu);
//		super.onPrepareOptionsMenu(menu);
//	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
//			finish();
			break;
		case R.id.menu_refresh:
			loadInfos();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public interface NewsCallback {
		void newsDownloaded(List<News> infos);
	}
	
	
	private void getInfosFromCache() {
		// TODO get the infos from cache and display them 
		// meanwhile new infos are being downloaded and 
		// eventually the view will get refreshed
		DownloadNews.getInfosFromCache();
	}
	
	private void loadInfos() {
		setRefreshActionButtonState(true);
		DownloadNews.downloadInfos(this, new NewsCallback() {
			@Override
			public void newsDownloaded(List<News> infos) {
				addAdapterToListViews(infos);
				setRefreshActionButtonState(false);
			}
		});
	}
	
	
	private void setRefreshActionButtonState(boolean refreshing) {
		if(refreshing) {
			mOptionsMenuitemRefresh.setActionView(R.layout.actionbar_indeterminate_progress);
		} else {
			mOptionsMenuitemRefresh.setActionView(null);
		}
	}
	
	private void addAdapterToListViews(List<News> infos) {
		List<List<News>> cityInfos = new ArrayList<List<News>>();
		cityInfos.add(DownloadNews.getInfosForArea(infos, DownloadNews.BOLZANO));
		cityInfos.add(DownloadNews.getInfosForArea(infos, DownloadNews.MERANO));
//		for (int i = 1; i <= mCities.length; i++) {
//			cityInfos.add(DownloadNews.getInfosForArea(infos, i));
//		}
		mPagerAdapter.setData(cityInfos);
	}
	
}