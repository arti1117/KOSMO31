package com.kosmo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dao.SeatDAO;
import com.kosmo.dto.SeatVO;

@Service
public class SeatServiceImpl implements SeatService {
	
	@Inject
	private SeatDAO dao;
	
	
	@Override
	public List<SeatVO> selectSeat(SeatVO bean) throws Exception {
		return dao.selectSeat(bean);
	}

	@Override
	public SeatVO selectSeatValue(SeatVO bean) throws Exception {
		return dao.selectSeatValue(bean);
	}
	
	@Override
	public int selectTotalSeatCount(SeatVO bean) throws Exception {
		return dao.selectTotalSeatCount(bean);
	}
	
	@Override
	public int selectRevSeatCount(SeatVO bean) throws Exception {
		return dao.selectRevSeatCount(bean);
	}
	
	@Override
	public int updateSeat(BookingsVO bean) throws Exception {
		return dao.updateSeat(bean);
	}
	
	@Override
	public int bookingSeat(SeatVO bean) throws Exception {
		return dao.bookingSeat(bean);
	}
	
	@Override
	public int insertHallSeat(SeatVO bean) throws Exception {
		return dao.insertHallSeat(bean);
	}
	
	@Override
	public int deleteSeat(BookingsVO bean) throws Exception {
		return dao.deleteSeat(bean);
	}
	
	@Override
	public int deleteHall(SeatVO bean) throws Exception {
		return dao.deleteHall(bean);
	}
	
}
