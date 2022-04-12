package com.javafirst.dao;

import com.javafirst.data.Goods;

import java.util.List;

public interface GoodsDao {
    void insertGoods(Goods goods) ;

    int updateAccount(Goods goods);

    Goods selectById(Integer gid);

    List<Goods> selectGoodsAll();
}