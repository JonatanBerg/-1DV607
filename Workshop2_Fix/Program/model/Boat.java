package workshop2.model;

public class Boat {
	private int length;
	private BoatType type;
	
	public enum BoatType {
		Canoe,
		Motorsailer,
		Sailboat,
		Other
	}
	
	public Boat(int length, BoatType type) {
		this.length = length;
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public BoatType getType() {
		return type;
	}

	public void setType(BoatType type) {
		this.type = type;
	}
}
