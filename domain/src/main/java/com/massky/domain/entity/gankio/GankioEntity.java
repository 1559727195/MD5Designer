package com.massky.domain.entity.gankio;

import com.massky.domain.entity.base.MultiTypeIdEntity;

import java.util.List;

public class GankioEntity {
private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    private List<ResultsEntity> results;

    public  static class ResultsEntity extends MultiTypeIdEntity{
        public static final String PARAMS_ANDROID = "Android";
        public static final String PARAMS_IOS = "iOS";
        public static final String PARAMS_H5 = "前端";
        public static final String PARAMS_FULI = "福利";
        public static final String PARAMS_ALL = "全部";

        public static final int TYPE_GANK_IO = 1;
        public static final String HEADER_TITLE = "Gankio";
        /**
         * _id : 56cc6d22421aa95caa7078fe
         * createdAt : 2015-10-07T13:00:31.548Z
         * desc : 设置购物数量的view
         * publishedAt : 2015-10-08T04:29:48.0Z
         * type : Android
         * url : https://github.com/himanshu-soni/QuantityView
         * used : true
         * who : Jason
         * source : web
         * images : ["https://github.com/booncol/Pulsator4Droid/raw/master/demo.gif"]
         */

        private String _id;
        private String createdAt;
        private String desc;

        public String get_id() {
            return _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean isUsed() {
            return used;
        }

        public String getWho() {
            return who;
        }

        public String getSource() {
            return source;
        }

        public List<String> getImages() {
            return images;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        private String publishedAt;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private String source;
        private List<String> images;





        @Override
        public String getStringId() {
            return _id;
        }

        @Override
        public int getItemType() {
            return TYPE_GANK_IO;
        }
    }
}
