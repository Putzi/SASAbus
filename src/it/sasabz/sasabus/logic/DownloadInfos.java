package it.sasabz.sasabus.logic;

import it.sasabz.sasabus.data.orm.InformationList;
import it.sasabz.sasabus.ui.info.InfoActivity.InfosCallback;
import android.app.Activity;

public class DownloadInfos {
	
	
	public void getInfosFromCache(InfosCallback callback) {
		
		//TODO retrieve the infos from the cache
	}

	public void downloadInfos(final Activity activity, final InfosCallback callback) {
		
		//TODO download the infos and generate necessary objects
		
//		InformationList info = new InformationList(callback);
//		info.execute();
		
		
		Thread thread = new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e){
					e.printStackTrace();
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
		
		
		//TODO save infos to cache, so that the next time a user
		//enters he sees the old infos, while new ones are being downloaded
		
	}
	
}
