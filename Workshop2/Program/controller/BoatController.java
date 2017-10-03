package workshop2.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import workshop2.model.Boat;
import workshop2.model.Member;
import workshop2.view.DisplayView;

public class BoatController {
	private ArrayList<Boat> boats = new ArrayList<Boat>();
	
	public BoatController() {
		boats = getSavedBoats();
	}
	
	public void addNewBoat(String name, int length, String type, ArrayList<Member> members) throws InputMismatchException {
		boolean memberFound = false;
		int index = 0;
		
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getName().equals(name)) {
				index = i;
				memberFound = true;
			}
		}
		
		if(!memberFound) {
			throw new NoSuchElementException(name);
		}
		
		boats.add(new Boat(members.get(index).getMemberId(), length, type));
		
		saveBoatsToFile();
	}
	
	public void deleteBoat(String name, ArrayList<Member> members, DisplayView view) {
		ArrayList<Boat> matchingBoats = getBoatsByMember(name, members);
		Boat deleteBoat = null;
		
		if(matchingBoats.size() == 0) {
			throw new NoSuchElementException(name);
		}
		
		view.showMessage("\nWhat boat do you want to delete?");
		for(int i = 0; i < matchingBoats.size(); i++) {
			view.showMessage("\n" + (i + 1) + ". Length: " + matchingBoats.get(i).getLength() + "   Type: " + matchingBoats.get(i).getType());
		}
		view.showMessage("\nYour choice: ");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		
		if(choice > 0 && choice <= matchingBoats.size()) {
			deleteBoat = matchingBoats.get(choice - 1);
		} else {
			throw new NullPointerException("Invalid number");
		}
		
		for(int i = 0; i < boats.size(); i++) {
			if(boats.get(i).getMemberId() == deleteBoat.getMemberId() 
					&& boats.get(i).getLength() == deleteBoat.getLength() 
						&& boats.get(i).getType().equals(deleteBoat.getType())) {
				boats.remove(i);
			}
		}
		
		saveBoatsToFile();
	}
	
	public void deleteBoatsByMemberId(int memberId) {
		ArrayList<Integer> boatsToRemove = new ArrayList<Integer>();
		this.boats = getSavedBoats();
		
		for(int i = 0; i < boats.size(); i++) {
			if(boats.get(i).getMemberId() == memberId) {
				boatsToRemove.add(i);
			}
		}
		
		for(int i = boatsToRemove.size() - 1; i >= 0; i--) {
			this.boats.remove(boatsToRemove.get(i).intValue());
		}

		saveBoatsToFile();
	}
	
	public void editBoat(String name, ArrayList<Member> members, DisplayView view) throws InputMismatchException {
		ArrayList<Boat> matchingBoats = getBoatsByMember(name, members);
		Boat editBoat = null;
		
		if(matchingBoats.size() == 0) {
			throw new NoSuchElementException(name);
		}
		
		view.showMessage("\nWhat boat do you want to edit?");
		for(int i = 0; i < matchingBoats.size(); i++) {
			view.showMessage("\n" + (i + 1) + ". Length: " + matchingBoats.get(i).getLength() + "   Type: " + matchingBoats.get(i).getType());
		}
		view.showMessage("\nYour choice: ");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		
		if(choice > 0 && choice <= matchingBoats.size()) {
			editBoat = matchingBoats.get(choice - 1);
		} else {
			throw new NullPointerException("Invalid number");
		}
		
		for(int i = 0; i < boats.size(); i++) {
			if(boats.get(i).getMemberId() == editBoat.getMemberId() 
					&& boats.get(i).getLength() == editBoat.getLength() 
						&& boats.get(i).getType().equals(editBoat.getType())) {
				boats.remove(i);
			}
		}
		
		view.showMessage("New length: ");
		int newLength = in.nextInt();
		in.nextLine();
		view.showMessage("New type: ");
		String newType = in.nextLine();
		
		boats.add(new Boat(editBoat.getMemberId(), newLength, newType));
		
		saveBoatsToFile();
	}
	
	
	public ArrayList<Boat> getSavedBoats() {
		ArrayList<Boat> boats = new ArrayList<Boat>();
		BufferedReader reader = null;
		String filePath = "boats.txt";
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();

			int counter = 0;
			int memberId = 0;
			int length = 0;
			String type = "";		

			while (line != null) {
				if(counter == 0) {
					memberId = Integer.parseInt(line);
					counter++;
				} else if(counter == 1) {
					length = Integer.parseInt(line);
					counter++;
				} else if(counter == 2) {
					type = line;
					counter = 0;
					boats.add(new Boat(memberId, length, type));
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
		return boats;
	}
	
	public ArrayList<Boat> getBoatsByMember(String name, ArrayList<Member> members) {
		ArrayList<Boat> allBoats = getSavedBoats();
		ArrayList<Boat> matchingBoats = new ArrayList<Boat>();
		
		for(int i = 0; i < allBoats.size(); i++) {
			for(int j = 0; j < members.size(); j++) {
				if(allBoats.get(i).getMemberId() == members.get(j).getMemberId() && name.equals(members.get(j).getName())) {
					matchingBoats.add(allBoats.get(i));
				}
			}
		}
		
		return matchingBoats;
	}
	
	private void saveBoatsToFile() {
		PrintWriter out = null;
		String filePath = "boats.txt";
		
		try {
			out = new PrintWriter(filePath);
			
			for(Boat boat : this.boats) {
				out.println(boat.getMemberId());
				out.println(boat.getLength());
				out.println(boat.getType());
			}
		}  catch(FileNotFoundException e) {
			System.out.println("File \"" + filePath + "\" not found");
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
}
