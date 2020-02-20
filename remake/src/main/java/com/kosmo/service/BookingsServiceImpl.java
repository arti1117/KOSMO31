package com.kosmo.service;

import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;

import com.kosmo.dao.BookingsDAO;
//import com.kosmo.dao.SeatDAO;
import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.SeatVO;


@Service
public class BookingsServiceImpl implements BookingsService {

	@Inject
	private BookingsDAO dao;
		
	
	@Override
	public List<BookingsVO> selectBook(BookingsVO bean) throws Exception {
		return dao.selectBook(bean);
	}
	
	@Override
	public int insertBook(BookingsVO bean) throws Exception {
		return dao.insertBook(bean);
	}
	
	@Override
	public int insertBookInfo(SeatVO bean) throws Exception {
		return dao.insertBookInfo(bean);
	}
	
	@Override
	public int deleteBook(BookingsVO bean) throws Exception {
		return dao.deleteBook(bean);
	}
	
}
