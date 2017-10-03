package workshop2.model;

public class Member {
	private String name;
	private String personalNr;
	private int memberId;
	
	public Member() {
		
	}
	
	public Member(String name, String personalNr, int memberId) {
		this.name = name;
		this.personalNr = personalNr;
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonalNr() {
		return personalNr;
	}

	public void setPersonalNr(String personalNr) {
		this.personalNr = personalNr;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

}
