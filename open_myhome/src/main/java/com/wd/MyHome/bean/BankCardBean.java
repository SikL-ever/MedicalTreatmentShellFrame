package com.wd.MyHome.bean;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/5 21:17
 * @Description：描述信息
 */
public class BankCardBean {

    /**
     * log_id : 144718895115129615
     * result : {"bank_card_number":"3568 8900 8000 0005","valid_date":"07/21","bank_card_type":2,"bank_name":"招商银行"}
     */

    private long log_id;
    private ResultBean result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * bank_card_number : 3568 8900 8000 0005
         * valid_date : 07/21
         * bank_card_type : 2
         * bank_name : 招商银行
         */

        private String bank_card_number;
        private String valid_date;
        private int bank_card_type;
        private String bank_name;

        public String getBank_card_number() {
            return bank_card_number;
        }

        public void setBank_card_number(String bank_card_number) {
            this.bank_card_number = bank_card_number;
        }

        public String getValid_date() {
            return valid_date;
        }

        public void setValid_date(String valid_date) {
            this.valid_date = valid_date;
        }

        public int getBank_card_type() {
            return bank_card_type;
        }

        public void setBank_card_type(int bank_card_type) {
            this.bank_card_type = bank_card_type;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
    }
}
