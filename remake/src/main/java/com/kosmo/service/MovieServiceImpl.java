package com.kosmo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kosmo.bean.PagingBean;
import com.kosmo.dao.MovieDAO;
import com.kosmo.dto.MovieVO;

@Service
public class MovieServiceImpl implements MovieService {

	@Inject
	private MovieDAO dao;
	
	
	@Override
	public int selectListTotCount(PagingBean bean) throws Exception {
		return dao.selectListTotCount(bean);
	}
	
	@Override
	public List<MovieVO> selectMovieList(PagingBean bean) throws Exception {
		return dao.selectMovieList(bean);
	}
	
	@Override
	public MovieVO selectMovie(MovieVO bean) throws Exception {
		return dao.selectMovie(bean);
	}
	
	@Override
	public int insertMovie(MovieVO bean) throws Exception {
		return dao.insertMovie(bean);
	}
	
	@Override
	public int updateMovie(MovieVO bean) throws Exception {
		return dao.updateMovie(bean);
	}
	
	@Override
	public int deleteMovie(MovieVO bean) throws Exception {
		return dao.deleteMovie(bean);
	}
}
