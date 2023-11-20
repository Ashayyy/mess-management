package Codes;

import java.util.Comparator;

public class CustomerSortByDate implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		return ((Customer)o1).getPlan().compareTo(((Customer)o2).getPlan());
	}

}
