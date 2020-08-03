package com.springboot;

import com.springboot.beans.SignPull;
import com.springboot.beans.Cipher;
import com.springboot.mapper.SignPullMapper;
import com.springboot.mapper.CipherMapper;
import com.springboot.mapper.MessageMapper;
import com.springboot.service.MessageService;
import com.springboot.utils.RegesUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApplyApplicationTests {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private SignPullMapper checkMapper;
    @Resource
    private CipherMapper cipherMapper;

    @Resource
    private MessageService messageService;

    @Test
    void contextLoads() {
        String name = "胡成晔1";
        boolean chinese = RegesUtils.isChinese(name);
        System.out.println(chinese == false);
    }
    @Test
    void contextLoads1() {
        String name = "18167117885";
        boolean mo = RegesUtils.isMobile(name);
        System.out.println(mo);
    }
    @Test
    void contextLoads2() {
        SignPull check = new SignPull("123","123","123","123","123","123");
        System.out.println(check);
        int insert = checkMapper.insert(check);
        System.out.println(insert);

    }
    @Test
    void contextLoads3() {
        Cipher cipher = new Cipher("123","123","123");
        int insert = cipherMapper.insert(cipher);
        System.out.println(insert);
    }
    @Test
    void contextLoads4() {
        int id = messageService.getMessageById("123");
        System.out.println(id);
    }
}
