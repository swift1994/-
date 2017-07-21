package com.twohand.dao;

import com.twohand.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    User selectByPrimaryKey(Integer id);

    int insert(User user);

    int insertSelective(User user);

    int updateByPrimayKeySelective(User user);

    int updateByPrimaryKey(User user);

    User getUserByPhone(String phone);

    int updateGoodsNum(@Param("id")Integer id,@Param("goodsNum")Integer goodsNum);

    public List<User> getUserList(@Param("username")String username);


    User getUserByPhoneAndName(@Param("phone")String phone,@Param("username") String name);
}
