package workshop2;

import java.io.File;
import java.io.IOException;

import workshop2.controller.DisplayController;
import workshop2.model.Registry;
import workshop2.view.DisplayView;

public class ProgramMain {
	
	public static void main(String args[]) throws IOException {
		// Creates members.txt if it doesn't exist
		File file = new File("members.txt");
		file.createNewFile(); 
		
		DisplayView view = new DisplayView();
		Registry registry = new Registry();
		DisplayController displayController = new DisplayController(view, registry);
		
		displayController.startDisplaying();
	}
}
