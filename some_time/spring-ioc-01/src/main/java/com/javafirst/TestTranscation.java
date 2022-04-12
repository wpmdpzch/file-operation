package com.javafirst;

import com.javafirst.bean.TVSeriesBean;
import com.javafirst.dao.GoodsDao;
import com.javafirst.dao.TVSeriesDao;
import com.javafirst.data.Goods;
import com.javafirst.service.GoodsService;
import com.javafirst.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TestTranscation {

    @Test
    public void testSqlConnection() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        System.out.println("测试 MyBatis 链接数据库驱动：" + sqlSession.toString());
    }

    /**
     * 查询所有记录
     */
    @Test
    public void testSelectGoodsAll() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        List<Goods> goodsList = goodsDao.selectGoodsAll();
        sqlSession.close();

        System.out.println("查询所有货物：" + goodsList.size());
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }

    @Test
    public void testInsertGoodsByObject() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);

        Goods goods = new Goods();
        goods.setAccount(50);
        goods.setName("手机5G");
        goods.setPrice(5299f);

        goodsDao.insertGoods(goods);
        goods = new Goods();
        goods.setAccount(10);
        goods.setName("显示屏4K");
        goods.setPrice(2299f);
        goodsDao.insertGoods(goods);

        sqlSession.commit();
        sqlSession.close();

        testSelectGoodsAll();
    }
    @Test
    public void spring_transaction() {
        String config = "beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        GoodsService buyGoods = (GoodsService) context.getBean("buyGoods");
        buyGoods.buyGoods(4, 2);

        System.out.println("测试方法执行完成了!");
    }



}
