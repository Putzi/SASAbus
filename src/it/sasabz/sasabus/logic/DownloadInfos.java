package it.sasabz.sasabus.logic;

import java.util.ArrayList;
import java.util.List;

import it.sasabz.sasabus.data.models.DBObject;
import it.sasabz.sasabus.data.models.Information;
import it.sasabz.sasabus.data.orm.InformationList;
import it.sasabz.sasabus.ui.info.InfoActivity.InfosCallback;
import android.app.Activity;

public class DownloadInfos {

	
	public List<Information> getInfosFromCache() {
		
		List<Information> infos = new ArrayList<Information>();
		
		//TODO retrieve the infos from the cache
		infos.add(new Information(0, "BZ: Umleitung Linien 113 und interne Linie Leifers", "BZ: Deviazione linee 113 ed interna linea Laives", 
				"MONTAG, 5. AUGUST 2013, EIN ABSCHNITT DER WEISSENSTEINSTR. WEGEN AUSHUB-ARBEITEN FÜR DEN VERKEHR GESPERRT WIRD."+
				"DAHER WERDEN DIE LINIEN 113 UND INTERNE LINIE LEIFERS FOLGENDE STRECKENFÜHRUNG DURCHFÜHREN:"+
				"LINIE 113"+
				"AB ZUGBAHNHOF: UNVERÄNDERT BIS ZUR DANTE STR., WEITER ÜBER DIE WEISSENSTEIN STR.,  KENNEDY STR. USW.;"+
				"AB PFATTEN: UNVERÄNDERT BIS ZUR KENNEDY STR., DANN WEISSENSTEIN STR., DANTE STR. USW."+
				"INTERNE LINIE LEIFERS"+
				"AB ZUGBAHNHOF: UNVERÄNDERT BIS ZUR A. DIAZ STR., WEITER ÜBER DIE  KENNEDY STR.,  WEISSENSTEIN STR., DANTE STR. USW."+
				"BEI BEDARF WERDEN ALLE AUF DER STRECKE LIEGENDEN HALTESTELLEN EINGEHALTEN.",
				"LUNEDÌ 5 AGOSTO 2013, VERRÀ CHIUSO AL TRAFFICO UN TRATTO DI VIA PIETRALBA, CAUSA LAVORI DI SCAVO."+
				"PERTANTO LE LINEE 113 ED INTERNA LAIVES EFFETTUERANNO IL SEGUENTE PERCORSO:"+
				"LINEA 113"+
				"DA STAZIONE: INVARIATO FINO IN VIA DANTE POI VIA PIETRALBA, VIA KENNEDY ECC.;"+
				"DA VADENA: INVARIATO FINO IN VIA KENNEDY POI VIA PIETRALBA, VIA DANTE, ECC."+
				"LINEA INTERNA LAIVES"+
				"DA STAZIONE: INVARIATO FINO IN VIA A. DIAZ POI VIA KENNEDY, VIA PIETRALBA, VIA DANTE ECC.;"+
				"A RICHIESTA VERRANNO EFFETTUATE TUTTE LE FERMATE PRESENTI SUL PERCORSO.", 0));
		infos.add(new Information(1, "Einrichtung einer neuen Haltestelle in Siebeneich ab 8.7.2013", "Einrichtung einer neuen Haltestelle in Siebeneich ab 8.7.2013", "Message...", "Message...", 0));
		infos.add(new Information(2, "Verlegung der Haltestelle \"St. Jakob Str. 4\"", "Verlegung der Haltestelle \"St. Jakob Str. 4\"", "Message...", "Message...", 0));
		infos.add(new Information(3, "Nightliner Tarif der letzten Kursfahrten der Linie Bozen-Meran ab 29.3.2013", "Nightliner Tarif der letzten Kursfahrten der Linie Bozen-Meran ab 29.3.2013", "Message...", "Message...", 0));
		infos.add(new Information(4, "Nightliner Tarif der letzten Kursfahrten der Linie Bozen-Meran ab 29.3.2013", "Nightliner Tarif der letzten Kursfahrten der Linie Bozen-Meran ab 29.3.2013", "Message...", "Message...", 0));
		
		
		infos.add(new Information(0, "Streckenabänderung der Linien 3 und 6 – Änderung der Fahrtenfrequenz der Linie 4 an Sonn- und Feiertagen", "Streckenabänderung der Linien 3 und 6 – Änderung der Fahrtenfrequenz der Linie 4 an Sonn- und Feiertagen", "Message...", "Message...", 1));
		infos.add(new Information(1, "Umleitung der Linien 3, 4, 6 und 146 ab 24.7.2013", "Umleitung der Linien 3, 4, 6 und 146 ab 24.7.2013", "Message...", "Message...", 1));
		infos.add(new Information(3, "Umleitung der Stadtlinien anlässlich der Veranstaltungen \"Langer Dienstag\"", "Umleitung der Stadtlinien anlässlich der Veranstaltungen \"Langer Dienstag\"", "Message...", "Message...", 1));
		infos.add(new Information(4, "Einrichtung einer neuen Haltestelle in Siebeneich ab 8.7.2013", "Einrichtung einer neuen Haltestelle in Siebeneich ab 8.7.2013", "Message...", "Message...", 1));
		infos.add(new Information(5, "Der Fahrkartenschalter in Meran zieht um", "Der Fahrkartenschalter in Meran zieht um", "Message...", "Message...", 1));
		infos.add(new Information(6, "Umleitung der Linien 211, 212 und 213 ab 4.2.2013", "Umleitung der Linien 211, 212 und 213 ab 4.2.2013", "Message...", "Message...", 1));
		infos.add(new Information(7, "Nightliner Tarif der letzten Kursfahrten der Linie Bozen-Meran ab 29.3.2013", "Nightliner Tarif der letzten Kursfahrten der Linie Bozen-Meran ab 29.3.2013", "Message...", "Message...", 1));
		
		
		return infos;
		
	}

	public void downloadInfos(final Activity activity, final InfosCallback callback) {
		
		//TODO download the infos and generate necessary objects
		
		InformationList info = new InformationList(callback);
		info.execute();
		//↑ returns null!?
		
		
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
//		thread.start();
		
		
		//TODO save infos to cache, so that the next time a user
		//enters he sees the old infos, while new ones are being downloaded
		
	}

	public String[] getAreas(List<Information> infos) {
		String[] areas = null;
		
		
		//TODO get all areas (cities) that are mentioned in the news
		//should only be Merano and Bolzano (but let's keep it clean and flexible) 😊
		//maybe sort by name ascending
		
		areas = new String[]{"Bolzano", "Merano"};
		
		return areas;
	}

	public static List<Information> getInfosForArea(List<Information> infos, String area) {
		// TODO return only the infos which belong to a certain area
		List<Information> filteredInfos = new ArrayList<Information>();
		
		for (Information information : infos) {
			if (information.getStadt() == 0 && area == "Bolzano") {
				filteredInfos.add(information);
			} else if (information.getStadt() == 1 && area == "Merano") {
				filteredInfos.add(information);
			}
		}
		
		return filteredInfos;
	}

	
}
