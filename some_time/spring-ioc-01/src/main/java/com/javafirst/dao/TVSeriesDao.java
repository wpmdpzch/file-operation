package com.javafirst.dao;

import com.javafirst.bean.TVSeriesBean;

import java.util.List;
import java.util.Map;

public interface TVSeriesDao {

    /**
     * 根据id查询电视剧信息；
     * @param tvId
     * @return
     */
    TVSeriesBean selectTVSeriesById(Integer tvId);

    /**
     * 查询所有电视剧信息
     * @return
     */
    List<TVSeriesBean> selectTVSeriesAll();

    /**
     *
     * @param title
     * @param subTitle
     * @param type
     */
    void addTVSeriesOne(String title, String subTitle, int type);

    /**
     *
     * @param tvSeriesBean
     */
    void addTVSeriesObject(TVSeriesBean tvSeriesBean);

    void deleteTVSeriesBeanById(int tvId);

    /**
     * 根据ID 来修改标题
     * @param tvId
     */
    void updateTVSeriesBeanTitleById(Integer tvId,String tvTitle);

    List<TVSeriesBean> selectByDynamicSQL_if(Map<String,Object> params);

    List<TVSeriesBean> selectByDynamicSQL_choose(Map<String,Object> params);

    List<TVSeriesBean> selectByDynamicSQL_foreach(List<Integer> ids);

    List<TVSeriesBean> selectByDynamicSQL_foreachByObject(List<TVSeriesBean> series);

}
