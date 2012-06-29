package com.uncc.cci.toolkit.newCampaign;

import com.uncc.cci.toolkit.DashboardActivity;
import com.uncc.cci.toolkit.HomeActivity;
import com.uncc.cci.toolkit.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import datastructures.Campaign;

public class CampaignInfoActivity extends DashboardActivity {
	Campaign camp;
	EditText txtTitle;
	EditText txtPurpose;
	EditText txtInstructions;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_campaign_info);
		txtTitle = (EditText) findViewById(R.id.editTextTitle);
		txtPurpose = (EditText) findViewById(R.id.editTextPurpose);
		txtInstructions = (EditText) findViewById(R.id.editTextBio);
		Bundle b = this.getIntent().getExtras();
		System.out.println("Entering Basic Info");

		if(b!=null){
			try {
				camp = b.getParcelable("campaign");
				camp.debugCampaign();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtTitle.setText(camp.getTitle());
			txtPurpose.setText(camp.getPurpose());
			txtInstructions.setText(camp.getInstructions());
		}	else {
			camp = new Campaign();
			txtTitle.setText("Great Northern Flamingo Hunt");
			txtPurpose.setText("Find the Great Northern Flamingo in heat");
			txtInstructions.setText("Capture the Great Northern Flamingos mating call ONLY while they are in heat");
		}

		ImageButton back = (ImageButton) findViewById(R.id.cancel_btn);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(CampaignInfoActivity.this, HomeActivity.class);
				myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(myIntent);
			}

		});
		ImageButton next = (ImageButton) findViewById(R.id.next_btn);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (txtTitle.getText().toString().equals("")) {
					Toast.makeText(getBaseContext(), "Please give your campaign a title", Toast.LENGTH_SHORT).show();
				}else if (txtPurpose.getText().toString().equals("")) {
					Toast.makeText(getBaseContext(), "Please explain the purpose of your campaign", Toast.LENGTH_SHORT).show();
				}else if (txtInstructions.getText().toString().equals("")) {
					Toast.makeText(getBaseContext(), "Please give your users instructions", Toast.LENGTH_SHORT).show();
				} else {
					camp.setTitle(txtTitle.getText().toString());
					camp.setPurpose(txtPurpose.getText().toString());
					camp.setInstructions(txtInstructions.getText().toString()	);

					Intent myIntent = new Intent(CampaignInfoActivity.this, CampaignAddSensorsActivity.class);
					Bundle b = new Bundle();
					b.putParcelable("campaign", camp); 
					myIntent.putExtras(b);
					startActivity(myIntent);
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		return;
	}

}