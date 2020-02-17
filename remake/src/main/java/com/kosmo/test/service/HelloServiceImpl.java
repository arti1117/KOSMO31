package com.kosmo.test.service;

import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;
 
import com.kosmo.test.dao.HelloDAO;
import com.kosmo.test.dto.HelloVO;
 
@Service
public class HelloServiceImpl implements HelloService {
 
    @Inject
    private HelloDAO dao;
    
    @Override
    public List<HelloVO> selectMember() throws Exception {
 
        return dao.selectMember();
    }
 
}

