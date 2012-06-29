package datastructures;

import java.io.Serializable;
import java.util.ArrayList;


public class Candidate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3849945029343710667L;
	ArrayList<User> participants = null;
	
	public ArrayList<User> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<User> participants) {
		this.participants = participants;
	}
	
	public void clearParticipants() {
		this.participants = null;	
	}


	public Candidate() {};
public int describeContents() {
	// TODO Auto-generated method stub
	return 0;
}
}

