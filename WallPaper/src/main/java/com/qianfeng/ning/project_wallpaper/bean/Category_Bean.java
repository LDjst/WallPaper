package com.qianfeng.ning.project_wallpaper.bean;

import java.util.List;

/**
 * Created by Ning on 2016/5/31.
 */
public class Category_Bean {
    /**
     * code : E00000000
     * msg : 操作成功。

     */

    private String msg;
    /**
     * ID : 1042
     * TipContent : 小编日也加班为大家寻找美图，恳请大家给个好评！
     * DescWords : 清纯 | 可爱 | 性感
     * LockType : 0
     * LockKey : {"URLScheme":"https://itunes.apple.com/cn/app/id507388249","title":"u89e3u9501u63d0u793a","appID":"507388249"}
     * PicCategoryName : 美女图片
     * PicCategoryLogo : /picture1/M00/14/65/wKiFR1Ycm6KAGnq3AABVPcUNCOg695.jpg
     * CategoryPic : http://bzpic.spriteapp.cn/picture1/M00/14/65/wKiFR1Ycm6KAGnq3AABVPcUNCOg695.jpg
     * SecondCategoryList : [{"ID":2140,"BigCategoryId":1042,"PicCategoryName":"性感","CategoryPic":""},{"ID":2142,"BigCategoryId":1042,"PicCategoryName":"清纯","CategoryPic":""},{"ID":2143,"BigCategoryId":1042,"PicCategoryName":"萝莉","CategoryPic":""},{"ID":2144,"BigCategoryId":1042,"PicCategoryName":"气质","CategoryPic":""},{"ID":2666,"BigCategoryId":1042,"PicCategoryName":"模特","CategoryPic":""},{"ID":2667,"BigCategoryId":1042,"PicCategoryName":"女优","CategoryPic":""}]
     * tags : [{"id":"164","name":"美女"},{"id":"29318","name":"美女"},{"id":"357","name":"性感"},{"id":"34852","name":"性感迷人"},{"id":"34312","name":"美女 性感"},{"id":"32680","name":"清新"},{"id":"29321","name":"性感"},{"id":"32634","name":"女神"},{"id":"34151","name":"萌妹子"},{"id":"36666","name":"气质"}]
     */

    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String ID;
        private String TipContent;
        private String DescWords;
        private String LockType;
        private String LockKey;
        private String PicCategoryName;
        private String PicCategoryLogo;
        private String CategoryPic;
        /**
         * id : 164
         * name : 美女
         */

        private List<TagsBean> tags;
        /**
         * ID : 1042
         * TipContent : 小编日也加班为大家寻找美图，恳请大家给个好评！
         * DescWords : 清纯 | 可爱 | 性感
         * LockType : 0
         * LockKey : {"URLScheme":"https://itunes.apple.com/cn/app/id507388249","title":"u89e3u9501u63d0u793a","appID":"507388249"}
         * PicCategoryName : 美女图片
         * PicCategoryLogo : /picture1/M00/14/65/wKiFR1Ycm6KAGnq3AABVPcUNCOg695.jpg
         * CategoryPic : http://bzpic.spriteapp.cn/picture1/M00/14/65/wKiFR1Ycm6KAGnq3AABVPcUNCOg695.jpg
         * SecondCategoryList : [{"ID":2140,"BigCategoryId":1042,"PicCategoryName":"性感","CategoryPic":""},{"ID":2142,"BigCategoryId":1042,"PicCategoryName":"清纯","CategoryPic":""},{"ID":2143,"BigCategoryId":1042,"PicCategoryName":"萝莉","CategoryPic":""},{"ID":2144,"BigCategoryId":1042,"PicCategoryName":"气质","CategoryPic":""},{"ID":2666,"BigCategoryId":1042,"PicCategoryName":"模特","CategoryPic":""},{"ID":2667,"BigCategoryId":1042,"PicCategoryName":"女优","CategoryPic":""}]
         * tags : [{"id":"164","name":"美女"},{"id":"29318","name":"美女"},{"id":"357","name":"性感"},{"id":"34852","name":"性感迷人"},{"id":"34312","name":"美女 性感"},{"id":"32680","name":"清新"},{"id":"29321","name":"性感"},{"id":"32634","name":"女神"},{"id":"34151","name":"萌妹子"},{"id":"36666","name":"气质"}]
         */

        private List<DataBean> data;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTipContent() {
            return TipContent;
        }

        public void setTipContent(String TipContent) {
            this.TipContent = TipContent;
        }

        public String getDescWords() {
            return DescWords;
        }

        public void setDescWords(String DescWords) {
            this.DescWords = DescWords;
        }

        public String getLockType() {
            return LockType;
        }

        public void setLockType(String LockType) {
            this.LockType = LockType;
        }

        public String getLockKey() {
            return LockKey;
        }

        public void setLockKey(String LockKey) {
            this.LockKey = LockKey;
        }

        public String getPicCategoryName() {
            return PicCategoryName;
        }

        public void setPicCategoryName(String PicCategoryName) {
            this.PicCategoryName = PicCategoryName;
        }

        public String getPicCategoryLogo() {
            return PicCategoryLogo;
        }

        public void setPicCategoryLogo(String PicCategoryLogo) {
            this.PicCategoryLogo = PicCategoryLogo;
        }

        public String getCategoryPic() {
            return CategoryPic;
        }

        public void setCategoryPic(String CategoryPic) {
            this.CategoryPic = CategoryPic;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class TagsBean {
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "ID='" + ID + '\'' +
                    ", TipContent='" + TipContent + '\'' +
                    ", DescWords='" + DescWords + '\'' +
                    ", LockType='" + LockType + '\'' +
                    ", LockKey='" + LockKey + '\'' +
                    ", PicCategoryName='" + PicCategoryName + '\'' +
                    ", PicCategoryLogo='" + PicCategoryLogo + '\'' +
                    ", CategoryPic='" + CategoryPic + '\'' +
                    ", tags=" + tags +
                    '}';
        }


    }

    @Override
    public String toString() {
        return "Category_Bean{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
