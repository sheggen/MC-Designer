package datastructures;

import java.io.Serializable;

public class CamcorderSensor  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1249777628830473994L;

	int recordLength;

	//private String captureOptions[] = {"Record Length"};  //, "Use High Resolution", "Use Low Resolution"

	public CamcorderSensor() {};

	public CamcorderSensor(int recordLength){
		this.recordLength= recordLength;
	}

	/*public void setCaptureOption(String string){
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
	}*/
	public int getRecordLength() {
		return recordLength;
	}
	public void setRecordLength(int recordLength) {
		this.recordLength = recordLength;
	}

}
