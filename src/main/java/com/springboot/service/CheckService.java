package com.springboot.service;

import com.springboot.beans.SignPull;

public interface CheckService {
    /**
     * 将学生的签到信息添加进入数据库
     * @param check
     * @return
     */
    int getCheckInsert(SignPull check);
}
