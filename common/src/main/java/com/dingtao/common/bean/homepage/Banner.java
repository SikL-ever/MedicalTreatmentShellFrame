package com.dingtao.common.bean.homepage;


import java.util.List;

public class Banner {
        /**
         * imageUrl : https://n1.hdfimg.com/g1/M03/A5/00/wYYBAFz10_WAd9DRAAEzaplfqDM541.jpg
         * jumpUrl : http://www.crha.cn/yxyjdt.html
         * rank : 1
         * title : 医研究动态学
         */

        private String imageUrl;
        private String jumpUrl;
        private int rank;
        private String title;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

}
