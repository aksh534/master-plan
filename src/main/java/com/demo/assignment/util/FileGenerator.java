package com.demo.assignment.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.assignment.Application;
import com.demo.assignment.model.Activity;

public abstract class FileGenerator {
	
	private static final String headersSeparator = ";";
	private static final String propertiesSeparator = ",";
	private static final String valuesSeparator = ":";
	private static final String dateFormat = "yyyy-MM-dd";
	private static SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

	public abstract ByteArrayInputStream generateOutputFile(List<Activity> activities) throws IOException;
	
	private synchronized String readFile(File file) {
        if (null == file)
            return "";
        char buf[] = null;
        try {
            Reader reader = new FileReader(file);
            buf = new char[(int) file.length()];
            reader.read(buf, 0, buf.length);
            reader.close();
        } catch (Exception e) {
            return "";
        }
        return new String(buf);
    } 
	
	protected Map<Integer, String> getFileHeaders() {
		String content = readFile(new File(Application.getHeadersFilePath()));
		return extractPropertiesFromHeaders(content.split(headersSeparator));
	}
	
	private Map<Integer, String> extractPropertiesFromHeaders(String[] headerValues) {
		if(headerValues == null || headerValues.length == 0) {
			// TODO Exception
			return null;
		}
		
		Map<Integer, String> headers = new HashMap<Integer, String>();
		for(String header: headerValues) {
			String[] properties = extractPropertiesFromHeader(header);
			if(properties == null || properties.length < 2) {
				// TODO Exception
				continue;
			}
			
			String headerName = refine(properties[0]);
			int position = Integer.parseInt(refine(properties[1]));
			headers.put(position, headerName);
		}
		
		return headers;
	}
	
	private String[] extractPropertiesFromHeader(String header) {
		if(header == null || header.isEmpty()) {
			return null;
		}
		
		return header.split(propertiesSeparator);
	}
	
	private String refine(String property) {
		String[] values = property.split(valuesSeparator);
		return values[1].trim();
	}
	
	protected String processDate(Date date) {
		return formatter.format(date);
	}

}
