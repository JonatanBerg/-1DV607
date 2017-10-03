package workshop2;

import java.io.File;
import java.io.IOException;

import workshop2.controller.BoatController;
import workshop2.controller.DisplayController;
import workshop2.controller.MemberController;
import workshop2.view.DisplayView;

public class ProgramMain {
	
	public static void main(String args[]) throws IOException {
		File file = new File("members.txt");
		file.createNewFile(); 
		file = new File("boats.txt");
		file.createNewFile(); // if file already exists will do nothing 
		
		DisplayView view = new DisplayView();
		MemberController memberController = new MemberController();
		BoatController boatController = new BoatController();
		DisplayController displayController = new DisplayController(view, memberController, boatController);
		
		displayController.startDisplaying();
	}
}
