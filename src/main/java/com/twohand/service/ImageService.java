package com.twohand.service;

import com.twohand.pojo.Image;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public interface ImageService {
    int insert(Image image);

    List<Image> getImagesByGoodsPrimaryKey(Integer goodsId);

    int deleteImagesByGoodsPrimaryKey(Integer goodsId);
}
