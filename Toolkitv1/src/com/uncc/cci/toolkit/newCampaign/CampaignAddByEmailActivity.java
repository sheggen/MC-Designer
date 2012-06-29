package com.uncc.cci.toolkit.newCampaign;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;

import com.uncc.cci.toolkit.DashboardActivity;
import com.uncc.cci.toolkit.R;

import datastructures.Campaign;
import datastructures.Candidate;
import datastructures.User;

public class CampaignAddByEmailActivity extends DashboardActivity {
	private ArrayList<Contact> contacts = null;
	Campaign camp;

	private ProgressDialog progressDialog = null; 
	private ContactAdapter contactAdapter = null;
	private Runnable viewContacts = null;
	private SparseBooleanArray selectedContacts =   new SparseBooleanArray()  ; 

	ListView list;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = this.getIntent().getExtras();
		setContentView(R.layout.new_campaign_invite_participants_by_email_options);
		if(b!=null) {
			camp = b.getParcelable("campaign");
			camp.debugCampaign();
		}
		contacts = new ArrayList<Contact>();

		this.contactAdapter = new ContactAdapter(this, R.layout.listview, contacts);
		list = (ListView)findViewById(R.id.list1234);
		list.setAdapter(this.contactAdapter);
		viewContacts = new Runnable(){
			public void run() {
				getContacts();
			}
		};

		Thread thread =  new Thread(null, viewContacts, "ContactReadBackground");
		thread.start();
		progressDialog = ProgressDialog.show(CampaignAddByEmailActivity.this,"Retrieving contacts ...", "Only contacts with emails will be invited to your campaign", true);
		System.out.println(contacts.size());
		//            contactAdapter.notifyDataSetChanged();
		//(new AlertDialog.Builder(this).setTitle("Add by Email").setMessage("Only contacts with emails will be invited to your campaign").create()).show();


		ImageButton back = (ImageButton) findViewById(R.id.cancel_btn_email);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), CampaignInviteParticipantsOptionsActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivity(myIntent);
			}

		});

		ImageButton next = (ImageButton) findViewById(R.id.next_btn_email);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				System.out.println(selectedContacts.size());
				camp.setCandidate(new Candidate());
				camp.getCandidate().setParticipants(new ArrayList<User>());
				//TODO Populate Camp with data from User class
				for(int i=0;i<selectedContacts.size();i++){
					User user = new User();
					if (selectedContacts.valueAt(i)) {
						if (!(contacts.get(selectedContacts.keyAt(i)).getEmail()==(null))) {
							user.setName(contacts.get(selectedContacts.keyAt(i)).getContactName());
							user.setEmail(contacts.get(selectedContacts.keyAt(i)).getEmail());
							camp.getCandidate().getParticipants().add(user);
							System.out.println("User added: "+user.getName());
							System.out.println("Email: "+user.getEmail());
						}
					}

				}
				Intent myIntent = new Intent(view.getContext(), CampaignInviteParticipantsOptionsActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivity(myIntent);
			}


		});

	}



	private void getContacts(){
		try{
			String[] projection = new String[] {
					ContactsContract.Contacts.DISPLAY_NAME,
					ContactsContract.Contacts.HAS_PHONE_NUMBER,
					ContactsContract.Contacts._ID
			};
			String[] projectionEmail = new String[] {
					ContactsContract.CommonDataKinds.Email.DATA,
					ContactsContract.CommonDataKinds.Email.TYPE
			};

			String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + "='1'";
			Cursor cursor = managedQuery(ContactsContract.Contacts.CONTENT_URI, projection, selection, null, ContactsContract.Contacts.DISPLAY_NAME);

			contacts = new ArrayList<Contact>();

			while(cursor.moveToNext()){
				Contact contact = new Contact();

				String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

				contact.setContactName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
				/*contact.setEmail(Email.DATA);
        	        	System.out.println("Email set to : "+contact.getEmail());*/
				contacts.add(contact);

				String selectionEmail = ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=?";

				Cursor emailCur = managedQuery(ContactsContract.CommonDataKinds.Email.CONTENT_URI,projectionEmail,selectionEmail, new String[]{contactId}, null);

				while (emailCur.moveToNext()) {
					contact.setEmail(emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
				}
				emailCur.close();

			}

			cursor.close();


			runOnUiThread(returnRes);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private Runnable returnRes = new Runnable() {

		public void run() {
			// close the progress dialog
			if (progressDialog.isShowing())
				progressDialog.dismiss();
			System.out.println("Inside return res: "+contacts.size());
			//contactAdapter.notifyDataSetChanged();
			for(int i=0;i<contacts.size();i++){
				contactAdapter.add(contacts.get(i));
			}
			contactAdapter.notifyDataSetChanged();
		}
	};

	public class Contact {
		private String contactName;
		private String email;

		public void setEmail(String email) {
			this.email = email;
		}

		public String getEmail() {
			return email;
		}

		public String getContactName() {
			return contactName;
		}
		public void setContactName(String contactName) {
			this.contactName = contactName;
		}


	}

	public class ContactAdapter extends ArrayAdapter<Contact> {

		private ArrayList<Contact> items;

		public ContactAdapter(Context context, int textViewResourceId, ArrayList<Contact> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = vi.inflate(R.layout.listview, null);
			}
			Contact contact = items.get(position);
			if (contact != null) {
				CheckBox nameCheckBox = (CheckBox) view.findViewById(R.id.checkBox123);
				nameCheckBox.setChecked(selectedContacts.get(position));

				if (nameCheckBox != null) {
					nameCheckBox.setText(contact.getContactName());
				}

				nameCheckBox.setOnClickListener(new OnItemClickListener(position,nameCheckBox.getText(),nameCheckBox));

			}
			return view;
		}
	}

	private class OnItemClickListener implements OnClickListener{           
		private int position;
		//private CharSequence text;
		private CheckBox checkBox;
		OnItemClickListener(int position, CharSequence text,CheckBox checkBox){
			this.position = position;
			//this.text = text;
			this.checkBox = checkBox;
		}
		public void onClick(View arg0) {
			if (checkBox.isChecked()) {
				selectedContacts.append(position, true);
			}

		}               
	}



}
