package com.vitallog.persistence.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vitallog.domain.board.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	@Modifying
	@Query(value="delete from reply where board_id=%?1%",nativeQuery = true)
	public void deleteReply(int id);
}
