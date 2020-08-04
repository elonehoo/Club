package com.springboot.service.Impl;

import com.springboot.beans.SignPull;
import com.springboot.mapper.SignPullMapper;
import com.springboot.service.SignPullService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SignPullServiceImpl implements SignPullService {

    @Resource
    private SignPullMapper signPullMapper;

    @Override
    public int getCheckInsert(SignPull check) {
        int insert = signPullMapper.insert(check);
        return insert;
    }

    @Override
    public SignPull getSignPullById(String uuid) {
        SignPull signPull = signPullMapper.selectById(uuid);
        return signPull;
    }

    @Override
    public String getRegistration(SignPull signPull) {
        //判断账号是否正在签到
        if (signPull.getCheckIS().equals( "maybe")){
            return "签到请求已经提交了,请联系管理员同意";
        }else if (signPull.getCheckIS().equals("true") ){
            return "正在签到，请不要重复提交签到请求";
        }
        //未签到，完成签到
        //获取当前的时间
        LocalTime time = LocalTime.now(); // get the current time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String format = time.format(formatter);
        signPull.setCheckIS("maybe");
        signPull.setCheckStart(format);
        int i = signPullMapper.updateById(signPull);
        if (i == 1) {
            return "签到已经提交!请联系管理员同意请求";
        }else {
            return "签到失败!请联系管理员";
        }
    }

    @Override
    public String getCheckOut(SignPull signPull) {
        //获取当前的时间
        LocalTime time = LocalTime.now(); // get the current time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String format = time.format(formatter);
        //进行签到时间计算
        //记录之前的总时间
        long totalTime = Long.valueOf(signPull.getCheckTime());
        SimpleDateFormat simpleFormat = new SimpleDateFormat("HH:mm");//如2016-08-10 20:40
        //把当前时间和要比较的时间转换为Date类型，目的在于得到这两个时间的毫秒值
        Date parse = null;
        Date now = null;
        try {
            parse = simpleFormat.parse(signPull.getCheckStart());
            now = simpleFormat.parse(format);
        } catch (Exception e) { }
        //获得这两个时间的毫秒值后进行处理(因为我的需求不需要处理时间大小，所以此处没有处理，可以判断一下哪个大就用哪个作为减数。)
        long diff = now.getTime() - parse.getTime();
        //此处用毫秒值除以分钟再除以毫秒既得两个时间相差的分钟数
        long minute = diff/60/1000;
        totalTime += minute;
        System.out.println(parse);
        System.out.println(now);
        System.out.println(totalTime);
        //判断是否可以进行结束签到
        if (signPull.getCheckIS().equals("maybe")){
            return "签到还未开始!请联系管理员,同意请求";
        }else if (signPull.getCheckIS().equals("false")){
            return "还没开始签到";
        }
        //修改
        signPull.setCheckIS("false");
        signPull.setCheckTerminate(format);
        signPull.setCheckTime(String.valueOf(totalTime));

        //完成修改
        int i = signPullMapper.updateById(signPull);
        if (i == 1){
            return "签退成功!已经成功签到" + totalTime + "分钟";
        }else {
            return "签退失败!请联系管理员";
        }
    }

    @Override
    public List<SignPull> getSignPullCheckIs(Map<String, Object> map) {
        List<SignPull> signPullList = signPullMapper.selectByMap(map);
        return signPullList;
    }

    @Override
    public String getSignPullUpdateCheckIs(SignPull signPull) {
        int i = signPullMapper.updateById(signPull);
        if (i == 1){
            return "请求成功";
        }else {
            return "请求失败";
        }

    }
}
