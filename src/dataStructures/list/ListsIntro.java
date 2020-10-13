package dataStructures.list;

import java.util.ArrayList;
import java.util.List;

public class ListsIntro {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(20);
		list.add(10);
		list.add(90);
		list.add(70);
		list.add(30);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(45);
		list2.addAll(list);
		System.out.println(list2.toString());

	}

}
