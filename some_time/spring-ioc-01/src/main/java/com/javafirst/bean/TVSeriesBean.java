package com.javafirst.bean;

public class TVSeriesBean {
    private int tvId;
    private String tvTitle;
    private String tvSubTitle;
    private int tvType;

    public int getTvId() {
        return tvId;
    }

    public void setTvId(int tvId) {
        this.tvId = tvId;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTvSubTitle() {
        return tvSubTitle;
    }

    public void setTvSubTitle(String tvSubTitle) {
        this.tvSubTitle = tvSubTitle;
    }

    public int getTvType() {
        return tvType;
    }

    public void setTvType(int tvType) {
        this.tvType = tvType;
    }

    @Override
    public String toString() {
        return "TVSeriesBean{" +
                "tvId=" + tvId +
                ", tvTitle='" + tvTitle + '\'' +
                ", tvSubTitle='" + tvSubTitle + '\'' +
                ", tvType=" + tvType +
                '}';
    }
}
