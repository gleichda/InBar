package inbar.beans;

import java.io.Serializable;

/**
 * 
 * @author sabine
 *
 */
public class BarBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String barname;
	private String vorname;
	private String nachname;
	private String chefmail;
	private String strasse;
	private String hausnummer;
	private String plz;
	private String ort;
	private String barmail;
	private String baruser;
	private int barid;
	private String bbeschreibung;
	private String mbeschreibung;
	private String lbeschreibung;
	private int bildId; //Da das Bild ueber einen Separaten Request aufgerufen wird, muss es nicht in der BarBean gespeichert werden.
	private int musikId;
	
	
	//Getter/Setter Barname
	public String getBarname() {
		return barname;
	}
	public void setBarname(String barname) {
		this.barname = barname;
	}
	//Getter/Setter Vorname des Gesch�ftsf�hrers	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	//Getter/Setter Nachname des Gesch�ftsf�hrers
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	//Getter/Setter E-Mail-Adresse des Gesch�ftsf�hrers
	public String getChefmail() {
		return chefmail;
	}
	public void setChefmail(String chefmail) {
		this.chefmail = chefmail;
	}
	//Getter/Setter Stra�e der Bar
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	//Getter/Setter Hausnummer der Bar
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	//Getter/Setter PLZ der Bar
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	//Getter/Setter Ort der Bar
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	//Getter/Setter E-Mail-Adresse der Bar
	public String getBarmail() {
		return barmail;
	}
	public void setBarmail(String email) {
		this.barmail = email;
	}
	//Getter/Setter UserName der Bar
	public String getBaruser() {
		return baruser;
	}
	public void setBaruser(String baruser) {
		this.baruser = baruser;
	}
	public int getBarid() {
		return barid;
	}
	public void setBarid(int barid) {
		this.barid = barid;
	}
	public String getBbeschreibung() {
		return bbeschreibung;
	}
	public void setBbeschreibung(String bbeschreibung) {
		this.bbeschreibung = bbeschreibung;
	}
	public String getMbeschreibung() {
		return mbeschreibung;
	}
	public void setMbeschreibung(String mbeschreibung) {
		this.mbeschreibung = mbeschreibung;
	}
	public String getLbeschreibung() {
		return lbeschreibung;
	}
	public void setLbeschreibung(String lbeschreibung) {
		this.lbeschreibung = lbeschreibung;
	}
	public int getBildId() {
		return bildId;
	}
	public void setBildId(int bildId) {
		this.bildId = bildId;
	}
	public int getMusikId() {
		return musikId;
	}
	public void setMusikId(int musikId) {
		this.musikId = musikId;
	}	
	
}
