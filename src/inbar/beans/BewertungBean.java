package inbar.beans;

import java.io.Serializable;

public class BewertungBean implements Serializable {
	/**
	 * @author david
	 * @date 04.01.2018
	 */
	private static final long serialVersionUID = 1L;
	private int bewertungsid;
	private int barid;
	private int userid;
	private int bewertung;
	private String kommentar;
	private UserBean user;
	
	
	
	public int getBewertungsid() {
		return bewertungsid;
	}
	public void setBewertungsid(int bewertungsid) {
		this.bewertungsid = bewertungsid;
	}
	public int getBarid() {
		return barid;
	}
	public void setBarid(int barid) {
		this.barid = barid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getBewertung() {
		return bewertung;
	}
	public void setBewertung(int bewertung) {
		this.bewertung = bewertung;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
	

}
