package com.anblicks.interview.zen.q2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PhoneBook {

	private Map<Integer, PhoneDto> contactList;

	public PhoneBook(Map<Integer, PhoneDto> contactList) {
		super();
		this.contactList = contactList;
	}
	
	public void setContactList(Map<Integer, PhoneDto> contactList) {
		this.contactList = contactList;
	}

	public Map<Integer, PhoneDto> getContactList() {
		return contactList;
	}

	public String Insert(PhoneDto pd) {

		if (!isDuplicate(pd)) { 

			Map<Integer, PhoneDto> contactList = getContactList();
			
			contactList.put(pd.getPhoneNumber(), pd);
			
			setContactList(contactList);
			
			return pd.toString() + " : "+ PhoneBookConstants.Success ;

		} else {
			
			return pd.toString() + " : "+PhoneBookConstants.Fail;
		}
	}

	private boolean isDuplicate(PhoneDto pd) {

		Map<Integer, PhoneDto> contactList = getContactList();

		if (!contactList.isEmpty()) {
			if (contactList.containsKey(pd.getPhoneNumber())) {
				return true;
			}
			return false;

		} else {
			return false;
		}

	}
	
	public List<PhoneDto> getSortedContactList() {
	
	return new ArrayList<PhoneDto>(getContactList().values())
	          .stream()
			  .sorted(Comparator.comparing(PhoneDto::getFirstName))
			  .collect(Collectors.toList());
	
	}
	
	public static String[] stringSplit(String str,String regex) {
		
		return str.split(regex);
		
	}

}
