package com.springboot.controller;

import com.springboot.beans.SignPull;
import com.springboot.beans.Cipher;
import com.springboot.beans.Message;
import com.springboot.service.CheckService;
import com.springboot.service.CipherService;
import com.springboot.service.MessageService;
import com.springboot.utils.RegesUtils;
import com.springboot.utils.UUIDUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
public class Apply {
    @Resource
    private CheckService checkService;
    @Resource
    private CipherService cipherService;
    @Resource
    private MessageService messageService;

    /**
     * 登录
     * @param map
     * @return
     */
    @PostMapping("/register")
    @CrossOrigin
    public String signIn(@RequestBody HashMap<String,Object> map){
        //获取前端页面通过JSON数据传来的值
        //学生姓名
        String name = (String) map.get("memberName");
        //学生学号
        String number = (String) map.get("memberNumber");
        //学生电话号码
        String phone = (String) map.get("memberPhone");
        //学生班级
        String clazz = (String) map.get("memberClazz");
        //判断姓名是否全是中文
        boolean chinese = RegesUtils.isChinese(name);
        if (chinese == false){
            return "名字含有了非法字符!请联系管理员";
        }
        //判断学号长度
        if (number.length() != 10){
            return "学号不正确!请联系管理员";
        }
        //判断电话号码
        boolean mobile = RegesUtils.isMobile(phone);
        if (mobile == false){
            return "电话号码不正确!请联系管理员";
        }
        //判断学号是否被注册了
        int isN = messageService.getMessageById(number);
        if ( isN == 0 ){
            return "学号已经被注册了!请联系管理员";
        }
        String id = UUIDUtils.getId();
        Message message = new Message(id,number,name,phone,clazz);
        Cipher cipher = new Cipher(id,number,number);
        SignPull signPull = new SignPull(id,number,"false","0","0","0");
        int isY = checkService.getCheckInsert(signPull);
        int isC = cipherService.getCipherInsert(cipher);
        int isM = messageService.getMessageInsert(message);
        if (isM == 1 && isY == 1 && isC == 1){
            return "注册成功!";
        }else {
            return "注册失败!请联系管理员";
        }

    }
}
