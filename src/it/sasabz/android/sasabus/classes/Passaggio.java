/**
 * 
 *
 * Passaggio.java
 * 
 * Created: 14.12.2011 16:34:43
 * 
 * Copyright (C) 2011 Paolo Dongilli & Markus Windegger
 * 
 *
 * This file is part of SasaBus.

 * SasaBus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SasaBus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SasaBus.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package it.sasabz.android.sasabus.classes;

import android.database.Cursor;
import android.text.format.Time;


/**
 * @author Markus Windegger (markus@mowiso.com)
 *
 */
public class Passaggio extends DBObject {
	
	private int idPalina = 0;
	
	private int codCorsa = 0;
	
	private int progressivo = 0;
	
	private Time orario = null;

	
	public Passaggio()
	{
		super();
	}
	
	public Passaggio(int id, int idPalina, int codCorsa, int progressivo, Time orario)
	{
		super(id);
		this.setIdPalina(idPalina);
		this.setCodCorsa(codCorsa);
		this.setProgressivo(progressivo);
		this.setOrario(orario);
	}
	
	public Passaggio(int id, int idPalina, int codCorsa, int progressivo, String orario)
	{
		super(id);
		this.setIdPalina(idPalina);
		this.setCodCorsa(codCorsa);
		this.setProgressivo(progressivo);
		this.setOrario(orario);
	}
	
	public Passaggio(Cursor c)
	{
		super(c.getInt(c.getColumnIndex("_id")));
		this.setIdPalina(c.getInt(c.getColumnIndex("id_palina")));
		this.setCodCorsa(c.getInt(c.getColumnIndex("codice_corsa")));
		this.setProgressivo(c.getInt(c.getColumnIndex("progressivo")));
		this.setOrario(c.getString(c.getColumnIndex("orario")));
	}
	
	
	
	
	
	/**
	 * @return the idPalina
	 */
	public int getIdPalina() {
		return idPalina;
	}

	/**
	 * @param idPalina the idPalina to set
	 */
	public void setIdPalina(int idPalina) {
		this.idPalina = idPalina;
	}

	/**
	 * @return the codCorsa
	 */
	public int getCodCorsa() {
		return codCorsa;
	}

	/**
	 * @param codCorsa the codCorsa to set
	 */
	public void setCodCorsa(int codCorsa) {
		this.codCorsa = codCorsa;
	}

	/**
	 * @return the progressivo
	 */
	public int getProgressivo() {
		return progressivo;
	}

	/**
	 * @param progressivo the progressivo to set
	 */
	public void setProgressivo(int progressivo) {
		this.progressivo = progressivo;
	}

	/**
	 * @return the orario
	 */
	public Time getOrario() {
		return orario;
	}

	/**
	 * @param orario the orario to set
	 */
	public void setOrario(Time orario) {
		this.orario = orario;
	}
	
	/**
	 * @param orario the orario to set
	 */
	public void setOrario(String orario) {
		this.orario = new Time();
		String [] split = orario.split(":");
		this.orario.set(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0]), 0, 0, 0);
	}
	
	
}