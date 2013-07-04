package it.sasabz.sasabus.ui.routing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

	
	private ViewGroup viewgroupDepartureInputfield;
	private AutoCompleteTextView autoCompleteTextViewDeparture;
	
	private ViewGroup viewgroupArrivalInputField;
	private AutoCompleteTextView autoCompleteTextViewArrival;
	
	private ViewGroup viewgroupDepartureMore;
	private ImageButton imageButtonDepartureMore;
	
	private ViewGroup viewgroupArrivalMore;
	private ImageButton imageButtonArrivalMore;
	
	private ViewGroup viewgroupDepartureSearchButtons;
	private ViewGroup viewgroupArrivalSearchButtons;
	
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
		viewgroupDepartureInputfield = (ViewGroup) view.findViewById(R.id.linearlayout_departure_inputfield);
		autoCompleteTextViewDeparture = (AutoCompleteTextView) view.findViewById(R.id.autocompletetextview_departure);
		
		viewgroupArrivalInputField = (ViewGroup) view.findViewById(R.id.linearlayout_arrival_inputfield);
		autoCompleteTextViewArrival = (AutoCompleteTextView) view.findViewById(R.id.autocompletetextview_arrival);
		
		viewgroupDepartureMore = (ViewGroup) view.findViewById(R.id.linearlayout_departure_more);
		imageButtonDepartureMore = (ImageButton) viewgroupDepartureMore.findViewById(R.id.imagebutton_more);
		
		viewgroupArrivalMore = (ViewGroup) view.findViewById(R.id.linearlayout_arrival_more);
		imageButtonArrivalMore = (ImageButton) viewgroupArrivalMore.findViewById(R.id.imagebutton_more);
		
		viewgroupDepartureSearchButtons = (ViewGroup) view.findViewById(R.id.linearlayout_departure_buttons);
		viewgroupArrivalSearchButtons = (ViewGroup) view.findViewById(R.id.linearlayout_arrival_buttons);
		
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
			List<ViewGroup> b1 = new ArrayList<ViewGroup>();
				b1.add(viewgroupDepartureSearchButtons);
				b1.add(viewgroupDepartureInputfield);
			imageButtonDepartureMore.setTag(b1);
			
		imageButtonArrivalMore.setOnClickListener(onClickListenerForMoreButtons);
			List<ViewGroup> b2 = new ArrayList<ViewGroup>();
				b2.add(viewgroupArrivalSearchButtons);
				b2.add(viewgroupArrivalInputField);
			imageButtonArrivalMore.setTag(b2);
	}
	
	private ViewGroup containerButtons;
	private LayoutParams paramsButtons;
	private ViewGroup containerInputfield;
	private LayoutParams paramsInputfield;
	
	private void openCloseSearchButtons(View v) {
		List<?> b = (List<?>) v.getTag();
		containerButtons = (ViewGroup) b.get(0);
		containerInputfield = (ViewGroup) b.get(1);
		LinearLayout button = (LinearLayout) v.getParent();
		
		paramsButtons = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		paramsInputfield = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		
		if (!button.isSelected()) {
			paramsButtons.addRule(RelativeLayout.LEFT_OF, button.getId());
			containerButtons.setLayoutParams(paramsButtons);
			
			paramsInputfield.addRule(RelativeLayout.LEFT_OF, containerButtons.getId());
			containerInputfield.setLayoutParams(paramsInputfield);
			
			button.setSelected(true);
		} else {
			paramsButtons.addRule(RelativeLayout.RIGHT_OF, button.getId());
			containerButtons.setLayoutParams(paramsButtons);
			
			paramsInputfield.addRule(RelativeLayout.LEFT_OF, button.getId());
			containerInputfield.setLayoutParams(paramsInputfield);
			
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