package com.vitallog.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.vitallog.domain.Information;

public interface InformationRepository extends JpaRepository<Information, String>, QuerydslPredicateExecutor<Information> {
	
}
