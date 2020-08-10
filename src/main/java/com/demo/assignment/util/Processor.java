package com.demo.assignment.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.demo.assignment.model.Activity;

public class Processor {
	
	public static void sort(List<Activity> activities, String sortCriteria) { // throws Exception
		Comparator<Activity> sorter = getSorter(sortCriteria);
		if(sorter == null) {
			// TODO Exception Bad request
			return;
		}
		Collections.sort(activities, sorter);
	}
	
	public static Comparator<Activity> getSorter(String sortCriteria)  {
		Comparator<Activity> sorter = null;
		
		switch(sortCriteria) {
			case "wbs":
				sorter = new SortBySerialNo();
				break;
			case "startDate":
				sorter = new SortByStartDate();
				break;
		}
		
		return sorter;
	}
	
	private static class SortBySerialNo implements Comparator<Activity> {

		@Override
		public int compare(Activity a, Activity b) {
			return a.getSerialNo().compareTo(b.getSerialNo());
		}
	}
	
	private static class SortByStartDate implements Comparator<Activity> {

		@Override
		public int compare(Activity a, Activity b) {
			if(a.getStartDate().equals(b.getStartDate())) {
				return a.getSerialNo().compareTo(b.getSerialNo());
			}
			return a.getStartDate().compareTo(b.getStartDate());
		}
		
	}
}
