package com.twohand.controller;

import com.twohand.pojo.User;
import com.twohand.service.UserService;
import com.twohand.util.UserGrid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
@Controller
public class MainController {
    @Resource
    private UserService userService;
    @RequestMapping("/api/v1/users")
    @ResponseBody
    public UserGrid getuserList(@RequestParam(value="page",required =false)Integer page,
                                @RequestParam(value="pageSize",required =false )Integer pageSize,
                                @RequestParam(value="username",required = false) String username){
        System.out.println("username:"+username);
        int total=userService.getUserNum(username);
        String pageStr=pageSize+"";
        String pageSizeStr=pageSize+"";
        if("".equals(pageStr)){
            page=1;
        }
        if("".equals(pageSizeStr))
            pageSize=10;
        List<User> data=userService.getPageUser(1,10,username);
        System.out.println("data:"+data.size());
        UserGrid userGrid=new UserGrid();
        userGrid.setData(data);
        userGrid.setTotal(total);
        return userGrid;
    }

}
