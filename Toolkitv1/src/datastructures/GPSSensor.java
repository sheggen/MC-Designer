package datastructures;

import java.io.Serializable;

public class GPSSensor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1976863525858526093L;
	String captureoption="";
	private String captureOptions[] = {"Continuous Capture", "One-Time Capture"}; //"Interval Capture",

	public GPSSensor() {};

	public GPSSensor(String capopt){
		this.captureoption= capopt;
	}
	public void setOption(String string){
		this.captureoption=string;
	}
	public String getOption(){
		return this.captureoption;
	}
	public String[] getOptions() {
		return captureOptions;
	}
	public void setOptions(String options[]) {
		this.captureOptions = options;
	}
}
