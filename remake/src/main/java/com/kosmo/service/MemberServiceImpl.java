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
	public boolean selectLoginMember(MemberVO bean) throws Exception {
		int resVal = dao.selectLoginMember(bean);
				
		if(resVal == 1) {
			return true;
		}
		
		return false;
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
		//중복된 회원가입을 방지하기 위해서 회원을 조회한다.
		MemberVO mBean = dao.selectMember(bean);
		
		if(mBean == null) {
			
			String remail = bean.getEmail() + "@" + bean.getEmail_();
			bean.setEmail(remail);
			
			int intVal = dao.insertMember(bean);
			
			return intVal;
			
		}
		
		throw new Exception("회원가입에러");
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
