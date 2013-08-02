package it.sasabz.sasabus.ui;

import it.sasabz.android.sasabus.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;

public class CustomDialog extends SherlockDialogFragment {
	
	static String TITLE = "title";
	static String MESSAGE = "message";
	static String POSITIVE = "positive";
	static String NEGATIVE = "negative";
	
	private TextView textviewTitle;
	private TextView textViewMessage;
	private Button buttonNegative;
	private Button buttonPositive;
	
	private DialogInterface.OnClickListener positiveListener;
	private DialogInterface.OnClickListener negativeListener;
	
	public static CustomDialog newInstance(String title, String text,
			String textPositiveButton, String textNegativeButton) {
		CustomDialog infoDialog = new CustomDialog();
		
		Bundle args = new Bundle();
			args.putString(TITLE, title);
			args.putString(MESSAGE, text);
			args.putString(POSITIVE, textPositiveButton);
			args.putString(NEGATIVE, textNegativeButton);
		infoDialog.setArguments(args);
		
		return infoDialog;
	}
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		Dialog dialog = new Dialog(getSherlockActivity());
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		dialog.setContentView(R.layout.custom_dialog);
		
		WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
		wmlp.width = WindowManager.LayoutParams.FILL_PARENT;
		
		initializeViews(dialog);
		
		setText();
		
		
		return dialog;
	}
	
	private void initializeViews(Dialog dialog){
		textviewTitle = (TextView) dialog.findViewById(R.id.textview_title);
		textViewMessage = (TextView) dialog.findViewById(R.id.textview_message);
		buttonNegative = (Button) dialog.findViewById(R.id.button_negative);
		buttonPositive = (Button) dialog.findViewById(R.id.button_positive);
	}
	
	private void setText() {
		Bundle arguments = getArguments();
		String title = arguments.getString(TITLE);
		String message = arguments.getString(MESSAGE);
		String negative = arguments.getString(NEGATIVE);
		String positive = arguments.getString(POSITIVE);
		
		buttonNegative.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (negativeListener != null) {
					negativeListener.onClick(getDialog(), 0);
				}
			}
		});
		
		buttonPositive.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (positiveListener != null) {
					positiveListener.onClick(getDialog(), 0);
				}
			}
		});
		
		if (negative == null) {
			buttonNegative.setVisibility(View.GONE);
		}
		
		textviewTitle.setText(title);
		textViewMessage.setText(message);
		buttonNegative.setText(negative);
		buttonPositive.setText(positive);
	}
	
	public void setOnPositiveClickListener(DialogInterface.OnClickListener positiveListener) {
		this.positiveListener = positiveListener;
	}
	
	public void setOnNegativeClickListener(DialogInterface.OnClickListener negativeListener) {
		this.negativeListener = negativeListener;
	}
	
}