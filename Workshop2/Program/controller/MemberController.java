package workshop2.controller;

import workshop2.model.Member;
import workshop2.view.DisplayView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MemberController {
	private ArrayList<Member> members = new ArrayList<Member>();
	
	public MemberController() {
		members = getSavedMembers();
	}
	
	public void addNewMember(String name, String personalNr) {
		if(members.size() != 0) {
			members.add(new Member(name, personalNr, members.get(members.size() - 1).getMemberId() + 1));
		} else {
			members.add(new Member(name, personalNr, 1));
		}
		
		saveMembersToFile();
	}
	
	public void deleteMember(String name, BoatController boatController) {
		boolean memberFound = false;
		int memberId = 0;
		
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getName().equals(name)) {
				memberId = members.get(i).getMemberId();
				members.remove(i);
				memberFound = true;
			}
		}
		
		if(memberFound) {
			boatController.deleteBoatsByMemberId(memberId);
		}
		
		saveMembersToFile();
	}
	
	public void editMember(String name, DisplayView view) {
		boolean found = false;
		int index = 0;
		
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getName().equals(name)) {
				index = i;
				found = true;
			}
		}
		
		if(found == false) {
			throw new NoSuchElementException();
		} 
		
		Scanner in = new Scanner(System.in);
		view.showMessage("New name: ");
		String newName = in.nextLine();
		view.showMessage("New personal number: ");
		String newPersonalNr = in.nextLine();
		
		members.get(index).setName(newName);
		members.get(index).setPersonalNr(newPersonalNr);
		
		saveMembersToFile();
	}
	
	public void displayMember(String name, DisplayView view) {
		boolean found = false;
		int index = 0;
		
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getName().equals(name)) {
				index = i;
				found = true;
			}
		}
		
		if(!found) {
			throw new NoSuchElementException();
		} 
		
		view.showMessage("\nName: " + members.get(index).getName());
		view.showMessage("\nPersonal number: " + members.get(index).getPersonalNr());
		view.showMessage("\nMember ID: " + members.get(index).getMemberId());
	}
	
	private void saveMembersToFile() {
		PrintWriter out = null;
		String filePath = "members.txt";
		
		try {
			out = new PrintWriter(filePath);
			
			for(Member member : this.members) {
				out.println(member.getName());
				out.println(member.getPersonalNr());
				out.println(member.getMemberId());
			}
		}  catch(FileNotFoundException e) {
			System.out.println("File \"" + filePath + "\" not found");
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	private ArrayList<Member> getSavedMembers() {
		ArrayList<Member> members = new ArrayList<Member>();
		BufferedReader reader = null;
		String filePath = "members.txt";
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			int counter = 0;
			String name = "";
			String personalNr = "";
			int memberId = 0;

			while (line != null) {
				if(counter == 0) {
					name = line;
					counter++;
				} else if(counter == 1) {
					personalNr = line;
					counter++;
				} else if(counter == 2) {
					memberId = Integer.parseInt(line);
					counter = 0;
					members.add(new Member(name, personalNr, memberId));
				}
				line = reader.readLine();
			}
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
		return members;
	}
	
	public ArrayList<Member> getMembers() {
		return members;
	}
}

