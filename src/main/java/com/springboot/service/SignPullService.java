package com.springboot.service;

import com.springboot.beans.SignPull;

import java.util.List;
import java.util.Map;

public interface SignPullService {
    /**
     * 将学生的签到信息添加进入数据库
     * @param check
     * @return
     */
    int getCheckInsert(SignPull check);

    /**
     * 通过uuid查找该账号的学生
     * @param uuid
     * @return
     */
    SignPull getSignPullById(String uuid);

    /**
     * 完成签到
     * @param signPull
     * @return
     */
    String getRegistration(SignPull signPull);

    /**
     * 完成签退
     * @param signPull
     * @return
     */
    String getCheckOut(SignPull signPull);

    /**
     * 查找所有状态为maybe的学生
     * @param map
     * @return
     */
    List<SignPull> getSignPullCheckIs(Map<String, Object> map);

    /**
     * 同意某个人的签到请求
     * @param signPull
     * @return
     */
    String getSignPullUpdateCheckIs(SignPull signPull);
}
