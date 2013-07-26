package it.sasabz.sasabus.logic;

import it.sasabz.sasabus.ui.info.InfoActivity.InfosCallback;
import android.app.Activity;

public class DownloadInfos {

	public void downloadInfos(final Activity activity, final InfosCallback callback) {
		
		//TODO download the infos and generate necessary objects
		
		
		Thread thread = new Thread(){
			@Override
			public void run() {
				try {
					synchronized(this) {
						wait(2000);
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							callback.infosDownloaded(null);
						}
					});
				}
				
			}
		};
		thread.start();
	}
	
}
