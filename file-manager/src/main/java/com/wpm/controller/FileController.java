package com.wpm.controller;


import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cm")
public class FileController{
	Logger logger = Logger.getLogger(FileController.class);
	
    @GetMapping("/resource")
    public Object queryResource(Map params) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return null;
	}

}