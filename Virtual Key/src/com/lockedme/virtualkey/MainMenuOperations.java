package com.lockedme.virtualkey;

import java.util.InputMismatchException;

public class MainMenuOperations {

	public static void mainMenu(String targetDirectory) {
		int mainMenuChoice = 0;
		String appState = "c";

		System.out.println("You specified the following target directory: " + targetDirectory);
		// Prepare an ArrayList of the files available
		FileOperations.buildFileList(targetDirectory);
		// Main menu operations
		while (appState.equalsIgnoreCase("c")) {
			// Display the Main Menu Options
			try {
				mainMenuChoice = Menu.mainMenuOptions();
				System.out.println("Selected main menu option: " + mainMenuChoice);

				switch (mainMenuChoice) {
				case 1:
					System.out.println("Retrieving files from " + targetDirectory + " in ascending order");
					FileOperations.retrieveFiles(targetDirectory);
					break;
				case 2:
					FileOperations.businessOptionsMenu(targetDirectory);
					break;
				case 3:
					System.out.println("Closing application...");
					System.exit(1);
				default:
					System.out.println("Please enter a valid option..");
					break;
				}// end switch

			} catch (InputMismatchException e) {
				MyScanner.sc.nextLine();
			} // end catch
			catch (Exception e) {
				e.printStackTrace();
			} // end catch

			try {
				System.out.println("Enter 'c' to continue, 'x' to quit: ");
				appState = MyScanner.sc.next();
			} catch (InputMismatchException e) {
				System.out.println("you entered invalid input");
				MyScanner.sc.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // end while

		if (appState.equalsIgnoreCase("x")) {
			System.out.println("Quitting the application...");
			System.exit(1);
		} else {
			System.out.println("Entered invalid sequence...Quitting the application...");
			System.exit(1);
		}

	}// end mainMenu()

}
