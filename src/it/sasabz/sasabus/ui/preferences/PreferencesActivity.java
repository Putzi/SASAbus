/**
 * 
 */
package it.sasabz.sasabus.ui.preferences;

import it.sasabz.android.sasabus.R;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

import android.os.Bundle;

/**
 * Manages all the personal preferences of the user
 */
public class PreferencesActivity extends SherlockPreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
		getSupportActionBar().setHomeButtonEnabled(true);
	}

}
