package datastructures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;

//import DBConnections.DBHelper;


public class Campaign implements Parcelable, Serializable{ 

	private static final long serialVersionUID = -4110235172891969460L;
	int id;
	String title,instructions,purpose = "";
	String startTime,endTime,region,locationOnDisk = "";
	int minNumberOfParticipants,targetNumberOfSubmissions;
	Boolean status = null;
	ArrayList <Example> example = null;
	Sensor sensors = null;
	//ArrayList<User> participants = null;
	Candidate candidate = null;
	Incentive incentive = null;
	Connection conn = null;
	PreparedStatement statement = null;
	String sqlQuery;
	int campaignOrganizer = 1;  //TODO Set to -1; pull value from user login  
	Profile profile = null;
	Boolean openToPublic = false;


	public Boolean getOpenToPublic() {
		return openToPublic;
	}

	public void setOpenToPublic(Boolean openToPublic) {
		this.openToPublic = openToPublic;
	}

	String accessPermission = null;
	private String accessPermissions[] = {"Users can see all campaign data", "Users cannot see campaign data", "Users can see data they've submitted"};

	// SAVE CAMPAIGN
	// INSERT INTO CAMPAIGNS
	// ADD TO INCENTIVES USED
	// ADD TO PARTICIPANTS TABLE
	// ADD TO CREATOR TABLE
	// ADD TO SENSORS OPTIONS USED TABLE

	// add participant

	// get all participants



	/*public void save(){

		try{
			if (dbhelper == null){
				dbhelper = DBHelper.INSTANCE;		
			}
			conn = dbhelper.getConnection();

			//CHECK IF CAMPAIGN EXISTS **DID NOT CHECK YET

			//ADD ENTRIES TO THE CAMPAIGN TABLE
			sqlQuery = "insert into campaign";

			statement = conn.prepareStatement(sqlQuery);

			//SET VARIABLES IN THE STATEMENT

			statement.executeQuery();
			//CHECK IF USER EXISTS
			//IF YES, ADD TO PARTICIPANTS TABLE
			//IF NO, ADD TO USERS TABLE AND PARTICIPANTS TABLE


			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{

			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}*/

	public String getAccessPermission() {
		return accessPermission;
	}

	public void setAccessPermission(String accessPermission) {
		this.accessPermission = accessPermission;
	}

	public String[] getAccessPermissions() {
		return accessPermissions;
	}

	public void setAccessPermissions(String[] accessPermissions) {
		this.accessPermissions = accessPermissions;
	}

	public Campaign() {		
	}

	public int getCampaignOrganizer() {
		return campaignOrganizer;
	}

	public void setCampaignOrganizer(int campaignOrganizer) {
		this.campaignOrganizer = campaignOrganizer;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLocationOnDisk() {
		return locationOnDisk;
	}

	public void setLocationOnDisk(String locationOnDisk) {
		this.locationOnDisk = locationOnDisk;
	}

	public int getMinNumberOfParticipants() {
		return minNumberOfParticipants;
	}

	public void setMinNumberOfParticipants(int minNumberOfParticipants) {
		this.minNumberOfParticipants = minNumberOfParticipants;
	}

	public int getTargetNumberOfSubmissions() {
		return targetNumberOfSubmissions;
	}

	public void setTargetNumberOfSubmissions(int targetNumberOfSubmissions) {
		this.targetNumberOfSubmissions = targetNumberOfSubmissions;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ArrayList<Example> getExample() {
		return example;
	}

	public void setExample(ArrayList<Example> example) {
		this.example = example;
	}

	public Sensor getSensors() {
		return sensors;
	}

	public void setSensors(Sensor sensors) {
		this.sensors = sensors;
	}

	/*public ArrayList<User> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<User> participants) {
		this.participants = participants;
	}

	public void clearParticipants() {
		this.participants = null;	
	}*/


	public Incentive getIncentive() {
		return incentive;
	}

	public void setIncentive(Incentive incentive) {
		this.incentive = incentive;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getStatement() {
		return statement;
	}

	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String toXML() {
		String xmlString ="";
		xmlString = xmlString.concat("<toolkitxml>");
		xmlString = xmlString.concat("<Action>createCampaign</Action>");
		xmlString = xmlString.concat("<Campaign");
		if(this.getTitle()!=""){
			xmlString = xmlString.concat(" title =\""+this.getTitle()+"\"");
		}
		if(this.getInstructions()!=""){
			xmlString = xmlString.concat(" instructions =\""+this.getInstructions()+"\"");
		}
		if(this.getPurpose()!=""){
			xmlString = xmlString.concat(" purpose =\""+this.getPurpose()+"\"");
		}
		if(this.getCampaignOrganizer() != -1){
			xmlString = xmlString.concat(" campaignorganizerID =\""+this.getCampaignOrganizer()+"\"");
		}

		//TODO move to campaignoptions

		/*if(this.getStartTime()!=null){
			xmlString = xmlString.concat(" starttime =\""+this.getStartTime()+"\"");
		}
		if(this.getEndTime()!=null){
			xmlString = xmlString.concat(" endtime =\""+this.getEndTime()+"\"");
		}
		if(this.getRegion()!=""){
			xmlString = xmlString.concat(" region =\""+this.getRegion()+"\"");
		}*/

		//xmlString = xmlString.concat(" status =\""+this.getStartTime()+"\"");

		xmlString = xmlString.concat(" >");

		if(this.getExample()!=null){
			xmlString = xmlString.concat("<examples>");
			for(int i =0;i< this.getExample().size();i++){
				xmlString = xmlString.concat("<example sensorType = \""+this.getExample().get(i).Sensor + "\" filename = \""+ this.getExample().get(i).filename +"\" />");
			}
			xmlString = xmlString.concat("</examples>");
		}

		if(this.getSensors()!=null){
			xmlString = xmlString.concat("<sensors>");
			System.out.println(this.getSensors().getSensors().get("GPS"));
			if(this.getSensors().getSensors().get("GPS")){
				xmlString = xmlString.concat("<GPS ");
				//xmlString = xmlString.concat("gpscaptureoption = \""+ this.getSensors().getGps().getOption());
				xmlString = xmlString.concat("gpscaptureoption = \""+ 0);
				xmlString = xmlString.concat("\" />");
			}
			if(this.getSensors().getSensors().get("CAMCORDER")){
				xmlString = xmlString.concat("<camcorder ");
				xmlString = xmlString.concat("recordLength = \""+ this.getSensors().getCamcorder().getRecordLength());
				xmlString = xmlString.concat(" />");
			}
			if(this.getSensors().getSensors().get("CAMERA")){
				xmlString = xmlString.concat("<camera ");
				/*xmlString.concat(" autofocusoption =\""+this.getSensors().getCamera().getAutoFocusOption()+"\""); 
				xmlString.concat(" resolutionoption =\""+this.getSensors().getCamera().getResolutionOption()+"\"");*/
				xmlString = xmlString.concat(" />");
			}
			if(this.getSensors().getSensors().get("ACCELEROMETER")){
				xmlString = xmlString.concat("<accelerometer ");
				if(this.getSensors().getAccelerometer().getCaptureOption() == this.getSensors().getAccelerometer().getCaptureOptions()[0])
					xmlString = xmlString.concat("captureoption = \"" + 1 +"\"");
				else if (this.getSensors().getAccelerometer().getCaptureOption() == this.getSensors().getAccelerometer().getCaptureOptions()[1]) 
					xmlString = xmlString.concat("captureoption = \"" + 2 +"\"");
				xmlString = xmlString.concat(" />");
			}
			if(this.getSensors().getSensors().get("AUDIO")){
				xmlString = xmlString.concat("<audio ");
				//xmlString = xmlString.concat("captureoption = " + this.getSensors().getAudio().getCaptureOption() +"\"");
				xmlString = xmlString.concat("captureoption = \"" + 0 +"\"");//hardcoding to continuous 
				xmlString = xmlString.concat(" />");
			}
			if(this.getSensors().getSensors().get("TEXT")){
				xmlString = xmlString.concat("<text>");
				for (int i = 0; i < this.getSensors().getText().getListOfQuestions().size(); i++) {
					xmlString = xmlString.concat("<Question ");
					xmlString = xmlString.concat("text = \"" + this.getSensors().getText().getListOfQuestions().get(i).getQuestionText());
					xmlString = xmlString.concat("\"/>");
				}

				xmlString = xmlString.concat("</text>");
			}
			xmlString = xmlString.concat("</sensors>");
		}

		if(this.getCandidate()!=null | this.getProfile() != null){
			xmlString = xmlString.concat("<Participants ");
			xmlString = xmlString.concat("opentopublic = \"" + this.getOpenToPublic()+"\">");
			//TODO fix me crashin'
			if (this.getCandidate()!=null) {
				if (this.getCandidate().getParticipants().size() != 0) {
					xmlString = xmlString.concat("<InviteByEmail>");
					for(int i=0;i< this.getCandidate().getParticipants().size();i++){
						xmlString = xmlString.concat("<User>");
						xmlString = xmlString.concat("<uname>"+this.getCandidate().getParticipants().get(i).getName() + "</uname>");
						xmlString = xmlString.concat("<uemail>"+this.getCandidate().getParticipants().get(i).getEmail()+"</uemail>");
						xmlString = xmlString.concat("</User>");
					}
					xmlString = xmlString.concat("</InviteByEmail>");
				}
			}

			if(this.getProfile() != null) {
				xmlString = xmlString.concat("<MatchProfile ");
				if(this.getProfile().getGender() != null)
					xmlString = xmlString.concat("gender = \"" + this.getProfile().getGender() + "\""); 
				xmlString = xmlString.concat(">");
				if(this.getProfile().getEthnicity()!= null) {
					xmlString = xmlString.concat("<ethnicity white = \"" + Boolean.toString(this.getProfile().getEthnicity().get("white"))+ "\" ");
					xmlString = xmlString.concat(" black = \"" + Boolean.toString(this.getProfile().getEthnicity().get("black"))+ "\" "); 
					xmlString = xmlString.concat(" asian = \"" + Boolean.toString(this.getProfile().getEthnicity().get("asian"))+ "\" "); 
					xmlString = xmlString.concat(" hispanic = \"" + Boolean.toString(this.getProfile().getEthnicity().get("hispanic"))+ "\" "); 
					xmlString = xmlString.concat(" nativeamerican = \"" + Boolean.toString(this.getProfile().getEthnicity().get("nativeamerican"))+ "\" "); 
					xmlString = xmlString.concat(" multiracial = \"" + Boolean.toString(this.getProfile().getEthnicity().get("multiracial"))+ "\" "); 
					xmlString = xmlString.concat("/>");
				}
				if(this.getProfile().getSelectedOptions().get("ageRange")) {
					xmlString = xmlString.concat("<ageRange ");
					//if(this.getProfile().getMinAge() != -1)
						xmlString = xmlString.concat("min = \"" + this.getProfile().getMinAge()  + "\" ");
					//if(this.getProfile().getMaxAge() != -1)
						xmlString = xmlString.concat("max = \"" + this.getProfile().getMaxAge() + "\" ");
					xmlString = xmlString.concat("/>");
				}
				if(this.getProfile().getMapCenter() != null) {
					xmlString = xmlString.concat("<region  ");
					xmlString = xmlString.concat("center  = \"" + this.getProfile().getMapCenter()+ "\" ");
					xmlString = xmlString.concat("range  = \"" + this.getProfile().getRange()+ "\" ");
					xmlString = xmlString.concat("/>");
				}
				xmlString = xmlString.concat("</MatchProfile>");
			}
			xmlString = xmlString.concat("</Participants>");
		}

		if(this.getIncentive()!=null){
			xmlString = xmlString.concat("<Incentives> ");

			if (this.getIncentive().getDirectMessage() != null) {
				if (this.getIncentive().getDirectMessage().getType()==0) {
					xmlString = xmlString.concat("<directmessageregular>");
					xmlString = xmlString.concat("<joinArray>");
					for (int i = 0; i < this.getIncentive().getDirectMessage().getJoin().size(); i++) {
						xmlString = xmlString.concat("<message>"+this.getIncentive().getDirectMessage().getJoin().get(i)+"</message>");
					}
					xmlString = xmlString.concat("</joinArray>");
					xmlString = xmlString.concat("<motivArray>");
					for (int i = 0; i < this.getIncentive().getDirectMessage().getMotiv().size(); i++) {
						xmlString = xmlString.concat("<message>"+this.getIncentive().getDirectMessage().getMotiv().get(i)+"</message>");
					}
					xmlString = xmlString.concat("</motivArray>");
					xmlString = xmlString.concat("<idleArray>");
					for (int i = 0; i < this.getIncentive().getDirectMessage().getIdle().size(); i++) {
						xmlString = xmlString.concat("<message>"+this.getIncentive().getDirectMessage().getIdle().get(i)+"</message>");
					}
					xmlString = xmlString.concat("</idleArray>");

					xmlString = xmlString.concat("</directmessageregular>");
				}


				if (this.getIncentive().getDirectMessage().getType()==1) {
					xmlString = xmlString.concat("<directmessagegoal>");
					xmlString = xmlString.concat("<joinArray>");
					for (int i = 0; i < this.getIncentive().getDirectMessage().getJoin().size(); i++) {
						xmlString = xmlString.concat("<message>"+this.getIncentive().getDirectMessage().getJoin().get(i)+"</message>");
					}
					xmlString = xmlString.concat("</joinArray>");
					xmlString = xmlString.concat("<motivArray>");
					for (int i = 0; i < this.getIncentive().getDirectMessage().getMotiv().size(); i++) {
						xmlString = xmlString.concat("<message>"+this.getIncentive().getDirectMessage().getMotiv().get(i)+"</message>");
					}
					xmlString = xmlString.concat("</motivArray>");
					xmlString = xmlString.concat("<idleArray>");
					for (int i = 0; i < this.getIncentive().getDirectMessage().getIdle().size(); i++) {
						xmlString = xmlString.concat("<message>"+this.getIncentive().getDirectMessage().getIdle().get(i)+"</message>");
					}
					xmlString = xmlString.concat("</idleArray>");
					xmlString = xmlString.concat("<targetArray>");
					for (int i = 0; i < this.getIncentive().getDirectMessage().getTarget().size(); i++) {
						xmlString = xmlString.concat("<message>"+this.getIncentive().getDirectMessage().getTarget().get(i)+"</message>");
					}
					xmlString = xmlString.concat("</targetArray>");

					xmlString = xmlString.concat("</directmessagegoal>");
				}		
			}

			if (this.getIncentive().getMonetary() != null) {
				xmlString = xmlString.concat("<monetary>");
				xmlString = xmlString.concat("<moneyMax>" + this.getIncentive().getMonetary().getMonetaryOptions().get("moneyMax") + "</moneyMax>");  //TODO Confirm the value remains after redesign of Monetary Incentive Code
				xmlString = xmlString.concat("<moneySubmissionAccepted>" + this.getIncentive().getMonetary().getMonetaryOptions().get("acceptedAmount")  + "</moneySubmissionAccepted>");
				xmlString = xmlString.concat("<paymentMethod>" + this.getIncentive().getMonetary().getMonetaryOptions().get("alertOption") + "</paymentMethod>");
				xmlString = xmlString.concat("<moneyinstructions>" + this.getIncentive().getMonetary().getMonetaryOptions().get("moneyInstructions") + "</moneyinstructions>"); 
				xmlString = xmlString.concat("</monetary>");
			}

			if (this.getIncentive().getPoint() != null) {
				xmlString = xmlString.concat("<point>");
				xmlString = xmlString.concat("<pointsStart>" + this.getIncentive().getPoint().getPointsOption().get("startPoints") + "</pointsStart>");
				xmlString = xmlString.concat("<pointsSubmission>" + this.getIncentive().getPoint().getPointsOption().get("submissionPoints") + "</pointsSubmission>");
				xmlString = xmlString.concat("<pointsLeader>" + this.getIncentive().getPoint().getPointsOption().get("leaderboardPoints") + "</pointsLeader>");
				xmlString = xmlString.concat("<pointsLevel>" + this.getIncentive().getPoint().getPointsOption().get("levelPoints") + "</pointsLevel>");
				xmlString = xmlString.concat("<pointsinstructions>"+this.getIncentive().getPoint().getPointsOption().get("instructions")+"</pointsinstructions>");
				xmlString = xmlString.concat("<levelmsg>"+this.getIncentive().getPoint().getPointsOption().get("newLevel")+"</levelmsg>");
				xmlString = xmlString.concat("</point>");
			}
			xmlString = xmlString.concat("</Incentives>");
		}
		xmlString = xmlString.concat("</Campaign>");
		xmlString = xmlString.concat("</toolkitxml>");
		return xmlString;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	//MAKE SURE WRITETOPARCEL AND CAMPAIGN(PARCEL SOURCE) READ AND WRITE VALUES ARE IN THE SAME ORDER
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Write new classes once implemented!!!
		dest.writeString(title);
		dest.writeString(purpose);
		dest.writeString(instructions);
		dest.writeValue(sensors);
		dest.writeValue(incentive);
		dest.writeValue(profile);
		dest.writeValue(candidate);
		dest.writeByte((byte)(openToPublic ? 1:0));

	}
	public Campaign(Parcel source){
		/*
		 * Reconstruct from the Parcel
		 */

		// TODO Read new classes once implemented!!!
		title = source.readString();
		purpose= source.readString();
		instructions = source.readString();
		sensors = (Sensor) source.readValue(Sensor.class.getClassLoader());
		incentive = (Incentive) source.readValue(Incentive.class.getClassLoader());
		profile = (Profile) source.readValue(Profile.class.getClassLoader());
		//participants = (ArrayList<User>) source.readSerializable();
		//source.readTypedList(participants, Campaign.CREATOR);
		candidate = (Candidate) source.readValue(Candidate.class.getClassLoader());
		openToPublic = source.readByte() == 1;
	}

	public static final Creator<Campaign> CREATOR = new Creator<Campaign>() {
		public Campaign createFromParcel(Parcel source) {
			return new Campaign(source);
		}


		public Campaign[] newArray(int arg0) {
			// TODO Auto-generated method stub
			return new Campaign[arg0];

		}
	};

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile prof) {
		this.profile = prof;
	}

	public void clearProfile() {
		this.profile = null;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public void clearCandidate() {
		this.candidate = null;
	}

	public void debugCampaign() {
		try {
			System.out.println("Title: " + this.title);
		} catch (Exception e) {

		}
		try {
			System.out.println("Instructions: " + this.instructions);
		} catch (Exception e) {

		}
		try {
			System.out.println("Purpose: " + this.purpose);
		} catch (Exception e) {

		}
		try {
			System.out.println("Start Time: " + this.startTime);
		} catch (Exception e) {

		}
		try {
			System.out.println("End Time: " + this.endTime);
		} catch (Exception e) {

		}
		// System.out.println("Min. # Part: "+Integer.toString(minNumberOfParticipants));
		// System.out.println("Target # sub.: "+Integer.toString(targetNumberOfSubmissions));
		try {
			System.out.println("status: " + this.status.toString());
		} catch (Exception e) {

		}
		// System.out.println("Example: "+example.toString());
		try {
			System.out.println("Sensors: "
					+ this.getSensors().getSensors().toString());
			System.out.println("Sensors Hashmap: "
					+ this.getSensors().getSensors().get("GPS"));
		} catch (Exception e) {

		}

		try {
			System.out.println("GPS: "
					+ this.getSensors().getGps().getOption().toString());
		} catch (Exception e1) {
		}

		try {
			System.out.println("Camera: "
					+ this.getSensors().getCamera().getAutoFocusOption().toString() + ", " + this.getSensors().getCamera().getResolutionOption().toString());
		} catch (Exception e1) {
		}

		try {
			System.out.println("Audio: "
					+ this.getSensors().getAudio().getCaptureOption().toString());
		} catch (Exception e1) {

		}

		try {
			System.out.println("Accelerometer: "
					+ this.getSensors().getAccelerometer().getCaptureOption().toString()+ ", " + this.getSensors().getAccelerometer().getModeOption().toString());
		} catch (Exception e1) {

		}

		try {
			System.out.println("Camcorder: "
					+ Integer.toString(this.getSensors().getCamcorder().getRecordLength()));
		} catch (Exception e1) {
		}

		try {
			Iterator<Question> itr = this.getSensors().getText().getListOfQuestions().iterator();
			while(itr.hasNext()) {
				Question element = (Question)itr.next();
				System.out.print("Text: "
						+ element.getQuestionText() + " ");
			}
			System.out.println();
		} catch (Exception e1) {
		}

		try {
			Iterator<User> itr = this.getCandidate().getParticipants().iterator();
			while (itr.hasNext()) {
				User user = (User)itr.next();
				System.out.print("Candidates: " + user.getName() + ", "+ user.getEmail());

			}
			System.out.println();
		} catch (Exception e) {
		}

		try {
			System.out.println("Profile: " + this.getProfile().getSelectedOptions().toString());
		} catch (Exception e) {
		}

		try {
			System.out.println("Gender: " + this.getProfile().getGender().toString());
		} catch (Exception e) {
		}

		try {
			System.out.println("Age Range: " + Integer.toString(this.getProfile().getMinAge()) + " to " + Integer.toString(this.getProfile().getMaxAge()));
		} catch (Exception e) {
		}

		try {
			System.out.println("Ethnicity: " + this.getProfile().getEthnicity().toString());
		} catch (Exception e) {
		}

		try {
			System.out.println("Region: " + this.getProfile().getMapCenter().toString() + ", " + this.getProfile().getRange());
		} catch (Exception e) {
		}

		try {
			System.out.println("Incentives: "
					+ this.getIncentive().getSelectedIncentive());
		} catch (Exception e) {
		}

		try {
			System.out.println("Campaign Organizer: "
					+ this.campaignOrganizer);
		} catch (Exception e) {
		}

		try {
			System.out.println("Access Permissions: "
					+ this.accessPermission);
		} catch (Exception e) {
		}
	}

	public String createFile() {
		File f = new File(Environment.getExternalStorageDirectory()+"/mcm/");
		f.mkdir();
		String filename = Environment.getExternalStorageDirectory()+ "/mcm/"+ String.valueOf(System.nanoTime())+ ".xml";
		File file = new File(filename);
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write(this.toXML());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filename;
	}
}




