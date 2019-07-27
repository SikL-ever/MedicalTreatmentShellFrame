package com.dingtao.common.bean.homepage;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/13 11:48
 * @Description：描述信息
 */
@Entity
public class LsjlBean {
    @Id(autoincrement = true) //自增主键
    private Long id;
    @Unique // 搜索记录(唯一)
    private String name;
    @Generated(hash = 1820074583)
    public LsjlBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1587417836)
    public LsjlBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
