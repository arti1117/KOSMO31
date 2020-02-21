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
		
		MovieVO mBean = dao.selectMovie(bean);
		String hall = mBean.getHall();
		
		//영화관에 따라 좌석이 다르게 나오게 하기
		if(hall.equals("A")){
			mBean.setSeatCntX(10);
			mBean.setSeatCntY(10);
		}else if(hall.equals("B")){
			mBean.setSeatCntX(7);
			mBean.setSeatCntY(10);
		}else if(hall.equals("C")){
			mBean.setSeatCntX(10);
			mBean.setSeatCntY(5);
		}
		
		return mBean;
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
