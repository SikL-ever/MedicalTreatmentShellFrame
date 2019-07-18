package com.dingtao.common.bean.wardBean;

import android.graphics.Color;

public class TabBean {
//     "departmentName": "内科",
//             "id": 7,
//             "pic": "http://172.17.8.100/images/health/department_pic/nk.jpg",
//             "rank": 1
    public String departmentName;
    public int id;
    public String pic;
    public int rank;
    public int textColor=Color.BLACK;
    public boolean checked;
//    public String #

    public TabBean(String departmentName, int id, String pic, int rank, boolean checked) {
        this.departmentName = departmentName;
        this.id = id;
        this.pic = pic;
        this.rank = rank;
        this.checked = checked;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
