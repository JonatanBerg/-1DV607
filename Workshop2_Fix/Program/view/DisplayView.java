package workshop2.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import workshop2.model.Boat;
import workshop2.model.Member;

public class DisplayView {

	public void showMenu() {
		System.out.println("\nChoose an option: ");
		System.out.println("1. Show a compact list of the members");
		System.out.println("2. Show a verbose list of the members");
		System.out.println("3. Register a new member");
		System.out.println("4. Delete a member");
		System.out.println("5. Edit a member");
		System.out.println("6. View a member");
		System.out.println("7. Register a new boat");
		System.out.println("8. Delete a boat");
		System.out.println("9. Edit a boat");
		System.out.println("0. Quit");
		
		System.out.print("\nYour choice: ");
	}
	
	public void displayCompactList(Iterable<Member> members) {
		System.out.println("\nCompact list of members:");
		System.out.println("==============================");
		
		for(Member member : members) {
			System.out.println("\nName: " + member.getName());
			System.out.println("MemberID: " + member.getMemberId());
			System.out.println("Number of boats: " + member.getAmountBoats());
		}
	}
	
	public void displayVerboseList(Iterable<Member> members) {
		System.out.println("\nVerbose list of members:");
		System.out.println("==============================");
		
		for(Member member : members) {
			System.out.println("\nName: " + member.getName());
			System.out.println("Personal number: " + member.getPersonalNr());
			System.out.println("MemberID: " + member.getMemberId());
			
			System.out.println("\nOwned boats: ");
			if(member.getAmountBoats() == 0) {
				System.out.println("None");
			} else {
				for(Boat boat : member.getBoats()) {
					System.out.println("Length: " + boat.getLength());
					System.out.println("Type: " + boat.getType());
				}
			}
			System.out.println("\n==============================");
		}
	}
	
	public String[] displayAddMember() {
		String[] input = new String[2];
		
		System.out.println("\nRegister a new member: ");
		System.out.println("==============================");
		System.out.println("\nName: ");
		input[0] = getStringInput();
		 
		System.out.println("Personal number: ");
		input[1] = getStringInput();
		
		return input;
	}
	
	public String displayDeleteMember() {
		System.out.println("\nRemove a member: ");
		System.out.println("==============================");
		System.out.println("\nName: ");
		String name = getStringInput();
		
		return name;
	}
	
	public String[] displayEditMember() {
		System.out.println("\nEdit a member: ");
		System.out.println("==============================");
		System.out.println("\nName: ");
		String name = getStringInput();
		
		System.out.println("\nNew name: ");
		String newName = getStringInput();
		
		System.out.println("\nNew personal number: ");
		String newPersonalNr = getStringInput();
		
		String[] input = {name, newName, newPersonalNr};
		return input;
	}
	
	public String displayMember() {
		System.out.println("\nView a member: ");
		System.out.println("==============================");
		System.out.println("\nName: ");
		String name = getStringInput();
		
		return name;
	}
	
	public String[] displayAddBoat() {
		System.out.println("\nRegister a new boat: ");
		System.out.println("==============================");
		System.out.println("\nName of owner: ");
		String name = getStringInput();
		
		System.out.println("\nLength of boat: ");
		int length = getIntInput();
		System.out.println("\nType of boat: ");
		String type = getStringInput();
		
		String[] input = {name, type, Integer.toString(length)};
		return input;	
	}
	
	public String[] displayDeleteBoat(Iterable<Member> members) {
		int boatToDelete = -1;
		
		System.out.println("\nRemove a boat: ");
		System.out.println("==============================");
		System.out.println("\nName of owner: ");
		String name = getStringInput();
		
		for(Member member : members) {
			if(member.getName().equals(name)) {
				if(member.getAmountBoats() == 0) {
					System.out.println("This member owns no boats");
				} else {
					System.out.println("\nWhat boat do you want to delete?");
					int i = 0;
					for(Boat boat : member.getBoats()) {
						System.out.println(i + ". Type: " + boat.getType() + "   Length: " + boat.getLength());
						i++;
					}
					System.out.print("\nYour choice: ");
					boatToDelete = getIntInput();
				}
			}
		}
		
		String[] input = {name, Integer.toString(boatToDelete)};
		return input;
	}
	
	public String[] displayEditBoat(Iterable<Member> members) {
		int boatToEdit = -1;
		int memberId = -1;
		
		System.out.println("\nEdit a boat: ");
		System.out.println("==============================");
		System.out.println("\nName of owner: ");
		String name = getStringInput();
		
		// Shows all boats owned by the named member
		for(Member member : members) {
			if(member.getName().equals(name)) {
				memberId = member.getMemberId();
				if(member.getAmountBoats() == 0) {
					System.out.println("This member owns no boats");
				} else {
					System.out.println("\nWhat boat do you want to edit?");
					int i = 0;
					for(Boat boat : member.getBoats()) {
						System.out.println(i + ". Type: " + boat.getType() + "   Length: " + boat.getLength());
						i++;
					}
					System.out.print("\nYour choice: ");
					boatToEdit = getIntInput();
				}
			}
		}
	
		System.out.println("\nNew length: ");
		int newLength = getIntInput();
		System.out.println("\nNew type: ");
		String newType = getStringInput();
		
		String[] input = {Integer.toString(memberId), Integer.toString(boatToEdit), Integer.toString(newLength), newType};
		return input;
	}
	
	public void displayChosenMember(Member member) {
		System.out.println("\nName: " + member.getName());
		System.out.println("Personal number: " + member.getPersonalNr());
		System.out.println("Member ID: " + member.getMemberId());
		System.out.println("\nBoats:");
		
		if(member.getAmountBoats() == 0) {
			System.out.println("None");
		} else {
			int i = 0;
			for(Boat boat : member.getBoats()) {
				System.out.println(i + ". Type: " + boat.getType() + "   Length: " + boat.getLength());
				i++;
			}
		}
	}
	
	public void displayMissingUserError(String name) {
		System.out.println("No user with the name " + name + " could be found");
	}

	public void displayIncorrectLengthError(int length) {
		System.out.println("Incorrect length of the boat: " + length);
	}
	
	public void displayInvalidChoiceError() {
		System.out.println("Invalid choice");
	}
	
	public void displayMissingBoatError(String name, int boatNumber) {
		System.out.println("The user '" + name + "' has no boat with he number " + boatNumber);
	}
	
	public void displayWelcome() {
		System.out.println("Welcome to the boat club!");
	}
	
	public void displayEndMessage() {
		System.out.println("\nClosing the program...");
	}
	
	public String getStringInput() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		String input = scan.nextLine();
		return input;
	}
	
	public int getIntInput(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int input = -1;
		
		try {
			input = scan.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Invalid input");
		}

		return input;
	}
}
