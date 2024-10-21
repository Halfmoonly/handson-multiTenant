package com.lyflexi.samples.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyflexi.samples.model.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ly
 * @Date: 2024/6/18 21:52
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPo> {
}
