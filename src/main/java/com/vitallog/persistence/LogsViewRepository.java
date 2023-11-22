package com.vitallog.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vitallog.domain.LogsView;

public interface LogsViewRepository extends JpaRepository<LogsView, Integer> {
	// 유저 메인에서 최신 3개 로그
	@Query(value="select seq, exer_date, exer_time, kcal, member_name, exercise "
			+ "from logs_view where memberid=%?1% "
			+ "order by seq desc limit 3", nativeQuery=true)
	List<Object[]> getMainPageUserLogs(String userid);
	
	
	// 유저의 모든 로그 
	@Query(value="select seq, exer_date, exer_time, kcal, member_name, exercise "
			+ "from logs_view where memberid=%?1% "
			+ "order by seq desc", nativeQuery = true)
	List<Object[]> getUserLogs(String userid);
	// 모든 로그에서 합계
	@Query(value="select exer_date, sum(exer_time), sum(kcal) "
			+ "from logs_view where memberid=%?1% "
			+ "group by exer_date", nativeQuery = true)
	List<Object[]> getUserLogsTotal(String userid);
	
	
	// 오늘 날짜의 총 칼로리와 운동시간
	@Query(value="select member_name, sum(kcal), sum(exer_time),exer_date "
			+ "from logs_view "
			+ "where memberid=%?1% and exer_date=%?2%", nativeQuery=true)
	List<Object[]> getTodayTotalLogs(String userid, String date);
	
	
	// 오늘 날짜의 로그 데이터
	@Query(value="select seq, exer_date, exer_time, kcal, member_name, exercise "
			+ "from logs_view where memberid=%?1% and exer_date=%?2% "
			+ "order by seq desc",nativeQuery = true)
	List<Object[]> getTodayLogs(String userid, String date);
	
	// 특정 기간 내의 로그 데이터
	@Query(value="select seq, exer_date, exer_time, kcal, member_name, exercise "
			+ "from logs_view "
			+ "where memberid=%?1% and exer_date between %?2% and %?3%",nativeQuery = true)
	List<Object[]> getPeriodsLogs(String userid, String fromDate, String toDate);
	
	// 특정 기간 내 로그에서 합계
	@Query(value = "select exer_date, sum(exer_time), sum(kcal) " 
			+ "from logs_view "
			+ "where memberid=%?1% and exer_date between %?2% and %?3% "
			+ "group by exer_date", nativeQuery = true)
	List<Object[]> getPeriodsLogsTotal(String userid, String fromDate, String toDate);
	
}
