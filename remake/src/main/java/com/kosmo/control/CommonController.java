package com.kosmo.control;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosmo.service.MovieService;

@Controller
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	
	@Inject
	private MovieService service;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		
		logger.info("index");
		
		return "/index";
	}
	
	@RequestMapping(value = "/admin/adminIndex", method = RequestMethod.GET)
	public String adminIndex() {
		
		logger.info("Administrator Index");
		
		return "/admin/adminIndex";
	}
	
	
}
