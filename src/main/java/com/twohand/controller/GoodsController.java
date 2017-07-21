package com.twohand.controller;

import com.twohand.pojo.*;
import com.twohand.service.CatelogService;
import com.twohand.service.GoodsService;
import com.twohand.service.ImageService;
import com.twohand.service.UserService;
import com.twohand.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2017/6/28.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CatelogService catelogService;

    @RequestMapping("/homeGoods")
    public ModelAndView homeGoods()throws Exception{
        ModelAndView mv=new ModelAndView();
        int catelogSize=7;
        int goodsSize=6;
        for(int i=1;i<catelogSize;i++){
            List<Goods> goodsList=null;
            List<GoodsExtend> goodsAndImage=null;
            goodsList=goodsService.getGoodsByCatelogOrderByDate(i,goodsSize);
            goodsAndImage=new ArrayList<GoodsExtend>();
            for(int j=0;j<goodsList.size();j++){
                GoodsExtend goodsExtend =new GoodsExtend();
                Goods goods=goodsList.get(j);
                List<Image> images=imageService.getImagesByGoodsPrimaryKey(goods.getId());
                goodsExtend.setImages(images);
                goodsExtend.setGoods(goods);
                goodsAndImage.add(j,goodsExtend);
            }
            String key="catelog"+"Goods"+i;
            mv.addObject(key,goodsAndImage);
        }
        mv.setViewName("goods/homeGoods");
        return mv;
    }
    @RequestMapping("/search")
    public ModelAndView searchGoods(@RequestParam(value="str",required = false) String str)throws Exception{
        List<Goods> goodsList=goodsService.searchGoods(str,str);
        List<GoodsExtend> goodsExtendList=new ArrayList<GoodsExtend>();
        for(int i=0;i<goodsList.size();i++){
            GoodsExtend goodsExtend=new GoodsExtend();
            Goods goods=goodsList.get(i);
            List<Image> imageList=imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(imageList);
            goodsExtendList.add(i,goodsExtend);
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("goodsExtendList",goodsExtendList);
        mv.addObject("search",str);
        mv.setViewName("/goods/searchGoods");
        return mv;
    }
    @RequestMapping("/catelog/{id}")
    public ModelAndView catelogGoods(HttpServletRequest request,
                                     @PathVariable("id")Integer id,@RequestParam(value ="str",required = false)String str)throws Exception{
        List<Goods> goodslist=goodsService.getGoodsByCatelog(id,str,str);
        Catelog catelog=catelogService.selectByPrimaryKey(id);
        List<GoodsExtend> goodsExtendlist=new ArrayList<GoodsExtend>();
        for(int i=0;i<goodslist.size();i++){
            GoodsExtend goodsExtend=new GoodsExtend();
            Goods goods=goodslist.get(i);
            List<Image> imageList=imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setImages(imageList);
            goodsExtend.setGoods(goods);
            goodsExtendlist.add(i,goodsExtend);

        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("goodsExtendlist",goodsExtendlist);
        mv.addObject("catelog",catelog);
        mv.addObject("search",str);
        mv.setViewName("/goods/catelogGoods");
        return mv;
    }
    @RequestMapping("/goodsId/{id}")
    public ModelAndView getGoodsById(@PathVariable("id")Integer id,@RequestParam(value = "str",required = false)String str)throws  Exception{
        Goods goods=goodsService.getGoodsByPrimaryKey(id);
        User seller=userService.selectByPrimaryKey(goods.getUserId());
        Catelog catelog=catelogService.selectByPrimaryKey(goods.getCatelogId());
        GoodsExtend goodsExtend=new GoodsExtend();
        List<Image> imageList=imageService.getImagesByGoodsPrimaryKey(id);
        goodsExtend.setGoods(goods);
        goodsExtend.setImages(imageList);
        ModelAndView mv=new ModelAndView();
        mv.addObject("goodExtend",goodsExtend);
        mv.addObject("seller",seller);
        mv.addObject("catelog",catelog);
        mv.addObject("search",str);
        mv.setViewName("/goods/detailGoods");
        return mv;
    }
    @RequestMapping("/editGoods/{id}")
    public ModelAndView editGoods(@PathVariable("id")Integer id) throws Exception{
        Goods goods=goodsService.getGoodsByPrimaryKey(id);
        List<Image> imageList=imageService.getImagesByGoodsPrimaryKey(id);
        GoodsExtend goodsExtend=new GoodsExtend();
        goodsExtend.setImages(imageList);
        goodsExtend.setGoods(goods);
        ModelAndView mv=new ModelAndView();
        mv.addObject("goodsExtend",goodsExtend);
        mv.setViewName("/goods/editGoods");
        return mv;
    }
    @RequestMapping("/editGoodsSubmit")
    public String editGoodsSubmit(HttpServletRequest request,Goods goods)throws Exception{
        User cur_user=(User)request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        String polishTime= DateUtil.getNowTime();
        goods.setPolishTime(polishTime);
        goodsService.updateGoodsByPrimaryKeyWithBLOBs(goods.getId(),goods);
        return "redirect:/user/allGoods";
    }
    @RequestMapping("/offGoods")
    public ModelAndView offGoods() throws Exception{
        return null;
    }
    @RequestMapping("/deleteGoods/{id}")
    public String deleteGoods(HttpServletRequest request,@PathVariable("id")Integer id)throws Exception{
        Goods goods=goodsService.getGoodsByPrimaryKey(id);
        User cur_user=(User)request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        int number=cur_user.getGoodsNum();
        Integer catelog_id=goods.getCatelogId();
        Catelog catelog=catelogService.selectByPrimaryKey(catelog_id);
        catelogService.updateCatelogNum(catelog_id,catelog.getNumber()-1);
        cur_user.setGoodsNum(number-1);
        userService.updateGoodsNum(cur_user.getId(),number-1);
        request.getSession().setAttribute("cur_user",cur_user);
        imageService.deleteImagesByGoodsPrimaryKey(id);
        goodsService.deleteGoodsByPrimaryKey(id);
        return "redirect:/user/allGoods";
    }
    @RequestMapping("/publishGoods")
    public String pulishGoods(HttpServletRequest request){
        User cur_user=(User)request.getSession().getAttribute("cur_user");
        if(cur_user==null){
            return  "/goods/homeGoods";
        }else{
            return "/goods/pubGoods";
        }
    }
    @RequestMapping("publishGoodsSubmit")
    public String publishGoodsSubmit(HttpServletRequest request, Image img, Goods goods, MultipartFile image){
        User cur_user=(User)request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        int i=goodsService.addGoods(goods,10);
        int goodId=goods.getId();
        imageService.insert(img);
        int number=cur_user.getGoodsNum();
        Integer catelog_id=goods.getCatelogId();
        Catelog catelog=catelogService.selectByPrimaryKey(catelog_id);
        catelogService.updateCatelogNum(catelog_id,catelog.getNumber()+1);
        userService.updateGoodsNum(cur_user.getId(),number+1);
        cur_user.setGoodsNum(number+1);
        request.getSession().setAttribute("cur_user",cur_user);
        return "redirect:/user/allGoods";

    }
    @ResponseBody
    @RequestMapping("/uploadFile")
    public Map<String,Object> uploadFile(HttpSession session,MultipartFile myfile)throws Exception{
        String oldFileName=myfile.getOriginalFilename();
        String file_path=session.getServletContext().getRealPath("upload");
        if(myfile!=null &&oldFileName!=null&&oldFileName.length()>0){
            String newFileName= UUID.randomUUID()+oldFileName.substring(oldFileName.lastIndexOf("."));
            File newFile=new File(file_path+"/"+newFileName);
            myfile.transferTo(newFile);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("success","成功啦");
            map.put("imgUrl",newFileName);
            return map;
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("error","图片不合法");
            return map;
        }
    }




}
