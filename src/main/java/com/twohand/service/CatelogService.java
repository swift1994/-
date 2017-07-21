package com.twohand.service;

import com.twohand.pojo.Catelog;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface CatelogService {

    List<Catelog> getAllCatelog();

    int getCount(Catelog catelog);

    Catelog selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Catelog catelog);

    int updateCatelogNum(Integer id,Integer number);

}
