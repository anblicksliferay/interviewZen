package com.anblicks.interview.zen.q2.test;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.anblicks.interview.zen.q2.PhoneBook;
import com.anblicks.interview.zen.q2.PhoneBookConstants;
import com.anblicks.interview.zen.q2.PhoneDto;

public class PhoneBookTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		PhoneBook phoneBook = new PhoneBook(new HashMap<Integer, PhoneDto>());

		Scanner sc = new Scanner(System.in);
		
		System.out.println("####Question-2####");
		
		System.out.println("Please Enter the Contact Data String !!");

		String sourceString = sc.next();

		String[] arrOfStr = PhoneBook.stringSplit(sourceString, PhoneBookConstants.SEMICOLON);

		for (String s : arrOfStr) {
			
			String[] split = PhoneBook.stringSplit(s, PhoneBookConstants.COMMA);
			
			if(split.length == 3) {
				
				int phoneNumber = -1;
				
				try {
					
					phoneNumber = Integer.parseInt( split[2]);
				} catch (Exception e) {
					System.out.println("Improper Contact PhoneNumber !!");
					continue;
				}
				System.out.println(phoneBook.Insert(new PhoneDto(split[0], split[1], phoneNumber)));
				
			}
			else {
				System.out.println("Improper Contact Data !!");
			}

		}
		System.out.println();
		System.out.println("Phone Book :");
		
		
		List<PhoneDto> sortedContactList = phoneBook.getSortedContactList();
		
		for(PhoneDto pd : sortedContactList) {
			System.out.println(pd.toString());
		}
	}

}
