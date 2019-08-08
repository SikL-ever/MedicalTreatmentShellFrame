package com.wd.MyHome.cardUtils;

import java.net.URLEncoder;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/5 21:13
 * @Description：描述信息
 */
public class Bankcard {
    public static void main(String[] args) {
        // 银行卡识别url
        String bankcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
        // 本地图片路径
        String filePath = "#####本地文件路径#####";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "#####调用鉴权接口获取的token#####";
            String result = HttpUtil.post(bankcardIdentificate, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
