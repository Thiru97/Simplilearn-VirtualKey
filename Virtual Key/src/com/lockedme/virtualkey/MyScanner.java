package com.lockedme.virtualkey;

import java.util.Scanner;

public class MyScanner {
	public static Scanner sc;

	public static void openScanner() {
		// Open a Scanner to read input from the console
		sc = new Scanner(System.in);
	}

	public static void closeScanner() {
		sc.close();
	}
}
