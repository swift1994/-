package com.twohand.util;

import com.twohand.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public class UserGrid {
    private List<User> data;
    private int total;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal(){
        return total;

    }
}
