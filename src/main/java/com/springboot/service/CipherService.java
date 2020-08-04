package com.springboot.service;

import com.springboot.beans.Cipher;

public interface CipherService {
    /**
     * 将学生的登录信息添加进入数据库
     * @param cipher
     * @return
     */
    int getCipherInsert(Cipher cipher);

    /**
     * 判断账号和密码是否正确
     * @param account
     * @param password
     * @return
     */
    Cipher getCipherLogin(String account, String password);

    /**
     * 查找这个学生的账号和密码
     * @param uuid
     * @return
     */
    Cipher getCipherById(String uuid);

    /**
     * 修改这个学生的密码
     * @param cipher
     * @param oldPassword
     * @param new_password_one
     * @param new_password_two
     * @return
     */
    String getCipherUpdate(Cipher cipher, String oldPassword, String new_password_one, String new_password_two);
}
