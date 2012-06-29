package datastructures;

import java.io.Serializable;
import java.util.ArrayList;

public class DirectMessageIncentive  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2764452023523896195L;


	int type;
	ArrayList<String> join = new ArrayList<String> ();
	ArrayList<String> idle = new ArrayList<String> ();
	ArrayList<String> motiv = new ArrayList<String> ();
	ArrayList<String> target = new ArrayList<String> ();


	public DirectMessageIncentive(int type){
		this.type = type;
		this.setJoin(new ArrayList<String> ());
	}
	public boolean isEmpty() {
		if (type == 0) {
			if (join.isEmpty() || idle.isEmpty() || motiv.isEmpty())
				return true;
			else
				return false;
		}
		else if (type == 1) {
			if (join.isEmpty() || idle.isEmpty() || motiv.isEmpty() || target.isEmpty())
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public ArrayList<String> getJoin() {
		return join;
	}
	public ArrayList<String> getIdle() {
		return idle;
	}
	public ArrayList<String> getMotiv() {
		return motiv;
	}
	public ArrayList<String> getTarget() {
		return target;
	}
	public void setJoin(ArrayList<String> join) {
		this.join = join;
	}
	public void setIdle(ArrayList<String> idle) {
		this.idle = idle;
	}
	public void setMotiv(ArrayList<String> motiv) {
		this.motiv = motiv;
	}
	public void setTarget(ArrayList<String> target) {
		this.target = target;
	}

	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	

}
