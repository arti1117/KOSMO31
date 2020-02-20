package com.kosmo.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kosmo.bean.PagingBean;
import com.kosmo.dto.MovieVO;

@Repository
public class MovieDAOImpl implements MovieDAO {

	@Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.kosmo.mapper.movieMapper";
	
    @Override
	public int selectListTotCount(PagingBean bean) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".selectListTotCount");
		
	}
	
    @Override
	public List<MovieVO> selectMovieList(PagingBean bean) throws Exception {
    	
    	return sqlSession.selectList(Namespace + ".selectMovieList");
    	
    }

    @Override
	public MovieVO selectMovie(MovieVO bean) throws Exception {
    	
    	return sqlSession.selectOne(Namespace + ".selectMovie");
    	
    }
		
    @Override
	public int insertMovie(MovieVO bean) throws Exception {
    	
    	return sqlSession.insert(Namespace + ".insertMovie");
    	
    }
	
    @Override
	public int updateMovie(MovieVO bean) throws Exception {
    	
    	return sqlSession.update(Namespace + ".updateMovie");
    	
    }
	
    @Override
	public int deleteMovie(MovieVO bean) throws Exception {
    	
    	return sqlSession.delete(Namespace + ".deleteMovie");
    }
	
}
