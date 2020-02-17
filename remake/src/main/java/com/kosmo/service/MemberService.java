package com.kosmo.service;

import java.util.List;

import com.kosmo.dto.MemberVO;

public interface MemberService {
	
	public int selectLoginMember(MemberVO bean) throws Exception;
	
	public MemberVO selectMember(MemberVO bean) throws Exception;
	
	public MemberVO emailcheck(MemberVO bean) throws Exception;
	
	public List<MemberVO> selectMemberList(MemberVO bean) throws Exception;

	public int insertMember(MemberVO bean) throws Exception;
	
	public int updateMember(MemberVO bean) throws Exception;

	public int deleteMember(MemberVO bean) throws Exception;

}
