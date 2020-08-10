package com.demo.assignment.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.assignment.model.Activity;
import com.demo.assignment.repository.ActivityRepository;
import com.demo.assignment.util.FileGenerator;
import com.demo.assignment.util.Processor;

@Service
public class ActivityService {
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	@Qualifier("ExcelFileGenerator")
	FileGenerator fileGenerator;
	
	public ByteArrayInputStream downloadMasterPlanAsCSV(String sortCriteria) throws IOException {
		Activity root = activityRepository.findBySerialNo("1");
		List<Activity> activities = new ArrayList<Activity>();
		getAllActivities(root, activities);
		
		if(sortCriteria != null && !sortCriteria.isEmpty()) {
			Processor.sort(activities, sortCriteria);
		}
		
		return fileGenerator.generateOutputFile(activities);
	}
	
	private void getAllActivities(Activity activity, List<Activity> activities) {
		if(activity == null) {
			return;
		}
		
		activities.add(activity);
		for(Activity subActivity: activity.getSubActivities()) {
			getAllActivities(subActivity, activities);
		}
	}
}
