package it.sasabz.sasabus.ui.routing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import it.sasabz.android.sasabus.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.actionbarsherlock.app.SherlockFragment;

public class SearchFragment extends SherlockFragment {

	private AutoCompleteTextView autoCompleteTextViewDeparture;
	private AutoCompleteTextView autoCompleteTextViewArrival;
	
	private LinearLayout linearLayoutDepartureMore;
	private ImageButton imageButtonDepartureMore;
	
	private LinearLayout linearLayoutArrivalMore;
	private ImageButton imageButtonArrivalMore;
	
	private LinearLayout linearLayoutSearchButtonsDeparture;
	private LinearLayout linearLayoutSearchButtonsArrival;
	
	private Button buttonDate;
	private Button buttonTime;
	private Button buttonSearch;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		
		initializeViews(view);
		
		addOnClickListenerForMoreButtons(view);
		
		insertCurrentDateIntoButton(view);
		addOnclickListenerForDate(view);
		
		insertCurrentTimeIntoButton(view);
		addOnclickListenerForTime(view);
		
		addOnclickListenerForSearchButton(view);
		
		return view;
	}

	
	/**
	 * Initialize all the views present in the search fragment
	 * @param view the fragment which gets inflated
	 */
	private void initializeViews(View view) {
		autoCompleteTextViewDeparture = (AutoCompleteTextView) view.findViewById(R.id.autocompletetextview_departure);
		autoCompleteTextViewArrival = (AutoCompleteTextView) view.findViewById(R.id.autocompletetextview_arrival);
		
		linearLayoutDepartureMore = (LinearLayout) view.findViewById(R.id.linearlayout_departure_more);
		imageButtonDepartureMore = (ImageButton) linearLayoutDepartureMore.findViewById(R.id.imagebutton_more);
		
		linearLayoutArrivalMore = (LinearLayout) view.findViewById(R.id.linearlayout_arrival_more);
		imageButtonArrivalMore = (ImageButton) linearLayoutArrivalMore.findViewById(R.id.imagebutton_more);
		
		linearLayoutSearchButtonsDeparture = (LinearLayout) view.findViewById(R.id.linearlayout_departure_buttons);
		linearLayoutSearchButtonsArrival = (LinearLayout) view.findViewById(R.id.linearlayout_arrival_buttons);
		
		buttonDate = (Button) view.findViewById(R.id.button_date);
		buttonTime = (Button) view.findViewById(R.id.button_time);
		
		View bs = view.findViewById(R.id.button_search);
		buttonSearch = (Button) bs.findViewById(R.id.button_search);
	}


	//More buttons
	private void addOnClickListenerForMoreButtons(View view) {
		OnClickListener onClickListenerForMoreButtons = new OnClickListener() {
			@Override
			public void onClick(View v) {
				openCloseSearchButtons(v);
			}
		};
		imageButtonDepartureMore.setOnClickListener(onClickListenerForMoreButtons);
			imageButtonDepartureMore.setTag(linearLayoutSearchButtonsDeparture);
		imageButtonArrivalMore.setOnClickListener(onClickListenerForMoreButtons);
			imageButtonArrivalMore.setTag(linearLayoutSearchButtonsArrival);
	}
	
	private LinearLayout container;
	private LayoutParams paramsContainer;
	private LinearLayout button;
	private LayoutParams paramsButton;
	
	private void openCloseSearchButtons(View v) {
		container = (LinearLayout) v.getTag();
		button = (LinearLayout) v.getParent();
		
		paramsContainer = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		paramsButton = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	
		if (!button.isSelected()) {
			paramsContainer.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			paramsButton.addRule(RelativeLayout.LEFT_OF, container.getId());
			
			container.setLayoutParams(paramsContainer);
			button.setLayoutParams(paramsButton);
			
			button.setSelected(true);
		} else {
			paramsButton.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			paramsContainer.addRule(RelativeLayout.RIGHT_OF, button.getId());
			
			button.setLayoutParams(paramsButton);
			container.setLayoutParams(paramsContainer);
			
			button.setSelected(false);
		}
		
	}
	

	//Date
	/**
	 * Insert the current date into the date-button
	 * @param view
	 * 			is the total view of the fragment
	 */
	private void insertCurrentDateIntoButton(View view){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DatePicker.dateFormat, Locale.ITALY);
        String currentDate = simpleDateFormat.format(new Date());
        
        buttonDate.setText(currentDate);
	}
	
	/**
	 * Add an OnClickListener to the date-button with the date,
	 * so that it can open up a DatePicker when the user clicks
	 * @param view
	 * 			is the total view of the fragment
	 */
	private void addOnclickListenerForDate(View view){
		buttonDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				openDatePickerDialog(b);
			}
		});
	}
	
	/**
	 * Actually open the DatePicker
	 */
	public void openDatePickerDialog(Button button){
		String dateButtonText = button.getText().toString();
		
		DatePicker datePicker = new DatePicker();
		datePicker.setDateAlreadySetString(dateButtonText);
		datePicker.setCallback(new DateHasBeenSetListener() {
			@Override
			public void dateHasBeenSet(String date) {
				buttonDate.setText(date);
			}
		});
		datePicker.show(getSherlockActivity().getSupportFragmentManager(), "Date Picker");
	}
	
	public interface DateHasBeenSetListener {
		void dateHasBeenSet(String date);
	}
	
	
	//Time
	/**
	 * get the time-button from the view and insert the current time
	 * @param view
	 * 			is the total view of the fragment
	 */
	private void insertCurrentTimeIntoButton(View view){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimePicker.timeFormat, Locale.ITALY);
        String currentTime = simpleDateFormat.format(new Date());
        
        buttonTime.setText(currentTime);
	}
	
	/**
	 * adds an OnClickListener to the date-button with the date,
	 * so that it can open up a DatePicker when the user clicks
	 * @param view
	 * 			is the total view of the fragment
	 */
	private void addOnclickListenerForTime(View view){
		buttonTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				openTimePickerDialog(b);
			}
		});
	}
	
	/**
	 * actually opens the TimePicker
	 * @param view
	 * 			is the total view of the fragment
	 */
	public void openTimePickerDialog(Button button){
		String timeButtonText = button.getText().toString();
		
		TimePicker timePicker = new TimePicker();
		timePicker.setTimeAlreadySetString(timeButtonText);
		timePicker.setCallback(new TimeHasBeenSetListener() {
			@Override
			public void timeHasBeenSet(String time) {
				buttonTime.setText(time);
				Log.i("testtime",time);
			}
		});
		timePicker.show(getSherlockActivity().getSupportFragmentManager(), "Time Picker");
		
	}
	
	public interface TimeHasBeenSetListener {
		void timeHasBeenSet(String time);
	}
	
	
	//Search Button
	private void addOnclickListenerForSearchButton(View view) {
		buttonSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//Get the contents of the views
				String departurePredefined = autoCompleteTextViewDeparture.getHint().toString();
				String departure = autoCompleteTextViewDeparture.getText().toString();
				String arrival = autoCompleteTextViewArrival.getText().toString();
				String date = buttonDate.getText().toString();
				String time = buttonTime.getText().toString();
				
				//Check if the input fields are not empty
				if (!departure.trim().equals("") || !departurePredefined.trim().equals("") && arrival.trim().equals("")){
					
					//check whether the user has inserted a departure bus stop,
					//or whether we should use the predefined one, which is 
					//the closest bus stop to the last known location
					if(!departurePredefined.trim().equals("")){
						departure = departurePredefined;
					}
					
					departure = "(" + departure.replace(" -", ")");
					arrival = "(" + arrival.replace(" -", ")");
					
				}
				
				Intent intentSearchResults = new Intent(getSherlockActivity(), SearchResultsActivity.class);
				startActivity(intentSearchResults);
			}
		});
		
	}
	
}