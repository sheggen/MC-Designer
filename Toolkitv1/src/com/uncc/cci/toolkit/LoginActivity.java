package com.uncc.cci.toolkit;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	EditText un,pw;
	TextView error;
	Button ok;
	ViewStub stub;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		un=(EditText)findViewById(R.id.et_un);
		pw=(EditText)findViewById(R.id.et_pw);
		ok=(Button)findViewById(R.id.btn_login);
		error=(TextView)findViewById(R.id.tv_error);
		//		stub = (ViewStub)findViewById(R.id.progress_stub);

		pw.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
						(keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press

					ok.performClick();
					return true;
				}
				return false;
			}
		});



		ok.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				//				stub.inflate();
				ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();  
				postParameters.add(new BasicNameValuePair("username", un.getText().toString()));  
				postParameters.add(new BasicNameValuePair("password", pw.getText().toString()));  

				//String valid = "1";  
				String response = "";  

				//				TODO Remove once live; debug only!					
				if (un.getText().toString().equals("Sheggen")) {
					startActivity(new Intent(getApplicationContext(), HomeActivity.class));

				} else {
					try { 
						//TODO Change this so it uses HTTPConnector class
						response = CustomHttpClient.executeHttpPost("http://152.15.99.147/check.php", postParameters);  //Enter Your remote PHP,ASP, Servlet file link  
						String res=response.toString();  
						res= res.replaceAll("\\s+","");
						char x = res.toCharArray()[1];
						if(x == '1') {
							//error.setText("Correct Username and Password!");  
							startActivity(new Intent(getApplicationContext(), HomeActivity.class)); 
						}
						else { 
							error.setText("Sorry!! Incorrect Username or Password.  Try again!"); 
						} 

					} catch (Exception e) {  
						error.setText(e.toString());

					}
				} 
			}
		});  
	}}  
