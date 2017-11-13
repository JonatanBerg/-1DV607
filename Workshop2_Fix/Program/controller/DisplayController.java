package workshop2.controller;

import workshop2.model.Registry;
import workshop2.view.DisplayView;

public class DisplayController {
	private DisplayView view;
	private Registry registry;
	
	public DisplayController(DisplayView view, Registry registry) {
		this.view = view;
		this.registry = registry;
	}
	
	public void startDisplaying() {
		view.displayWelcome();
		int choice = -1;
		
		while(choice != 0) {
			view.showMenu();
			choice = view.getIntInput();

			switch(choice) {
				case 1:
					// Show a compact list of the members
					view.displayCompactList(registry.getMembers());
					break;
				case 2:
					// Show a verbose list of members
					view.displayVerboseList(registry.getMembers());
					break;
				case 3:
					// Register a new member
					String[] addMemberInfo = view.displayAddMember(); // [0] = name, [1] = personalNr
					registry.addNewMember(addMemberInfo[0], addMemberInfo[1]); 
					break;
				case 4:
					// Delete a member
					String deleteName = view.displayDeleteMember();
					if(registry.userExists(deleteName)) {
						registry.deleteMember(registry.getMemberIdFromName(deleteName));
					} else {
						view.displayMissingUserError(deleteName);
					}
					break;
				case 5:
					// Edit a member
					String[] editNameInfo = view.displayEditMember(); // [0] = old name, [1] = new name, [2] new personalNr
					if(registry.userExists(editNameInfo[0])) {
						registry.editMember(registry.getMemberIdFromName(editNameInfo[0]), editNameInfo[1], editNameInfo[2]); 
					} else {
						view.displayMissingUserError(editNameInfo[0]);
					}
					break;
				case 6:
					// View a member
					String viewMember = view.displayMember();
					if(registry.userExists(viewMember)) {
						view.displayChosenMember(registry.getMember(registry.getMemberIdFromName(viewMember)));
					} else {
						view.displayMissingUserError(viewMember);
					}
					break;
				case 7:
					// Register a new boat
					String[] addBoatInfo = view.displayAddBoat();  // [0] = owner name, [1] = type, [2] length
					String addMemberName = addBoatInfo[0];
					String addBoatType = addBoatInfo[1];
					int addBoatLength = Integer.parseInt(addBoatInfo[2]);
					
					if(!registry.userExists(addMemberName)) {
						view.displayMissingUserError(addMemberName);
					} else if(addBoatLength <= 0) { // Checks if boat length is valid
						view.displayIncorrectLengthError(Integer.parseInt(addBoatInfo[2]));
					} else {
						registry.addNewBoat(registry.getMemberIdFromName(addMemberName), addBoatType, addBoatLength);
					}
					break;
				case 8:
					// Delete a boat
					String[] deleteBoatInfo = view.displayDeleteBoat(registry.getMembers()); // [0] = owner name, [1] = boat number
					String deleteMemberName = deleteBoatInfo[0];
					int deleteBoatNumber = Integer.parseInt(deleteBoatInfo[1]);
					
					if(!registry.userExists(deleteMemberName)) {
						view.displayMissingUserError(deleteMemberName);
					} else if(!registry.boatExists(deleteMemberName, deleteBoatNumber)) {
						view.displayMissingBoatError(deleteMemberName, deleteBoatNumber);
					} else {
						registry.deleteBoat(registry.getMemberIdFromName(deleteMemberName), deleteBoatNumber);
					}
					break;
				case 9:
					// Edit a boat
					String[] editBoatInfo = view.displayEditBoat(registry.getMembers()); // [0] = memberId, [1] = boat number, [2] = new length, [3] = new type
					int editMemberId = Integer.parseInt(editBoatInfo[0]);
					int editBoatNumber = Integer.parseInt(editBoatInfo[1]);
					int editNewLength = Integer.parseInt(editBoatInfo[2]);
					String editNewType = editBoatInfo[3];
					String editMemberName = registry.getNameFromMemberId(editMemberId);

					if(!registry.userExists(editMemberName)) {
						view.displayMissingUserError(editMemberName);
					} else if(!registry.boatExists(editMemberName, editBoatNumber)) {
						view.displayMissingBoatError(editMemberName, editBoatNumber);
					} else {
						registry.editBoat(editMemberId, editBoatNumber, editNewLength, editNewType);
					}
					break;
				case 0: 
					// End program
					view.displayEndMessage();
					break;
				default: 
					break;
			}
		}
	}
}


