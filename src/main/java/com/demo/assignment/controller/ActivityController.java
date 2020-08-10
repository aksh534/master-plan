package com.demo.assignment.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.assignment.service.ActivityService;

@RestController
@RequestMapping("/api/v1")
public class ActivityController {
	
	@Autowired
	ActivityService activityService;
	
	@GetMapping(value = "/downloadFile")
	public ResponseEntity<InputStreamResource> downloadMasterPlanAsCSV(
								@RequestParam(name="sort", required=false) String sortCriteria) {
		
		ByteArrayInputStream dateStream = null;
		try {
			
			dateStream = activityService.downloadMasterPlanAsCSV(sortCriteria);
		}
		catch(Exception ex) {
			return ResponseEntity.
					status(HttpStatus.INTERNAL_SERVER_ERROR).
					build();
		}

		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=output.xlsx");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(dateStream));
	}
}
