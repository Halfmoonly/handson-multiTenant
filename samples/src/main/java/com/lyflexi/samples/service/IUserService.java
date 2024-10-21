package com.lyflexi.samples.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyflexi.samples.model.param.UserParam;
import com.lyflexi.samples.model.po.UserPo;


/**
 * @Author: ly
 * @Date: 2024/6/18 21:55
 */
public interface IUserService extends IService<UserPo> {
    IPage<UserPo> pageSearch(IPage<UserPo> page, UserParam param);

    Boolean delete(Long id);
}
