package com.kosmo.test.dao;

import java.util.List;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 
import com.kosmo.test.dto.HelloVO;
 
@Repository
public class HelloDAOImpl implements HelloDAO {
 
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.kosmo.test.mapper.helloMapper";
    
    @Override
    public List<HelloVO> selectMember() throws Exception {
 
        return sqlSession.selectList(Namespace+".selectMember");
    }
 
}

