	package com.lockedme.virtualkey;
	
	import java.util.InputMismatchException;
	
	public class Menu {
	
		public static void welcomeScreen() {
	
			System.out.println("\n************************************************\n");
			System.out.println("Welcome to the Virtual Keys Repository of LockedMe.com");
			System.out.println("Version: 1.0 PROTOTYPE");
			System.out.println("Client: Lockers Pvt. Ltd");
			System.out.println("Full Stack Developer Name: Thirumavalaven");
			System.out.println("************************************************\n");
	
		}// end welcome()
	
		public static int mainMenuOptions() {
			int r = 0; // added this to handle any exceptions
	
			String[] options = { "************************************************",
					"*               MAIN MENU                      *", "************************************************",
					"1. Display the current file names in ASCENDENING order", "2. Open Business Level Operations Menu",
					"3. Close the application", "************************************************" };
	
			// Display Main Menu Options
			for (int i = 0; i < options.length; i++) {
				System.out.println(options[i]);
			}
	
			try {
				System.out.println("Choose your option...");
				r = MyScanner.sc.nextInt();
				// couldn't return r from here; have to move it outside of the try-catch block
			} catch (InputMismatchException e) {
				MyScanner.sc.nextLine(); // consuming the input that was causing the exception, clearing the input
											// stream, and allowing the user to input something again
			} catch (Exception e) {
				e.printStackTrace();
			} // end catch
			return r;
		}// end mainMainOptions()
	}
