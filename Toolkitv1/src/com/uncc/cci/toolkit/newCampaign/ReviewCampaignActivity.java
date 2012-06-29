package com.uncc.cci.toolkit.newCampaign;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.uncc.cci.toolkit.DashboardActivity;
import com.uncc.cci.toolkit.HTTPConnector;
import com.uncc.cci.toolkit.HomeActivity;
import com.uncc.cci.toolkit.R;

import datastructures.Campaign;

public class ReviewCampaignActivity extends DashboardActivity {
	Campaign camp;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_campaign_review);

		Bundle b = this.getIntent().getExtras();
		System.out.println("Entering Review");
		if(b!=null){
			camp = b.getParcelable("campaign");
			camp.debugCampaign();            
		}

		//TODO Populate textviews with data from camp
		if (camp.getTitle() != "") {
			((TextView)findViewById(R.id.textTitleInput)).setText(camp.getTitle());	
		}
		if (camp.getPurpose() != "") {
			((TextView)findViewById(R.id.textPurposeInput)).setText(camp.getPurpose());	
		}
		if (camp.getInstructions() != "") {
			((TextView)findViewById(R.id.textInstructionsInput)).setText(camp.getInstructions());	
		}


		if (camp.getSensors() != null) {
			StringBuilder sensorstring = new StringBuilder();
			if (camp.getSensors().getSensors().get("ACCELEROMETER"))
				sensorstring.append("Accelerometer, ");
			if (camp.getSensors().getSensors().get("CAMCORDER"))
				sensorstring.append("Camcorder, ");
			if (camp.getSensors().getSensors().get("CAMERA"))
				sensorstring.append("Camera, ");
			if (camp.getSensors().getSensors().get("AUDIO"))
				sensorstring.append("Audio, ");
			if (camp.getSensors().getSensors().get("TEXT"))
				sensorstring.append("Text, ");
			if (camp.getSensors().getSensors().get("GPS"))
				sensorstring.append("GPS, ");
			/*HashMap<String, Boolean> sensors = (HashMap<String, Boolean>)camp.getSensors().getSensors();
			Iterator<?> it = sensors.entrySet().iterator();
			 */
			/*while(it.hasNext()) {
				Map.Entry<?,?> pairs = (Map.Entry<?,?>)it.next();
				if (pairs.getValue().equals(true)) {

					sensorstring.append(pairs.getKey()+", ");

				}
				it.remove();
			}*/

			if (sensorstring.length() > 0) 
				sensorstring.delete((sensorstring.length()-2), (sensorstring.length()));
			sensorstring.toString().toLowerCase();
			((TextView)findViewById(R.id.textSensorsInput)).setText("Your campaign uses these sensors: "+sensorstring);
		}

		//TODO Get number of selected participants invited by email
		System.out.println("Profile null: "+camp.getProfile());
		if (camp.getProfile() != null) {
			System.out.println("Profile looks like: " + camp.getProfile().getSelectedOptions());
			StringBuilder profilestring = new StringBuilder();
			if (camp.getProfile().getSelectedOptions().get("gender"))
				profilestring.append("Gender, ");		
			if (camp.getProfile().getSelectedOptions().get("ageRange")) 
				profilestring.append("Age Range, ");
			if (camp.getProfile().getSelectedOptions().get("ethnicity"))
				profilestring.append("Ethnicity, ");
			if (camp.getProfile().getSelectedOptions().get("region"))
				profilestring.append("Region, ");



			/*HashMap<String, Boolean> profileOptions = (HashMap<String, Boolean>)camp.getProfile().getSelectedOptions();
			Iterator<?> it = profileOptions.entrySet().iterator();
			StringBuilder profilestring = new StringBuilder();
			while(it.hasNext()) {
				Map.Entry<?,?> pairs = (Map.Entry<?,?>)it.next();
				if (pairs.getValue().equals(true)) {

					profilestring.append(pairs.getKey()+", ");

				}
				it.remove();
			}*/
			if (profilestring.length() > 0) 
				profilestring.delete((profilestring.length()-2), (profilestring.length()));
			profilestring.toString().toLowerCase();
			((TextView)findViewById(R.id.textParticipantsInput)).setText("Your campaign recruits participants by: "+ profilestring);	
		}


		ImageButton back = (ImageButton) findViewById(R.id.cancel_btn);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				/*if (camp.getIncentive().getIncentive().get("directmessage")) {
					Intent myIntent = new Intent(ReviewCampaignActivity.this, CampaignAddDirectMessages.class);
					Bundle b = new Bundle();
					b.putParcelable("campaign", (Parcelable) camp);
					myIntent.putExtras(b);
					startActivity(myIntent);
				}
				else {*/
					Intent myIntent = new Intent(ReviewCampaignActivity.this, CampaignAddIncentivesActivity.class);
					Bundle b = new Bundle();
					b.putParcelable("campaign", (Parcelable) camp);
					myIntent.putExtras(b);
					startActivity(myIntent);
				//}
			}
		});


		//TODO change to "Submit Campaign" Button
		ImageButton next = (ImageButton) findViewById(R.id.next_btn);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//TODO Send all information to XML file, then upload

				new Thread(new Runnable() {
					public void run() {

						HTTPConnector connector = new HTTPConnector();
						connector.submitCampaign(camp.createFile());
					}
				}).start();

				buildAlert();
				
			}

			private void buildAlert() {
				AlertDialog.Builder builder = new AlertDialog.Builder(ReviewCampaignActivity.this);
				builder.setCancelable(false);
				builder.setTitle("Campaign Uploaded!");
				builder.setIcon(R.drawable.joincampaign);
				builder.setMessage("You will receive an email shortly describing how to download and install your campaign");
				builder.setInverseBackgroundForced(true);
				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int which) {
				    dialog.dismiss();
				    
				    Intent myIntent = new Intent(ReviewCampaignActivity.this, HomeActivity.class);
					myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

					/*Bundle b = new Bundle();
					b.putParcelable("campaign", camp);
					myIntent.putExtras(b);*/
					startActivity(myIntent);
				  }
				});
				AlertDialog alert = builder.create();
				alert.show();
				
			}

		});
		System.out.println("End of onCreate");
		camp.debugCampaign();

	}

	@Override
	public void onBackPressed() {
		return;
	}

	public void onClickReview (View v)
	{
		int id = v.getId ();
		switch (id) {
		case R.id.editDescriptionButton :
			Intent myIntent1 = new Intent(ReviewCampaignActivity.this, CampaignInfoActivity.class);
			Bundle b1 = new Bundle();
			b1.putParcelable("campaign", (Parcelable) camp);
			myIntent1.putExtras(b1);
			startActivity(myIntent1);
			break;
		case R.id.editSensorButton :
			Intent myIntent2 = new Intent(getApplicationContext(), CampaignAddSensorsActivity.class);
			Bundle b2 = new Bundle();
			b2.putParcelable("campaign", (Parcelable) camp);
			myIntent2.putExtras(b2);
			startActivity(myIntent2);
			break;
		case R.id.editParticipantButton :
			Intent myIntent3 = new Intent(getApplicationContext(), CampaignInviteParticipantsOptionsActivity.class);
			Bundle b3 = new Bundle();
			b3.putParcelable("campaign", (Parcelable) camp);
			myIntent3.putExtras(b3);
			startActivity(myIntent3);
			break;
		case R.id.editIncentivesButton :
			Intent myIntent4 = new Intent(getApplicationContext(), CampaignAddIncentivesActivity.class);
			Bundle b4 = new Bundle();
			b4.putParcelable("campaign", (Parcelable) camp);
			myIntent4.putExtras(b4);
			startActivity(myIntent4);
			break;
			/*case R.id.editOptionsButton :
			Intent myIntent5 = new Intent(getApplicationContext(), CampaignAddOptionsActivity.class);
			Bundle b5 = new Bundle();
			b5.putParcelable("campaign", (Parcelable) camp);
			myIntent5.putExtras(b5);
			startActivity(myIntent5);
			break;*/
		default: 
			break;
		}
	}
}