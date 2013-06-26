/**
 *
 * TabHostActivity.java
 *
 * Created: Mar 15, 2012 22:40:06 PM
 *
 * Copyright (C) 2012 Paolo Dongilli and Markus Windegger
 *
 * This file is part of SasaBus.

 * SasaBus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SasaBus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SasaBus. If not, see <http://www.gnu.org/licenses/>.
 *
 * This activity provides a map an the possibility to show a list of
 * bus stops which were contained in a "journey" (from - to)
 *
 * This activity is the "main" activity of this application. It holds the tabhostwidget, which
 * controls the activities added to the tabs (like online-offlineActivity etc 
 *
 */
package it.sasabz.android.sasabus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import it.sasabz.android.sasabus.classes.dialogs.About;
import it.sasabz.android.sasabus.classes.dialogs.Credits;
import it.sasabz.android.sasabus.fragments.BusSchedulesFragment;
import it.sasabz.android.sasabus.fragments.InfoFragment;
import it.sasabz.android.sasabus.fragments.NextBusFragment;
import it.sasabz.android.sasabus.fragments.SearchFragment;
import it.sasabz.android.sasabus.utility.Utility;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
/**
 *Main Activity that holds all the tabs
 */
public class MainTabActivity extends SherlockFragmentActivity {

	private TabsAdapter mTabsAdapter;
	private ViewPager mViewPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Resources resources = getResources();

		addTabs(savedInstanceState);

		setInitialTab();
	}

	/**
	 * Adds Tabs to the TabsAdapter and set the text
	 * 
	 * @param savedInstanceState
	 *            is the Bundle where the current tab may be saved
	 */
	private void addTabs(Bundle savedInstanceState) {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// ViewPager
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		setContentView(mViewPager);

		mTabsAdapter = new TabsAdapter(this, mViewPager);

		// addTabs
		// Tab Search
		String textTabSearch = getResources().getString(
				R.string.search_connection);
		mTabsAdapter.addTab(actionBar.newTab().setText(textTabSearch),
				SearchFragment.class, null);

		// Tab Bus Schedules
		String textTabSchedules = getResources().getString(R.string.offline);
		mTabsAdapter.addTab(actionBar.newTab().setText(textTabSchedules),
				BusSchedulesFragment.class, null);

		// Tab Next Bus
		String textTabNextBus = getResources().getString(R.string.next_bus);
		mTabsAdapter.addTab(actionBar.newTab().setText(textTabNextBus),
				NextBusFragment.class, null);

		// Tab Infos
		String textTabInfos = getResources().getString(R.string.info);
		mTabsAdapter.addTab(actionBar.newTab().setText(textTabInfos),
				InfoFragment.class, null);

		if (savedInstanceState != null)
		{
			actionBar.setSelectedNavigationItem(savedInstanceState.getInt(
					"activeTab", 0));
		}
	}
	
	private void setInitialTab() {
		if (Utility.hasNetworkConnection(this)) {
			getSupportActionBar().setSelectedNavigationItem(0);
		} else {
			getSupportActionBar().setSelectedNavigationItem(1);
		}
	}
	
	/**
	 * Add the menu
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.optionmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			new About(this).show();
			return true;
		case R.id.menu_map:
			new Credits(this).show();
			return true;
		}
		return false;
	}
	
	
	/**
	 * Handles the Tab changes and swiping through tabs
	 */
	public static class TabsAdapter extends FragmentPagerAdapter implements
			ActionBar.TabListener, ViewPager.OnPageChangeListener {
		private final Context mContext;
		private final ActionBar mActionBar;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		static final class TabInfo {
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(Class<?> _class, Bundle _args) {
				clss = _class;
				args = _args;
			}
		}

		public TabsAdapter(SherlockFragmentActivity activity, ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mActionBar = activity.getSupportActionBar();
			mViewPager = pager;
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		/**
		 * Adds a new Tab to the ActionBar
		 * @param tab
		 * @param clss
		 * @param args
		 */
		public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
			TabInfo info = new TabInfo(clss, args);
			tab.setTag(info);
			tab.setTabListener(this);
			mTabs.add(info);
			mActionBar.addTab(tab);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			mActionBar.setSelectedNavigationItem(position);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			Object tag = tab.getTag();
			for (int i = 0; i < mTabs.size(); i++)
			{
				if (mTabs.get(i) == tag)
				{
					mViewPager.setCurrentItem(i);
				}
			}
			
//			if (tag.equals(mContext.getResources().getString(R.string.online))
//					&& !Utility.hasNetworkConnection(this))
//			{
//				getTabHost().setCurrentTabByTag(
//						getResources().getString(R.string.offline));
//				AlertDialog.Builder builder = new AlertDialog.Builder(this);
//				builder.setCancelable(true);
//				builder.setMessage(R.string.no_network_connection);
//				builder.setTitle(R.string.error_title);
//				builder.setNeutralButton(android.R.string.cancel,
//						new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								dialog.cancel();
//								dialog.dismiss();
//							}
//						});
//				builder.create().show();
//			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			
		}

	}
}
