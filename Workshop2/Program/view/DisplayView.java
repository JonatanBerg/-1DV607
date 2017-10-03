package workshop2.view;


public class DisplayView {
	
	public void showMenu() {
		System.out.println("\n\nChoose an option: ");
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
	
	public void showMessage(String message) {
		System.out.print(message);
	}
}
