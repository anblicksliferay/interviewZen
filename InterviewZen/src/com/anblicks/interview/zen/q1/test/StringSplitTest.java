package com.anblicks.interview.zen.q1.test;

import java.util.Scanner;

import com.anblicks.interview.zen.q1.StringSplit;

public class StringSplitTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

			System.out.println("#####Question-1####");
			System.out.println();
			while (true) {
				System.out.println("Menu List to Select:");
				System.out.println("1. Perform Function !!");
				System.out.println("2. Quit !!");

				Scanner sc = new Scanner(System.in);

				int menuInput = -1;
				try {
					menuInput = sc.nextInt();
				} catch (Exception e) {
					System.out.println("Input misMatch Please select it properly !!");
					System.out.println();
					continue;
				}
				

				if (menuInput == 1) {
					System.out.println("Please Enter the String : ");
					String sourceString = sc.next();
					System.out.println("Please Enter the SplitParameter : ");
					int splitParameter = sc.nextInt();
					System.out.println("Your Destination String : "
							+ new StringSplit(sourceString.trim(), splitParameter).displayMulti());
					System.out.println();
				} else if (menuInput == 2) {
					System.out.println("Ok Bye !!");
					break;

				} else {
					System.out.println("Please Select the Operations Properly !!");
				}

			}

	}

}
