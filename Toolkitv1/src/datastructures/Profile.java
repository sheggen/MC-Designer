package datastructures;

import java.io.Serializable;
import java.util.HashMap;

public class Profile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8212863190905886923L;
	HashMap<String, Boolean> selectedOptions = null;
	String gender;
	int maxAge = -1;
	int minAge = -1;
	HashMap<String, Boolean> ethnicity = null;
	String mapCenter;
	int range = -1;
	//NOTE: range is [latitude, longitude]
	
	private void init_optionsHashmap(){
		this.selectedOptions.put("gender",false);
		this.selectedOptions.put("ageRange",false);
		this.selectedOptions.put("ethnicity",false);
		this.selectedOptions.put("region",false);
	}
	
	public void init_ethnicityHashmap() {
		this.ethnicity = new HashMap<String, Boolean>();
		this.ethnicity.put("white", false);
		this.ethnicity.put("black", false);
		this.ethnicity.put("hispanic", false);
		this.ethnicity.put("asian", false);
		this.ethnicity.put("nativeamerican", false);
		this.ethnicity.put("multiracial", false);
	}

	private String genderOptions[] = {"Female", "Male"};
	private String ethnicityOptions[] = {"White","Black","Hispanic","Asian","Native","Multiracial"};

	public HashMap<String, Boolean> getSelectedOptions() {
		return selectedOptions;
	}

	public Profile() {
		this.setSelectedOptions(new HashMap<String,Boolean>());
		init_optionsHashmap();
	}
	
	public void setSelectedOptions(HashMap<String, Boolean> selectedOptions) {
		this.selectedOptions = selectedOptions;

	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		//if (gender.equals(genderOptions[0]) | gender.equals(genderOptions[1]))
		this.gender = gender;
		//if (this.selectedOptions.get("gender").equals(false))
			this.selectedOptions.put("gender",true);
	}
	public void clearGender() {
		this.gender = "";
		this.selectedOptions.put("gender", false);
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
		//if (this.selectedOptions.get("ageRange").equals(false))
			this.selectedOptions.put("ageRange",true);
		
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
		//if (this.selectedOptions.get("ageRange").equals(false))
			this.selectedOptions.put("ageRange",true);
	}
	public void clearAgeRange() {
		this.selectedOptions.put("ageRange", false);
		this.minAge = -1;
		this.maxAge = -1;
	}
	public HashMap<String, Boolean> getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(HashMap<String, Boolean> ethnicity) {
		this.ethnicity = ethnicity;
		//if (this.selectedOptions.get("ethnicity").equals(false))
			this.selectedOptions.put("ethnicity",true);
			this.init_ethnicityHashmap();
	}
	
	public void clearEthnicity() {
		this.ethnicity.clear();
		this.selectedOptions.put("ethnicity", false);
	}
	public String getMapCenter() {
		return mapCenter;
	}
	public void setMapCenter(String mapCenter) {
		this.mapCenter = mapCenter;
		//if (this.selectedOptions.get("region").equals(false))
			this.selectedOptions.put("region",true);
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	
	public void clearRegion() {
		this.mapCenter = null;
		this.range = -1;
		this.selectedOptions.put("region", false);
	}
	public String[] getGenderOptions() {
		return genderOptions;
	}
	public String[] getEthnicityOptions() {
		return ethnicityOptions;
	}
	
	 
}

