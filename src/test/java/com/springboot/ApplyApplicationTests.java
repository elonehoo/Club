package com.springboot;

import com.springboot.beans.SignPull;
import com.springboot.beans.Cipher;
import com.springboot.mapper.SignPullMapper;
import com.springboot.mapper.CipherMapper;
import com.springboot.mapper.MessageMapper;
import com.springboot.service.CipherService;
import com.springboot.service.MessageService;
import com.springboot.utils.RegesUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private CipherService cipherService;

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
    @Test
    void contextLoads5() {
        String ni = "2019002125";
        Map<String,Object> map = new HashMap<>();

        map.put("memberNumber",ni);
        map.put("memberCipher",ni);

        List<Cipher> list = cipherMapper.selectByMap(map);
        System.out.println(list.get(0));
    }
}
