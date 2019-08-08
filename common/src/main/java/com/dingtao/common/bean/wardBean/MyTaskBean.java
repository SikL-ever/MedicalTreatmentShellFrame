package com.dingtao.common.bean.wardBean;

/**
 * @Author：lenovo
 * @Date：2019/8/2 19:36
 * @Description：描述信息
 */
public class MyTaskBean {
    /*  "id": 1001,
            "reward": 2,
            "taskName": "签到",
            "taskType": 1,
            "whetherFinish": 2,
            "whetherReceive": 3*/
    int id;
    int reward;
    String taskName;
    int taskType;
    int whetherFinish;
    int whetherReceive;
    int Backcolor=0xffffffff;

    public MyTaskBean(int backcolor) {
        Backcolor = backcolor;
    }

    public int getBackcolor() {
        return Backcolor;
    }

    public void setBackcolor(int backcolor) {
        Backcolor = backcolor;
    }

    public MyTaskBean(int id, int reward, String taskName, int taskType, int whetherFinish, int whetherReceive) {
        this.id = id;
        this.reward = reward;
        this.taskName = taskName;
        this.taskType = taskType;
        this.whetherFinish = whetherFinish;
        this.whetherReceive = whetherReceive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getWhetherFinish() {
        return whetherFinish;
    }

    public void setWhetherFinish(int whetherFinish) {
        this.whetherFinish = whetherFinish;
    }

    public int getWhetherReceive() {
        return whetherReceive;
    }

    public void setWhetherReceive(int whetherReceive) {
        this.whetherReceive = whetherReceive;
    }
}
