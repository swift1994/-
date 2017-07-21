package com.twohand.dao;

import com.twohand.pojo.Comments;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface CommentsMapper {

    int deleteByprimarykey(Integer id);

    int updateByPrimaryKey(Integer id);

    Comments selectByPrimaryKey(Integer id);

    int insert(Comments comments);

    int insertSelective(Comments comments);

    int updateByPrimaryKeySelective(Comments comments);

    int updateByPrimmaryKeyWithBLOBs(Comments comments);


}
