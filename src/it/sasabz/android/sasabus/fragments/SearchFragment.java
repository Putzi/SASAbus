package it.sasabz.android.sasabus.fragments;

import it.sasabz.android.sasabus.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class SearchFragment extends SherlockFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		
		return view;
	}
	
}
