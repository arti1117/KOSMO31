package com.kosmo.dao;

import java.util.List;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.SeatVO;

public interface BookingsDAO {

	// 예약정보 출력
	public List<BookingsVO> selectBook(BookingsVO reserveBean) throws Exception;
	
	// 예약 테이블 삽입
	public int insertBook(BookingsVO reserveBean) throws Exception;

	// 영화좌석 예매
	public int insertBookInfo(SeatVO seatBean) throws Exception;
	
	// 회원 정보 삭제시 좌석 삭제
	public int deleteBook(BookingsVO bean) throws Exception;
	
}

