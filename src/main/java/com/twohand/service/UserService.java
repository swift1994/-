package com.twohand.service;

import com.twohand.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface UserService {

    public void addUser(User user);

    User getUserByPhone(String phone);

    void updateUserName(User user);

    int updateGoodsNum(Integer id,Integer goodsNum);

    User selectByPrimaryKey(Integer id);

    List<User> getPageUser(Integer page,Integer pageSize,String username);

    int getUserNum(String username);

    User getUserByPhoneAndName(String phone,String name);
}
