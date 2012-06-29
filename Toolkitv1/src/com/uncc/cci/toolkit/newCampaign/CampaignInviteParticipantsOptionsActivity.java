package com.uncc.cci.toolkit.newCampaign;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import com.uncc.cci.toolkit.DashboardActivity;
import com.uncc.cci.toolkit.R;

import datastructures.Campaign;
import datastructures.Candidate;
import datastructures.Profile;

public class CampaignInviteParticipantsOptionsActivity extends DashboardActivity {
	/** Called when the activity is first created. */
	Campaign camp;
	CheckBox profile_match_check;
	Boolean userCheckedProfileMatch = false;
	CheckBox inviteByEmail;
	CheckBox openToPublic;
	CheckBox inviteByFacebook;
	Candidate candidate;
	Profile profile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_campaign_invite_participants_options);
		Bundle b = this.getIntent().getExtras();
		System.out.println("Entering participants");

		if(b!=null) {
			camp = b.getParcelable("campaign");
			camp.debugCampaign();
		}


		inviteByEmail = (CheckBox) findViewById(R.id.invite_email);
		profile_match_check = (CheckBox) findViewById(R.id.profile_match);
		openToPublic = (CheckBox)findViewById(R.id.publicCheck);
		inviteByFacebook = (CheckBox) findViewById(R.id.facebookCheck);


		if (camp.getCandidate()==null) {
			System.out.println("Get Candidates was null");
			//candidate = new Candidate();
			//camp.setCandidate(new Candidate());
		}else {
			System.out.println("Get Candidates was not null");
			if (camp.getCandidate().getParticipants().size() > 0){
				System.out.println("Email checked in participants: " + camp.getCandidate().getParticipants().size());
				inviteByEmail.setChecked(true);
				Toast.makeText(getBaseContext(), "You have invited "+camp.getCandidate().getParticipants().size()+ " contact(s)", Toast.LENGTH_LONG).show();
			}
		}

		if (camp.getProfile()== null) {
			//profile = new Profile();
			System.out.println("Profile not set");
		}else if (camp.getProfile().getSelectedOptions().containsValue(true)){
			//final Profile profile = camp.getProfile();
			System.out.println("Profile checked in participants: "	+ camp.getProfile().getSelectedOptions());
			profile_match_check.setChecked(true);
		} else {
			System.out.println("Profile checked but empty: "	+ camp.getProfile().getSelectedOptions());
		}


		if (camp.getOpenToPublic()) {
			openToPublic.setChecked(true);
		}



		openToPublic.setOnCheckedChangeListener(openToPublic_checked_change);
		inviteByFacebook.setOnCheckedChangeListener(inviteByFacebook_checked_change);
		inviteByEmail.setOnCheckedChangeListener(inviteByEmail_checked_change);
		profile_match_check.setOnCheckedChangeListener(profileMatch_checked_change);


		ImageButton back = (ImageButton) findViewById(R.id.cancel_btn);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), CampaignAddSensorsActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivityForResult(myIntent, 0);
			}

		});

		ImageButton next = (ImageButton) findViewById(R.id.next_btn);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (openToPublic.isChecked()) {
					camp.setOpenToPublic(true);
				}
				if (candidate != null) {
					camp.setCandidate(candidate);
				}
				if  (userCheckedProfileMatch) {
					Intent myIntent = new Intent(view.getContext(), CampaignProfileMatchActivity.class);
					Bundle b = new Bundle();
					b.putParcelable("campaign", (Parcelable) camp);
					myIntent.putExtras(b);
					startActivityForResult(myIntent, 0);
				}
				else {

					Intent myIntent = new Intent(view.getContext(), CampaignAddIncentivesActivity.class);
					Bundle b = new Bundle();
					b.putParcelable("campaign", (Parcelable) camp);
					myIntent.putExtras(b);
					startActivityForResult(myIntent, 0);
				}
			}

		});
	}

	@Override
	public void onBackPressed() {
		return;
	}


	private CompoundButton.OnCheckedChangeListener openToPublic_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked){
				Toast.makeText(CampaignInviteParticipantsOptionsActivity.this, "Anyone can now join your campaign", Toast.LENGTH_SHORT).show();

			}else {
				camp.setOpenToPublic(false);
			}

		} 
	};


	private CompoundButton.OnCheckedChangeListener inviteByEmail_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked){
				//TODO add datastructure dump of check status
				if (openToPublic.isChecked()) {
					camp.setOpenToPublic(true);
				}

				Intent myIntent = new Intent(CampaignInviteParticipantsOptionsActivity.this, CampaignAddByEmailActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivity(myIntent);
			}else{
				camp.clearCandidate();
			}

		} 
	};

	private CompoundButton.OnCheckedChangeListener inviteByFacebook_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked){
				//ADD IN THE FUTURE
				Toast.makeText(CampaignInviteParticipantsOptionsActivity.this, "Coming soon!",Toast.LENGTH_SHORT).show();
				inviteByFacebook.setChecked(false);
			}

		} 
	};



	private CompoundButton.OnCheckedChangeListener profileMatch_checked_change = new CompoundButton.OnCheckedChangeListener(){
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			if(isChecked){
				userCheckedProfileMatch = true;
				//TODO add datastructure dump of check status
				if (openToPublic.isChecked()) {
					camp.setOpenToPublic(true);
				}
				Intent myIntent = new Intent(CampaignInviteParticipantsOptionsActivity.this, CampaignProfileMatchActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivity(myIntent);
			}else{
				camp.clearProfile();
			}

		} 
	};

}