package it.sasabz.android.sasabus.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it.sasabz.android.sasabus.R;
import it.sasabz.android.sasabus.classes.dialogs.DatePicker;
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
		
		return view;
	}
	
	private void insertCurrentDateIntoButton(View view){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = simpleDateFormat.format(new Date());
        
        
        Button buttonDate = (Button) view.findViewById(R.id.button_date);
        buttonDate.setText(currentDate);
	}
	
	private void insertCurrentTimeIntoButton(View view){
		
	}
	
	private void addOnclickListenerForDate(View view){
		Button buttonDate = (Button) view.findViewById(R.id.button_date);
		buttonDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openDatePickerDialog(v);
			}
		});
	}
	
	public void openDatePickerDialog(View view){
		DatePicker datePicker = new DatePicker();
		
		datePicker.buttonDate = (Button) view;
		datePicker.show(getSherlockActivity().getSupportFragmentManager(), "Date Picker");
	}
	
	
}