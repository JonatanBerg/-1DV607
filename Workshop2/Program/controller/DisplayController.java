package workshop2.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import workshop2.model.Boat;
import workshop2.model.Member;
import workshop2.view.DisplayView;

public class DisplayController {
	private DisplayView view;
	private MemberController memberController;
	private BoatController boatController;
	
	public DisplayController(DisplayView view, MemberController memberController, BoatController boatController) {
		this.view = view;
		this.memberController = memberController;
		this.boatController = boatController;
	}
	
	public void startDisplaying() {
		view.showMessage("Welcome to the boat club!");
		int choice = -1;
		
		while(choice != 0) {
			view.showMenu();
			try {
				choice = getIntInput();
			} catch (InputMismatchException e) {
				view.showMessage("Error: Enter a number between 0 and 9");
			}
			switch(choice) {
				case 1:
					// Show a compact list of the members
					DisplayCompactList();
					break;
				case 2:
					// Show a verbose list of members
					DisplayVerboseList();
					break;
				case 3:
					// Register a new member
					DisplayAddMember();
					break;
				case 4:
					// Delete a member
					DisplayDeleteMember();
					break;
				case 5:
					// Edit a member
					DisplayEditMember();
					break;
				case 6:
					// View a member
					DisplayMember();
					break;
				case 7:
					// Register a new boat
					DisplayAddBoat();
					break;
				case 8:
					// Delete a boat
					DisplayDeleteBoat();
					break;
				case 9:
					// Edit a boat
					DisplayEditBoat();
					break;
				case 0: 
					view.showMessage("\nClosing the program...");
				default: 
					break;
			}
		}
	}
	
	private void DisplayCompactList() {
		ArrayList<Member> members = memberController.getMembers();
		
		view.showMessage("\nCompact list of members:");
		view.showMessage("\n==============================");
		for(int i = 0; i < members.size(); i++) {
			view.showMessage("\nName: " + members.get(i).getName());
			view.showMessage("\nMemberID: " + members.get(i).getMemberId());
			view.showMessage("\nNumber of boats: " + boatController.getBoatsByMember(members.get(i).getName(), members).size());
			view.showMessage("\n");
		}
	}
	
	private void DisplayVerboseList() {
		ArrayList<Member> members = memberController.getMembers();
		
		view.showMessage("\nVerbose list of members:");
		view.showMessage("\n==============================");
		for(int i = 0; i < members.size(); i++) {
			view.showMessage("\nName: " + members.get(i).getName());
			view.showMessage("\nPersonal number: " + members.get(i).getPersonalNr());
			view.showMessage("\nMemberID: " + members.get(i).getMemberId());
			
			ArrayList<Boat> ownedBoats = boatController.getBoatsByMember(members.get(i).getName(), members);
			
			if(ownedBoats.size() > 0 ) {
				view.showMessage("\n\nOwned boats: ");
				for(int j = 0; j < ownedBoats.size(); j++) {
					view.showMessage("\nLength: " + ownedBoats.get(j).getLength());
					view.showMessage("\nType: " + ownedBoats.get(j).getType());
				}
			} else {
				view.showMessage("\nNumber of boats: 0");
			}
			view.showMessage("\n==============================");
		}
	}
	
	private void DisplayAddMember() {
		view.showMessage("\nRegister a new member: ");
		view.showMessage("\nName: ");
		String name = getStringInput();
		view.showMessage("Personal number: ");
		String personalNr = getStringInput();
		
		memberController.addNewMember(name, personalNr);
	}
	
	private void DisplayDeleteMember() {
		view.showMessage("\nRemove a member: ");
		view.showMessage("\nName: ");
		String name = getStringInput();
		
		memberController.deleteMember(name, boatController);
	}
	
	private void DisplayEditMember() {
		view.showMessage("\nEdit a member: ");
		view.showMessage("\nName: ");
		String name = getStringInput();
		
		try {
			memberController.editMember(name, this.view);
		} catch (NoSuchElementException e) {
			view.showMessage("Error: No such member found");
		}
	}
	
	private void DisplayMember() {
		view.showMessage("\nView a member: ");
		view.showMessage("\nName: ");
		String name = getStringInput();
		
		try {
			memberController.displayMember(name, this.view);
		} catch (NoSuchElementException e) {
			view.showMessage("Error: No such member found");
		}
	}
	
	private void DisplayAddBoat() {
		int length = -1;
		
		view.showMessage("\nRegister a new boat: ");
		view.showMessage("\nName of owner: ");
		String name = getStringInput();
		view.showMessage("Length of boat: ");
		try {
			 length = getIntInput();
		} catch(InputMismatchException e) {
			view.showMessage("Invalid length");
		}
		if(length > 0) {
			view.showMessage("Type of boat: ");
			String type = getStringInput();
			
			try {
				boatController.addNewBoat(name, length, type, memberController.getMembers());
			} catch (NoSuchElementException e) {
				view.showMessage("Error: No member with the name \"" + e.getMessage() + "\" found");
			} 
		}
	}
	
	private void DisplayDeleteBoat() {
		view.showMessage("\nRemove a boat ");
		view.showMessage("\nName of owner: ");
		String name = getStringInput();
		
		try {
			boatController.deleteBoat(name, memberController.getMembers(), view);
		} catch (NoSuchElementException e) {
			view.showMessage("Error: No boat owner with the name \"" + e.getMessage() + "\" found");
		} catch(NullPointerException e) {
			view.showMessage(e.getMessage());
		} 
	}
	
	private void DisplayEditBoat() {
		view.showMessage("\nEdit a boat ");
		view.showMessage("\nName of owner: ");
		String name = getStringInput();
		
		try {
			boatController.editBoat(name, memberController.getMembers(), view);
		} catch(InputMismatchException e) {
			view.showMessage("Invalid number");
		} catch (NoSuchElementException e) {
			view.showMessage("Error: No boat owner with the name \"" + e.getMessage() + "\" found");
		} catch(NullPointerException e) {
			view.showMessage(e.getMessage());
		} 
	}
	
	private int getIntInput() throws InputMismatchException {
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		
		return input;
	}
	
	private String getStringInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();

		return input;
	}
}
