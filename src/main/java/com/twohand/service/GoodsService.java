package com.twohand.service;

import com.twohand.pojo.Goods;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface GoodsService {

    int addGoods(Goods goods,Integer duration);

    Goods getGoodsByPrimaryKey(Integer goodsId);

    void updateGoodsByPrimaryKeyWithBLOBs(int goodsId,Goods goods);

    void deleteGoodsByPrimaryKey(Integer id);

    List<Goods> getAllGoods();

    List<Goods> searchGoods(String name,String describle);

    List<Goods> getGoodsByCatelog(Integer id ,String name,String describle);

    List<Goods> getGoodsByCatelogOrderByDate(Integer catelogId,Integer limit);

    List<Goods> getGoodsByUserId(Integer user_is);
}
