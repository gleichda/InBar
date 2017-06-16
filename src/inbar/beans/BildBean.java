package inbar.beans;

import java.io.Serializable;

public class BildBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String bildname;
	private int bildid;
	private byte[] bild;
	public String getBildname() {
		return bildname;
	}
	public void setBildname(String bildname) {
		this.bildname = bildname;
	}
	public int getBildid() {
		return bildid;
	}
	public void setBildid(int bildid) {
		this.bildid = bildid;
	}
	public byte[] getBild() {
		return bild;
	}
	public void setBild(byte[] bild) {
		this.bild = bild;
	}
	
	
}