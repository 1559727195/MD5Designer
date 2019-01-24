package com.massky.domain.entity.zhihu;

import com.massky.domain.entity.base.MultiTypeIdEntity;

import java.io.Serializable;
import java.util.List;

public class ZhihuNewsEntity {

    private String date;
    private List<StoriesEntity> stories;
    private List<TopStoriesEntity> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesEntity extends MultiTypeIdEntity {
        /**
         * images : ["https://pic3.zhimg.com/v2-2c4233255c4749048bf27d09f04b3e56.jpg"]
         * type : 0
         * id : 9594997
         * ga_prefix : 083019
         * title : 作为一名人民警察，我来讲讲如何让孩子真正远离性侵的「魔爪」
         */
        public static final int TYPE_ZHIHU_NEWS = 0;
        public static final String HEADER_TITLE = "知乎日报";
        public static final String HEADER_OPTIONS = "最新";
        private int type;
        private long id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public int getItemType() {
            return TYPE_ZHIHU_NEWS;
        }

        @Override
        public String getStringId() {
            return String.valueOf(id);
        }

        @Override
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesEntity implements Serializable {
        /**
         * image : https://pic4.zhimg.com/v2-161dca9a30a58d3dd195321d7be45baf.jpg
         * type : 0
         * id : 9593371
         * ga_prefix : 083018
         * title : 初音未来十岁了，我第一次知道她，还是因为那首甩葱歌
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
