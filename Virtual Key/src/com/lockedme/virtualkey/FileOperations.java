package com.lockedme.virtualkey;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class FileOperations {

	public static Path myFile;
	public static List<File> fileList = new ArrayList<File>();
	public static int numFilesinDirectory = 0;
	public static String targetFileName = null;

	public static void businessOptionsMenu(String targetDirectory) {

		boolean loop = true;

		while (loop) {
			System.out.println("................................................");
			System.out.println(".     Business Level Options Sub-Menu          .");
			System.out.println("................................................");

			String[] options = { "1. Add new file", "2. Delete File (case sensisitive)",
					"3. Search for File (case sensisitive)", "4. Go back to main menu",
					"................................................" };

			// Display the Business Level Options Menu to the Console
			for (int i = 0; i < options.length; i++) {
				System.out.println(options[i]);
			}

			try {
				// User will input their selection
				System.out.println("Choose your option...");
				int y = MyScanner.sc.nextInt();

				switch (y) {
				case 1:
					System.out.println("Specify the file name to ADD to " + targetDirectory + ": ");
					targetFileName = MyScanner.sc.next();
					myFile = Paths.get(targetDirectory + "\\" + targetFileName);
					addFile(targetDirectory);
					break;
				case 2:
					System.out.println("Specify the file name to DELETE from " + targetDirectory + ": ");
					targetFileName = MyScanner.sc.next();
					myFile = Paths.get(targetDirectory + "\\" + targetFileName);
					deleteFile(targetDirectory);
					break;
				case 3:
					System.out.println("Specify the file name to SEARCH from " + targetDirectory + ": ");
					targetFileName = MyScanner.sc.next();
					myFile = Paths.get(targetDirectory + "\\" + targetFileName);
					System.out.println("Searching for file: " + targetFileName);
					searchForFile(targetDirectory);
					break;
				case 4:
					loop = false;
					MainMenuOperations.mainMenu(targetDirectory);
					break;
				default:
					System.out.println("Please enter a valid option...");
					break;
				}// end switch
			} catch (InputMismatchException e) {
				System.out.println("You entered invalid input! Try again..");
				MyScanner.sc.nextLine(); // consuming the input that was causing the exception, clearing the input
											// stream, and allowing the user to input something again
			} // end catch()
			catch (Exception e) {
				e.printStackTrace();
			} // end catch()
		} // end while

	}// end businessOptionsMenu()

	public static void retrieveFiles(String targetDirectory) throws IOException {

		int count = 0;
		Path dirPath = Paths.get(targetDirectory);

		if (Files.exists(dirPath) && Files.isDirectory(dirPath)) {
			System.out.println("Directory: " + dirPath.toAbsolutePath());
			System.out.println("Files: ");
			DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath);
			for (Path p : dirStream) {
				if (Files.isRegularFile(p)) {
					System.out.println(p.getFileName()); // already sorted in ascending order
					count++;
				}
			} // end for
		} // end if

		numFilesinDirectory = count;

		System.out.println("There are " + numFilesinDirectory + " files in the directory.");

	}// end retrieveFiles

	public static void addFile(String targetDirectory) throws IOException {
		/*
		 * the requirement specifically stated "You can ignore the case sensitivity of
		 * the file names
		 */
		Path filePath = Paths.get(targetDirectory, targetFileName);

		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
			System.out.println("File created successfully!"); // case sensitivity ignored

			buildFileList(targetDirectory); // re-building the fileList after the addition
			Collections.sort(fileList); // sort the resultant ArrayList in ascending order
		} else {
			System.out.println("File already exists");
		}
	}// end addFile()

	public static void deleteFile(String targetDirectory) throws IOException {

		// The requirement was to implement case sensitive file delete functionality
		File dirTest = new File(targetDirectory);

		if (Files.exists(myFile)) {
			System.out.println("Specified file exists...");
			System.out.println("Do you really want to delete please confirm - y or n?");
			String proceed = MyScanner.sc.next();

			if (proceed.equalsIgnoreCase("y")) {
				for (File f : dirTest.listFiles()) {
					if (f.getCanonicalFile().getName().equals(myFile.getFileName().toString())) {
						Files.deleteIfExists(myFile);
						System.out.println("File deleted Successfully");
						buildFileList(targetDirectory); // re-building the fileList after the deletion
						Collections.sort(fileList); // sort the resultant ArrayList in ascending order
					} // end canonical check

				} // end for
			} else {
				System.out.println("Not deleting the file per your request...");
			}
		} // end if(proceed)
		else
			System.out.println("File not found in " + dirTest + " ....");

	}// end deleteFile()

	public static void searchForFile(String targetDirectory) {

		File dirTest = new File(targetDirectory);
		boolean fnf = true;

		for (File f : dirTest.listFiles()) {
			try {
				if (f.getCanonicalFile().getName().equals(myFile.getFileName().toString())) {
					System.out.println(myFile.getFileName().toString() + " exists in " + dirTest);
					fnf = false;
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end for()

		if (fnf != false)
			System.out.println(myFile.getFileName().toString() + " DOES NOT exist in " + dirTest + "...");

	}// searchForFile()

	public static void buildFileList(String targetDirectory) {
		int count = 0;

		File dirTest = new File(targetDirectory);

		for (File file : dirTest.listFiles()) {
			fileList.add(file);
			count++;
		}

		numFilesinDirectory = count;

	}// end buildFileList()

}
