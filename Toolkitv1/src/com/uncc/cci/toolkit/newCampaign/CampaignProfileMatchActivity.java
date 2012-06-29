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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.uncc.cci.toolkit.DashboardActivity;
import com.uncc.cci.toolkit.R;

import datastructures.Campaign;
import datastructures.Profile;

public class CampaignProfileMatchActivity extends DashboardActivity{

	Campaign camp;
	CheckBox gender;
	CheckBox ageRange;
	CheckBox ethnicity;
	CheckBox region;
	PopupWindow pw;
	View pview;
	int minAgeEditText;
	int maxAgeEditText;
	Profile profile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_campaign_profile_match);
		Bundle b = this.getIntent().getExtras();
		System.out.println("Entering profile matching");
		if(b!=null) {
			camp = b.getParcelable("campaign");
			camp.debugCampaign();
		}
		
		gender = (CheckBox) findViewById(R.id.setGender);
		ageRange = (CheckBox) findViewById(R.id.setAge);
		ethnicity = (CheckBox) findViewById(R.id.setEthnicity);
		region = (CheckBox) findViewById(R.id.setRegion);
		
		//if (camp.getProfile() == null){
			profile = new Profile();
		//}	else {
			/*if (camp.getProfile().getGender() != null) {
				profile.setGender(camp.getProfile().getGender());
			}
			profile.setMaxAge(camp.getProfile().getMaxAge());
			profile.setMinAge(camp.getProfile().getMinAge());
			if (camp.getProfile().getEthnicity() != null) {
				profile.setEthnicity(camp.getProfile().getEthnicity());
			}
			if (camp.getProfile().getRange() != -1) {
				profile.setRange(camp.getProfile().getRange());
				profile.setMapCenter(camp.getProfile().getMapCenter());				
			}*/
		//}
		
		ImageButton back = (ImageButton) findViewById(R.id.cancel_btn);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(CampaignProfileMatchActivity.this, CampaignInviteParticipantsOptionsActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivity(myIntent);
			}

		});

		ImageButton next = (ImageButton) findViewById(R.id.next_btn);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				camp.setProfile(profile);
				System.out.println("Profile before next activity: "+ camp.getProfile().getSelectedOptions().toString());
				Intent myIntent = new Intent(CampaignProfileMatchActivity.this, CampaignInviteParticipantsOptionsActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				//System.out.println("inside profile matching");
				//camp.debugCampaign();
				myIntent.putExtras(b);
				//System.out.println("profile ");
				//((Campaign)myIntent.getExtras().getParcelable("campaign")).debugCampaign();
				startActivity(myIntent);
			}

		});

		gender.setOnCheckedChangeListener(gender_checked_change);
		ageRange.setOnCheckedChangeListener(ageRange_checked_change);
		ethnicity.setOnCheckedChangeListener(ethnicity_checked_change);
		region.setOnCheckedChangeListener(region_checked_change);
	}

	private CompoundButton.OnCheckedChangeListener gender_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked){
			
				LayoutInflater inflater = (LayoutInflater)CampaignProfileMatchActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				pview = inflater.inflate(R.layout.new_campaign_gender_config,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,400,400,true);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);

				Button submit = (Button)pview.findViewById(R.id.submit_gender_btn);
				submit.setOnClickListener(gender_submit_button_click_listener);
			}else{
				profile.clearGender();
			}

		}; 

		//Inside Gender Popup window, submit button selected
		private OnClickListener gender_submit_button_click_listener = new OnClickListener() {
			public void onClick(View v) {

				RadioButton genderMale = (RadioButton)pview.findViewById(R.id.genderMale);
				if (genderMale.isChecked()) {
					profile.setGender("Male");
				}

				RadioButton genderFemale = (RadioButton)pview.findViewById(R.id.genderFemale);
				if (genderFemale.isChecked()) {
					profile.setGender("Female");
				}
				pw.dismiss();

			}

		};
	};

	private CompoundButton.OnCheckedChangeListener ageRange_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked){

				LayoutInflater inflater = (LayoutInflater)CampaignProfileMatchActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				pview = inflater.inflate(R.layout.new_campaign_agerange_config,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,400,400,true);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);
				
				Button submit = (Button)pview.findViewById(R.id.submit_ageRange_btn);
				submit.setOnClickListener(ageRange_submit_button_click_listener);
			}else{
				profile.clearAgeRange();
			}

		} 

		//Inside Age Range Popup window, submit button selected
		private OnClickListener ageRange_submit_button_click_listener = new OnClickListener() {
			public void onClick(View v) {
				try {
					minAgeEditText = Integer.parseInt(((EditText)pview.findViewById(R.id.minAge)).getText().toString());
					System.out.println(minAgeEditText);
				} catch (NumberFormatException e) {
					minAgeEditText = 0;
				}
				try {
					maxAgeEditText = Integer.parseInt(((EditText)pview.findViewById(R.id.maxAge)).getText().toString());
				} catch (NumberFormatException e) {
					maxAgeEditText = 0;
				}
				
				if (minAgeEditText == 0 & maxAgeEditText == 0) {
					ageRange.setChecked(false);
					pw.dismiss();
				} else if (minAgeEditText == 0 & maxAgeEditText > 0) {
					profile.setMaxAge(maxAgeEditText);
					pw.dismiss();
				} else if (minAgeEditText > 0 & maxAgeEditText == 0) {
					profile.setMinAge(minAgeEditText);
					pw.dismiss();
				} else if (minAgeEditText > 0 & maxAgeEditText > 0 & minAgeEditText < maxAgeEditText) {
					profile.setMinAge(minAgeEditText);
					profile.setMaxAge(maxAgeEditText);
					pw.dismiss();					
				} else {
					Toast.makeText(CampaignProfileMatchActivity.this, "Invalid age range; select new values", Toast.LENGTH_SHORT).show();
				}
			}
		};
	};

	private CompoundButton.OnCheckedChangeListener ethnicity_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked){
				profile.setEthnicity(new HashMap<String, Boolean>());
				LayoutInflater inflater = (LayoutInflater)CampaignProfileMatchActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				pview = inflater.inflate(R.layout.new_campaign_ethnicity_config,(ViewGroup)findViewById(R.id.popup_element));

				pw = new PopupWindow(pview,400,700,true);
				pw.showAtLocation(pview, Gravity.CENTER, 0, 0);

				CheckBox white = (CheckBox)pview.findViewById(R.id.white);
				white.setOnCheckedChangeListener(white_checked_change);
				CheckBox black = (CheckBox)pview.findViewById(R.id.black);
				black.setOnCheckedChangeListener(black_checked_change);
				CheckBox hispanic = (CheckBox)pview.findViewById(R.id.hispanic);
				hispanic.setOnCheckedChangeListener(hispanic_checked_change);
				CheckBox asian = (CheckBox)pview.findViewById(R.id.asian);
				asian.setOnCheckedChangeListener(asian_checked_change);
				CheckBox nativeAmerican = (CheckBox)pview.findViewById(R.id.nativeAmerican);
				nativeAmerican.setOnCheckedChangeListener(nativeAmerican_checked_change);
				CheckBox multiracial = (CheckBox)pview.findViewById(R.id.multiracial);
				multiracial.setOnCheckedChangeListener(multiracial_checked_change);
				
				Button submit = (Button)pview.findViewById(R.id.submit_ethnicity_btn);
				submit.setOnClickListener(ethnicity_submit_button_click_listener);
			}else{
				profile.clearEthnicity();
			}

		} 


		private CompoundButton.OnCheckedChangeListener white_checked_change = new CompoundButton.OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if(isChecked){
					profile.getEthnicity().put("white", true);
				}else{
					profile.getEthnicity().put("white", false);
				}
			}
		};
		
		private CompoundButton.OnCheckedChangeListener black_checked_change = new CompoundButton.OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if(isChecked){
					profile.getEthnicity().put("black", true);
				}else{
					profile.getEthnicity().put("black", false);
				}
			}
		};
		
		private CompoundButton.OnCheckedChangeListener hispanic_checked_change = new CompoundButton.OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if(isChecked){
					profile.getEthnicity().put("hispanic", true);
				}else{
					profile.getEthnicity().put("hispanic", false);
				}
			}
		};
		
		private CompoundButton.OnCheckedChangeListener asian_checked_change = new CompoundButton.OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if(isChecked){
					profile.getEthnicity().put("asian", true);
				}else{
					profile.getEthnicity().put("asian", false);
				}
			}
		};
		
		private CompoundButton.OnCheckedChangeListener nativeAmerican_checked_change = new CompoundButton.OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if(isChecked){
					profile.getEthnicity().put("nativeamerican", true);
				}else{
					profile.getEthnicity().put("nativeamerican", false);
				}
			}
		};
		
		private CompoundButton.OnCheckedChangeListener multiracial_checked_change = new CompoundButton.OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if(isChecked){
					profile.getEthnicity().put("multiracial", true);
				}else{
					profile.getEthnicity().put("multiracial", false);
				}
			}
		};
		
		//Inside Ethnicity Popup window, submit button selected
		private OnClickListener ethnicity_submit_button_click_listener = new OnClickListener() {
			public void onClick(View v) {
				pw.dismiss();

			}
		};
	};

	private CompoundButton.OnCheckedChangeListener region_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked){

				Intent myIntent = new Intent(CampaignProfileMatchActivity.this, ShowMapScreen.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivityForResult(myIntent, 1);
			}else{
				profile.clearRegion();
			}
		} 
	};
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            
            profile.setMapCenter(data.getExtras().get("mapCenter").toString());
            profile.setRange(Integer.parseInt(data.getExtras().get("longSpan").toString()));
            
        }
        else{
            Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show();
        }
    }
	@Override
	public void onBackPressed() {
		return;
	}

}
