package workshop2.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import workshop2.model.Boat.BoatType;

public class Registry {
	private ArrayList<Member> members = new ArrayList<Member>();
	
	public Registry() {
		getSavedMembers();
	}
	
	// Adds a new member and calculates what memberId the new member should get based on the memberId of the current last member
	public void addNewMember(String name, String personalNr) {
		if(members.size() != 0) {
			members.add(new Member(name, personalNr, members.get(members.size() - 1).getMemberId() + 1));
		} else {
			members.add(new Member(name, personalNr, 1));
		}
		saveMembersToFile();
	}
	
	// Deletes a member
	public void deleteMember(int memberId) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getMemberId() == memberId) {
				members.remove(i);
			}
		}
		saveMembersToFile();
	}
	
	// Edits a member
	public void editMember(int memberId, String newName, String newPersonalNr) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getMemberId() == memberId) {
				members.get(i).setName(newName);
				members.get(i).setPersonalNr(newPersonalNr);
			}
		}
		saveMembersToFile();
	}
	
	// Returns a member based on a memberId
	public Member getMember(int memberId) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getMemberId() == memberId) {
				return members.get(i);
			}
		}
		throw new NoSuchElementException();
	}
	
	public Iterable<Member> getMembers() {
		return members;
	}
	
	// Returns a memberId based on a name (doesn't work perfectly if there's more than 1 with the same name)
	public int getMemberIdFromName(String name) {
		int memberId = -1;
		
		for(Member member : members) {
			if(member.getName().equals(name)) {
				memberId = member.getMemberId();
				return memberId;
			}
		}
		return memberId;
	}
	
	// Returns a name based on a memberId 
	public String getNameFromMemberId(int memberId) {
		String name = "";

		for(Member member : members) {
			if(member.getMemberId() == memberId) {
				name = member.getName();
				return name;
			}
		}
		return name;
	}

	// Adds a new boat to a certain member
	public void addNewBoat(int memberId, String type, int length) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getMemberId() == memberId) {
				members.get(i).addBoat(length, validateType(type));
			}
		}
		saveMembersToFile();
	}
	
	// Deletes a boat
	public void deleteBoat(int memberId, int boatNumber) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getMemberId() == memberId) {
				members.get(i).removeBoat(boatNumber);
			}
		}
		saveMembersToFile();
	}
	
	// Removes all boats owned by a member
	public void deleteBoatsByMemberId(int memberId) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getMemberId() == memberId) {
				for(int j = members.get(i).getAmountBoats(); j >= 0; j--) {
					members.get(i).removeBoat(j);
				}
			}
		}
		saveMembersToFile();
	}
	
	// Edits a boat
	public void editBoat(int memberId, int boatNumber, int newLength, String newType) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getMemberId() == memberId) {
				members.get(i).editBoat(boatNumber, newLength, validateType(newType));
			}
		}
		saveMembersToFile();
	}

	// Returns true if there's a member with the specified name, false otherwise
	public boolean userExists(String name) {
		boolean found = false;
		
		for(Member member : members) {
			if(member.getName().equals(name)) {
				found = true;
				return found;
			}
		}
		return found;
	}
	
	// Returns true if a boat exists specified by owner name and boat number, false otherwise
	public boolean boatExists(String name, int boatNumber) {
		boolean found = false;
		
		if(boatNumber < 0) {
			return false;
		}
		
		for(Member member : members) {
			if(member.getName().equals(name)) {
				if(member.getAmountBoats() > boatNumber) {
					found = true;
					return  found;
				}
			}
		}
		return found;
	}

	// Returns the corresponding boat type based on a string, return BoatType.Other if no other matches
	// It may not be correct to validate this in the Registry model, but couldn't come up with where it would be better located
	private BoatType validateType(String type) {
		if(type.toLowerCase().equals(BoatType.Canoe.toString().toLowerCase())) {
			return BoatType.Canoe;
		} else if(type.toLowerCase().equals(BoatType.Motorsailer.toString().toLowerCase()) ) {
			return BoatType.Motorsailer;
		} else if(type.toLowerCase().equals(BoatType.Sailboat.toString().toLowerCase())) {
			return BoatType.Sailboat;
		} else {
			return BoatType.Other;
		}
	}

	// Saves all the current member information to a text file
	private void saveMembersToFile() {
		PrintWriter out = null;
		String filePath = "members.txt";
		
		try {
			out = new PrintWriter(filePath);
			
			for(Member member : this.members) {
				out.println(member.getName());
				out.println(member.getPersonalNr());
				out.println(member.getMemberId());
				out.println(member.getAmountBoats());
				for(Boat boat : member.getBoats()) {
					out.println(boat.getType());
					out.println(boat.getLength());
				}
			}
			// Probably not ok/ideal to print anything from here, but program won't function if the file doesn't open so I decided it's ok this time
		}  catch(FileNotFoundException e) {
			System.out.println("File \"" + filePath + "\" not found");
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}

	// Gets all member information from text file and saves it to the members ArrayList
	private void getSavedMembers() {
		BufferedReader reader = null;
		String filePath = "members.txt";
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			int counter = 0;
			String name = "";
			String personalNr = "";
			int memberId = 0;
			int amountBoats = 0;

			// Checks each line in the text file for certain information about each member and then adds the member to the members ArrayList
			while (line != null) {
				if(counter == 0) {
					name = line;
					counter++;
				} else if(counter == 1) {
					personalNr = line;
					counter++;
				} else if(counter == 2) {
					memberId = Integer.parseInt(line);
					members.add(new Member(name, personalNr, memberId));
					counter++;
				} else if(counter == 3) {
					// Adds all boats owned by the current member
					amountBoats = Integer.parseInt(line);
					for(int i = 0; i < amountBoats; i++) {
						line = reader.readLine();
						String boatType = line;
						line = reader.readLine();
						int boatLength = Integer.parseInt(line);
						members.get(members.size() - 1).addBoat(boatLength, validateType(boatType));
					}
					counter = 0;
				}
				line = reader.readLine();
			}
		// Probably not ok/ideal to print anything from here, but program won't function if the file doesn't open so I decided it's ok this time
		} catch(FileNotFoundException e) {
			System.out.println("File \"" + filePath + "\" not found");
		} catch(IOException e) {
			System.out.println("I/O error occured");
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
