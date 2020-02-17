package com.kosmo.dao;

import java.util.List;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kosmo.dto.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.kosmo.mapper.memberMapper";
    
	@Override
	public int selectLoginMember(MemberVO bean) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".selectLoginMember");
		
	}
	
	@Override
	public MemberVO selectMember(MemberVO bean) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".selectMember");
		
	}
	
	@Override
	public MemberVO emailcheck(MemberVO bean) throws Exception {
		
		return sqlSession.selectOne(Namespace + ".emailcheck");
	}
	
	@Override
	public List<MemberVO> selectMemberList(MemberVO bean) throws Exception {
		
		return sqlSession.selectList(Namespace + ".selectMemberList");
		
	}

	@Override
	public int insertMember(MemberVO bean) throws Exception {
		
		return sqlSession.insert(Namespace + ".insertMember");
		
	}
	
	@Override
	public int updateMember(MemberVO bean) throws Exception {
		
		return sqlSession.update(Namespace + ".updateMember");
		
	}

	@Override
	public int deleteMember(MemberVO bean) throws Exception {
		
		return sqlSession.delete(Namespace + ".deleteMember");
	}
	
	
}
