package com.massky.md5designer.entity;

import com.massky.domain.entity.common.CommonFooterEntity;
import com.massky.md5designer.R;
import com.massky.md5designer.constant.Constant;

public class ExpandCollapseFooterEntity extends CommonFooterEntity {
    private static final String COLLAPSE = "收起";
    private static final String EXPAND = "收起";
    private static final int STATUS_EXPAND = 0;
    private static final int STATUS_COLLAPSE = 1;

    private int status;

    public ExpandCollapseFooterEntity(int level) {
        super(Constant.EMPTY, level, EXPAND);
        status = STATUS_COLLAPSE;
    }

    public boolean isFlod() {
        return status == STATUS_COLLAPSE;
    }

    public void switchStatus() {
        if (status == STATUS_EXPAND) {
            status = STATUS_COLLAPSE;
            return;
        }
        status = STATUS_EXPAND;
    }

    public void initStatus(String title) {
        this.title = title;
        status = STATUS_COLLAPSE;
    }

    public int getStatus() {
        return status;
    }

    public int getIconResId() {
        if (status == STATUS_EXPAND) {
            return R.mipmap.icon_collapse;
        }
        return R.mipmap.icon_expand;
    }

    @Override
    public String getStringId() {
        if (status == STATUS_EXPAND) {
            return COLLAPSE;
        }
        return title;
    }
}
