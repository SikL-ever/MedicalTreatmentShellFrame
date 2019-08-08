package com.dingtao.common.bean.MyUser;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/6 15:54
 * @Description：描述信息
 */
public class YhBean {

    /**
     * bankCardNumber : 623153 0500002875337
     * bankCardType : 1
     * bankName : 乌海银行
     * bindTime : 1565020800000
     * userId : 59
     */

    private String bankCardNumber;
    private int bankCardType;
    private String bankName;
    private long bindTime;
    private int userId;

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public int getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(int bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getBindTime() {
        return bindTime;
    }

    public void setBindTime(long bindTime) {
        this.bindTime = bindTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
