package com.uncc.cci.toolkit;

import android.os.Parcel;
import android.os.Parcelable;

public class MyParcelable implements Parcelable {
    String name;
    boolean gps_toggle, audio_toggle, picture_toggle, video_toggle, light_toggle, motion_toggle, text_toggle;
    
    public int describeContents() {
        return 0;
    }
    
    public MyParcelable(String name, boolean gps_toggle, boolean audio_toggle, boolean picture_toggle, boolean video_toggle, boolean light_toggle, boolean motion_toggle, boolean text_toggle) {
        this.name=name;
        this.gps_toggle=gps_toggle;
        this.audio_toggle=audio_toggle;
        this.picture_toggle=picture_toggle;
        this.video_toggle=video_toggle;
        this.light_toggle=light_toggle;
        this.motion_toggle=motion_toggle;
        this.text_toggle=text_toggle;
    }

    public String getName() {
        return name;
    }
    
    public boolean getGPS() {
        return gps_toggle;
    }
    
    public boolean getAudio() {
        return audio_toggle;
    }
    
    public boolean getPicture() {
        return picture_toggle;
    }
    
    public boolean getVideo() {
        return video_toggle;
    }
    
    public boolean getLight() {
        return light_toggle;
    }
    
    public boolean getMotion() {
        return motion_toggle;
    }
    
    public boolean getText() {
        return text_toggle;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setGPS(boolean gps_toggle) {
        this.gps_toggle = gps_toggle;
    }
    
    public void setAudio(boolean audio_toggle) {
        this.audio_toggle = audio_toggle;
    }
    
    public void setPicture(boolean picture_toggle) {
        this.picture_toggle = picture_toggle;
    }
    
    public void setVideo(boolean video_toggle) {
        this.video_toggle = video_toggle;
    }
    
    public void setLight(boolean light_toggle) {
        this.light_toggle = light_toggle;
    }
    
    public void setMotion(boolean motion_toggle) {
        this.motion_toggle = motion_toggle;
    }
    
    public void setText(boolean text_toggle) {
        this.text_toggle = text_toggle;
    }
    
    
    public static final Parcelable.Creator<MyParcelable> CREATOR = new Parcelable.Creator<MyParcelable>() {
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };
    
    public MyParcelable() { ; };
    
    MyParcelable(MyParcelable in) {
       
    	name = in.getName();
        gps_toggle = in.getGPS();
        audio_toggle = in.getAudio();
        picture_toggle = in.getPicture();
        video_toggle = in.getVideo();
        light_toggle = in.getLight();
        motion_toggle = in.getMotion();
        text_toggle = in.getText();
    }

    public MyParcelable(Parcel in) {
		// TODO Auto-generated constructor stub
    	name = in.readString();
        gps_toggle = in.readInt() == 1;
        audio_toggle = in.readInt() == 1;
        picture_toggle = in.readInt() == 1;
        video_toggle = in.readInt() == 1;
        light_toggle = in.readInt() == 1;
        motion_toggle = in.readInt() == 1;
        text_toggle = in.readInt() == 1;
	}

	public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Name");
        sb.append("{name=").append(name);
        sb.append(", GPS='").append(gps_toggle ? "-set-" : "-unset-").append('\'');
        sb.append(", Audio='").append(audio_toggle ? "-set-" : "-unset-").append('\'');
        sb.append(", Picture='").append(picture_toggle ? "-set-" : "-unset-").append('\'');
        sb.append(", Video=").append(video_toggle ? "-set-" : "-unset-").append('\'');
        sb.append(", Light='").append(light_toggle ? "-set-" : "-unset-").append('\'');
        sb.append(", Motion='").append(motion_toggle ? "-set-":"-unset-").append('\'');
        sb.append(", Text='").append(text_toggle ? "-set-":"-unset-").append('\'');
        sb.append('}');
        return sb.toString();
    }
    
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(gps_toggle ? 1 : 0);
		dest.writeInt(audio_toggle ? 1 : 0);
		dest.writeInt(picture_toggle ? 1 : 0); 
		dest.writeInt(video_toggle ? 1 : 0);
		dest.writeInt(light_toggle ? 1 : 0);
		dest.writeInt(motion_toggle ? 1 : 0); 
		dest.writeInt(text_toggle ? 1 : 0);
	}

	
} 