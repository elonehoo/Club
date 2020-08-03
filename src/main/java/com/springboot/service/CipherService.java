package com.springboot.service;

import com.springboot.beans.Cipher;

public interface CipherService {
    /**
     * 将学生的登录信息添加进入数据库
     * @param cipher
     * @return
     */
    int getCipherInsert(Cipher cipher);
}
