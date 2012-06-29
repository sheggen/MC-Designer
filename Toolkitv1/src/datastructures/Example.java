package datastructures;

import java.io.Serializable;

public class Example  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3145577656675145369L;
	String Sensor;
	String filename;
	
	public String getSensor() {
		return Sensor;
	}
	public void setSensor(String sensor) {
		Sensor = sensor;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
