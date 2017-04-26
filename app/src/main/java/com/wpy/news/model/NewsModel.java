package com.wpy.news.model;

import java.util.List;

/**
 * Created by X230 on 2017/4/11.
 */

public class NewsModel {
    /**
     * https://api.tianapi.com/social/?key=6cad4a694ce80d4c7596d738ec7c9c1c&num=10&page=1
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-04-11 19:15","title":"河南小学生被罚站骂教师遭摁地掌掴 教师被拘","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487774030_ss.jpg","url":"http://news.sohu.com/20170411/n487778728.shtml"},{"ctime":"2017-04-11 19:56","title":"苏州一商业楼盗水80万吨 律师：涉事者或判无期","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487785831_ss.jpeg","url":"http://news.sohu.com/20170411/n487785830.shtml"},{"ctime":"2017-04-11 18:07","title":"退休官员向他人发淫秽信息：开除党籍 降低待遇","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487748603_ss.jpeg","url":"http://news.sohu.com/20170411/n487767376.shtml"},{"ctime":"2017-04-11 18:25","title":"杭州西湖区民房火灾：3人死亡14人受伤送医","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487750983_ss.jpeg","url":"http://news.sohu.com/20170411/n487770117.shtml"},{"ctime":"2017-04-11 18:35","title":"上海黄浦公布共享单车禁区：禁止投、骑、停","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487752590_ss.jpg","url":"http://news.sohu.com/20170411/n487771255.shtml"},{"ctime":"2017-04-11 18:42","title":"雄安新区父母支持北漂子女辞职：不差你挣的钱","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487771257_ss.jpeg","url":"http://news.sohu.com/20170411/n487772508.shtml"},{"ctime":"2017-04-11 18:52","title":"官员醉驾撞死2名学生逃逸 法院判5年遭检察院抗诉","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487774030_ss.jpg","url":"http://news.sohu.com/20170411/n487774029.shtml"},{"ctime":"2017-04-11 17:30","title":"新疆住建厅原副厅长获刑11年 绿城子公司涉案","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487750983_ss.jpeg","url":"http://news.sohu.com/20170411/n487761741.shtml"},{"ctime":"2017-04-11 17:31","title":"男子乱棍打死19岁堂弟并沉尸河道 一审被处死刑","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487752590_ss.jpg","url":"http://news.sohu.com/20170411/n487761808.shtml"},{"ctime":"2017-04-11 16:06","title":"陕西商鞅变法遗址遭盗掘 警方缴获200余件秦砖汉瓦","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170411/Img487743208_ss.jpeg","url":"http://news.sohu.com/20170411/n487748151.shtml"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-04-11 19:15
         * title : 河南小学生被罚站骂教师遭摁地掌掴 教师被拘
         * description : 搜狐社会
         * picUrl : http://photocdn.sohu.com/20170411/Img487774030_ss.jpg
         * url : http://news.sohu.com/20170411/n487778728.shtml
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
