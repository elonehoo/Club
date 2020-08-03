package com.springboot.service.Impl;

import com.springboot.beans.Cipher;
import com.springboot.mapper.CipherMapper;
import com.springboot.service.CipherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CipherServiceImpl implements CipherService {

    @Resource
    private CipherMapper cipherMapper;

    @Override
    public int getCipherInsert(Cipher cipher) {
        int insert = cipherMapper.insert(cipher);
        return insert;
    }
}
