package workshop2.model;

import java.util.ArrayList;

import workshop2.model.Boat.BoatType;

public class Member {
	private String name;
	private String personalNr;
	private int memberId;
	private ArrayList<Boat> boats = new ArrayList<Boat>();
	
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
	
	public int getAmountBoats() {
		return boats.size();
	}
	
	public Iterable<Boat> getBoats() {
		return boats;
	}
	
	public void addBoat(int length, BoatType type) {
		boats.add(new Boat(length, type));
	}
	
	public void removeBoat(int boatNumber) {
		boats.remove(boatNumber);
	}
	
	public void editBoat(int boatNumber, int newLength, BoatType newType) {
		boats.get(boatNumber).setLength(newLength);
		boats.get(boatNumber).setType(newType);
	}
}
