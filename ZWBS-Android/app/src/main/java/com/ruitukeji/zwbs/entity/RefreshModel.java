package com.ruitukeji.zwbs.entity;

/**
 * Created by Administrator on 2017/2/13.
 */

public class RefreshModel {
    public String title;
    public String detail;

    public RefreshModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RefreshModel(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
}
