package org.nexusiq.successmetrics.controller;

import java.util.Map;

import org.nexusiq.successmetrics.service.ModelService;
import org.nexusiq.successmetrics.service.NetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ApplicationsController {
	
	@Autowired 
	private ModelService modelService;
	
	@Autowired 
	private NetworkService networkService;
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationsController.class);

	@GetMapping({"/applications"})
    public String applications(Model model) {
								
		log.info("In ApplicationsController");

		Map <String, Object> map = modelService.setApplicationsReportModel();
		
		model.mergeAttributes(map);
		
		boolean netAvailable = networkService.netIsAvailable();

		model.addAttribute("netAvailable", netAvailable);
		
        return "applications";
    }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> noCityFound(EmptyResultDataAccessException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
    }
	
}


