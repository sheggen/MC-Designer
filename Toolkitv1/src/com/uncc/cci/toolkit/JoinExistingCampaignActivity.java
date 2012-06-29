/*
 * Copyright (C) 2011 Wglxy.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uncc.cci.toolkit;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * This is the activity for feature 5 in the dashboard application.
 * It displays some text and provides a way to get back to the home activity.
 *
 */

public class JoinExistingCampaignActivity extends DashboardActivity 
{

	/**
	 * onCreate
	 *
	 * Called when the activity is first created. 
	 * This is where you should do all of your normal static set up: create views, bind data to lists, etc. 
	 * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
	 * 
	 * Always followed by onStart().
	 *
	 * @param savedInstanceState Bundle
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView (R.layout.activity_f5);
		setTitleFromActivityLabel (R.id.title_text);

		ListView lv1 = (ListView)findViewById(R.id.f5ListView1);
		String[] list = getResources().getStringArray(R.array.part_list);
		lv1.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, list));
	}

} // end class
