package inbar.beans;

import java.io.Serializable;

public class SuchBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String suchbegriff;
	private String[] musikart;
	private String suchart;
	
	
	public String getSuchbegriff() {
		return suchbegriff;
	}
	public void setSuchbegriff(String suchbegriff) {
		this.suchbegriff = suchbegriff;
	}
	public String[] getMusikart() {
		return musikart;
	}
	public void setMusikart(String[] musikart) {
		this.musikart = musikart;
	}
	public String getSuchart() {
		return suchart;
	}
	public void setSuchart(String suchart) {
		this.suchart = suchart;
	}
	
	
}
