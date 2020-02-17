package com.kosmo.service;

import java.util.List;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.SeatVO;

public interface SeatService {

	public List<SeatVO> selectSeat(SeatVO seatBean) throws Exception;

	public SeatVO selectSeatValue(SeatVO seatBean) throws Exception;

	public int selectTotalSeatCount(SeatVO seatBean) throws Exception;
	
	public int selectRevSeatCount(SeatVO seatBean) throws Exception;
	
	public int updateSeat(BookingsVO bean) throws Exception;
	
	public int bookingSeat(SeatVO sbean) throws Exception;

	public int insertHallSeat(SeatVO seatBean) throws Exception;
	
	public int deleteSeat(BookingsVO bean) throws Exception;

	public int deleteHall(SeatVO bean) throws Exception;
	
}
