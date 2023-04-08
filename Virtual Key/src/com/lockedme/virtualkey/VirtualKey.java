/* **********************************************
 * End-of-Phase 1 Project: Virtual Key Repository
 * Student: Thirumavalaven
 * Code Finished Date: 03 April 2023
 * Code Started Date: 07 April 2023 * 
 * ********************************************* */
package com.lockedme.virtualkey;
import java.io.File;
import java.util.InputMismatchException;

public class VirtualKey {

	// class instance variables
	public static String targetDirectory = null;

	// Parameterized and Default Constructors
	public static void setTargetDirectory(String path) {
		targetDirectory = path;
	}
	public static void setTargetDirectory() {
		targetDirectory = "D:\\Simplilearn\\LockedMe\\Test";
	}

	// Main program starts here and contains the driver code
	public static void main(String[] args) {
		MyScanner.openScanner();
		Menu.welcomeScreen();
		try {

			System.out.println(
					"Do you like to run in Test directory(D:\\Test) or change to specified directory, Please enter 'yes' or 'no' ?");
			String test = MyScanner.sc.next();

			// Set the target directory
			if (test.equalsIgnoreCase("yes"))
				setTargetDirectory();
			else if (test.equalsIgnoreCase("no")) {
				System.out.println("Enter the target directory for LockedMe.com to use...");
				String path = MyScanner.sc.next();
				File f = new File(path);
				// Checks if the specified path is available and is a directory
				if (f.exists() && f.isDirectory())
					setTargetDirectory(path);
				else {
					System.out.println("Invalid directory...");
					System.out.println("Terminating Application");
					System.exit(1);
				}
			} else {
				System.out.println("Entered Invalid option, Terminating Application...");
				System.exit(1);
			}

		} catch (InputMismatchException e) {
			MyScanner.sc.nextLine(); // clearing out the buffer
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Displaying Main menu
		MainMenuOperations.mainMenu(targetDirectory);
		MyScanner.closeScanner();

	}// end main()

}// end class