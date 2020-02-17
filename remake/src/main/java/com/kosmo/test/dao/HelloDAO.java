package com.kosmo.test.dao;

import java.util.List;

import com.kosmo.test.dto.HelloVO;
 
public interface HelloDAO {
    
    public List<HelloVO> selectMember() throws Exception;
}
