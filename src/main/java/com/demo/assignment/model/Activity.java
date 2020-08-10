package com.demo.assignment.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "data")
public class Activity {
	
	@Id
	@JsonProperty(value = "serialNo")
	private String serialNo;
	
	@JsonProperty(value = "activityName")
	private String activityName;
	
	@JsonProperty(value = "startDate")
	private Date startDate;
	
	@JsonProperty(value = "endDate")
	private Date endDate;
	
	@JsonProperty(value = "subActivities")
	private List<Activity> subActivities;
	
	public Activity() {
		
	}

	public Activity(String serialNo, String activityName, Date startDate, Date endDate) {
		this.serialNo = serialNo;
		this.activityName = activityName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.subActivities = new ArrayList<Activity>();
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getName() {
		return activityName;
	}

	public void setName(String activityName) {
		this.activityName = activityName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public List<Activity> getSubActivities() {
		return subActivities;
	}

	public void addSubActivity(Activity activity) {
		this.subActivities.add(activity);
	}
	
	public boolean isLeafActivity() {
		return this.subActivities == null || this.subActivities.isEmpty();
	}

	@Override
	public String toString() {
		return "Activity [id=" + serialNo + ", name=" + activityName + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}

