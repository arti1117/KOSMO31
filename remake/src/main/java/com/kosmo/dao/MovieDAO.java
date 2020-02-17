package com.kosmo.dao;

import java.util.List;

import com.kosmo.bean.PagingBean;
import com.kosmo.dto.MovieVO;

public interface MovieDAO {
	
	public int selectListTotCount(PagingBean pagingBean) throws Exception;
	
	public List<MovieVO> selectMovieList(PagingBean pagingBean) throws Exception;

	public MovieVO selectMovie(MovieVO movieBean) throws Exception;
		
	public int insertMovie(MovieVO movieBean) throws Exception;
	
	public int updateMovie(MovieVO movieBean) throws Exception;
	
	public int deleteMovie(MovieVO movieBean) throws Exception;
	
}