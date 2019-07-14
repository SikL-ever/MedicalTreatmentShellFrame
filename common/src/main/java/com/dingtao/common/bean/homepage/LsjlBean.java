package com.dingtao.common.bean.homepage;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/13 11:48
 * @Description：描述信息
 */
@Entity
public class LsjlBean {
    @Id
    private long id;
    private String name;
    @Generated(hash = 1831376828)
    public LsjlBean(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1587417836)
    public LsjlBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
