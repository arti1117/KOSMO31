package com.kosmo.dao;

import java.util.List;

import com.kosmo.dto.MemberVO;

public interface MemberDAO {
		
	/** 로그인 진행 **/
	public int selectLoginMember(MemberVO bean) throws Exception;
	
	/* 한명의 회원을 조회한다. */
	public MemberVO selectMember(MemberVO bean) throws Exception;
	
	/* 중복 이메일을 확인한다. */
	public MemberVO emailcheck(MemberVO bean) throws Exception;
	
	/* 전 회원 목록 출력*/
	public List<MemberVO> selectMemberList(MemberVO bean) throws Exception;

	/* joinMember : 회원가입*/
	public int insertMember(MemberVO bean) throws Exception;
	
	/** 회원정보 수정 **/
	public int updateMember(MemberVO bean) throws Exception;

	/** 회원정보 삭제 **/
	public int deleteMember(MemberVO bean) throws Exception;
	
}