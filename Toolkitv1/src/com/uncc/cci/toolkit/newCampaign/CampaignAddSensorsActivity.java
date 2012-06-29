package com.uncc.cci.toolkit.newCampaign;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.uncc.cci.toolkit.DashboardActivity;
import com.uncc.cci.toolkit.R;

import datastructures.AccelerometerSensor;
import datastructures.AudioSensor;
import datastructures.CamcorderSensor;
import datastructures.CameraSensor;
import datastructures.Campaign;
import datastructures.GPSSensor;
import datastructures.Sensor;
import datastructures.TextSensor;

public class CampaignAddSensorsActivity extends DashboardActivity {
	/** Called when the activity is first created. */
	LinearLayout layout ;
	HashMap<String,Boolean> sensors;
	Button submit;
	PopupWindow pw;
	Campaign camp;
	ImageButton back;
	CheckBox resolutionCheck;
	CheckBox autofocusCheck;
	RadioButton highResolutionRadio;
	RadioButton lowResolutionRadio;
	Spinner gps_options;
	Spinner audio_options;
	Spinner accelerometerCaptureOptions;
	Spinner accelerometerModeOptions;
	Spinner text_options;
	EditText recordLength;
	CheckBox gps_chk;
	RadioGroup radioGroup;
	RadioButton camera_chk;
	RadioButton audio_chk;
	RadioButton accelerometer_chk;
	RadioButton camcorder_chk;
	CheckBox text_chk;
	Boolean state = false;

	GPSSensor gpssensor = null;
	CameraSensor camerasensor = null;
	AudioSensor audiosensor = null;
	CamcorderSensor camcordersensor = null;
	AccelerometerSensor accelerometersensor = null;
	TextSensor textsensor = null;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_campaign_add_sensors);

		radioGroup = (RadioGroup)findViewById(R.id.sensorRadioGroup);
		gps_chk = (CheckBox) findViewById(R.id.gpsCheck);
		camera_chk = (RadioButton) findViewById(R.id.cameraRadio);
		audio_chk = (RadioButton) findViewById(R.id.audioRadio);
		accelerometer_chk = (RadioButton) findViewById(R.id.accelerometerRadio);
		camcorder_chk = (RadioButton) findViewById(R.id.camcorderRadio);
		text_chk = (CheckBox) findViewById(R.id.textCheck);

		Bundle b = this.getIntent().getExtras();
		System.out.println("Entering Sensors");
		if(b!=null){
			try {
				camp = b.getParcelable("campaign");
				camp.debugCampaign();
			}catch (Exception e) {
				e.printStackTrace();
			} 
		}

		////////////Set checkboxes/Radios based on Parcelable//////////////////
		if (camp.getSensors() == null){
			camp.setSensors(new Sensor());
			camp.getSensors().setSensors(new HashMap<String, Boolean>());
			System.out.println("Sensor set: "+camp.getSensors().getSensors());  
		}else {
			System.out.println("All sensors about to be reset: "+ camp.getSensors().getSensors());
			if (camp.getSensors().getSensors().get("GPS")){
				gps_chk.setChecked(true);
				gpssensor = new GPSSensor();
				gpssensor.setOption(camp.getSensors().getGps().getOption());
				camp.getSensors().clearGps();
			}
			if (camp.getSensors().getSensors().get("CAMERA")){
				camera_chk.setChecked(true);
				camerasensor = new CameraSensor();
				camerasensor.setAutoFocusOption(camp.getSensors().getCamera().getAutoFocusOption());
				camerasensor.setResolutionOption(camp.getSensors().getCamera().getResolutionOption());
				camp.getSensors().clearCamera();
				state = true;
			}
			if (camp.getSensors().getSensors().get("ACCELEROMETER")){
				accelerometer_chk.setChecked(true);
				accelerometersensor = new AccelerometerSensor();
				accelerometersensor.setCaptureOption(camp.getSensors().getAccelerometer().getCaptureOption());
				accelerometersensor.setModeOption(camp.getSensors().getAccelerometer().getModeOption());
				camp.getSensors().clearAccelerometer();
				state = true;
			}
			if (camp.getSensors().getSensors().get("AUDIO")){
				audio_chk.setChecked(true);
				audiosensor = new AudioSensor();
				audiosensor.setCaptureOption(camp.getSensors().getAudio().getCaptureOption());
				camp.getSensors().clearAudio();
				state = true;
			}
			if (camp.getSensors().getSensors().get("CAMCORDER")){
				camcorder_chk.setChecked(true);
				camcordersensor = new CamcorderSensor();
				camcordersensor.setRecordLength(camp.getSensors().getCamcorder().getRecordLength());
				camp.getSensors().clearCamcorder();
				state = true;
			}
			if (camp.getSensors().getSensors().get("TEXT")){
				textsensor = new TextSensor();
				textsensor = camp.getSensors().getText();
				text_chk.setChecked(true);
			}
			System.out.println("All sensors reset: "+ camp.getSensors().getSensors());
		}

		////////////////////////////////////////////////////////////
		back = (ImageButton) findViewById(R.id.cancel_btn);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(CampaignAddSensorsActivity.this, CampaignInfoActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivity(myIntent);
			}

		});

		ImageButton nextButton = (ImageButton) findViewById(R.id.next_btn);
		nextButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (!gps_chk.isChecked() & !text_chk.isChecked() & !camera_chk.isChecked() & !audio_chk.isChecked() & !accelerometer_chk.isChecked() & !camcorder_chk.isChecked()) {
					Toast.makeText(CampaignAddSensorsActivity.this, "No sensors selected; please select a sensor", Toast.LENGTH_SHORT).show();

				}else {
					if(camp.getSensors()==null){
						camp.setSensors(new Sensor());
					}
					camp.getSensors().setGps(gpssensor);
					camp.getSensors().setCamera(camerasensor);
					camp.getSensors().setAudio(audiosensor);
					camp.getSensors().setCamcorder(camcordersensor);
					camp.getSensors().setAccelerometer(accelerometersensor);
					camp.getSensors().setText(textsensor);
					
					Intent myIntent = new Intent(CampaignAddSensorsActivity.this, CampaignInviteParticipantsOptionsActivity.class);
					Bundle b = new Bundle();
					b.putParcelable("campaign", (Parcelable) camp);
					myIntent.putExtras(b);

					startActivity(myIntent);
				}
			}
		});


		///////////////////////////////Sensor Checkbox Listeners/////////////////////////////////
		gps_chk.setOnCheckedChangeListener(gps_checked_change);
		camera_chk.setOnCheckedChangeListener(camera_checked_change);
		audio_chk.setOnCheckedChangeListener(audio_checked_change);
		accelerometer_chk.setOnCheckedChangeListener(accelerometer_checked_change);
		camcorder_chk.setOnCheckedChangeListener(camcorder_checked_change);
		text_chk.setOnCheckedChangeListener(text_checked_change);
		/////////////////////////////////////////////////////////////////////////////////////////
		
		//////////////////Sensor Click Listeners (for unchecking radiobuttons)////////////////////
		camera_chk.setOnClickListener(radioClickListener);
		accelerometer_chk.setOnClickListener(radioClickListener);
		camcorder_chk.setOnClickListener(radioClickListener);
		audio_chk.setOnClickListener(radioClickListener);
		/////////////////////////////////////////////////////////////////////////////////////////

	}

	///////////////////////Radio Group click listener/////////////////////////
	OnClickListener radioClickListener = new OnClickListener() {
		public void onClick(View v) {
			if (v.getId() == radioGroup.getCheckedRadioButtonId() && state) {
				radioGroup.clearCheck();
				camp.getSensors().resetSensors();
				camp.getSensors().clearCamera();
				camp.getSensors().clearAudio();
				camp.getSensors().clearCamcorder();
				camp.getSensors().clearAccelerometer();
			}else {
				state = true;
			}
		}
	};
	//////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public void onBackPressed() {
		return;
	}

	///////////////////////////////GPS Methods////////////////////////////////////(WORKING)
	private CompoundButton.OnCheckedChangeListener gps_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			//Context mContext = getApplicationContext();
			if(isChecked){
				gpssensor =  new GPSSensor();
				gpssensor.setOption(gpssensor.getOptions()[0]); //hardcoding to first element .. But value hardcoded to 0 in toxml()

			/*	LayoutInflater inflater = (LayoutInflater)CampaignAddSensorsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View pview = inflater.inflate(R.layout.new_campaign_gps_config,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,400,400,true);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);

				gps_options = (Spinner)pview.findViewById(R.id.gpsSpinner);
				ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, gpssensor.getOptions());
				spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_item);
				gps_options.setAdapter(spinnerArrayAdapter);
				gps_options.setSelection(0);

				submit = (Button)pview.findViewById(R.id.submit_gps_btn);
				submit.setOnClickListener(gps_submit_button_click_listener);*/
			}else{
				gpssensor = null;
				System.out.println("GPS unchecked");
			}

		} 
	};

	//Inside GPS Popup window, submit button selected
	/*private OnClickListener gps_submit_button_click_listener = new OnClickListener() {
		public void onClick(View v) {
			gpssensor.setOption(gps_options.getSelectedItem().toString());
			pw.dismiss();

		}

	};*/

	///////////////////////////////Camera Methods////////////////////////////////////
	//Camera radio selected



	private CompoundButton.OnCheckedChangeListener camera_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked){
				camp.getSensors().resetSensors();
				System.out.println("Camera reset sensors: "+ camp.getSensors().getSensors());
				state = false;
				camerasensor =  new CameraSensor();
				camerasensor.setAutoFocusOption(camerasensor.getAutoFocusOptions()[1]);
				camerasensor.setResolutionOption(camerasensor.getResolutionOptions()[0]);
				

				//////////////////////Future Options popup for camera; disabled for now////////////////////
				/*LayoutInflater inflater = (LayoutInflater)CampaignAddSensorsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View pview = inflater.inflate(R.layout.new_campaign_camera_config,(ViewGroup)findViewById(R.id.camera_popup_element));

				pw = new PopupWindow(pview,400,400,true);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);

				resolutionCheck = (CheckBox)pview.findViewById(R.id.resolutionCheck);
				resolutionCheck.setOnCheckedChangeListener(resolution_checked_change);

				autofocusCheck = (CheckBox)pview.findViewById(R.id.autofocusCheck);
				//autofocusCheck.setOnCheckedChangeListener(autofocus_checked_change);

				highResolutionRadio = (RadioButton)pview.findViewById(R.id.highResolutionRadio);
				lowResolutionRadio = (RadioButton)pview.findViewById(R.id.lowResolutionRadio);

				submit = (Button)pview.findViewById(R.id.submit_camera_btn);
				submit.setOnClickListener(camera_submit_button_click_listener);*/

				//camp.getSensors().clearCamera();
				//camerasensor = null;
				////////////////////////////////////////////////////////////////////////

			}else{
				camerasensor = null;
				System.out.println("Camera unchecked");
			}
		} 
	};

	
	//////////////////////Future Options popup for camera; disabled for now////////////////////

	/*private OnClickListener camera_submit_button_click_listener = new OnClickListener() {
		public void onClick(View v) {
			if (autofocusCheck.isChecked()){
				camerasensor.setAutoFocusOption(camerasensor.getAutoFocusOptions()[1]);
			}else{
				camerasensor.setAutoFocusOption(camerasensor.getAutoFocusOptions()[0]);
			}
			if (highResolutionRadio.isChecked()) {
				camerasensor.setResolutionOption(camerasensor.getResolutionOptions()[0]);
				trace(camerasensor.getResolutionOptions()[0]);
			}else{
				camerasensor.setResolutionOption(camerasensor.getResolutionOptions()[1]);
				trace(camerasensor.getResolutionOptions()[1]);
			}
			pw.dismiss();
		}
	};

	private CompoundButton.OnCheckedChangeListener resolution_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if(isChecked){
				LayoutInflater inflater = (LayoutInflater)CampaignAddSensorsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View pview = inflater.inflate(R.layout.new_campaign_camera_config,(ViewGroup)findViewById(R.id.camera_popup_element));

				pw.dismiss();
				resolutionCheck = (CheckBox)pview.findViewById(R.id.resolutionCheck);
				resolutionCheck.setChecked(true);
				//TODO Removed LL below; now change visibility of radiogroup directly
				LinearLayout resolutionPopout = (LinearLayout)pview.findViewById(R.id.resolutionPopout);

				resolutionPopout.setVisibility(View.VISIBLE);

				pw.setHeight(500);
				pw.setContentView(pview);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);

				submit = (Button)pview.findViewById(R.id.submit_camera_btn);
				submit.setOnClickListener(camera_submit_button_click_listener);
			}
			else{
				//TODO why doesn't this code ever run when checkbox is unchecked?
				LayoutInflater inflater = (LayoutInflater)CampaignAddSensorsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View pview = inflater.inflate(R.layout.new_campaign_camera_config,(ViewGroup)findViewById(R.id.camera_popup_element));

				pw.dismiss();
				camera_chk.setChecked(false);
				resolutionCheck = (CheckBox)pview.findViewById(R.id.resolutionCheck);
				resolutionCheck.setChecked(false);

				LinearLayout resolutionPopout = (LinearLayout)pview.findViewById(R.id.resolutionPopout);
				resolutionPopout.setVisibility(View.GONE);

				pw.setHeight(400);
				pw.setContentView(pview);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);

				submit = (Button)pview.findViewById(R.id.submit_camera_btn);
				submit.setOnClickListener(camera_submit_button_click_listener);
			}


		}*/


	///////////////////////////////Audio Methods////////////////////////////////////
	private CompoundButton.OnCheckedChangeListener audio_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			//Context mContext = getApplicationContext();

			if(isChecked){
				camp.getSensors().resetSensors();
				System.out.println("Audio reset sensors: "+ camp.getSensors().getSensors());
				state = false;
				audiosensor =  new AudioSensor();
				audiosensor.setCaptureOption(audiosensor.getCaptureOptions()[0]);
				//camp.getSensors().setAudio(audiosensor);
				/*LayoutInflater inflater = (LayoutInflater)CampaignAddSensorsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View pview = inflater.inflate(R.layout.new_campaign_audio_config,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,400,400,true);

				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);

				audio_options = (Spinner)pview.findViewById(R.id.audio_spinner);

				ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, audiosensor.getCaptureOptions());
				spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_item);
				audio_options.setAdapter(spinnerArrayAdapter);
				audio_options.setSelection(0);

				submit = (Button)pview.findViewById(R.id.submit_audio_btn);
				submit.setOnClickListener(audio_submit_button_click_listener);*/

				/*}else{
				camp.getSensors().clearAudio();
				audiosensor = null;
				 */
			}else{
				audiosensor = null;
				System.out.println("Audio unchecked");
			}

		} 
	};
	/*
	private OnClickListener audio_submit_button_click_listener = new OnClickListener() {
		public void onClick(View v) {

			audiosensor.setCaptureOption(audio_options.getSelectedItem().toString());
			pw.dismiss();
		}
	};*/




	///////////////////////////////Accelerometer Methods////////////////////////////////////
	private CompoundButton.OnCheckedChangeListener accelerometer_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			Context mContext = getApplicationContext();

			if(isChecked){
				camp.getSensors().resetSensors();
				System.out.println("Accelerometer reset sensors: "+ camp.getSensors().getSensors());
				state = false;
				accelerometersensor =  new AccelerometerSensor();

				LayoutInflater inflater = (LayoutInflater)CampaignAddSensorsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View pview = inflater.inflate(R.layout.new_campaign_accelerometer_config,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,400,450,true);

				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);
				submit = (Button)pview.findViewById(R.id.submit_accelerometer_btn);

				//accelerometerModeOptions = (Spinner)pview.findViewById(R.id.modeSpinner);
				accelerometerCaptureOptions = (Spinner)pview.findViewById(R.id.captureSpinner);

				//ArrayAdapter<String> modeSpinnerArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, accelerometersensor.getModeOptions());
				ArrayAdapter<String> captureSpinnerArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, accelerometersensor.getCaptureOptions());

				//modeSpinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_item);
				//accelerometerModeOptions.setAdapter(modeSpinnerArrayAdapter);

				captureSpinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_item);
				accelerometerCaptureOptions.setAdapter(captureSpinnerArrayAdapter);

				//accelerometerModeOptions.setSelection(0);
				accelerometerCaptureOptions.setSelection(0);

				submit.setOnClickListener(accelerometer_submit_button_click_listener);
			}else{
				accelerometersensor = null;
				System.out.println("Accelerometer unchecked");
			}

		} 
	};

	private OnClickListener accelerometer_submit_button_click_listener = new OnClickListener() {
		public void onClick(View v) {
			accelerometersensor.setCaptureOption(accelerometerCaptureOptions.getSelectedItem().toString());
			//accelerometersensor.setModeOption(accelerometerModeOptions.getSelectedItem().toString());

			pw.dismiss();
		}
	};



	///////////////////////////////Camcorder Methods////////////////////////////////////
	private CompoundButton.OnCheckedChangeListener camcorder_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			//Context mContext = getApplicationContext();

			if(isChecked){
				Toast.makeText(getApplicationContext(),"Coming soon", Toast.LENGTH_SHORT).show();
				camcorder_chk.setChecked(false);
				camcordersensor = null;
				System.out.println("Camcorder unchecked");
			/*	camp.getSensors().resetSensors();
				System.out.println("Camera reset sensors: "+ camp.getSensors().getSensors());
				state = false;
				camcordersensor =  new CamcorderSensor();

				LayoutInflater inflater = (LayoutInflater)CampaignAddSensorsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View pview = inflater.inflate(R.layout.new_campaign_camcorder_config,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,400,400,true);

				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);
				submit = (Button)pview.findViewById(R.id.submit_camcorder_btn);

				recordLength = (EditText)pview.findViewById(R.id.recordLengthValue);

				submit.setOnClickListener(camcorder_submit_button_click_listener);*/
			}else{
				camcordersensor = null;
				System.out.println("Camcorder unchecked");
			}

		} 
	};

	/*private OnClickListener camcorder_submit_button_click_listener = new OnClickListener() {
		public void onClick(View v) {

			camcordersensor.setRecordLength(Integer.parseInt(recordLength.getText().toString()));

			pw.dismiss();
		}
	};*/





	///////////////////////////////Text Methods////////////////////////////////////
	private CompoundButton.OnCheckedChangeListener text_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			//Context mContext = getApplicationContext();

			if(isChecked){

				if(camp.getSensors()==null){
					camp.setSensors(new Sensor());
				}
				camp.getSensors().setGps(gpssensor);
				camp.getSensors().setCamera(camerasensor);
				camp.getSensors().setAudio(audiosensor);
				camp.getSensors().setCamcorder(camcordersensor);
				camp.getSensors().setAccelerometer(accelerometersensor);

				Intent myIntent = new Intent(CampaignAddSensorsActivity.this, CampaignAddTextQuestionsActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivityForResult(myIntent, 0);
			}else{
				//camp.getSensors().clearText();
				camp.getSensors().clearText();
				try {
					System.out.println("Text unchecked: " + camp.getSensors().getText().getListOfQuestions().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Text sensor had a null");
				}
			}

		} 
	};


}