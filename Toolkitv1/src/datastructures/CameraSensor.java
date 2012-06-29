package datastructures;

import java.io.Serializable;

public class CameraSensor  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4711792893544179781L;
	String autoFocusOption;
	String resolutionOption;
	
	/*private String cameraOptions[] = {"Set Resolution","Use Auto-Focus"};
	private String resolutionOptions[] = {"Use Highest Resolution", "Use Lowest Resolution"};
	*/
	
	private String autoFocusOptions[] = {"No Auto Focus","Auto Focus"};
	private String resolutionOptions[] = {"Use Highest Resolution", "Use Lowest Resolution"};
	public CameraSensor() {};
	
	public CameraSensor(String autofocusopt, String resopt ){
		 this.autoFocusOption = autofocusopt;
		 this.resolutionOption = resopt;
	 }

	public String getAutoFocusOption() {
		return autoFocusOption;
	}

	public void setAutoFocusOption(String autoFocusOption) {
		this.autoFocusOption = autoFocusOption;
	}

	public String getResolutionOption() {
		return resolutionOption;
	}

	public void setResolutionOption(String resolutionOption) {
		this.resolutionOption = resolutionOption;
	}

	public String[] getAutoFocusOptions() {
		return autoFocusOptions;
	}

	public void setAutoFocusOptions(String[] autoFocusOptions) {
		this.autoFocusOptions = autoFocusOptions;
	}

	public String[] getResolutionOptions() {
		return resolutionOptions;
	}

	public void setResolutionOptions(String[] resolutionOptions) {
		this.resolutionOptions = resolutionOptions;
	}
	
}
