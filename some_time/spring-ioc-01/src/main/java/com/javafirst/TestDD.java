package com.javafirst;

import com.javafirst.bean.TVSeriesBean;
import com.javafirst.dao.TVSeriesDao;
import com.javafirst.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TestDD {

    @Test
    public void testSqlConnection() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        System.out.println("测试 MyBatis 链接数据库驱动：" + sqlSession.toString());
    }

    /**
     * 查询所有记录
     */
    @Test
    public void testSelectTVSeriesAll() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);
        List<TVSeriesBean> tvSeriesBeans = tvSeriesDao.selectTVSeriesAll();
        sqlSession.close();

        System.out.println("查询所有记录(电视剧)：" + tvSeriesBeans.size());
        for (TVSeriesBean tvSeries : tvSeriesBeans) {
            System.out.println(tvSeries);
        }
    }

    @Test
    public void testAddTVSeriesOne() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);

        tvSeriesDao.addTVSeriesOne("AA", "jin yong", 1);
        sqlSession.commit();
        sqlSession.close();

        System.out.println("添加记录成功！");
        testSelectTVSeriesAll();
    }

    @Test
    public void testAddTVSeriesOneAnother() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);

        TVSeriesBean tvSeriesBean = new TVSeriesBean();
        tvSeriesBean.setTvTitle("BB");
        tvSeriesBean.setTvSubTitle("jj");
        tvSeriesBean.setTvType(2);

        tvSeriesDao.addTVSeriesObject(tvSeriesBean);
        sqlSession.commit();
        sqlSession.close();

        System.out.println("添加记录成功！");

        testSelectTVSeriesAll();
    }

    /**
     * 删除一条记录 根据ID
     */
    @Test
    public void testDeleteTVSeriesBeanById() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);

        tvSeriesDao.deleteTVSeriesBeanById(3);
        sqlSession.commit();
        sqlSession.close();

        System.out.println("删除一条记录成功！!");
        testSelectTVSeriesAll();
    }

    /**
     * 修改指定记录的数据
     */
    @Test
    public void testUpdateTVSeriesBeanTitleById() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);

        tvSeriesDao.updateTVSeriesBeanTitleById(4, "《新版射雕英雄传》");
        sqlSession.commit();
        sqlSession.close();

        System.out.println("update一条记录成功！!");
        testSelectTVSeriesAll();
    }

    /**
     * 动态SQL-if 测试
     */
    @Test
    public void testDynamicSQL_if(){
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);

        Map<String,Object> params = new HashMap<>();
        //通过标题或者类型来查找数据
//        params.put("title","<<人民的名义>>");
        params.put("type",2);

        List<TVSeriesBean> beanList = tvSeriesDao.selectByDynamicSQL_if(params);

        for(TVSeriesBean tvSeriesBean : beanList){
            System.out.println(tvSeriesBean);
        }

        sqlSession.close();
    }

    /**
     * 动态SQL-choose 测试
     */

    @Test
    public void testSelectDynamicSQLChoose(){
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);

        Map<String,Object> params = new HashMap<>();
        params.put("title", "xxx");
        params.put("type", 12);
        // 条件都不满足 则取默认值
        params.put("id", 2);

        List<TVSeriesBean> beanList = tvSeriesDao.selectByDynamicSQL_choose(params);

        for(TVSeriesBean tvSeriesBean : beanList){
            System.out.println(tvSeriesBean);
        }

        sqlSession.close();
    }

    /**
     * 动态SQL测试，foreach 基本数据类型
     */
    @Test
    public void testSelectDynamicSQLForeach(){
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);

        List<Integer> ids = new ArrayList<Integer>();
        ids.add(6);
        ids.add(10);
        ids.add(12);

        List<TVSeriesBean> beanList = tvSeriesDao.selectByDynamicSQL_foreach(ids);

        for(TVSeriesBean tvSeriesBean : beanList){
            System.out.println(tvSeriesBean);
        }

        sqlSession.close();
    }

    /**
     * 动态SQL测试，foreach Object类型
     */
    @Test
    public void testSelectDynamicSQLForeachByObject(){
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);

        List<TVSeriesBean> series = new ArrayList<TVSeriesBean>();
        TVSeriesBean tvSeriesBean1 = new TVSeriesBean();
        tvSeriesBean1.setTvId(7);

        series.add(tvSeriesBean1);

        List<TVSeriesBean> beanList = tvSeriesDao.selectByDynamicSQL_foreachByObject(series);

        for(TVSeriesBean tvSeriesBean : beanList){
            System.out.println(tvSeriesBean);
        }

        sqlSession.close();
    }
}
