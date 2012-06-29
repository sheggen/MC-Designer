package datastructures;

import java.io.Serializable;
import java.util.HashMap;



public class Sensor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3388458237725726712L;
	private HashMap<String,Boolean> sensors = null;
	private GPSSensor gps;
	private CameraSensor camera;
	private AudioSensor audio;
	private AccelerometerSensor accelerometer;
	private CamcorderSensor camcorder;
	private TextSensor text;
	
	private void init_sensorhashmap(){
		this.sensors.put("GPS",false);
		this.sensors.put("CAMERA",false);
		this.sensors.put("CAMCORDER",false);
		this.sensors.put("ACCELEROMETER",false);
		this.sensors.put("TEXT",false);
		this.sensors.put("AUDIO",false);
	}
	public void setSensors(HashMap<String, Boolean> sensors) {
		this.sensors = sensors;
		init_sensorhashmap();
	}
	public GPSSensor getGps() {
		return gps;
	}
	
	public void Sensors() {
		init_sensorhashmap();
	}
	
	public void setGps(GPSSensor gps) {
		if (this.sensors== null){
			this.setSensors(new HashMap<String,Boolean>());
		}
		if (gps!=null) 
			sensors.put("GPS", true);
		this.gps = gps;
	}
	public void clearGps() {
		/*if (this.sensors== null){
		//	this.setSensors(new HashMap<String,Boolean>());
			return;
		}*/
		sensors.put("GPS", false);
		this.gps = null;
	}
	public CameraSensor getCamera() {
		return camera;
	}
	public void setCamera(CameraSensor camera) {
		if (this.sensors== null){
			this.setSensors(new HashMap<String,Boolean>());
		}
		if (camera!=null) 
		sensors.put("CAMERA", true);
		this.camera = camera;
	}
	public void clearCamera() {
		if (this.sensors== null){
			//this.setSensors(new HashMap<String,Boolean>());
			return;
		}
		sensors.put("CAMERA", false);
		this.camera = null;
	}
	public AudioSensor getAudio() {
		return audio;
	}
	public void setAudio(AudioSensor audio) {
		if (this.sensors== null){
			this.setSensors(new HashMap<String,Boolean>());
		}
		if (audio!=null) 
		sensors.put("AUDIO", true);
		this.audio = audio;
	}
	public void clearAudio() {
		if (this.sensors== null){
			//this.setSensors(new HashMap<String,Boolean>());
			return;
		}
		sensors.put("AUDIO", false);
		this.audio = null;
	}
	public AccelerometerSensor getAccelerometer() {
		return accelerometer;
	}
	public void setAccelerometer(AccelerometerSensor accelerometer) {
		if (this.sensors== null){
			this.setSensors(new HashMap<String,Boolean>());
		}
		if (accelerometer!=null) 
		sensors.put("ACCELEROMETER", true);
		this.accelerometer = accelerometer;
	}
	public void clearAccelerometer() {
		if (this.sensors== null){
			//this.setSensors(new HashMap<String,Boolean>());
			return;
		}
		sensors.put("ACCELEROMETER", false);
		this.accelerometer = null;
	}
	public CamcorderSensor getCamcorder() {
		return camcorder;
	}
	public void setCamcorder(CamcorderSensor camcorder) {
		if (this.sensors== null){
			this.setSensors(new HashMap<String,Boolean>());
		}
		if (camcorder!=null) 
		sensors.put("CAMCORDER", true);
		
		this.camcorder = camcorder;
	}
	public void clearCamcorder() {
		if (this.sensors== null){
		//	this.setSensors(new HashMap<String,Boolean>());
			return;
		}
		sensors.put("CAMCORDER", false);
		
		this.camcorder = null;
	}
	
	public TextSensor getText() {
		return text;
	}
	public void setText(TextSensor text) {
		if (this.sensors== null){
			this.setSensors(new HashMap<String,Boolean>());
		}
		if (text!=null) 
		sensors.put("TEXT", true);
		this.text = text;
	}
	public void setText() {
		if (this.sensors== null){
			//this.setSensors(new HashMap<String,Boolean>());
			return;
		}
		sensors.put("TEXT", false);
		this.text = null;
	}
	
	public void clearText() {
		if (this.sensors== null){
		//	this.setSensors(new HashMap<String,Boolean>());
			return;
		}
		sensors.put("TEXT", false);
		
		this.text = null;
	}
	
	public HashMap<String, Boolean> getSensors() {
		// TODO Auto-generated method stub
		return this.sensors;
	}
	
	public void resetSensors() {
		this.sensors.put("CAMERA",false);
		this.sensors.put("CAMCORDER",false);
		this.sensors.put("ACCELEROMETER",false);
		this.sensors.put("AUDIO",false);
		this.clearCamera();
		this.clearCamcorder();
		this.clearAccelerometer();
		this.clearAudio();
	}
	
	
	// save
	
		// add sensor option
	
	/*public Sensor(){
		this.sensors = new HashMap<String,Boolean>();
		initialize();
	}

	public void set_sensor(String name){
		this.sensors.put(name, true);
		initialize_sensor(name);
	}

	private void initialize_sensor(String name) {
		// TODO Auto-generated method stub
		if(name.equalsIgnoreCase("GPS")){
			this.gps = new GPS();
		}
		if(name.equalsIgnoreCase("Camera")){
			this.camera = new Camera();
		}
		if(name.equalsIgnoreCase("Audio")){
			this.audio = new Audio();
		}
		if(name.equalsIgnoreCase("Accelerometer")){
			this.accelerometer = new Accelerometer();
		}
		if(name.equalsIgnoreCase("Camcorder")){
			this.camcorder = new Camcorder();
		}
		if(name.equalsIgnoreCase("Text")){
			this.text = new Text();
		}
		
	}

	public void clear_sensor(String name){
		this.sensors.put(name, false);
		//remove_sensor(name);
		if(name.equalsIgnoreCase("GPS")){
			this.gps = null;
		}
		if(name.equalsIgnoreCase("Camera")){
			this.camera = null;
		}
		if(name.equalsIgnoreCase("Audio")){
			this.audio = null;
		}
		if(name.equalsIgnoreCase("Accelerometer")){
			this.accelerometer = null;
		}
		if(name.equalsIgnoreCase("Camcorder")){
			this.camcorder = null;
		}
		if(name.equalsIgnoreCase("Text")){
			this.text = null;
		}
	}

	private void initialize() {
		//this.sensors.put("GPS", false);
		clear_sensor("GPS");
		clear_sensor("Camera");
		clear_sensor("Audio");
		clear_sensor("Accelerometer");
		clear_sensor("Camcorder");
		clear_sensor("Text");
	}
*/	
	
	
}
