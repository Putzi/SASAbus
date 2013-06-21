package it.sasabz.android.sasabus.classes.dialogs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockDialogFragment;

public class DatePicker extends SherlockDialogFragment implements OnDateSetListener{

	/**
	 * Button that opens the DatePicker and
	 * which Text is to set with the date
	 */
	public Button buttonDate;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * gets called when the dialog is being created
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		//Get the date from the Button and set it to the picker
		String dateAlreadySetString = buttonDate.getText().toString();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ITALIAN);
		Date dateAlreadySet = null;
		try
		{
			dateAlreadySet = simpleDateFormat.parse(dateAlreadySetString);
		} catch (ParseException e)
		{
			Log.e("error", "could not parse date");
			e.printStackTrace();
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateAlreadySet);
	
		int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        
		return new DatePickerDialog(getSherlockActivity(), this, year, monthOfYear, dayOfMonth);
	}

	/**
	 * gets called when the user has set a date and clicked on finish
	 */
	@Override
	public void onDateSet(android.widget.DatePicker view, int year,
			int monthOfYear, int dayOfMonth) {
		
		//in Android the month starts with 0, 
		//therefore we have to add 1
		Integer actualMonth = monthOfYear+1;
		
		String actualMonthString = actualMonth.toString();
		if (actualMonthString.length() < 2) {
			actualMonthString = "0"+actualMonthString;
		}
		
		buttonDate.setText(dayOfMonth+"."+actualMonthString+"."+year);
	}
	
}