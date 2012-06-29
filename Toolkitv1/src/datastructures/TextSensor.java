package datastructures;

import java.io.Serializable;
import java.util.ArrayList;

public class TextSensor  implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 7937987359174652045L;
	//	String option;
//	 private String options[] = {"Add a Multiple Choice List", "Add a Checkbox List", "Add a Text Box"};
	private ArrayList <Question> listOfQuestions; 
		
	public TextSensor() {};
	public ArrayList<Question> getListOfQuestions() {
		return listOfQuestions;
	}
	
	public void setListOfQuestions(ArrayList<Question> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}
	
	
}
