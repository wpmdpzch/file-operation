package com.javafirst;

import com.javafirst.bean.TVSeriesBean;
import com.javafirst.dao.TVSeriesDao;
import com.javafirst.data.MyStudent;
import com.javafirst.service.SomeService;
import com.javafirst.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        String config = "beans.xml";
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);
//
//        SomeService someService = (SomeService) applicationContext.getBean("someService");
//
//        Date date = (Date) applicationContext.getBean("sysDate");
//
//        MyStudent myStudent = (MyStudent) applicationContext.getBean("myStudent");
//
//        someService.doSome();
//        someService.doSome("无崖子", 87);
//        someService.returnPrice(9.0D,0.5f);
//
//        System.out.println(date.getTime());
//        System.out.println(myStudent.toString());
        testSelectTVSeriesAll();

    }

    public static void testSelectTVSeriesAll() {
        SqlSession sqlSession = MyBatisUtil.openSqlSession();
        TVSeriesDao tvSeriesDao = sqlSession.getMapper(TVSeriesDao.class);
        List<TVSeriesBean> tvSeriesBeans = tvSeriesDao.selectTVSeriesAll();
        sqlSession.close();

        System.out.println("查询所有记录(电视剧)：" + tvSeriesBeans.size());
        for (TVSeriesBean tvSeries : tvSeriesBeans) {
            System.out.println(tvSeries);
        }
    }





}
