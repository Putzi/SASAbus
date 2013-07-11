package it.sasabz.sasabus.ui.busschedules;

import it.sasabz.android.sasabus.R;
import it.sasabz.sasabus.data.models.DBObject;
import it.sasabz.sasabus.data.models.Itinerary;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BuslineDepartureAdapter extends ArrayAdapter<Itinerary> {

	public BuslineDepartureAdapter(Context context, int resource,
			int textViewResourceId, List<Itinerary> objects) {
		super(context, resource, textViewResourceId, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = super.getView(position, convertView, parent);

		TextView textviewBusstop = (TextView) view.findViewById(R.id.textviewBusstop);
		TextView textviewTime = (TextView) view.findViewById(R.id.textviewTime);
		
		String busstop = "" + super.getItem(position).getBusStopId();
		Time timeTime = super.getItem(position).getTime();
		String time = timeTime.hour+":"+timeTime.minute;
		
		textviewBusstop.setText(busstop);
		textviewTime.setText(time);
		
		return view;
	}

}