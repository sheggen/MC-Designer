package com.uncc.cci.toolkit.newCampaign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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

import datastructures.Campaign;
import datastructures.DirectMessageIncentive;
import datastructures.Incentive;
import datastructures.MonetaryIncentive;
import datastructures.PointIncentive;


public class CampaignAddIncentivesActivity extends DashboardActivity {
	/** Called when the activity is first created. */
	LinearLayout layout;
	Button submitbutton;
	Button cancelbutton;
	PopupWindow pw;
	Campaign camp;
	ImageButton back;
	Boolean state = false;

	DirectMessageIncentive directmessage = null;
	PointIncentive point = null;
	MonetaryIncentive monetary = null;
	int dmtype;
	RadioGroup dmrg;
	Toast toast;

	Spinner pointStart;
	Spinner pointSubmission;
	Spinner pointLevel;
	Spinner pointLeaderboard;
	EditText instructions;
	EditText level;
	EditText startupamount;
	EditText acceptedamount;
	Spinner alertoption;
	EditText moneyinstructions;

	RadioGroup rg;
	RadioButton messages_chk;
	RadioButton point_chk;
	RadioButton monetary_chk;
	RadioButton none_chk;
	Intent i;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_campaign_add_incentives);
		messages_chk = (RadioButton) findViewById(R.id.directCheck);
		point_chk = (RadioButton) findViewById(R.id.pointsCheck);
		monetary_chk = (RadioButton) findViewById(R.id.moneyCheck);
		none_chk = (RadioButton) findViewById(R.id.noneCheck);
		rg = (RadioGroup)findViewById(R.id.incentiveRadioButtons);
		System.out.println("Entering Incentives");

		Bundle b = this.getIntent().getExtras();
		if(b!=null){
			try {
				camp = b.getParcelable("campaign");
				camp.debugCampaign();
			}catch (Exception e) {
				e.printStackTrace();
			} 
		}
		if (camp.getIncentive() == null) {
			camp.setIncentive(new Incentive());
		}else {
			if (camp.getIncentive().getSelectedIncentive() == "directmessage") {
				directmessage = camp.getIncentive().getDirectMessage();
				messages_chk.setChecked(true);
				state = true;
			}
			else if (camp.getIncentive().getSelectedIncentive() == "point") {
				point = camp.getIncentive().getPoint();
				point_chk.setChecked(true);
				state = true;
			}
			else if (camp.getIncentive().getSelectedIncentive() == "monetary") {
				monetary = camp.getIncentive().getMonetary();
				monetary_chk.setChecked(true);
				state = true;
			}
			else
				none_chk.setChecked(true);
				state = true;
		}
		//////////////////Incentive Click Listeners (for unchecking radiobuttons)////////////////////
		messages_chk.setOnClickListener(radioClickListener);
		point_chk.setOnClickListener(radioClickListener);
		monetary_chk.setOnClickListener(radioClickListener);
		none_chk.setOnClickListener(radioClickListener);
		/////////////////////////////////////////////////////////////////////////////////////////


		//////////////////// Radio Buttons //////////////////////
		messages_chk.setOnCheckedChangeListener(direct_message_checked_change);
		point_chk.setOnCheckedChangeListener(point_checked_change);
		monetary_chk.setOnCheckedChangeListener(monetary_checked_change);
		none_chk.setOnCheckedChangeListener(none_checked_change);

		//////////////////////////////////////////////////////////

		back = (ImageButton) findViewById(R.id.cancel_btn);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(CampaignAddIncentivesActivity.this, CampaignInviteParticipantsOptionsActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivity(myIntent);
			}

		});

		ImageButton add_options = (ImageButton) findViewById(R.id.next_btn);
		add_options.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				int id = rg.getCheckedRadioButtonId();
				if (id == -1) {
					Toast.makeText(getBaseContext(), "No incentive selected", Toast.LENGTH_SHORT).show();
				}
				else {
					camp.setIncentive(new Incentive());
					if (id==R.id.directCheck) {
						camp.getIncentive().setDirectMessage(directmessage);
						Intent myIntent = new Intent(CampaignAddIncentivesActivity.this, CampaignAddDirectMessages.class);
						Bundle b = new Bundle();
						b.putParcelable("campaign", (Parcelable) camp);
						myIntent.putExtras(b);

						startActivity(myIntent);
					}
					else if (id==R.id.pointsCheck) {
						camp.getIncentive().setPoint(point);
						Intent myIntent = new Intent(CampaignAddIncentivesActivity.this, ReviewCampaignActivity.class);
						Bundle b = new Bundle();
						b.putParcelable("campaign", (Parcelable) camp);
						myIntent.putExtras(b);

						startActivity(myIntent);
					}
					else if (id==R.id.moneyCheck) {
						camp.getIncentive().setMonetary(monetary); 
						Intent myIntent = new Intent(CampaignAddIncentivesActivity.this, ReviewCampaignActivity.class);
						Bundle b = new Bundle();
						b.putParcelable("campaign", (Parcelable) camp);
						myIntent.putExtras(b);

						startActivity(myIntent);
					}
					else if (id==R.id.noneCheck) {
						camp.setIncentive(null);
						Intent myIntent = new Intent(CampaignAddIncentivesActivity.this, ReviewCampaignActivity.class);
						Bundle b = new Bundle();
						b.putParcelable("campaign", (Parcelable) camp);
						myIntent.putExtras(b);

						startActivity(myIntent);
					}
					//TODO Add Options Activity later
					//Intent myIntent = new Intent(CampaignAddIncentivesActivity.this, CampaignAddOptionsActivity.class);
				}
			}
		});
	}

	///////////////////////Radio Group click listener/////////////////////////
	OnClickListener radioClickListener = new OnClickListener() {
		public void onClick(View v) {
			if (v.getId() == rg.getCheckedRadioButtonId() && state) {
				rg.clearCheck();
				camp.getIncentive().clearDirectMessageIncentive();
				camp.getIncentive().clearMonetaryIncentive();
				camp.getIncentive().clearPointIncentive();
				state = false;
			}else {
				state = true;
			}
		}
	};
	/////////////////////////////////////////////////////////////////////////

	@Override
	public void onBackPressed() {
		return;
	}
	////////////////////First Radio Button (Direct Messages) Methods//////////////////////

	private CompoundButton.OnCheckedChangeListener direct_message_checked_change =  new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			/*Intent iExp = new Intent(this, CampaignAddDirectMessages.class); //TODO  Replace 'ActivityToCall' with the class name of the activity being called

			startActivity(iExp);*/
			//directmessage =  new DirectMessageIncentive("","","",0,"","");
			directmessage = new DirectMessageIncentive(0);
			getApplicationContext();

			if(isChecked){

				LayoutInflater inflater = (LayoutInflater)CampaignAddIncentivesActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View pview = inflater.inflate(R.layout.new_campaign_direct_message_config2,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,400,400,true);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);

				//				message1 = (EditText)pview.findViewById(R.id.messageFirstSubmit);
				//				message2a = (EditText)pview.findViewById(R.id.messageLike);
				//				message2b = (EditText)pview.findViewById(R.id.messageDislike);
				//				message3time = (EditText)pview.findViewById(R.id.numberIdle);
				//				message3timeunit = (Spinner)pview.findViewById(R.id.timeIdle);
				//				message3 = (EditText)pview.findViewById(R.id.messageIdle);
				dmrg = (RadioGroup) pview.findViewById(R.id.dm_type_rg);
				submitbutton = (Button)pview.findViewById(R.id.submit_direct_message_btn);
				submitbutton.setOnClickListener(direct_message_submit_button_click_listener);
				cancelbutton = (Button)pview.findViewById(R.id.cancel_direct_message_btn);
				cancelbutton.setOnClickListener(direct_message_cancel_button_click_listener);
			}else{
			
				try {
					dmrg.clearCheck();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
				directmessage = null;
				rg.clearCheck();
				state = false;
				//messages_chk.setChecked(false);
			}
		} 
		private OnClickListener direct_message_submit_button_click_listener = new OnClickListener() {
			public void onClick(View v) {
				try {
					if (dmrg.getCheckedRadioButtonId() == R.id.dm_regular) {
						directmessage.setType(0); 
						Log.d("myapp", "Set to: "+directmessage.getType());
						pw.dismiss();
					}
					else if (dmrg.getCheckedRadioButtonId() == R.id.dm_goal) {
						directmessage.setType(1);
						Log.d("myapp", "Set to: "+directmessage.getType());
						pw.dismiss();
					}
				} catch (Exception e) {
					//toast.show();
					e.printStackTrace();
				}
				state = true;
			}
		};
		private OnClickListener direct_message_cancel_button_click_listener = new OnClickListener() {
			public void onClick(View v) {
				//messages_chk.setChecked(false);
				rg.clearCheck();
				state = false;
				//dmrg = null;
				pw.dismiss();

			}
		};
	}; 



	////////////////////Second Radio Button (Points) Methods//////////////////////

	private CompoundButton.OnCheckedChangeListener point_checked_change =  new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			getApplicationContext();

			if(isChecked){

				LayoutInflater inflater = (LayoutInflater)CampaignAddIncentivesActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View pview = inflater.inflate(R.layout.new_campaign_points_system_config,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,450,700,true);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);

				pointStart = (Spinner)pview.findViewById(R.id.numbersOfpoints);
				pointSubmission = (Spinner)pview.findViewById(R.id.numbersOfPointsPerSubmission);
				pointLevel = (Spinner)pview.findViewById(R.id.numberOfPointsLevel);
				pointLeaderboard = (Spinner)pview.findViewById(R.id.numberOfPointsLeaderboard);
				instructions = (EditText)pview.findViewById(R.id.instructionsInput);
				level = (EditText)pview.findViewById(R.id.newlevelinput);

				submitbutton = (Button)pview.findViewById(R.id.submit_point_btn);
				submitbutton.setOnClickListener(point_submit_button_click_listener);
				cancelbutton = (Button)pview.findViewById(R.id.cancel_point_btn);
				cancelbutton.setOnClickListener(point_cancel_button_click_listener);
			}else{
				//camp.getIncentive().clearPointIncentive();
				point = null;
				rg.clearCheck();
				state = false;
				//point_chk.setChecked(false);
			}

		} 
		private OnClickListener point_submit_button_click_listener = new OnClickListener() {
			public void onClick(View v) {
				point =  new PointIncentive();
				point.getPointsOption().put("startPoints", pointStart.getSelectedItem().toString());
				point.getPointsOption().put("submissionPoints", pointSubmission.getSelectedItem().toString());
				point.getPointsOption().put("levelPoints", pointLevel.getSelectedItem().toString());
				point.getPointsOption().put("leaderboardPoints", pointLeaderboard.getSelectedItem().toString());
				point.getPointsOption().put("instructions", instructions.getText().toString());
				point.getPointsOption().put("newLevel", level.getText().toString());
				state = true;
				pw.dismiss();
			}
		};

		private OnClickListener point_cancel_button_click_listener = new OnClickListener() {
			public void onClick(View v) {
				rg.clearCheck();
				//point_chk.setChecked(false);
				state = false;
				pw.dismiss();

			}
		};
	}; 



	////////////////////Third Radio Button (Monetary) Methods//////////////////////

	private CompoundButton.OnCheckedChangeListener monetary_checked_change =  new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			
			getApplicationContext();

			if(isChecked){

				LayoutInflater inflater = (LayoutInflater)CampaignAddIncentivesActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View pview = inflater.inflate(R.layout.new_campaign_monetary_incentive_config,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,450,700,true);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);
				
				startupamount = (EditText)pview.findViewById(R.id.startupamounttext);
				acceptedamount = (EditText)pview.findViewById(R.id.gooddatatext);
				alertoption = (Spinner)pview.findViewById(R.id.alertoptionspinner);
				moneyinstructions = (EditText)pview.findViewById(R.id.moneyinstructions);
				
				submitbutton = (Button)pview.findViewById(R.id.submit_monetary_btn);
				submitbutton.setOnClickListener(monetary_submit_button_click_listener);
				cancelbutton = (Button)pview.findViewById(R.id.cancel_monetary_btn);
				cancelbutton.setOnClickListener(monetary_cancel_button_click_listener);
			}else{
				//camp.getIncentive().clearMonetaryIncentive();
				monetary = null;
				rg.clearCheck();
				state = false;
				//monetary_chk.setChecked(false);
			}

		} 
		private OnClickListener monetary_submit_button_click_listener = new OnClickListener() {
			public void onClick(View v) {
				monetary =  new MonetaryIncentive();

				monetary.getMonetaryOptions().put("moneyMax", startupamount.getText().toString());
				monetary.getMonetaryOptions().put("acceptedAmount", acceptedamount.getText().toString());
				monetary.getMonetaryOptions().put("alertOption", alertoption.getSelectedItem().toString());
				monetary.getMonetaryOptions().put("moneyInstructions", moneyinstructions.getText().toString());
				state = true;
				pw.dismiss();
			}
		};
		private OnClickListener monetary_cancel_button_click_listener = new OnClickListener() {
			public void onClick(View v) {
				rg.clearCheck();
				//monetary_chk.setChecked(false);
				state = false;
				pw.dismiss();

			}
		};
	}; 

	////////////////////Fourth Radio Button (None) Methods//////////////////////

	private CompoundButton.OnCheckedChangeListener none_checked_change =  new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			getApplicationContext();

			if(!isChecked){
				rg.clearCheck();
				state = false;
			}	
			

		} 
	};


	}