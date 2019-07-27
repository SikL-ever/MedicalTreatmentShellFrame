package com.dingtao.common.bean.MyUser;

/**
 * 佀常勇
 *
 * @Data:2019/7/18 11:22
 * 描述：
 */
public class UserRecordBean {
        public int userId;
        public int id;
        public String diseaseMain;
        public String diseaseNow;
        public String diseaseBefore;
        public String treatmentHospitalRecent;
        public String treatmentProcess;
        public long treatmentStartTime;
        public long treatmentEndTime;
        public String picture;

        @Override
        public String toString() {
                return "UserRecordBean{" +
                        "userId=" + userId +
                        ", id=" + id +
                        ", diseaseMain='" + diseaseMain + '\'' +
                        ", diseaseNow='" + diseaseNow + '\'' +
                        ", diseaseBefore='" + diseaseBefore + '\'' +
                        ", treatmentHospitalRecent='" + treatmentHospitalRecent + '\'' +
                        ", treatmentProcess='" + treatmentProcess + '\'' +
                        ", treatmentStartTime=" + treatmentStartTime +
                        ", treatmentEndTime=" + treatmentEndTime +
                        ", picture='" + picture + '\'' +
                        '}';
        }
}
