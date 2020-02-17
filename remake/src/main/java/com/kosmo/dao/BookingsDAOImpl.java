package com.kosmo.dao;

import java.util.List;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kosmo.dto.BookingsVO;
import com.kosmo.dto.SeatVO;

@Repository
public class BookingsDAOImpl implements BookingsDAO {

	@Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.kosmo.mapper.bookingsMapper";
    
    @Override
 	public List<BookingsVO> selectBook(BookingsVO reserveBean) throws Exception {
        
    	return sqlSession.selectList(Namespace + ".selectMember");
    	
    }
 	
 	@Override
 	public int insertBook(BookingsVO reserveBean) throws Exception {
 		
 		return sqlSession.insert(Namespace + ".insertBook");
 		
 	}

 	@Override
 	public int insertBookInfo(SeatVO seatBean) throws Exception {
 		
 		return sqlSession.insert(Namespace + ".insertBookInfo");
 		
 	}

 	@Override
 	public int deleteBook(BookingsVO bean) throws Exception {
 		
 		return sqlSession.delete(Namespace + ".deleteBook");
 		
 	}

}
