package com.massky.domain.entity.common;

import com.crazysunj.multitypeadapter.helper.RecyclerViewAdapterHelper;
import com.massky.domain.entity.base.MultiTypeIdEntity;

public class CommonFooterEntity extends MultiTypeIdEntity {
    private String id;
    private int type;
    protected String title;

    public CommonFooterEntity(String id, int level, String title) {
        this.id = id;
        this.type = level - RecyclerViewAdapterHelper.FOOTER_TYPE_DIFFER;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getStringId() {
        return id;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
