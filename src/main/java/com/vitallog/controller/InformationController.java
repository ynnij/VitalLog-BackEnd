package com.vitallog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vitallog.domain.Information;
import com.vitallog.service.InformationService;

@RestController
public class InformationController {
	@Autowired
	private InformationService informationService;
	
	@GetMapping("/api/vitallog/information")
	public Iterable<Information> getInformations(String age, String bmi, String gender){
		return informationService.getInformations(age, bmi, gender);
	}
	
	
}
