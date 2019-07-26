package com.dingtao.common.bean.wardBean;

public class BingzhengBean {
//     "departmentId": 2,
//             "id": 17,
//             "name": "颈椎病"
    public int departmentId;
    public int id;
    public String name;

    public BingzhengBean(int departmentId, int id, String name) {
        this.departmentId = departmentId;
        this.id = id;
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
}
