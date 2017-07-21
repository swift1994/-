package com.twohand.dao;

import com.twohand.pojo.Catelog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface CatelogMapper {

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(Catelog catelog);

    Catelog selectByPrimaryKey(Integer id);

    int insert(Catelog catelog);

    int insertSelective(Catelog catelog);

    int updateByPrimaryKeySelective(Catelog catelog);

    int updateCatelogNum(@Param("id") Integer id,@Param("number") Integer number);

    List<Catelog> getAllCatelog();

    int getCount(Catelog catelog);



}
