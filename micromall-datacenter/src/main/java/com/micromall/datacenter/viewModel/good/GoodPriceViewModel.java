package com.micromall.datacenter.viewModel.good;

/**
 * Created by Administrator on 2015/5/19.
 */
public class GoodPriceViewModel {
    private String levelId;
    private String levelName;
    private String price;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
