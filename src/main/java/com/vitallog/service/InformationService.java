package com.vitallog.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.metamodel.mapping.internal.ImmutableAttributeMappingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.vitallog.domain.Information;
import com.vitallog.domain.QInformation;
import com.vitallog.persistence.InformationRepository;

@Service
public class InformationService {
	@Autowired
	private InformationRepository infoRepo;
	
	//다이나믹 쿼리로 값을 받은 필드만 필터링해서 리턴
	public Iterable<Information> getInformations(String age, String bmi, String gender){
		QInformation qinfo = QInformation.information;
		BooleanBuilder builder = new BooleanBuilder();
		
		System.out.println(age+" "+bmi+" "+gender);
		
		if(!age.equals("")) {
			builder.and(qinfo.age.eq(age));
		}
		if(!bmi.equals("")) {
			builder.and(qinfo.bmi.eq(bmi));
		}
		if(!gender.equals("")) {
			builder.and(qinfo.gender.eq(gender));
		}
				
		return infoRepo.findAll(builder);
		
	}
	
}
