package com.dingtao.common.bean.homepage;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/16 8:50
 * @Description：描述信息
 */
public class WzzxBean1 {
    /**
     * departmentName : 内科
     * id : 7
     * pic : http://mobile.bwstudent.com/images/health/department_pic/nk.jpg
     * rank : 1
     */

    public String departmentName;
    public int id;
    public String pic;
    public int rank;

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
    /*{
        "departmentName": "内科 ",
            "id": 7,
            "pic": "http://mobile.bwstudent.com/images/health/department_pic/nk.jpg",
            "rank": 1
    },*/

}
