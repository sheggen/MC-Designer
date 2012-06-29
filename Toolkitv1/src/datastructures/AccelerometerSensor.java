package datastructures;

import java.io.Serializable;

public class AccelerometerSensor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6875825523284339971L;
	String captureOption;
	String modeOption;

	public AccelerometerSensor() {};

	private String captureOptions[] = {"Step Counter mode","Raw Data mode"};

	public AccelerometerSensor(String mode, String capture) {
		// TODO Auto-generated constructor stub
		this.captureOption = capture;
		this.modeOption = mode;
	}

	public void setCaptureOption(String string){
		this.captureOption=string;
	}
	public String getCaptureOption(){
		return this.captureOption;
	}
	public String[] getCaptureOptions() {
		return this.captureOptions;
	}
	public void setCaptureOptions(String options[]) {
		this.captureOptions = options;
	}

	//////////////////////////////////////////////////////////



	private String modeOptions[] = {"Step Counter mode","Raw Data mode"};

	public void setModeOption(String string){
		this.modeOption=string;
	}
	public String getModeOption(){
		return this.modeOption;
	}
	public String[] getModeOptions() {
		return this.modeOptions;
	}
	public void setModeOptions(String options[]) {
		this.modeOptions = options;
	}
	
	
}
