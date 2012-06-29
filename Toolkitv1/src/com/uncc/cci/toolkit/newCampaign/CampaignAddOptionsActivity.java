package com.uncc.cci.toolkit.newCampaign;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.uncc.cci.toolkit.DashboardActivity;
import com.uncc.cci.toolkit.R;

import datastructures.Campaign;

public class CampaignAddOptionsActivity extends DashboardActivity {
    /** Called when the activity is first created. */
	LinearLayout layout ;
	//HashMap<String,Boolean> sensors = new HashMap<String,Boolean>();
	Button submit;
	PopupWindow pw;
	Campaign camp;
	Spinner permission;
	ImageButton back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_campaign_options);
        Bundle b = this.getIntent().getExtras();
        System.out.println("Entering Options");
		if(b!=null){
			try {
				camp = b.getParcelable("campaign");
				camp.debugCampaign();
			}catch (Exception e) {
				e.printStackTrace();
			} 
		}
        //UNCOMMENT AFTER OPTIONS CLASS HAS BEEN CREATED IN "DATASTRUCTURES"
        //camp.getOptions = new Options();
        
        back = (ImageButton) findViewById(R.id.cancel_btn);
        back.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(CampaignAddOptionsActivity.this, CampaignAddIncentivesActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("campaign", (Parcelable) camp);
                myIntent.putExtras(b);
                startActivity(myIntent);
            }

        });
        ImageButton add_options = (ImageButton) findViewById(R.id.next_btn);
        add_options.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(CampaignAddOptionsActivity.this, ReviewCampaignActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("campaign", (Parcelable) camp);
                myIntent.putExtras(b);
                
                startActivity(myIntent);
            }

        });

        //camp = new Campaign("test title", "test purpose","test bio");
        CheckBox permissions_chk = (CheckBox) findViewById(R.id.permissionCheck);
      
        
        permissions_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        	public void onCheckedChanged(CompoundButton buttonView,
        	boolean isChecked) {
        	// TODO Auto-generated method stub
        		//sensors.put("GPS", true);


        		Context mContext = getApplicationContext();

        		if(isChecked){
        			
        	        //UNCOMMENT AFTER OPTIONS CLASS HAS BEEN CREATED IN "DATASTRUCTURES"
        			//camp.options.set_option("permission");
        			
        		LayoutInflater inflater = (LayoutInflater)CampaignAddOptionsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        		
        		View pview = inflater.inflate(R.layout.new_campaign_permission_config,(ViewGroup)findViewById(R.id.popup_element));
        			
        		pw = new PopupWindow(pview,400,400,true);
        		
        		pw.showAtLocation(pview, Gravity.CENTER, 0, 0);
        		submit = (Button)pview.findViewById(R.id.submit_permission_btn);
        		permission = (Spinner)pview.findViewById(R.id.permissionSpinner);
        		
        		//UNCOMMENT AFTER OPTIONS CLASS HAS BEEN CREATED IN "DATASTRUCTURES"
        		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, camp.getAccessPermissions());
				spinnerArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_item);
				
        		//ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, camp.options.permission.getVisibilityOptions());
        		permission.setAdapter(spinnerArrayAdapter);


        		/*ArrayAdapter <String> adapter = new ArrayAdapter<String>(mContext, 0, camp.sensors.gps.getFrequencies());
        				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        		*/
        		permission.setSelection(0);
        			  //  submit = new Button(this);
        			    //System.out.println(submit.toString());
        			    submit.setOnClickListener(permission_submit_button_click_listener);
        	}else{
        		
        		//UNCOMMENT AFTER OPTIONS CLASS HAS BEEN CREATED IN "DATASTRUCTURES"
        		//camp.options.clear_option("permission");
        	
        	}

        	} });
    }
    
    @Override
	public void onBackPressed() {
	   return;
	}
    
    private OnClickListener permission_submit_button_click_listener = new OnClickListener() {
        public void onClick(View v) {
        	System.out.println("selected item is "+permission.getSelectedItemPosition() );
    		//UNCOMMENT AFTER OPTIONS CLASS HAS BEEN CREATED IN "DATASTRUCTURES"
        	camp.setAccessPermission(permission.getSelectedItem().toString());
            pw.dismiss();
        }
    };
}