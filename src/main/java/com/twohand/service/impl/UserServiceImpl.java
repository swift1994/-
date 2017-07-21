package com.twohand.service.impl;

import com.github.pagehelper.PageHelper;
import com.twohand.dao.UserMapper;
import com.twohand.pojo.User;
import com.twohand.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.insert(user);
    }

    public User getUserByPhone(String phone){
        User user=userMapper.getUserByPhone(phone);

        return user;
    }
    public void updateUserName(User user){
        userMapper.updateByPrimaryKey(user);

    }
    public int updateGoodsNum(Integer id,Integer goodsNum){
        return userMapper.updateGoodsNum(id,goodsNum);
    }

    public User selectByPrimaryKey(Integer id){
        User user=userMapper.selectByPrimaryKey(id);
        return user;
    }
    public List<User> getPageUser(Integer page,Integer pageSize,String username){
        PageHelper.startPage(page,pageSize);
        List<User> data =userMapper.getUserList(username);
        System.out.println("UserServiceImpl:"+data.size());
        return data;
    }

    public int getUserNum(String username){
        List<User> users=userMapper.getUserList(username);
        return users.size();
    }

    public User getUserByPhoneAndName(String phone, String name) {
        User user=userMapper.getUserByPhoneAndName(phone,name);

        return user;
    }


}
