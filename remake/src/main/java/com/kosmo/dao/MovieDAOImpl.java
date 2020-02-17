package com.kosmo.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.kosmo.bean.PagingBean;
import com.kosmo.dto.MovieVO;

public class MovieDAOImpl implements MovieDAO {

	@Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.kosmo.mapper.movieMapper";
	
    @Override
	public int selectListTotCount(PagingBean pagingBean) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".selectListTotCount");
		
	}
	
    @Override
	public List<MovieVO> selectMovieList(PagingBean pagingBean) throws Exception {
    	
    	return sqlSession.selectList(Namespace + ".selectMovieList");
    	
    }

    @Override
	public MovieVO selectMovie(MovieVO movieBean) throws Exception {
    	
    	return sqlSession.selectOne(Namespace + ".selectMovie");
    	
    }
		
    @Override
	public int insertMovie(MovieVO movieBean) throws Exception {
    	
    	return sqlSession.insert(Namespace + ".insertMovie");
    	
    }
	
    @Override
	public int updateMovie(MovieVO movieBean) throws Exception {
    	
    	return sqlSession.update(Namespace + ".updateMovie");
    	
    }
	
    @Override
	public int deleteMovie(MovieVO movieBean) throws Exception {
    	
    	return sqlSession.delete(Namespace + ".deleteMovie");
    }
	
}
