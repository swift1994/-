package com.twohand.dao;

import com.twohand.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public interface GoodsMapper {
    /*
    通过主键删除
    @param id
    @return
     */
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByprimaryKey(Goods record);

    List<Goods> selectAllGoods();

    List<Goods> searchGoods(@Param("name") String name,@Param("describle") String describle);

    List<Goods> selectByCatelog(@Param("catelog_id") Integer catelog_id,@Param("name") String name,@Param("describle") String describle);

    List<Goods> selectBYDate(int page,int maxResults);

    List<Goods> selectByCatelogOrderByDate(@Param("catelogId") Integer catelog,@Param("limit") Integer limit);

    List<Goods> getGoodsByUserId(Integer user_id);

}
