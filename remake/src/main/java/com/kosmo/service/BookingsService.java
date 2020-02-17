package com.kosmo.service;

import java.util.List;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.SeatVO;

public interface BookingsService {

	public List<BookingsVO> selectBook(BookingsVO reserveBean) throws Exception;
	
	public int insertBook(BookingsVO reserveBean) throws Exception;

	public int insertBookInfo(SeatVO seatBean) throws Exception;
	
	public int deleteBook(BookingsVO bean) throws Exception;

}
