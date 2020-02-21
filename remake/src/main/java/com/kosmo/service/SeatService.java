package com.kosmo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.MovieVO;
import com.kosmo.dto.SeatVO;

public interface SeatService {

	public List<SeatVO> selectSeat(MovieVO bean) throws Exception;

	public SeatVO selectSeatValue(SeatVO bean, HttpSession session) throws Exception;

	public int selectTotalSeatCount(MovieVO bean) throws Exception;
	
	public int selectRevSeatCount(MovieVO bean) throws Exception;
	
	public int updateSeat(BookingsVO bean) throws Exception;
	
	public int bookingSeat(SeatVO bean, HttpSession session) throws Exception;

	public int insertHallSeat(SeatVO bean) throws Exception;
	
	public int deleteSeat(BookingsVO bean) throws Exception;

	public int deleteHall(SeatVO bean) throws Exception;
	
}
