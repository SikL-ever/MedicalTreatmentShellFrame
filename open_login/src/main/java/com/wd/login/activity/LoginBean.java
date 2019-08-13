package com.wd.login.activity;

/**
 * @Auther: 李吉梅
 * @Date: 2019-08-09 09:51:24
 * @Description: 登录的bean类
 */
public class LoginBean {


    /**
     * code : 200
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIiLCJhdWQiOiIiLCJpYXQiOjE1NjUxNjYwMTcsIm5iZiI6MTU2NTE2NjAxNywiZXhwIjoxNTY2NDYyMDE3LCJ1aWQiOjN9.NHLAki_Sr391s_6Kjn8TLjKlPuKL6xRdvjcN-r3vJ-I","lang":1,"accountstatus":1}
     * message :
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIiLCJhdWQiOiIiLCJpYXQiOjE1NjUxNjYwMTcsIm5iZiI6MTU2NTE2NjAxNywiZXhwIjoxNTY2NDYyMDE3LCJ1aWQiOjN9.NHLAki_Sr391s_6Kjn8TLjKlPuKL6xRdvjcN-r3vJ-I
         * lang : 1
         * accountstatus : 1
         */

        private String token;
        private int lang;
        private int accountstatus;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getLang() {
            return lang;
        }

        public void setLang(int lang) {
            this.lang = lang;
        }

        public int getAccountstatus() {
            return accountstatus;
        }

        public void setAccountstatus(int accountstatus) {
            this.accountstatus = accountstatus;
        }
    }
}
