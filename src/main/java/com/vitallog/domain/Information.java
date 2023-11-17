package com.vitallog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Information {
	@Id
	private String id;
	private String age;
	private String bmi;
	private String gender;
	private String exerciseStep;
	private String exerciseType;
}
