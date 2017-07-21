package com.twohand.dao;

import com.twohand.pojo.Image;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface ImageMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteImagesByGoodsPrimaryKey(Integer id);

    int insert(Image image);

    int insertSelective(Image image);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Integer id);

    int updateByPrimaryKeyWithBLOBs(Image image);

    int updateByPrimaryKey(Integer id);

    List<Image> selectByGoodsPriamryKey(Integer id);
}
