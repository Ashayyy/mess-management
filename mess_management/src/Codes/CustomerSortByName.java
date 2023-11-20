package Codes;

import java.util.Comparator;

public class CustomerSortByName implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		return ((Customer)o1).getFirstname().compareTo(((Customer)o2).getFirstname());
	}
	

}
