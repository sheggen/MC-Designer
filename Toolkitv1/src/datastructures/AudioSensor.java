package datastructures;

import java.io.Serializable;

public class AudioSensor  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -341070001098741503L;
	String captureOption;
	private String captureOptions[] = {"Continuous Capture", "Interval Capture"};

	public AudioSensor() {};

	public AudioSensor(String capopt){
		this.captureOption= capopt;
	}

	public void setCaptureOption(String string){
		this.captureOption=string;
	}
	public String getCaptureOption(){
		return this.captureOption;
	}
	public String[] getCaptureOptions() {
		return captureOptions;
	}
	public void setCaptureOptions(String options[]) {
		this.captureOptions = options;
	}
}
