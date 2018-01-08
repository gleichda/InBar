package inbar.beans;

import java.io.Serializable;
import java.util.Date;


public class EventBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int eventid;
	private String eventname;
	private String ebeschreibung;

	// Datentyp aus Übung 11 für Datum und Uhrzeit übernommen.
	private Date datum;
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
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
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

}
