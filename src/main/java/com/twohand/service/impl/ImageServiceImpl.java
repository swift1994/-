package com.twohand.service.impl;

import com.twohand.dao.ImageMapper;
import com.twohand.pojo.Image;
import com.twohand.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService{

    @Resource
    private ImageMapper imageMapper;

    public int insert(Image image){
       return  imageMapper.insert(image);
    }

    public List<Image> getImagesByGoodsPrimaryKey(Integer goodsId) {
        List<Image> imageList = imageMapper.selectByGoodsPriamryKey(goodsId);
        return imageList;
    }

    public int  deleteImagesByGoodsPrimaryKey(Integer goodsId){
        return imageMapper.deleteImagesByGoodsPrimaryKey(goodsId);
    }
}
