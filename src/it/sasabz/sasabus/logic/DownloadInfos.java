package it.sasabz.sasabus.logic;

import it.sasabz.sasabus.data.orm.InformationList;
import it.sasabz.sasabus.ui.info.InfoActivity.InfosCallback;
import android.app.Activity;

public class DownloadInfos {
	

	public void downloadInfos(final Activity activity, final InfosCallback callback) {
		
		//TODO download the infos and generate necessary objects
		
		InformationList info = new InformationList(activity, callback);
		info.execute();
		
	}
	
}
