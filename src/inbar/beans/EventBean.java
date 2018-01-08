package inbar.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author sabine
 *
 */
public class EventBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int eventid;
	private String eventname;
	private String ebeschreibung;

	// Datentyp aus �bung 11 f�r Datum und Uhrzeit �bernommen.
	private Date startdatum;
	private Date enddatum;
	private Date startzeit;
	private Date endzeit;
	
	
	
	

	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getEbeschreibung() {
		return ebeschreibung;
	}
	public void setEbeschreibung(String ebeschreibung) {
		this.ebeschreibung = ebeschreibung;
	}

	public Date getStartzeit() {
		return startzeit;
	}
	public void setStartzeit(Date startzeit) {
		this.startzeit = startzeit;
	}
	public Date getEndzeit() {
		return endzeit;
	}
	public void setEndzeit(Date endzeit) {
		this.endzeit = endzeit;
	}
	public Date getStartdatum() {
		return startdatum;
	}
	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}
	public Date getEnddatum() {
		return enddatum;
	}
	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}

}
