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

    @Override
    public Cipher getCipherById(String uuid) {
        Cipher cipher = cipherMapper.selectById(uuid);
        return cipher;
    }

    @Override
    public String getCipherUpdate(Cipher cipher, String oldPassword, String new_password_one, String new_password_two) {
        //判断两个新密码是否一致
        if (! new_password_one.equals(new_password_two)){
            return "新密码不一致,请重新输入";
        }
        //判断老密码是否正确
        if (! cipher.getMemberCipher().equals(oldPassword)){
            return "旧密码不正确!如若遗忘，请联系管理员";
        }
        //全部正确
        cipher.setMemberCipher(new_password_one);
        int i = cipherMapper.updateById(cipher);
        if (i == 1){
            return "密码修改成功";
        }else {
            return "密码修改失败!请联系管理员";
        }

    }
}
