package org.nexusiq.successmetrics.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileNameService {
	
	@Value("${csvfile.successmetrics}")
	private String csvSuccessMetricsFileName;
	
	@Value("${csvfile.policyviolations}")
	private String csvPolicyViolationsFileName;
	
	@Value("${csvfile.applicationEvaluations}")
	private String csvApplicationEvaluationsFileName;
	
	public static boolean PolicyViolationsReportExists = false;
	public static boolean ApplicationEvaluationsReportExists = false;
	public static boolean SuccessMetricsReportExists = false;

	
	public String getFilename(String metricType) {
		
		String fileName = null;
		
		switch (metricType) {
			case "successmetrics": fileName = csvSuccessMetricsFileName; break;
			case "policyviolations": fileName = csvPolicyViolationsFileName; break;
			case "applicationevaluations": fileName = csvApplicationEvaluationsFileName; break;
			default: break;
		}
		
		return fileName;
		
	}
	
	public String getFirstLine(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName)); 

	    String line = br.readLine(); 
	    br.close();
	    return line;
	}
	
}
