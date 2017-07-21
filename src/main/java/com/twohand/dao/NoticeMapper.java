package com.twohand.dao;

import com.twohand.pojo.Notice;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    Notice selectByPrimaryKey(Integer id);

    int insert(Notice notice);

    int insertSelective(Notice notice);

    int updateByPrimaryKeySelective(Notice notice);

    int updateByPrimaryKeyWithBLOBs(Notice noticce);

    int updateBYPrimaryKey(Notice notice);
}
