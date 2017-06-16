package inbar.beans;

import java.io.Serializable;

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
	private String passwort;
	private int barid;
	private String bbeschreibung;
	private String mbeschreibung;
	private String lbeschreibung;
	//private String bildname;
	//private int bildid;
	//private byte[] bild;
	
	
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
	//Getter/Setter Passwort f�r den User der Bar
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
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
/*	public String getBildname() {
		return bildname;
	}
	public void setBildname(String bildname) {
		this.bildname = bildname;
	}
	public byte[] getBild() {
		return bild;
	}
	public void setBild(byte[] bild) {
		this.bild = bild;
	}
	public int getBildid() {
		return bildid;
	}
	public void setBildid(int bildid) {
		this.bildid = bildid;
	}*/



	
	
}
