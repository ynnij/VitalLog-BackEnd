package com.vitallog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitallog.domain.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, String> {

}
