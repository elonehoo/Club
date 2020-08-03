package com.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.beans.Cipher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CipherMapper extends BaseMapper<Cipher> {

}
