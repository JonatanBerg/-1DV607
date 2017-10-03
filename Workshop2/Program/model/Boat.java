package workshop2.model;

public class Boat {
	private int memberId;
	private int length;
	private String type;
	
	public Boat(int memberId, int length, String type) {
		this.memberId = memberId;
		this.length = length;
		
		if(type.equals("Sailboat") || type.equals("Motorsailer") || type.equals("Canoe")) {
			this.type = type;
		} else {
			this.type = "Other";
		}
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
