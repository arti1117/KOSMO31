package com.kosmo.dao;

import java.util.List;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.SeatVO;

public interface SeatDAO {

	//좌석페이지 로딩시, 현재 좌석정보 조회
	public List<SeatVO> selectSeat(SeatVO seatBean) throws Exception;

	//현재 좌석정보 조회 (seatBean으로)
	public SeatVO selectSeatValue(SeatVO seatBean) throws Exception;

	// 전체 좌석 수 
	public int selectTotalSeatCount(SeatVO seatBean) throws Exception;
	
	// 현재 예매된 좌석 수
	public int selectRevSeatCount(SeatVO seatBean) throws Exception;
	
	// 회원 정보 삭제시 좌석 'N' 변경
	public int updateSeat(BookingsVO bean) throws Exception;
	
	/** 예약정보 입력 **/
	public int bookingSeat(SeatVO sbean) throws Exception;

	// insertHallSeat : 영화관 좌석 등록
	public int insertHallSeat(SeatVO seatBean) throws Exception;
	
	// 회원 정보 삭제시 좌석 삭제
	public int deleteSeat(BookingsVO bean) throws Exception;

	public int deleteHall(SeatVO bean) throws Exception;
	
	
}
