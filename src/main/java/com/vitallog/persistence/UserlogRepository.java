package com.vitallog.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vitallog.domain.Userlog;
import com.vitallog.dto.UserlogDto;

public interface UserlogRepository extends JpaRepository<Userlog, Integer> {
}
