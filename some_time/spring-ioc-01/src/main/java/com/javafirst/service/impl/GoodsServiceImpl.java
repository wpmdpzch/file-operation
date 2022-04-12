package com.javafirst.service.impl;

import com.javafirst.dao.GoodsDao;
import com.javafirst.dao.SaleDao;
import com.javafirst.data.Goods;
import com.javafirst.data.Sale;
import com.javafirst.service.GoodsService;
import com.javafirst.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class GoodsServiceImpl implements GoodsService {
    SaleDao saleDao;
    GoodsDao goodsDao;

//    @Transactional(
//            propagation = Propagation.REQUIRED,
//            isolation = Isolation.DEFAULT,
//            timeout = 12,
//            readOnly = false,
//            rollbackFor = {NullPointerException.class, IndexOutOfBoundsException.class})
    @Override
    public void buyGoods(Integer gid, Integer num) {
        System.out.println("buyGoods方法开始执行了...");
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        goodsDao = sqlSession.getMapper(GoodsDao.class);
        saleDao = sqlSession.getMapper(SaleDao.class);

        // 先插入记录 再减掉库存
        Sale sale = new Sale();
        sale.setGid(gid);
        sale.setNum(num);

        saleDao.insertSale(sale);
        sqlSession.commit();

        // 减掉库存之前 先判断库存数是否大于等于购买数量，避免更新后出现负库存
        Goods goods = goodsDao.selectById(gid);

        if (null == goods || gid > 1002 || gid < 0) {
            throw new NullPointerException("商品不存在");
        }
        if (goods.getAccount() < num) {
            throw new IndexOutOfBoundsException("库存不足");
        }

        // 更新库存
        Goods buyGoods = new Goods();
        buyGoods.setId(gid);
        buyGoods.setAccount(num);

        goodsDao.updateAccount(buyGoods);
        sqlSession.commit();

        sqlSession.close();

    }
}
