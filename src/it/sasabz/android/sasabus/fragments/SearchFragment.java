package it.sasabz.android.sasabus.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

import it.sasabz.android.sasabus.R;
import it.sasabz.android.sasabus.classes.dialogs.DatePicker;
import it.sasabz.android.sasabus.classes.dialogs.TimePicker;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;

public class SearchFragment extends SherlockFragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		
		insertCurrentDateIntoButton(view);
		addOnclickListenerForDate(view);
		
		insertCurrentTimeIntoButton(view);
		addOnclickListenerForTime(view);
		
		return view;
	}
	
	
	//Date
	/**
	 * get the date-button from the view and insert the current date
	 * @param view
	 * 			is the total view of the fragment
	 */
	private void insertCurrentDateIntoButton(View view){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ITALIAN);
        String currentDate = simpleDateFormat.format(new Date());
        
        Button buttonDate = (Button) view.findViewById(R.id.button_date);
        buttonDate.setText(currentDate);
	}
	
	/**
	 * adds an OnClickListener to the date-button with the date,
	 * so that it can open up a DatePicker when the user clicks
	 * @param view
	 * 			is the total view of the fragment
	 */
	private void addOnclickListenerForDate(View view){
		Button buttonDate = (Button) view.findViewById(R.id.button_date);
		buttonDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openDatePickerDialog(v);
			}
		});
	}
	
	/**
	 * actually opens the DatePicker
	 * @param view
	 * 			is the total view of the fragment
	 */
	public void openDatePickerDialog(View view){
		DatePicker datePicker = new DatePicker();
		
		datePicker.buttonDate = (Button) view;
		datePicker.show(getSherlockActivity().getSupportFragmentManager(), "Date Picker");
	}
	
	
	//Time
	/**
	 * get the time-button from the view and insert the current time
	 * @param view
	 * 			is the total view of the fragment
	 */
	private void insertCurrentTimeIntoButton(View view){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ITALIAN);
        String currentTime = simpleDateFormat.format(new Date());
        
        Button buttonTime = (Button) view.findViewById(R.id.button_time);
        buttonTime.setText(currentTime);
	}
	
	/**
	 * adds an OnClickListener to the date-button with the date,
	 * so that it can open up a DatePicker when the user clicks
	 * @param view
	 * 			is the total view of the fragment
	 */
	private void addOnclickListenerForTime(View view){
		Button buttonTime = (Button) view.findViewById(R.id.button_time);
		buttonTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openTimePickerDialog(v);
			}
		});
	}
	
	/**
	 * actually opens the TimePicker
	 * @param view
	 * 			is the total view of the fragment
	 */
	public void openTimePickerDialog(View view){
		TimePicker timePicker = new TimePicker();
		
		timePicker.buttonTime = (Button) view;
		timePicker.show(getSherlockActivity().getSupportFragmentManager(), "Time Picker");
	}
	
}