package com.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.beans.Message;
import com.springboot.mapper.CipherMapper;
import com.springboot.mapper.MessageMapper;
import com.springboot.mapper.SignPullMapper;
import com.springboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private CipherMapper cipherMapper;
    @Resource
    private SignPullMapper signPullMapper;

    @Override
    /**
     * 0代表有
     * 1代表没有
     */
    public int getMessageById(String number) {
        Map<String,Object> map = new HashMap<>();
        map.put("memberNumber",number);
        List<Message> list = messageMapper.selectByMap(map);
        if (list.size() == 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int getMessageInsert(Message message) {
        int insert = messageMapper.insert(message);
        return insert;
    }

    @Override
    public Message getMessageByNumber(String account) {
        Map<String,Object> map = new HashMap<>();
        map.put("memberNumber",account);
        List<Message> list = messageMapper.selectByMap(map);
        Message message = list.get(0);
        return message;
    }

    @Override
    public IPage<Message> getMessagePagin(int pageNum, int pageSize) {
        IPage<Message> iPage = messageMapper.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<Message>());
        return iPage;
    }

    @Override
    public String getMessageDeleteById(String uuid) {
        int i = messageMapper.deleteById(uuid);
        int i1 = cipherMapper.deleteById(uuid);
        int i2 = signPullMapper.deleteById(uuid);
        if (i == 1 && i1 == 1 && i2 == 1){
            return "删除成功!";
        }else {
            return "删除失败";
        }
    }

    @Override
    public Message getMessageByID(String uuid) {
        Message message = messageMapper.selectById(uuid);
        return message;
    }

    @Override
    public String getMessageUpdateById(Message message) {
        int i = messageMapper.updateById(message);
        if (i == 1){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }
}
