package com.kosmo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kosmo.dao.MemberDAO;
import com.kosmo.dto.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO dao;
	
	
	@Override
	public int selectLoginMember(MemberVO bean) throws Exception {
		return dao.selectLoginMember(bean);
	}
	
	@Override
	public MemberVO selectMember(MemberVO bean) throws Exception {
		return dao.selectMember(bean);
	}
	
	@Override
	public MemberVO emailcheck(MemberVO bean) throws Exception {
		return dao.emailcheck(bean);
	}
	
	@Override
	public List<MemberVO> selectMemberList(MemberVO bean) throws Exception {
		return dao.selectMemberList(bean);
	}
	
	@Override
	public int insertMember(MemberVO bean) throws Exception {
		return dao.insertMember(bean);
	}
	
	@Override
	public int updateMember(MemberVO bean) throws Exception {
		return dao.insertMember(bean);
	}
	
	@Override
	public int deleteMember(MemberVO bean) throws Exception {
		return dao.deleteMember(bean);
	}
	
}
