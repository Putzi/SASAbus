package it.sasabz.sasabus.ui.info;

import java.util.List;

import it.sasabz.android.sasabus.R;
import it.sasabz.sasabus.data.models.DBObject;
import it.sasabz.sasabus.data.models.Information;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class InfosAdapter extends ArrayAdapter<Information>{
	
	private Context context;
	private int resource;
	
	public InfosAdapter(Context context, int resource, int textViewResourceId,
			List<Information> objects) {
		super(context, resource, textViewResourceId, objects);
		this.context = context;
		this.resource = resource;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;

        if (convertView == null) {
        	view = LayoutInflater.from(context).inflate(resource, null);
        } else {
            view = convertView;
        }
        
        TextView textviewBusline = (TextView) view.findViewById(R.id.textview_busline);
        String buslines = context.getResources().getString(R.string.lines)
        		+"\n"+getItem(position).getStadt();
        textviewBusline.setText(buslines);
        
        TextView textviewInfoTitle = (TextView) view.findViewById(R.id.textview_title);
        String infoTitle = getItem(position).getTitel();
        textviewInfoTitle.setText(infoTitle);
        

        return view;
		
		
	}
}
