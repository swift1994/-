package com.twohand.controller;

import com.twohand.pojo.Goods;
import com.twohand.pojo.GoodsExtend;
import com.twohand.pojo.Image;
import com.twohand.pojo.User;
import com.twohand.service.GoodsService;
import com.twohand.service.ImageService;
import com.twohand.service.UserService;
import com.twohand.util.DateUtil;
import com.twohand.util.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private ImageService imageService;

    @RequestMapping("/addUser2")
    public String addUser(HttpServletRequest request, @ModelAttribute("user")User user1){
        String url=request.getHeader("Referer");
        User user=userService.getUserByPhone(user1.getPhone());
        if(user==null){
            String t= DateUtil.getNowTime();
            String str= MD5.md5(user1.getPassword());
            user1.setCreateAt(t);
            user1.setPassword(str);
            user1.setGoodsNum(0);
            userService.addUser(user1);
        }
        return "redirect:"+url;

    }
    @RequestMapping("/addUser")
    public User addUser23(HttpServletRequest request, @ModelAttribute("user")User user1){
        String url=request.getHeader("Referer");
        User user2=userService.getUserByPhoneAndName(user1.getPhone(),user1.getUsername());
        if(user2==null){
            String t=DateUtil.getNowTime();
            String str2=MD5.md5(user1.getPassword());
            user2.setCreateAt(t);
            user2.setPassword(str2);
            user2.setGoodsNum(0);
            userService.addUser(user2);
        }
        return user2;
    }
    @RequestMapping("/login")
    public ModelAndView loginValidate(HttpServletRequest request, HttpServletResponse response, User user , ModelMap modelMap){
        User cur_user=userService.getUserByPhone(user.getPhone());
        String url=request.getHeader("Referer");
        if(cur_user!=null){
            String pwd=MD5.md5(user.getPassword());
            if(pwd.equals(cur_user.getPassword())){
                request.getSession().setAttribute("cur_user",cur_user);
                return new ModelAndView("redirect:"+url);
            }
        }
        return new ModelAndView("redirect:"+url);
    }

    @RequestMapping("/changeName")
    public ModelAndView changeName(HttpServletRequest request,User user,ModelMap modelMap){
        String url=request.getHeader("Referer");
        User cur_user=(User)request.getSession().getAttribute("cur_user");
        cur_user.setUsername(user.getUsername());
        userService.updateUserName(cur_user);
        request.getSession().setAttribute("cur_user",cur_user);
        return new ModelAndView("redirect:"+url);
    }
    @RequestMapping("/updateInfo")
    public ModelAndView updateInfo(HttpServletRequest request,User user,ModelMap modelMap){
        User cur_user=(User)request.getSession().getAttribute("cur_user");
        cur_user.setUsername(user.getUsername());
        cur_user.setQq(user.getQq());
        userService.updateUserName(cur_user);
        request.getSession().setAttribute("cur_user",cur_user);
        return  new ModelAndView("redirect:/user/basic");
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("cur_user",null);
        return "redirect:/goods/homeGoods";
    }
    @RequestMapping("/home")
    public String home(){
        return "/user/home";
    }
    @RequestMapping("/basic")
    public String basic(){
        return "/user/basic";

    }
    @RequestMapping("/allGoods")
    public ModelAndView goods(HttpServletRequest request){
        User cur_user=(User)request.getSession().getAttribute("cur_user");
        Integer userId=cur_user.getId();
        List<Goods> goodsList=goodsService.getGoodsByUserId(userId);
        List<GoodsExtend> goodsAndImage=new ArrayList<GoodsExtend>();
        for(int i=0;i<goodsList.size();i++){
            GoodsExtend goodsExtend=new GoodsExtend();
            Goods goods=goodsList.get(i);
            List<Image>  images=imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(images);
            goodsAndImage.add(i,goodsExtend);
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("goodsAndImage",goodsAndImage);
        mv.setViewName("/user/goods");
        return mv;
    }

}
