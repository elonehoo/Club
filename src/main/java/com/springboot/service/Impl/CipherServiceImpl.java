package com.springboot.service.Impl;

import com.springboot.beans.Cipher;
import com.springboot.mapper.CipherMapper;
import com.springboot.service.CipherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CipherServiceImpl implements CipherService {

    @Resource
    private CipherMapper cipherMapper;

    @Override
    public int getCipherInsert(Cipher cipher) {
        int insert = cipherMapper.insert(cipher);
        return insert;
    }

    @Override
    public Cipher getCipherLogin(String account, String password) {
        Map<String , Object> map = new HashMap<>();
        map.put("memberNumber",account);
        map.put("memberCipher",password);
        List<Cipher> list = cipherMapper.selectByMap(map);
        if (list.size() == 1){
            return list.get(0);
        }else {
            return null;
        }
    }
}
