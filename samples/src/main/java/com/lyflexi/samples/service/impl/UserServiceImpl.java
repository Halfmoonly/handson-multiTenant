package com.lyflexi.samples.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import com.lyflexi.samples.dao.UserMapper;
import com.lyflexi.samples.model.param.UserParam;
import com.lyflexi.samples.model.po.UserPo;
import com.lyflexi.samples.service.IUserService;

/**
 * @Author: ly
 * @Date: 2024/6/18 21:55
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo> implements IUserService {



    @Override
    public IPage<UserPo> pageSearch(IPage<UserPo> page, UserParam param) {
        return this.page(page,Wrappers.<UserPo>lambdaQuery().orderByDesc(UserPo::getId));
    }

    @Override
    public Boolean delete(Long id) {
        return this.removeById(id);
    }
}
