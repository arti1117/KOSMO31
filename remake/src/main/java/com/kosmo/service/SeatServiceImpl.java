package com.kosmo.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

//import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.MemberVO;
import com.kosmo.dto.MovieVO;
import com.kosmo.constant.CommonConstant;
import com.kosmo.dao.BookingsDAO;
import com.kosmo.dao.SeatDAO;
import com.kosmo.dto.SeatVO;

@Service
public class SeatServiceImpl implements SeatService {
	
	@Inject
	private SeatDAO dao;
	
	@Inject
	private BookingsDAO bookingsDAO;
	
	
	@Override
	public List<SeatVO> selectSeat(MovieVO bean) throws Exception {
		String code = bean.getCode();
		
		SeatVO seatBean = new SeatVO();
		seatBean.setCode(code);
		
		//TODO 숫자>좌석으로 바꿔주기
		
		return dao.selectSeat(seatBean);
	}

	@Override
	public SeatVO selectSeatValue(SeatVO bean, HttpSession session) throws Exception {
		
		MovieVO movieBean = (MovieVO)session.getAttribute(CommonConstant.SEAT_MOIVE_BEAN);
		
		String code = movieBean.getCode();
		bean.setCode(code);
		
		return dao.selectSeatValue(bean);
	}
	
	@Override
	public int selectTotalSeatCount(MovieVO bean) throws Exception {
		
		String code = bean.getCode();
	
		SeatVO seatBean = new SeatVO();
		seatBean.setCode(code);
		
		return dao.selectTotalSeatCount(seatBean);
	}
	
	@Override
	public int selectRevSeatCount(MovieVO bean) throws Exception {
		String code = bean.getCode();
		
		SeatVO seatBean = new SeatVO();
		seatBean.setCode(code);
		
		return dao.selectRevSeatCount(seatBean);
	}
	
	@Override
	public int updateSeat(BookingsVO bean) throws Exception {
		return dao.updateSeat(bean);
	}
	
	@Transactional
	public int bookingSeat(SeatVO bean, HttpSession session) throws Exception {
		String[] seatHiddenArr;
		int totSeat=0;
		int seatCnt=0;
		int revCnt=0;
		SeatVO sBean = new SeatVO();
		
		//session정보 받아오기
		MovieVO movieBean = new MovieVO();
		movieBean = (MovieVO)session.getAttribute(CommonConstant.SEAT_MOIVE_BEAN);
		MemberVO memberBean = new MemberVO();
		memberBean = (MemberVO)session.getAttribute(CommonConstant.LOGIN_MEMBER_BEAN);
		
		String code = movieBean.getCode(); //sBean에 코드 넣어줘야해서

		String memberId = memberBean.getId(); //rBean에 코드 넣어주기 용도
		String hall = movieBean.getHall();
			
		
		
		//update
		seatHiddenArr = bean.getRevRequestSeatArr();
		for(int i =0; i<seatHiddenArr.length;i++){
			sBean.setRevRequestSeat(seatHiddenArr[i]);
			sBean.setCode(code);
			sBean.setId(memberId);
			seatCnt = dao.bookingSeat(sBean);
			totSeat = totSeat + seatCnt;
		}
		//예약테이블에 insert
		String[] seatShowArr;
		BookingsVO rBean = new BookingsVO();
		seatShowArr = bean.getRevRequestShowSeatArr();

		
		//rBean에 코드/id 넣어주기(예약 테이블에 넣으려고)

		rBean.setCode(code);
		rBean.setId(memberId);
		rBean.setHall(hall);
		
		//rev에 같은 좌석 예약되어있으면 튕기게 하기

			for(int i=0; i<seatShowArr.length;i++){
				rBean.setSeatHidden(seatHiddenArr[i]);
				rBean.setSeatShow(seatShowArr[i]);
				revCnt = revCnt+ bookingsDAO.insertBook(rBean);
			}
		//seatDB와 revDB의 처리개수가 일치하다면, 아닐시는 강제로 0 리턴시키기
		if(totSeat==revCnt){
			return totSeat;		
		}
		else{
			return 0;
		}
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
