package datastructures;

import java.io.Serializable;
import java.util.ArrayList;

public class Question  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8771206343755916821L;
	String questionText;
//	int numOptions;
	ArrayList<String> options;
	
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public ArrayList<String> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
		
}
