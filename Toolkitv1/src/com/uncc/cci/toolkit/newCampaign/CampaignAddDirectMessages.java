
package com.uncc.cci.toolkit.newCampaign;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.uncc.cci.toolkit.DashboardActivity;
import com.uncc.cci.toolkit.R;

import datastructures.Campaign;
import datastructures.DirectMessageIncentive;

public class CampaignAddDirectMessages extends DashboardActivity {
	Campaign camp;
	ImageButton back;
	ExpandableListAdapter mAdapter;
	//	ArrayList<String> join = new ArrayList<String>();
	//	ArrayList<String> idle = new ArrayList<String>();
	//	ArrayList<String> motiv = new ArrayList<String>();
	//	ArrayList<String> target = new ArrayList<String>();
	DirectMessageIncentive directmessage;
	ExpandableListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_campaign_directmessage_config3);
		// Set up our adapter
		list = (ExpandableListView) findViewById(R.id.dm_elv);
		Bundle b = getIntent().getExtras();
		if(b!=null){
			try {
				camp = b.getParcelable("campaign");
				camp.debugCampaign();
			}catch (Exception e) {
				e.printStackTrace();
			} 
		}
		directmessage = new DirectMessageIncentive(camp.getIncentive().getDirectMessage().getType());

		back = (ImageButton) findViewById(R.id.cancel_btn);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(getBaseContext(), CampaignAddIncentivesActivity.class);
				Bundle b = new Bundle();
				b.putParcelable("campaign", (Parcelable) camp);
				myIntent.putExtras(b);
				startActivity(myIntent);
			}

		});
		ImageButton add_options = (ImageButton) findViewById(R.id.next_btn);
		add_options.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {

				if (!directmessage.isEmpty()){

					camp.getIncentive().setDirectMessage(directmessage);
					// if (id==R.id.noneCheck) {
					Intent myIntent = new Intent(getBaseContext(), ReviewCampaignActivity.class);
					Bundle b = new Bundle();
					b.putParcelable("campaign", (Parcelable) camp);
					myIntent.putExtras(b);

					startActivity(myIntent);
					//}
					//TODO Add Options Activity later
					//Intent myIntent = new Intent(CampaignAddIncentivesActivity.this, CampaignAddOptionsActivity.class);
				}
				else {
					Toast.makeText(getBaseContext(), "Please select an option from all sections", Toast.LENGTH_SHORT).show();
				}
			}

		});
		mAdapter = new MyExpandableListAdapter(directmessage.getType());
		list.setAdapter(mAdapter);
		list.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Log.d("myapp", "click");
				CheckedTextView cb = (CheckedTextView) v.findViewWithTag("check");
				if (!cb.isChecked()) {
					cb.setCheckMarkDrawable(android.R.drawable.btn_star_big_on);
					cb.setChecked(true);
					Log.d("myapp", "cb: " + cb.getText().toString());
					switch (groupPosition) {
					//					case 0: join.add(cb.getText().toString()); break;
					//					case 1: motiv.add(cb.getText().toString()); break;
					//					case 2: idle.add(cb.getText().toString()); break;
					//					case 3: target.add(cb.getText().toString()); break;
					case 0: directmessage.getJoin().add(cb.getText().toString()); break;
					case 1: directmessage.getMotiv().add(cb.getText().toString()); break;
					case 2: directmessage.getIdle().add(cb.getText().toString()); break;
					case 3: directmessage.getTarget().add(cb.getText().toString()); break;
					}
				}
				else {
					cb.setChecked(false);
					cb.setCheckMarkDrawable(android.R.drawable.btn_star_big_off);
					switch (groupPosition) {
					//					case 0: join.add(cb.getText().toString()); break;
					//					case 1: motiv.add(cb.getText().toString()); break;
					//					case 2: idle.add(cb.getText().toString()); break;
					//					case 3: target.add(cb.getText().toString()); break;
					case 0: directmessage.getJoin().remove(cb.getText().toString()); break;
					case 1: directmessage.getMotiv().remove(cb.getText().toString()); break;
					case 2: directmessage.getIdle().remove(cb.getText().toString()); break;
					case 3: directmessage.getTarget().remove(cb.getText().toString()); break;
					}
				}
				return false;
			}
		});

		list.setOnGroupExpandListener(new OnGroupExpandListener() {

			public void onGroupExpand(int groupPosition) {
				int len = mAdapter.getGroupCount();
				for(int i=0; i<len; i++) {
					if(i != groupPosition) {
						list.collapseGroup(i);
					}
				}
			}

		});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();

		String title = ((CheckedTextView) info.targetView).getText().toString();

		int type = ExpandableListView.getPackedPositionType(info.packedPosition);
		if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
			int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
			int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition); 
			Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos,
					Toast.LENGTH_SHORT).show();
			return true;
		} else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
			int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
			Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_SHORT).show();
			return true;
		}

		return false;
	}

	/**
	 * A simple adapter which maintains an ArrayList of photo resource Ids. 
	 * Each photo is displayed as an image. This adapter supports clearing the
	 * list of photos and adding a new photo.
	 *
	 */
	public class MyExpandableListAdapter extends BaseExpandableListAdapter {
		// Sample data set.  children[i] contains the children (String[]) for groups[i].
		private String[] groups = null;
		//{ "People Names", "Dog Names", "Cat Names", "Fish Names" };
		private String[][] children = null;


		//                { "Arnold", "Barry", "Chuck", "David" },
		//                { "Ace", "Bandit", "Cha-Cha", "Deuce" },
		//                { "Fluffy", "Snuggles" },
		//                { "Goldy", "Bubbles" }

		public MyExpandableListAdapter(int type) {
			if (type == 0) {
				groups = getResources().getStringArray(R.array.directmessageincentive_options_regular);
				//{ "People Names", "Dog Names", "Cat Names", "Fish Names" };
				children = new String[][]{
						getResources().getStringArray(R.array.join_campaign),
						getResources().getStringArray(R.array.idle_messages),
						getResources().getStringArray(R.array.dm_regular_motivationalmessages)};
			}
			else {
				groups = getResources().getStringArray(R.array.directmessageincentive_options_goal);
				//{ "People Names", "Dog Names", "Cat Names", "Fish Names" };
				children = new String[][]{
						getResources().getStringArray(R.array.join_campaign),
						getResources().getStringArray(R.array.idle_messages),
						getResources().getStringArray(R.array.dm_goalbased_motivationalmessages),
						getResources().getStringArray(R.array.dm_goalbased_reachedtarget)};
			}
		}

		public Object getChild(int groupPosition, int childPosition) {
			return children[groupPosition][childPosition];
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		public int getChildrenCount(int groupPosition) {
			return children[groupPosition].length;
		}

		public TextView getGenericView() {
			// Layout parameters for the ExpandableListView
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, 64);

			TextView textView = new TextView(CampaignAddDirectMessages.this);
			textView.setLayoutParams(lp);
			// Center the text vertically
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView.setPadding(50, 0, 0, 0);
			textView.setTextColor(Color.WHITE);
			return textView;
		}

		public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
				View convertView, ViewGroup parent) {
			CheckedTextView checkedTextView = new CheckedTextView(CampaignAddDirectMessages.this);
			checkedTextView.setText(getChild(groupPosition, childPosition).toString());
			checkedTextView.setTextColor(Color.WHITE);
			checkedTextView.setTag("check");
			checkedTextView.setCheckMarkDrawable(android.R.drawable.btn_star_big_off);
			switch (groupPosition) {
			case 0: for (int i = 0; i < directmessage.getJoin().size(); i++) {
				if (directmessage.getJoin().contains(checkedTextView.getText().toString())) {
					checkedTextView.setChecked(true);
					checkedTextView.setCheckMarkDrawable(android.R.drawable.btn_star_big_on);
				}
			} break;
			case 1:for (int i = 0; i < directmessage.getIdle().size(); i++) {
				if (directmessage.getIdle().contains(checkedTextView.getText().toString())) {
					checkedTextView.setChecked(true);
					checkedTextView.setCheckMarkDrawable(android.R.drawable.btn_star_big_on);
				}
			} break;
			case 2:for (int i = 0; i < directmessage.getMotiv().size(); i++) {
				if (directmessage.getMotiv().contains(checkedTextView.getText().toString())) {
					checkedTextView.setChecked(true);
					checkedTextView.setCheckMarkDrawable(android.R.drawable.btn_star_big_on);
				}
			} break;
			case 3:for (int i = 0; i < directmessage.getTarget().size(); i++) {
				if (directmessage.getTarget().contains(checkedTextView.getText().toString())) {
					checkedTextView.setChecked(true);
					checkedTextView.setCheckMarkDrawable(android.R.drawable.btn_star_big_on);
				}
			} break;
			}
			return checkedTextView;
		}

		public Object getGroup(int groupPosition) {
			return groups[groupPosition];
		}

		public int getGroupCount() {
			return groups.length;
		}

		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
				ViewGroup parent) {
			TextView textView = getGenericView();
			textView.setText(getGroup(groupPosition).toString());
			return textView;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
		public boolean hasStableIds() {
			return true;
		}
	}
}