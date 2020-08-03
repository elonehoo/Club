package com.springboot.service;

import com.springboot.beans.Message;

public interface MessageService {
    /**
     * 查找这个学号的学生的个人信息
     * @param number
     * @return
     */
    int getMessageById(String number);

    /**
     * 将学生的信息添加进入数据库
     * @param message
     * @return
     */
    int getMessageInsert(Message message);

    /**
     * 通过学号查找学生的个人信息
     * @param account
     * @return
     */
    Message getMessageByNumber(String account);
}
