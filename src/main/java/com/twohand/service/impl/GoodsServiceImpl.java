package com.twohand.service.impl;

import com.twohand.dao.GoodsMapper;
import com.twohand.pojo.Goods;
import com.twohand.service.GoodsService;
import com.twohand.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    public int addGoods(Goods goods,Integer duration){
        String startTime= DateUtil.getNowDay();
        String endTime=DateUtil.getLastTime(startTime,duration);
        String polishTime=startTime;
        goods.setPolishTime(polishTime);
        goods.setEndTime(endTime);
        goods.setStartTime(startTime);
        return goodsMapper.insert(goods);
    }
    public Goods getGoodsByPrimaryKey(Integer goodsId){
        Goods goods=goodsMapper.selectByPrimaryKey(goodsId);
        return goods;
    }
    public void deleteGoodsByPrimaryKey(Integer goodsId){
        this.goodsMapper.deleteByPrimaryKey(goodsId);
    }
    public List<Goods> getAllGoods(){
        List<Goods> goods=this.goodsMapper.selectAllGoods();
        return goods;
    }
    public List<Goods> searchGoods(String name,String describle){
        List<Goods> goods=goodsMapper.searchGoods(name,describle);
        return goods;
    }
    public List<Goods> getGoodsByCatelog(Integer id,String name,String describle){
        List<Goods> goods=goodsMapper.selectByCatelog(id,name,describle);
        return goods;
    }
    public void updateGoodsByPrimaryKeyWithBLOBs(int goodsId,Goods goods){
        goods.setId(goodsId);
        this.goodsMapper.updateByPrimaryKeyWithBLOBs(goods);
    }
    public List<Goods> getGoodsByCatelogOrderByDate(Integer catelogId,Integer limit){
        List<Goods> goodsList=goodsMapper.selectByCatelogOrderByDate(catelogId, limit);
        return goodsList;
    }
    public List<Goods> getGoodsByUserId(Integer user_id){
        List<Goods> goodsList=goodsMapper.getGoodsByUserId(user_id);
        return goodsList;
    }




}
