package com.kosmo.dao;

import java.util.List;

import com.kosmo.bean.PagingBean;
import com.kosmo.dto.MovieVO;

public interface MovieDAO {
	
	public int selectListTotCount(PagingBean bean) throws Exception;
	
	public List<MovieVO> selectMovieList(PagingBean bean) throws Exception;

	public MovieVO selectMovie(MovieVO bean) throws Exception;
		
	public int insertMovie(MovieVO bean) throws Exception;
	
	public int updateMovie(MovieVO bean) throws Exception;
	
	public int deleteMovie(MovieVO bean) throws Exception;
	
}