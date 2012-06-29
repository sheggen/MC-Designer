package com.uncc.cci.toolkit.newCampaign;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uncc.cci.toolkit.DashboardActivity;
import com.uncc.cci.toolkit.R;

import datastructures.Campaign;
import datastructures.Question;
import datastructures.TextSensor;

public class CampaignAddTextQuestionsActivity extends DashboardActivity {
	Campaign camp;
	EditText questionText;
	int current_edittext_id;
	int questionCounter;
	LinearLayout ll;
	EditText current_edittext;
	private List<EditText> editTextList = new ArrayList<EditText>();
	TextSensor textsensor = null;
	
/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_campaign_text_config);
        
        Bundle b = this.getIntent().getExtras();
        System.out.println("Entering Text Sensor");
        
        current_edittext = (EditText)findViewById(R.id.questionEditText);
        editTextList.add(current_edittext);
        ll = (LinearLayout)findViewById(R.id.editTextBoxes);
        current_edittext_id = current_edittext.getId();
        questionCounter = 0;
        
        
        if(b!=null){
            try {
				camp = b.getParcelable("campaign");
				camp.debugCampaign();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }	
        
        if (camp.getSensors().getText() != null) {
        	if (camp.getSensors().getText().getListOfQuestions() != null) {
        		textsensor = camp.getSensors().getText();
        	}
        }
        
        ImageButton back = (ImageButton) findViewById(R.id.cancel_btn);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent myIntent = new Intent(CampaignAddTextQuestionsActivity.this, CampaignAddSensorsActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivity(myIntent);
            }

        });
        ImageButton next = (ImageButton) findViewById(R.id.next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	textsensor = new TextSensor();
            	textsensor.setListOfQuestions(new ArrayList<Question>());
            	for (EditText editText : editTextList) {
            		Question question = new Question();
            		if (!editText.getText().toString().isEmpty()) {
            			question.setQuestionText(editText.getText().toString());
            			textsensor.getListOfQuestions().add(question);
            		}
            	}
            	camp.getSensors().setText(textsensor);

                Intent myIntent = new Intent(CampaignAddTextQuestionsActivity.this, CampaignAddSensorsActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("campaign", camp); 
                myIntent.putExtras(b);
                startActivity(myIntent);
            }

        });
        
     //   final RelativeLayout layout = (RelativeLayout)findViewById(R.id.addQuestionLayout);
        
        final ImageButton newQuestion = (ImageButton) findViewById(R.id.plusButton);
        newQuestion.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		if (!current_edittext.getText().toString().equalsIgnoreCase("")){
        			questionCounter =questionCounter + 1; 
        			EditText ev = new EditText(CampaignAddTextQuestionsActivity.this);
        			editTextList.add(ev);
        			ev.setId(questionCounter);
        			ev.setHint(R.string.what_question_will_you_ask_);
        			ev.setHeight(50);
        			ev.setSingleLine(true);
        			ev.setBackgroundResource(R.drawable.rounded_edittext);
        			ev.setTextAppearance(getApplicationContext(),R.id.questionEditText);
        			LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        			llp.setMargins(0, 10, 0, 0);
        			ev.setLayoutParams(llp);
        			ll.addView(ev);
        			current_edittext = ev;
        			current_edittext_id = ev.getId();
        			trace(Integer.toString(questionCounter));
        			ev.requestFocus();
        		} else {
        			Toast.makeText(getApplicationContext(), "Enter a question before adding a new one", Toast.LENGTH_SHORT).show();
        		}
        	}
		});
    }
    
      		
    		
    
    @Override
	public void onBackPressed() {
	   return;
	}
    
}