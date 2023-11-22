package com.vitallog.persistence.board;

import org.springframework.data.jpa.repository.JpaRepository;


import com.vitallog.domain.board.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
}
