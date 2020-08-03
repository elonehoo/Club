package com.springboot.service.Impl;

import com.springboot.beans.Message;
import com.springboot.mapper.MessageMapper;
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
}
