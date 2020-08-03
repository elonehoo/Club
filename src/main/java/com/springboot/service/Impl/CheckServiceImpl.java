package com.springboot.service.Impl;

import com.springboot.beans.SignPull;
import com.springboot.mapper.SignPullMapper;
import com.springboot.service.CheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CheckServiceImpl implements CheckService {

    @Resource
    private SignPullMapper checkMapper;

    @Override
    public int getCheckInsert(SignPull check) {
        int insert = checkMapper.insert(check);
        return insert;
    }
}
