package it.sasabz.sasabus.ui.busline;

import it.sasabz.android.sasabus.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class BusSchedulesFragment extends SherlockFragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_bus_schedules, container, false);
		
		return view;
	}

}