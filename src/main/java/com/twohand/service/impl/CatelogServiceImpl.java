package com.twohand.service.impl;

import com.twohand.dao.CatelogMapper;
import com.twohand.pojo.Catelog;
import com.twohand.service.CatelogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
@Service("catelogService")
public class CatelogServiceImpl implements CatelogService {
    @Resource
    private CatelogMapper catelogMapper;

    public int getCount(Catelog catelog){
        return catelogMapper.getCount(catelog);
    }
    public List<Catelog> getAllCatelog(){
        return catelogMapper.getAllCatelog();
    }
    public Catelog selectByPrimaryKey(Integer id){
        return catelogMapper.selectByPrimaryKey(id);
    }
    public int updateByPrimaryKey(Catelog catelog){
        return catelogMapper.updateByPrimaryKey(catelog);
    }
    public int updateCatelogNum(Integer id,Integer number){
        return catelogMapper.updateCatelogNum(id,number);

    }



}
