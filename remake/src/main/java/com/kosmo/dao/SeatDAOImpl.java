package com.kosmo.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.SeatVO;

@Repository
public class SeatDAOImpl implements SeatDAO {
	
	@Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.kosmo.mapper.seatMapper";
	
	@Override
    public List<SeatVO> selectSeat(SeatVO bean) throws Exception {
		
		return sqlSession.selectList(Namespace + ".selectSeat");
	}

	@Override
	public SeatVO selectSeatValue(SeatVO bean) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".selectSeatValue");
	}

	@Override
	public int selectTotalSeatCount(SeatVO bean) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".selectTotalSeatCount");
	}
	
	@Override
	public int selectRevSeatCount(SeatVO bean) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".selectRevSeatCount");
		
	}
	
	@Override
	public int updateSeat(BookingsVO bean) throws Exception {
		
		return sqlSession.update(Namespace + ".updateSeat");
		
	}
	
	@Override
	public int bookingSeat(SeatVO bean) throws Exception {
		
		return sqlSession.update(Namespace + ".bookingSeat");
		
	}

	@Override
	public int insertHallSeat(SeatVO bean) throws Exception {
		
		return sqlSession.insert(Namespace + ".insertHallSeat");
		
	}
	
	@Override
	public int deleteSeat(BookingsVO bean) throws Exception {
		
		return sqlSession.update(Namespace + ".deleteSeat");
	}

	@Override
	public int deleteHall(SeatVO bean) throws Exception {
		
		return sqlSession.delete(Namespace + ".deleteHall");
	}
	
}
