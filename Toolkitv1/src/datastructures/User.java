package datastructures;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3292986332021219825L;
	int uid;
	String name;
	String email;
	String phone;
	int age;
	String ethnicity;
	String gender;
	String location;
	double geographiccoverage;
	
	
	public User(int uid, String name, String email, String phone, int age,
			String ethnicity, String gender, String location,
			double geographiccoverage) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.age = age;
		this.ethnicity = ethnicity;
		this.gender = gender;
		this.location = location;
		this.geographiccoverage = geographiccoverage;
	}
	
	public User() {
		
	}
	
	public User(int id) {
		this.uid = id;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getGeographiccoverage() {
		return geographiccoverage;
	}
	public void setGeographiccoverage(double geographiccoverage) {
		this.geographiccoverage = geographiccoverage;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

	
	//is_present_in_DB
	//is participant of campaign
	//get all users
	
	
	//save
	//add mobility profile
}
