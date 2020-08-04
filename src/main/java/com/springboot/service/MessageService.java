package com.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Message> getMessagePagin(int pageNum, int pageSize);

    /**
     * 删除用户
     * @param uuid
     * @return
     */
    String getMessageDeleteById(String uuid);

    /**
     * 查找该uuid的学生
     * @param uuid
     * @return
     */
    Message getMessageByID(String uuid);

    /**
     * 修改该学生的信息
     * @param message
     * @return
     */
    String getMessageUpdateById(Message message);
}
