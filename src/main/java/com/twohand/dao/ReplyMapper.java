package com.twohand.dao;

import com.twohand.pojo.Reply;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply reply);

    int insertSelecctive(Reply reply);

    Reply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reply reply);

    int updateByPrimaryKeyWithBLOBs(Reply reply);

    int updateByPrimaryKey(Reply reply);
}
