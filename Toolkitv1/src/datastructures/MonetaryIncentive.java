package datastructures;

import java.io.Serializable;
import java.util.HashMap;

public class MonetaryIncentive  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8335208819145552903L;

	private HashMap<String, String> monetaryOptions = null;
	
	public HashMap<String, String> getMonetaryOptions() {
		return monetaryOptions;
	}
	
	public void setMonetaryOptions(HashMap<String, String> monetaryOptions) {
		this.monetaryOptions = monetaryOptions;
	}

	public void clearMonetaryOptions () {
		this.monetaryOptions = null;
	}
	
	public MonetaryIncentive(){
		monetaryOptions = new HashMap<String, String>();
		init_monetaryOptions();
	}
	
	public void init_monetaryOptions() {
		this.monetaryOptions.put("moneyMax", "");
		this.monetaryOptions.put("acceptedAmount", "");
		this.monetaryOptions.put("alertOption", "");
		this.monetaryOptions.put("moneyInstructions", "");
		
	}
}
