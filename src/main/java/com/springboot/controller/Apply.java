package com.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.beans.*;
import com.springboot.service.SignPullService;
import com.springboot.service.CipherService;
import com.springboot.service.MessageService;
import com.springboot.utils.RegesUtils;
import com.springboot.utils.Result;
import com.springboot.utils.UUIDUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class Apply {
    @Resource
    private SignPullService signPullService;
    @Resource
    private CipherService cipherService;
    @Resource
    private MessageService messageService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 用户操作模式
     */



    /**
     * 注册
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
        //创建UUID
        String id = UUIDUtils.getId();
        //将信息插入数据库
        Message message = new Message(id,number,name,phone,clazz);
        Cipher cipher = new Cipher(id,number,number);
        SignPull signPull = new SignPull(id,number,"false","0","0","0");
        int isY = signPullService.getCheckInsert(signPull);
        int isC = cipherService.getCipherInsert(cipher);
        int isM = messageService.getMessageInsert(message);
        //判断是否插入成功
        if (isM == 1 && isY == 1 && isC == 1){
            return "注册成功!";
        }else {
            return "注册失败!请联系管理员";
        }
    }

    /**
     * 登录
     * @param map
     * @return
     */
    @PostMapping("/login")
    @CrossOrigin
    public Result login(@RequestBody HashMap<String,Object> map){

        //获取前端页面通过JSON数据传来的值
        String account = (String) map.get("account");
        String password = (String) map.get("password");
        //查找是否正确
        Cipher cipher = cipherService.getCipherLogin(account,password);
        if (cipher == null){
//            return "登录失败!账号或者密码错误";
            return new Result(null,"登录失败!账号或者密码错误",104);
        }
        //登录成功，将数据查找
        Message message = messageService.getMessageByNumber(account);
        Student student = new Student(message.getMemberUUID(),account,message.getMemberName());
        String token = UUID.randomUUID() + "";
        redisTemplate.opsForValue().set(token,student);
        return new Result(token,"登录成功!",100);
    }

    /**
     * 判断登录的用户
     * @param map
     * @return
     */
    @PostMapping("/session")
    @CrossOrigin
    public Result getSession(@RequestBody HashMap<String,Object> map){
        String token = (String) map.get("token");
        token = token == null ? "" : token;
        Student student = (Student) redisTemplate.opsForValue().get(token);
        if (student == null){
            return new Result(null,"获取登录用户信息失败",104);
        }
        redisTemplate.expire(token,30L, TimeUnit.MINUTES);
        return new Result(student,"获取登录用户信息成功",100);
    }

    /**
     * 开始签到
     * @param map
     * @return
     */
    @PostMapping("/registration")
    @CrossOrigin
    public Result getRegistration(@RequestBody HashMap<String,Object> map) {
        String uuid = (String) map.get("uuid");
        SignPull signPull = signPullService.getSignPullById(uuid);
        String value = signPullService.getRegistration(signPull);
        return new Result(value, "签到请求", 100);
    }

    /**
     * 签到结束
     * @param map
     * @return
     */
    @PostMapping("/checkOut")
    @CrossOrigin
    public Result getCheckOut(@RequestBody HashMap<String,Object> map) {
        String uuid = (String) map.get("uuid");
        SignPull signPull = signPullService.getSignPullById(uuid);
        String value = signPullService.getCheckOut(signPull);
        return new Result(value, "签退请求", 100);
    }

    /**
     * 修改密码
     */
    @PostMapping("/updCipher")
    @CrossOrigin
    public Result getUpdCipher(@RequestBody HashMap<String,Object> map) {
        String uuid = (String) map.get("uuid");
        String oldPassword = (String) map.get("oldPassword");
        String new_Password_One = (String) map.get("newPasswordOne");
        String new_Password_Two = (String) map.get("newPasswordTwo");
        Cipher cipher = cipherService.getCipherById(uuid);
        String value = cipherService.getCipherUpdate(cipher,oldPassword,new_Password_One,new_Password_Two);
        return new Result(value, "修改密码请求", 100);
    }
    /**
     * 退出
     */
    @PostMapping("/quit")
    @CrossOrigin
    public Result getQuit(@RequestBody HashMap<String,Object> map) {
        String token = (String) map.get("token");
        redisTemplate.delete(token);
        return new Result(null, "退出请求", 100);
    }



    /**
     * 管理模块
     */




    /**
     * 查看所有注册的学生信息
     */
    @PostMapping("/getAll")
    @CrossOrigin
    public Result getMessageAll(@RequestBody HashMap<String,Object> map) {
        int pageNum = (int) map.get("pageNum");
        int pageSize = 10;
        IPage<Message> messageList = messageService.getMessagePagin(pageNum,pageSize);
        return new Result(messageList, "查询全部", 100);
    }

    /**
     * 删除学生信息
     * @param map
     * @return
     */
    @PostMapping("/del")
    @CrossOrigin
    public Result getDeleteMessage(@RequestBody HashMap<String,Object> map) {
        String uuid = (String) map.get("uuid");
        String value = messageService.getMessageDeleteById(uuid);
        return new Result(value, "删除请求", 100);
    }

    /**
     * 查看修改的学生信息
     * @param map
     * @return
     */
    @PostMapping("/seiMessage")
    @CrossOrigin
    public Result getSessionMessage(@RequestBody HashMap<String,Object> map) {
        String uuid = (String) map.get("uuid");
        Message message = messageService.getMessageByID(uuid);
        return new Result(message,"修改请求",100);
    }

    /**
     * 修改学生的信息
     * @param map
     * @return
     */
    @PostMapping("/updMessage")
    @CrossOrigin
    public Result getUpdateMessage(@RequestBody HashMap<String,Object> map) {
        String uuid = (String) map.get("uuid");
        String number = (String) map.get("memberNumber");
        String name = (String) map.get("name");
        String phone = (String) map.get("phone");
        String clazz = (String) map.get("clazz");
        //创建修改后的学生信息
        Message message = new Message(uuid,number,name,phone,clazz);
        //进行修改操作
        String value = messageService.getMessageUpdateById(message);
        return new Result(value,"修改请求",100);
    }


    /**
     * 查看登录状态为maybe
     */
    @PostMapping("/seiMaybe")
    @CrossOrigin
    public Result getSessionMaybe() {
        String checkIs = "maybe";
        Map<String,Object> map = new HashMap<>();
        map.put("checkIS",checkIs);
        List<SignPull> signPullList = signPullService.getSignPullCheckIs(map);
        List<SignState> signStateList = new LinkedList<>();
        for (int i = 0 ; i < signPullList.size() ; i++){
            SignState signState = new SignState();
            signState.setUuid(signPullList.get(i).getMemberUUID());
            signState.setCheckIs(signPullList.get(i).getCheckIS());
            signState.setMemberNumber(signPullList.get(i).getMemberNumber());
            String uuid = signPullList.get(i).getMemberUUID();
            Message message = messageService.getMessageByID(uuid);
            signState.setMemberName(message.getMemberName());
            signStateList.add(signState);
        }
        return new Result(signStateList,"请求Maybe的用户",100);
    }

    /**
     * 查看登录状态为true
     */
    @PostMapping("/seiTrue")
    @CrossOrigin
    public Result getSessionTrue() {
        String checkIs = "true";
        Map<String,Object> map = new HashMap<>();
        map.put("checkIS",checkIs);
        List<SignPull> signPullList = signPullService.getSignPullCheckIs(map);
        List<SignState> signStateList = new LinkedList<>();
        for (int i = 0 ; i < signPullList.size() ; i++){
            SignState signState = new SignState();
            signState.setUuid(signPullList.get(i).getMemberUUID());
            signState.setCheckIs(signPullList.get(i).getCheckIS());
            signState.setMemberNumber(signPullList.get(i).getMemberNumber());
            String uuid = signPullList.get(i).getMemberUUID();
            Message message = messageService.getMessageByID(uuid);
            signState.setMemberName(message.getMemberName());
            signStateList.add(signState);
        }
        return new Result(signStateList,"请求true的用户",100);
    }
    /**
     * 同意签到请求
     */
    @PostMapping("/updMaybe")
    @CrossOrigin
    public Result getUpdateMaybe(@RequestBody HashMap<String,Object> map) {
        String uuid = (String) map.get("uuid");
        SignPull signPull = signPullService.getSignPullById(uuid);
        signPull.setCheckIS("true");
        String value = signPullService.getSignPullUpdateCheckIs(signPull);
        return new Result(value,"同意请求",100);
    }
    /**
     * 强制下线
     */
    @PostMapping("/updTrue")
    @CrossOrigin
    public Result getUpdateTrue(@RequestBody HashMap<String,Object> map) {
        String uuid = (String) map.get("uuid");
        SignPull signPull = signPullService.getSignPullById(uuid);
        signPull.setCheckIS("false");
        String value = signPullService.getSignPullUpdateCheckIs(signPull);
        return new Result(value,"下线请求",100);
    }
}
