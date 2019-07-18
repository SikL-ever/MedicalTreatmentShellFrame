package com.dingtao.common.bean.homepage;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/18 10:53
 * @Description：描述信息
 */
public class CjypBean1 {

    /**
     * drugsCategoryId : 1
     * id : 1
     * name :  [同仁堂]牛黄解毒片(薄膜衣片)
     * picture : https://imgq.ddky.com/c/product/328654/big/z_1.jpg?t=9898&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100
     */

    private int drugsCategoryId;
    private int id;
    private String name;
    private String picture;

    public int getDrugsCategoryId() {
        return drugsCategoryId;
    }

    public void setDrugsCategoryId(int drugsCategoryId) {
        this.drugsCategoryId = drugsCategoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
