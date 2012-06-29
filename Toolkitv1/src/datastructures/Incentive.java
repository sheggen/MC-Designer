package datastructures;

import java.io.Serializable;
import java.util.HashMap;

//TODO Fix issue with hashmap setting everything true.

public class Incentive implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2381175473683521229L;
	// save
	// add incentive option
	HashMap<String,Boolean> incentive = null;
	MonetaryIncentive monetary;
	PointIncentive point;
	DirectMessageIncentive directMessage;
	
	public Incentive() {
		incentive = new HashMap<String, Boolean>();
		init_Incentive();
	}
	private void init_Incentive() {
		this.incentive.put("monetary", false);
		this.incentive.put("point", false);
		this.incentive.put("directmessage", false);
	}
	public HashMap<String, Boolean> getIncentive() {
		return incentive;
	}
	public void setIncentive(HashMap<String, Boolean> incentive) {
		this.incentive = incentive;
		this.incentive.put("monetary", false);
		this.incentive.put("point", false);
		this.incentive.put("directmessage", false);
	}
	
	
	public MonetaryIncentive getMonetary() {
		return monetary;
	}
	
	public void setMonetary(MonetaryIncentive monetary) {
		if(this.incentive==null){
			this.setIncentive(new HashMap<String,Boolean>());
			
		}
		this.monetary = monetary;
		this.incentive.put("monetary", true);
	}
	public void clearMonetaryIncentive(){
		if(this.incentive==null){
		return;
			
		}
		this.monetary=null;
		this.getIncentive().put("monetary", false);
	}
	public PointIncentive getPoint() {
		return point;
	}
	public void setPoint(PointIncentive point) {
		if(this.incentive==null){
			this.setIncentive(new HashMap<String,Boolean>());
			
		}
		this.point = point;
		this.incentive.put("point", true);
	}
	public void clearPointIncentive(){
		if(this.incentive==null){
			return;
		}
		this.point = null;
		this.incentive.put("point", false);
	}
	public DirectMessageIncentive getDirectMessage() {
		return directMessage;
	}
	public void setDirectMessage(DirectMessageIncentive directMessage) {
		if(this.incentive==null){
			this.setIncentive(new HashMap<String,Boolean>());
			
		}
		this.directMessage = directMessage;
		this.incentive.put("directmessage", true);
		
	}
	public void clearDirectMessageIncentive(){
		if(this.incentive==null){
		
			return;
		}
		this.directMessage = null;
		this.incentive.put("directmessage", false);
	}
	public String getSelectedIncentive(){
		if(this.incentive.get("point")){
			return "point";
		}
		if(this.incentive.get("directmessage")){
			return "directmessage";
		}
		if(this.incentive.get("monetary")){
			return "monetary";
		}
		
		return "none";
	}
}
