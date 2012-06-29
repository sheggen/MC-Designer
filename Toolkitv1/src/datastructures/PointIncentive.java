package datastructures;

import java.io.Serializable;
import java.util.HashMap;

public class PointIncentive implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4358158501258007079L;
	
	private HashMap<String, String> pointsOption = null;

	public HashMap<String, String> getPointsOption() {
		return pointsOption;
	}

	public void setPointsOption(HashMap<String, String> pointsOption) {
		this.pointsOption = pointsOption;
	}
	
	public void clearPointsOption() {
		this.pointsOption = null;
	}
	
	public PointIncentive () {
		pointsOption = new HashMap<String, String>();
		init_pointsOptions();
	};
	
	public void init_pointsOptions() {
		this.pointsOption.put("startPoints", "");
		this.pointsOption.put("submissionPoints", "");
		this.pointsOption.put("levelPoints", "");
		this.pointsOption.put("leaderboardPoints", "");
		this.pointsOption.put("instructions", "");
		this.pointsOption.put("newLevel", "");
		
	}

	
}
