package com.kosmo.test.service;

import java.util.List;

import com.kosmo.test.dto.HelloVO;
 
public interface HelloService {
    
    public List<HelloVO> selectMember() throws Exception;
}
 
