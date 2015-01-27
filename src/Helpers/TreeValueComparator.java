package Helpers;

import java.util.Comparator;
import java.util.Map;

public class TreeValueComparator implements Comparator<Character> {

	Map<Character, Integer> base;
	
	public TreeValueComparator(Map<Character, Integer> base) {
		this.base = base;
	}

	public int compare(Character a, Character b) {
		return (base.get(a) >= base.get(b)) ? -1 : 1;
	}
}

