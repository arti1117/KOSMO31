package com.kosmo.service;

import java.util.List;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.SeatVO;

public interface BookingsService {

	public List<BookingsVO> selectBook(BookingsVO bean) throws Exception;
	
	public int insertBook(BookingsVO bean) throws Exception;

	public int insertBookInfo(SeatVO bean) throws Exception;
	
	public int deleteBook(BookingsVO bean) throws Exception;

}
